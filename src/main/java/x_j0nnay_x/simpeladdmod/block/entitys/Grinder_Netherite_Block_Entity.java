package x_j0nnay_x.simpeladdmod.block.entitys;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
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

import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.init.Mod_Tags;
import x_j0nnay_x.simpeladdmod.items.Grinder_Head_Item;
import x_j0nnay_x.simpeladdmod.menu.Grinder_Netherite_Menu;
import x_j0nnay_x.simpeladdmod.recipe.Grinder_Recipe;
import x_j0nnay_x.simpeladdmod.init.Mod_Constants;
import x_j0nnay_x.simpeladdmod.block.entitys.util.Inventory_Split_Helper;

import java.util.Optional;


public class Grinder_Netherite_Block_Entity extends BlockEntity implements MenuProvider {

    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(11) {
        @Override
        protected void onContentsChanged(int index, ItemStack previousContents) {
            super.onContentsChanged(index, previousContents);
            Grinder_Netherite_Block_Entity.this.setChanged();
        }
    };
    private final ResourceHandler<ItemResource> sideHandler = RangedResourceHandler.ofSingleIndex(inventory, GRINDING_SLOT);
    private final ResourceHandler<ItemResource> topHandler = RangedResourceHandler.of(inventory, INPUT_SLOT_1, INPUT_SLOT_4);
    private final ResourceHandler<ItemResource> bottomHandler = RangedResourceHandler.of(inventory, OUTPUT_SLOT_1, OUTPUT_SLOT_4);

    public static int GRINDING_SLOT = 0;
    public static int INPUT_SLOT_1 = 1;
    public static int INPUT_SLOT_2 = 2;
    public static int INPUT_SLOT_3 = 3;
    public static int INPUT_SLOT_4 = 4;
    public static int OUTPUT_SLOT_1 = 5;
    public static int OUTPUT_SLOT_2 = 6;
    public static int OUTPUT_SLOT_3 = 7;
    public static int OUTPUT_SLOT_4 = 8;
    public static int UPGRADE_SLOT = 9;
    public static int BOOST_SLOT = 10;
    private static final int[] GRINDER_SLOTS = new int[]{INPUT_SLOT_1, INPUT_SLOT_2, INPUT_SLOT_3, INPUT_SLOT_4};
    protected final ContainerData data;
    private int progress_1 = 0;
    private int progress_2 = 0;
    private int progress_3 = 0;
    private int progress_4 = 0;
    private int max_Progress;
    private int grinds_Left = 0;
    private int max_Grinds = 3;
    private int grind_Efficiency = 5;


