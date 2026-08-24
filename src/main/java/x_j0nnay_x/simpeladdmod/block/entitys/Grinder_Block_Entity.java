package x_j0nnay_x.simpeladdmod.block.entitys;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.*;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.RangedResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jspecify.annotations.Nullable;
import x_j0nnay_x.simpeladdmod.init.Mod_Config;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.init.Mod_Tags;
import x_j0nnay_x.simpeladdmod.items.Grinder_Head_Item;
import x_j0nnay_x.simpeladdmod.menu.Grinder_Menu;
import x_j0nnay_x.simpeladdmod.recipe.Grinder_Recipe;
import x_j0nnay_x.simpeladdmod.init.Mod_Constants;

import java.util.Optional;


public class Grinder_Block_Entity extends BlockEntity implements MenuProvider {

    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(5) {
        @Override
        protected void onContentsChanged(int index, ItemStack previousContents) {
            super.onContentsChanged(index, previousContents);
            Grinder_Block_Entity.this.setChanged();
        }
    };
    private final ResourceHandler<ItemResource> sideHandler = RangedResourceHandler.ofSingleIndex(inventory, GRINDING_SLOT);
    private final ResourceHandler<ItemResource> topHandler = RangedResourceHandler.ofSingleIndex(inventory, INPUT_SLOT);
    private final ResourceHandler<ItemResource> bottomHandler = RangedResourceHandler.ofSingleIndex(inventory, OUTPUT_SLOT);

    public static int INPUT_SLOT = 0;
    public static int GRINDING_SLOT = 1;
    public static int OUTPUT_SLOT = 2;
    public static int UPGRADING_SLOT = 3;
    public static int BOOSTING_SLOT = 4;
    protected final ContainerData data;
    private int progress = 0;
    private int max_Progress;
    private int grinds_Left = 0;
    private int max_Grinds = 3;
    private int grind_Efficiency = 5;
    private int has_Booster = 0;

    public Grinder_Block_Entity(BlockPos worldPosition, BlockState blockState) {
        super(Mod_Registrys.Mod_Block_Entity.GRINDER_BLOCK_ENTITY.get(), worldPosition, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int dataId) {
                return switch (dataId) {
                    case 0 -> Grinder_Block_Entity.this.progress;
                    case 1 -> Grinder_Block_Entity.this.max_Progress;
                    case 2 -> Grinder_Block_Entity.this.grinds_Left;
                    case 3 -> Grinder_Block_Entity.this.max_Grinds;
                    case 4 -> Grinder_Block_Entity.this.grind_Efficiency;
                    default -> 0;
                };
            }

            @Override
            public void set(int dataId, int value) {
                switch (dataId) {
                    case 0 -> Grinder_Block_Entity.this.progress = value;
                    case 1 -> Grinder_Block_Entity.this.max_Progress = value;
                    case 2 -> Grinder_Block_Entity.this.grinds_Left = value;
                    case 3 -> Grinder_Block_Entity.this.max_Grinds = value;
                    case 4 -> Grinder_Block_Entity.this.grind_Efficiency = value;
                }
            }

            @Override
            public int getCount() {
                return 5;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.simpeladdmod.grinder");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new Grinder_Menu(containerId, inventory, this, this.inventory, this.data);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt(Mod_Constants.MOD_CUSTOM +"grinder_progress", this.progress);
        output.putInt(Mod_Constants.MOD_CUSTOM +"grinder_grinds_left", this.grinds_Left);
        output.putInt(Mod_Constants.MOD_CUSTOM + "grinder_efficiency", this.grind_Efficiency);
        output.putChild("inventory", inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        progress = input.getIntOr(Mod_Constants.MOD_CUSTOM +"grinder_progress", 0);
        grinds_Left = input.getIntOr(Mod_Constants.MOD_CUSTOM +"grinder_grinds_left", 0);
        grind_Efficiency = input.getIntOr(Mod_Constants.MOD_CUSTOM + "grinder_efficiency", 5);
        input.child("inventory").ifPresent(inventory::deserialize);
    }


    public void drops() {
        if (this.level == null || this.level.isClientSide()) {
            return;
        }

        SimpleContainer inv = new SimpleContainer(inventory.size());
        for (int i = 0; i < inventory.size(); i++) {
            ItemAccess itemAccess = ItemAccess.forHandlerIndex(inventory, i);
            int amount = itemAccess.getAmount();
            ItemStack stack = itemAccess.getResource().toStack();
            inv.setItem(i, amount > 0 ? stack.copyWithCount(amount) : ItemStack.EMPTY);
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    public ResourceHandler<ItemResource> getItemHandler(Direction direction) {
        if (direction == null)
            return inventory;

        Direction facing = this.getBlockState().hasProperty(BlockStateProperties.HORIZONTAL_FACING)
                ? this.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING)
                : Direction.NORTH;

        return switch (getRelativeSide(facing, direction)) {
            case UP -> topHandler;
            case DOWN -> bottomHandler;
            case WEST, EAST,NORTH, SOUTH -> sideHandler;
        };
    }

    private Direction getRelativeSide(Direction facing, Direction absoluteSide) {
        if (absoluteSide.getAxis().isVertical()) return absoluteSide;
        if (absoluteSide == facing) return Direction.NORTH;
        if (absoluteSide == facing.getOpposite()) return Direction.SOUTH;
        if (absoluteSide == facing.getClockWise()) return Direction.WEST;
        if (absoluteSide == facing.getCounterClockWise()) return Direction.EAST;
        return absoluteSide;
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        setUpGrades();
        if(hasRecipe() && isOutputSlotEmptyOrReceivable()) {
            setChanged(level, pos, state);
            level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.LIT, isWorking()));
            if(this.grinds_Left > 0){
                increaseCraftingProgress();
                if(hasCraftingFinished()) {
                    useGrind();
                    craftItem();
                    resetProgress();
                }
            }else{
                resetGrinds();
            }
        }else {
            resetProgress();
            level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.LIT, isWorking()));
        }
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<Grinder_Recipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack output = recipe.get().value().assemble(new SingleRecipeInput(inventory.getResource(INPUT_SLOT).toStack()));
        boolean outputSlotAmount = canInsertAmountIntoOutputSlot(output.getCount());
        boolean outputSlotItem = canInsertItemIntoOutputSlot(output);

        return outputSlotItem && outputSlotAmount;
    }

    private Optional<RecipeHolder<Grinder_Recipe>> getCurrentRecipe() {
        return ((ServerLevel) level).recipeAccess().getRecipeFor(Mod_Registrys.Mod_Recipes.GRINDER_RECIPE_TYPES.get(),
                new SingleRecipeInput(inventory.getResource(INPUT_SLOT).toStack()), level);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return inventory.getResource(OUTPUT_SLOT).isEmpty() ||
                inventory.getResource(OUTPUT_SLOT).is(output.getItem());
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = inventory.getResource(OUTPUT_SLOT).isEmpty() ? 64 : inventory.getResource(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = inventory.getAmountAsInt(OUTPUT_SLOT);

        return maxCount >= currentCount + count;
    }

    private void craftItem() {
        Optional<RecipeHolder<Grinder_Recipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output().create();
        try(Transaction transaction = Transaction.openRoot()) {
            ItemAccess itemAccess = ItemAccess.forHandlerIndex(inventory, OUTPUT_SLOT);
            inventory.extract(inventory.getResource(INPUT_SLOT), 1, transaction);
            inventory.set(OUTPUT_SLOT, ItemResource.of(output), itemAccess.getAmount() + output.getCount());
            transaction.commit();
        }
    }
    private boolean isWorking() {
        if (progress > 0){
            if(this.grinds_Left > 0 || this.inventory.getResource(GRINDING_SLOT).is(Mod_Tags.Items.GRINDERS)){
                return true;
            }
        }
        return false;
    }


    private boolean isOutputSlotEmptyOrReceivable() {
        return inventory.getResource(OUTPUT_SLOT).isEmpty() ||
                inventory.getResource(OUTPUT_SLOT).test(stack -> stack.count() < stack.getMaxStackSize());
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.max_Progress;
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void setUpGrades(){
        if(inventory.getResource(this.UPGRADING_SLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_1.get())) {
            this.max_Progress = 40;
        }
        if(inventory.getResource(this.UPGRADING_SLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_2.get())) {
            this.max_Progress = 24;
        }
        if(inventory.getResource(this.UPGRADING_SLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_3.get())) {
            this.max_Progress = 10;
        }
        if(inventory.getResource(this.UPGRADING_SLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_4.get())) {
            this.max_Progress = 5;
        }
        if(inventory.getResource(this.UPGRADING_SLOT).isEmpty()){
            this.max_Progress = 60;
        }

    }

    private void resetGrindEfficiency(){
        this.grind_Efficiency = 5;
    }

    private void useGrind(){
        if(this.inventory.getResource(this.BOOSTING_SLOT).is(Mod_Registrys.Mod_Items.BOOST_UPGRADE.get())){
            if(this.grind_Efficiency > 0){
                this.grind_Efficiency--;
            }else {
                this.grinds_Left--;
                resetGrindEfficiency();
            }
        }else {
            this.grinds_Left--;
        }
    }

    private void resetGrinds() {
        if(this.inventory.getResource(this.GRINDING_SLOT).is(Mod_Tags.Items.GRINDERS)) {
            if(Grinder_Head_Item.canUse(this.inventory.getResource(GRINDING_SLOT).toStack()) || Mod_Config.GRINDER_BRAKES_GRINDER_ITEM.getAsBoolean()) {
                ItemStack grinderStack = Grinder_Head_Item.brakeItem(this.inventory.getResource(GRINDING_SLOT).toStack(), Mod_Config.GRINDER_BRAKES_GRINDER_ITEM.getAsBoolean());
                if (grinderStack.isEmpty()) {
                    this.inventory.set(GRINDING_SLOT, this.inventory.getResource(GRINDING_SLOT), 0);
                    this.grinds_Left = 0;
                    return;
                }
                this.inventory.set(GRINDING_SLOT, ItemResource.of(grinderStack), 1);
                grinds_Left = max_Grinds;
            }
        }else {
            this.grinds_Left = 0;
        }
    }

    /* BLOCK ENTITY SYNC STUFF */
    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

}