    public Grinder_Netherite_Block_Entity(BlockPos worldPosition, BlockState blockState) {
        super(Mod_Registrys.Mod_Block_Entity.GRINDER_NETHERITE_BLOCK_ENTITY.get(), worldPosition, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int dataId) {
                return switch (dataId) {
                    case 0 -> Grinder_Netherite_Block_Entity.this.progress_1;
                    case 1 -> Grinder_Netherite_Block_Entity.this.progress_2;
                    case 2 -> Grinder_Netherite_Block_Entity.this.progress_3;
                    case 3 -> Grinder_Netherite_Block_Entity.this.progress_4;
                    case 4 -> Grinder_Netherite_Block_Entity.this.max_Progress;
                    case 5 -> Grinder_Netherite_Block_Entity.this.grinds_Left;
                    case 6 -> Grinder_Netherite_Block_Entity.this.max_Grinds;
                    case 7 -> Grinder_Netherite_Block_Entity.this.grind_Efficiency;
                    default -> 0;
                };
            }

            @Override
            public void set(int dataId, int value) {
                switch (dataId) {
                    case 0 -> Grinder_Netherite_Block_Entity.this.progress_1 = value;
                    case 1 -> Grinder_Netherite_Block_Entity.this.progress_2 = value;
                    case 2 -> Grinder_Netherite_Block_Entity.this.progress_3 = value;
                    case 3 -> Grinder_Netherite_Block_Entity.this.progress_4 = value;
                    case 4 -> Grinder_Netherite_Block_Entity.this.max_Progress = value;
                    case 5 -> Grinder_Netherite_Block_Entity.this.grinds_Left = value;
                    case 6 -> Grinder_Netherite_Block_Entity.this.max_Grinds = value;
                    case 7 -> Grinder_Netherite_Block_Entity.this.grind_Efficiency = value;
                }
            }

            @Override
            public int getCount() {
                return 8;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.simpeladdmod.grinder_netherite");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new Grinder_Netherite_Menu(containerId, inventory, this, this.inventory, this.data);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt(Mod_Constants.MOD_CUSTOM +"upgraded_grinder_progress_1", this.progress_1);
        output.putInt(Mod_Constants.MOD_CUSTOM +"upgraded_grinder_progress_2", this.progress_2);
        output.putInt(Mod_Constants.MOD_CUSTOM +"upgraded_grinder_progress_3", this.progress_3);
        output.putInt(Mod_Constants.MOD_CUSTOM +"upgraded_grinder_progress_4", this.progress_4);
        output.putInt(Mod_Constants.MOD_CUSTOM +"upgraded_grinder_grinds_left", this.grinds_Left);
        output.putInt(Mod_Constants.MOD_CUSTOM + "upgraded_grinder_efficiency", this.grind_Efficiency);
        output.putChild("inventory", inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        progress_1 = input.getIntOr(Mod_Constants.MOD_CUSTOM +"upgraded_grinder_progress_1", 0);
        progress_2 = input.getIntOr(Mod_Constants.MOD_CUSTOM +"upgraded_grinder_progress_2", 0);
        progress_3 = input.getIntOr(Mod_Constants.MOD_CUSTOM +"upgraded_grinder_progress_3", 0);
        progress_4 = input.getIntOr(Mod_Constants.MOD_CUSTOM +"upgraded_grinder_progress_4", 0);
        grinds_Left = input.getIntOr(Mod_Constants.MOD_CUSTOM +"upgraded_grinder_grinds_left", 0);
        grind_Efficiency = input.getIntOr(Mod_Constants.MOD_CUSTOM + "upgraded_grinder_efficiency", 5);
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
            case WEST -> sideHandler;
            case EAST -> sideHandler;
            case NORTH, SOUTH -> sideHandler;
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
        if(this.inventory.getResource(this.BOOST_SLOT).is(Mod_Registrys.Mod_Items.BOOST_UPGRADE.get())){
            splitStack();
        }
        boolean anyWorkingSlot = false;
        boolean hadInventoryChange = false;

        for (int slot : GRINDER_SLOTS) {
            if (hasRecipe(slot) && isOutputSlotEmptyOrReceivable(slot)) {
                if (this.grinds_Left <= 0) {
                    int previousGrinds = this.grinds_Left;
                    resetGrinds();
                    hadInventoryChange |= previousGrinds != this.grinds_Left;
                }

                if (this.grinds_Left > 0) {
                    anyWorkingSlot = true;
                    increaseCraftingProgress(slot);

                    if (hasCraftingFinished(slot)) {
                        useGrind();
                        craftItem(slot);
                        resetProgress(slot);
                        hadInventoryChange = true;
                    }
                } else {
                    resetProgress(slot);
                }
            } else {
                resetProgress(slot);
            }
        }

        boolean lit = isWorking();
        if (state.hasProperty(BlockStateProperties.LIT) && state.getValue(BlockStateProperties.LIT) != lit) {
            level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.LIT, lit));
            hadInventoryChange = true;
        }

        if (hadInventoryChange || anyWorkingSlot) {
            setChanged(level, pos, state);
        }
    }

    private void splitStack() {
        if (Inventory_Split_Helper.rebalanceByItemGroups(this.inventory, GRINDER_SLOTS)) {
            this.setChanged();
        }
    }
    private boolean isWorking() {
        boolean hasProgress = this.progress_1 > 0 || this.progress_2 > 0 || this.progress_3 > 0 || this.progress_4 > 0;
        boolean hasPowerSource = this.grinds_Left > 0 || inventory.getResource(GRINDING_SLOT).is(Mod_Tags.Items.GRINDERS);
        return hasProgress && !isblockEmpty() && hasPowerSource;
    }
    private boolean isblockEmpty() {
        return inventory.getResource(INPUT_SLOT_1).isEmpty() && inventory.getResource(INPUT_SLOT_2).isEmpty() && inventory.getResource(INPUT_SLOT_3).isEmpty() && inventory.getResource(INPUT_SLOT_4).isEmpty();
    }

    private boolean hasRecipe(int slotNumber) {
        Optional<RecipeHolder<Grinder_Recipe>> recipe = getCurrentRecipe(slotNumber);
        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack output = recipe.get().value().assemble(new SingleRecipeInput(inventory.getResource(slotNumber).toStack()));
        boolean outputSlotAmount = canInsertAmountIntoOutputSlot(output.getCount(), slotNumber);
        boolean outputSlotItem = canInsertItemIntoOutputSlot(output, slotNumber);

        return outputSlotItem && outputSlotAmount;
    }

    private Optional<RecipeHolder<Grinder_Recipe>> getCurrentRecipe(int slotNumber) {
        return ((ServerLevel) level).recipeAccess().getRecipeFor(Mod_Registrys.Mod_Recipes.GRINDER_RECIPE_TYPES.get(),
                new SingleRecipeInput(inventory.getResource(slotNumber).toStack()), level);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output, int slotNumber) {
        int outputSlot = slotNumber + 4;
        return inventory.getResource(outputSlot).isEmpty() ||
                inventory.getResource(outputSlot).is(output.getItem());
    }

    private boolean canInsertAmountIntoOutputSlot(int count, int slotNumber) {
        int outputSlot = slotNumber + 4;
        int maxCount = inventory.getResource(outputSlot).isEmpty() ? 64 : inventory.getResource(outputSlot).getMaxStackSize();
        int currentCount = inventory.getAmountAsInt(outputSlot);

        return maxCount >= currentCount + count;
    }

    private void craftItem(int slotNumber) {
        int outputSlot = slotNumber + 4;
        Optional<RecipeHolder<Grinder_Recipe>> recipe = getCurrentRecipe(slotNumber);
        ItemStack output = recipe.get().value().output().create();
        try(Transaction transaction = Transaction.openRoot()) {
            ItemAccess itemAccess = ItemAccess.forHandlerIndex(inventory, outputSlot);
            inventory.extract(inventory.getResource(slotNumber), 1, transaction);
            inventory.set(outputSlot, ItemResource.of(output), itemAccess.getAmount() + output.getCount());
            transaction.commit();
        }
    }


    private boolean isOutputSlotEmptyOrReceivable(int slotNumber) {
        int outputSlot = slotNumber + 4;
        return inventory.getResource(outputSlot).isEmpty() ||
                inventory.getResource(outputSlot).test(stack -> stack.count() < stack.getMaxStackSize());
    }

    private void increaseCraftingProgress(int slot) {
        if(slot == INPUT_SLOT_1){
            this.progress_1++;
        }
        if(slot == INPUT_SLOT_2){
            this.progress_2++;
        }
        if(slot == INPUT_SLOT_3){
            this.progress_3++;
        }
        if(slot == INPUT_SLOT_4){
            this.progress_4++;
        }
    }

    private boolean hasCraftingFinished(int slot) {
        if(slot == INPUT_SLOT_1){
            return this.progress_1 >= this.max_Progress;
        }
        if(slot == INPUT_SLOT_2){
            return this.progress_2 >= this.max_Progress;
        }
        if(slot == INPUT_SLOT_3){
            return this.progress_3 >= this.max_Progress;
        }
        if(slot == INPUT_SLOT_4){
            return this.progress_4 >= this.max_Progress;
        }
        return false;
    }
    private void resetAllProgress() {
        this.progress_1 = 0;
        this.progress_2 = 0;
        this.progress_3 = 0;
        this.progress_4 = 0;
    }
    private void resetProgress(int slot) {
        if(slot == INPUT_SLOT_1){
            this.progress_1 = 0;
        }
        if(slot == INPUT_SLOT_2){
            this.progress_2 = 0;
        }
        if(slot == INPUT_SLOT_3){
            this.progress_3 = 0;
        }
        if(slot == INPUT_SLOT_4){
            this.progress_4 = 0;
        }
    }

    private void setUpGrades(){
        if(inventory.getResource(this.UPGRADE_SLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_1.get())) {
            this.max_Progress = 40;
        }
        if(inventory.getResource(this.UPGRADE_SLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_2.get())) {
            this.max_Progress = 24;
        }
        if(inventory.getResource(this.UPGRADE_SLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_3.get())) {
            this.max_Progress = 10;
        }
        if(inventory.getResource(this.UPGRADE_SLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_4.get())) {
            this.max_Progress = 5;
        }
        if(inventory.getResource(this.UPGRADE_SLOT).isEmpty()){
            this.max_Progress = 60;
        }
    }

    private void resetGrindEfficiency(){
        this.grind_Efficiency = 5;
    }

    private void useGrind(){
        if(this.inventory.getResource(this.BOOST_SLOT).is(Mod_Registrys.Mod_Items.BOOST_UPGRADE.get())){
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
            if(Grinder_Head_Item.canUse(this.inventory.getResource(GRINDING_SLOT).toStack())) {
                this.inventory.set(GRINDING_SLOT, ItemResource.of(Grinder_Head_Item.brakeItem(this.inventory.getResource(GRINDING_SLOT).toStack())), 1);
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