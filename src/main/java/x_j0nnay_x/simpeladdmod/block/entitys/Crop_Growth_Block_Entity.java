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
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
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
import x_j0nnay_x.simpeladdmod.init.Mod_Constants;
import x_j0nnay_x.simpeladdmod.init.Mod_Registrys;
import x_j0nnay_x.simpeladdmod.menu.Crop_Growth_Menu;
import x_j0nnay_x.simpeladdmod.recipe.Crop_Growth_Recipe;
import x_j0nnay_x.simpeladdmod.recipe.util.Crop_Groth_Recipe_Input;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/*
  this is inspired by Botany Pots/Botany trees this is my own spin on the idea. I've done this because I wanted something that would work as i hate tree farms and stuff like that.
  this is a block that will grow crops for you and give you the drops. it will use copper to run and will use dirt as a base for the crops to grow on.
  I know there is probably a better way to do this but this is my way, and it may not be pretty but it works and I like it.
  I also made it so you can just add recipes to this machine instead of having to make an addon.
 */
public class Crop_Growth_Block_Entity extends BlockEntity implements MenuProvider {

    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(20) {
        @Override
        protected void onContentsChanged(int index, ItemStack previousContents) {
            super.onContentsChanged(index, previousContents);
            Crop_Growth_Block_Entity.this.setChanged();
        }
    };
    private final ResourceHandler<ItemResource> outPutHandler = RangedResourceHandler.of(inventory, OUTPUTSLOT1, OUTPUTSLOT15);
    private final ResourceHandler<ItemResource> topHandler = RangedResourceHandler.ofSingleIndex(inventory, COPPERSLOT);


    public static int DIRTSLOT = 0;
    public static int CROPSLOT = 1;
    public static int OUTPUTSLOT1 = 2;
    public static int OUTPUTSLOT2 = 3;
    public static int OUTPUTSLOT3 = 4;
    public static int OUTPUTSLOT4 = 5;
    public static int OUTPUTSLOT5 = 6;
    public static int OUTPUTSLOT6 = 7;
    public static int OUTPUTSLOT7 = 8;
    public static int OUTPUTSLOT8 = 9;
    public static int OUTPUTSLOT9 = 10;
    public static int OUTPUTSLOT10 = 11;
    public static int OUTPUTSLOT11 = 12;
    public static int OUTPUTSLOT12 = 13;
    public static int OUTPUTSLOT13 = 14;
    public static int OUTPUTSLOT14 = 15;
    public static int OUTPUTSLOT15 = 16;
    public static int COPPERSLOT = 17;
    public static int SPEEDSLOT = 18;
    public static int EFFSLOT = 19;
    protected final ContainerData data;
    private int progress = 0;
    private int max_Progress;
    private int copper_Level = 0;
    private int max_Copper = 100;
    private int has_Boost;
    private int copper_use;
    private boolean shouldWork;

    public Crop_Growth_Block_Entity(BlockPos worldPosition, BlockState blockState) {
        super(Mod_Registrys.Mod_Block_Entity.CROP_GROWTH_BLOCK_ENTITY.get(), worldPosition, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int dataId) {
                return switch (dataId) {
                    case 0 -> Crop_Growth_Block_Entity.this.progress;
                    case 1 -> Crop_Growth_Block_Entity.this.max_Progress;
                    case 2 -> Crop_Growth_Block_Entity.this.copper_Level;
                    default -> 0;
                };
            }

            @Override
            public void set(int dataId, int value) {
                switch (dataId) {
                    case 0 -> Crop_Growth_Block_Entity.this.progress = value;
                    case 1 -> Crop_Growth_Block_Entity.this.max_Progress = value;
                    case 2 -> Crop_Growth_Block_Entity.this.copper_Level = value;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.simpeladdmod.crop_growth`");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new Crop_Growth_Menu(containerId, inventory, this, this.inventory, this.data);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt(Mod_Constants.MOD_CUSTOM +"crop_growth_progress", this.progress);
        output.putInt(Mod_Constants.MOD_CUSTOM +"crop_growth_copperlevel", this.copper_Level);
        output.putChild("inventory", inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        progress = input.getIntOr(Mod_Constants.MOD_CUSTOM +"crop_growth_progress", 0);
        copper_Level = input.getIntOr(Mod_Constants.MOD_CUSTOM +"crop_growth_copperlevel", 0);
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
        Containers.dropItemStack(this.level, this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ(),this.getCopperOutput());
    }

    public ResourceHandler<ItemResource> getItemHandler(Direction direction) {
        if (direction == null)
            return inventory;

        Direction facing = this.getBlockState().hasProperty(BlockStateProperties.HORIZONTAL_FACING)
                ? this.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING)
                : Direction.NORTH;

        return switch (getRelativeSide(facing, direction)) {
            case UP -> topHandler;
            case WEST, EAST,NORTH, SOUTH, DOWN -> outPutHandler;
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

    public ItemStack getCopperOutput() {
        return new ItemStack(Items.COPPER_INGOT, this.copper_Level);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        setUpGrades();
        setCopperLevel();
        setChanged(level, pos, state);
        boolean canProcess = hasRecipe() && keepWorking() && !isBlockFull();
        if (canProcess) {
            this.shouldWork = true;
            level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.LIT, isWorking()));
            if (this.copper_Level > 0) {
                increaseCraftingProgress();
                if (hasCraftingFinished()) {
                    craftItem();
                    resetProgress();
                }
            }
        } else {
            this.shouldWork = false;
            resetProgress();
            level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.LIT, isWorking()));
        }
    }
    private void setUpGrades(){
        if(this.inventory.getResource(EFFSLOT).is(Mod_Registrys.Mod_Items.BOOST_UPGRADE.get())){
            this.has_Boost = 1;
        }if (this.inventory.getResource(EFFSLOT).isEmpty()){
            this.has_Boost = 0;
        }if (this.inventory.getResource(SPEEDSLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_1.get())) {
            this.max_Progress = 2880;
        }if (this.inventory.getResource(SPEEDSLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_2.get())) {
            this.max_Progress = 2160;
        }if (this.inventory.getResource(SPEEDSLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_3.get())) {
            this.max_Progress = 1440;
        }if (this.inventory.getResource(SPEEDSLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_4.get())) {
            this.max_Progress = 450;
        }if (this.inventory.getResource(SPEEDSLOT).isEmpty()){
            this.max_Progress = 3600;
        }
    }

    private void setCopperLevel(){
        if(this.copper_Level == max_Copper)
            return;
        if(copper_Level < max_Copper) {
            if (this.inventory.getResource(COPPERSLOT).is(Items.COPPER_INGOT)) {
                this.copper_Level += 1;
                try(Transaction transaction = Transaction.openRoot()) {
                inventory.extract(COPPERSLOT, ItemResource.of(inventory.getResource(COPPERSLOT).toStack()), 1, transaction);
                transaction.commit();
                }
            }
        }
    }

    private static final int OUTPUT_SLOT_COUNT = 15;
    private static final int SLOT_STACK_CAP = 64;

    private boolean canWorkCheck(ItemStack itemStack, int slot) {
        ItemStack slotStack = this.inventory.getResource(slot).toStack();
        if (slotStack.isEmpty()) {
            return true;
        }

        return ItemStack.isSameItemSameComponents(slotStack, itemStack) && slotStack.getCount() < SLOT_STACK_CAP;
    }

    private boolean isSlotFull(int slot){
        return this.inventory.getResource(slot).toStack().getCount() == SLOT_STACK_CAP;
    }

    private static boolean canMergeItems(ItemStack stack1, ItemStack stack2) {
        return ItemStack.isSameItemSameComponents(stack1, stack2);
    }

    private void restCopperuse(){
        if (this.has_Boost == 1){
            this.copper_use = 4;
        }else{
            this.copper_use = 1;
        }
    }

    private void useCopper(){
        if(this.copper_use > 1){
            this.copper_use --;
        }else {
            restCopperuse();
            this.copper_Level--;
        }
    }
    private boolean isWorking(){
        return hasDirt() && hasCrops() && hasCopper() && this.shouldWork;
    }

    private boolean hasDirt(){
        return !this.inventory.getResource(DIRTSLOT).isEmpty();
    }

    private boolean hasCrops(){
        return !this.inventory.getResource(CROPSLOT).isEmpty();
    }

    private boolean hasCopper(){
        return this.copper_Level > 0;
    }
    private boolean isBlockFull(){
        for (int i = 0; i < 15; i++) {
            ItemStack slotStack = this.inventory.getResource(OUTPUTSLOT1 + i).toStack();
            if(slotStack.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    private boolean keepWorking(){
        return !isSlotFull(OUTPUTSLOT1) && !isSlotFull(OUTPUTSLOT2) && !isSlotFull(OUTPUTSLOT3) && !isSlotFull(OUTPUTSLOT4) && !isSlotFull(OUTPUTSLOT5) &&
                !isSlotFull(OUTPUTSLOT6) && !isSlotFull(OUTPUTSLOT7) && !isSlotFull(OUTPUTSLOT8) && !isSlotFull(OUTPUTSLOT9) && !isSlotFull(OUTPUTSLOT10) &&
                 !isSlotFull(OUTPUTSLOT11) && !isSlotFull(OUTPUTSLOT12) && !isSlotFull(OUTPUTSLOT13) && !isSlotFull(OUTPUTSLOT14) && !isSlotFull(OUTPUTSLOT15);
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

    private static List<ItemStack> getOuputList(ItemStack itemStack) {
        Item item = itemStack.getItem();
        List<ItemStack> output = new ArrayList<>();
        Random random = new Random();
        int count = random.nextInt(1, 3);
        ItemStack itemStack1 = new ItemStack(item, count);
        output.add(itemStack1);
        return output;
    }

    public void putDropsIn(ItemStack input) {
        List<ItemStack> output = getOuputList(input);
        this.shouldWork = false;
        for (ItemStack drops : output) {
            for (int i = 0; i < OUTPUT_SLOT_COUNT; i++) {
                int slot = OUTPUTSLOT1 + i;
                ItemStack stack = this.inventory.getResource(slot).toStack();
                if (!canWorkCheck(drops, slot) || isSlotFull(slot)) {
                    continue;
                }
                this.shouldWork = true;
                if (stack.isEmpty()) {
                    int insertCount = Math.min(drops.getCount(), SLOT_STACK_CAP);
                    this.inventory.set(slot, ItemResource.of(drops), insertCount);
                    drops.shrink(insertCount);
                } else if (canMergeItems(stack, drops)) {
                    ItemAccess itemAccess = ItemAccess.forHandlerIndex(inventory, slot);
                    int spaceLeft = SLOT_STACK_CAP - itemAccess.getAmount();
                    int insertCount = Math.min(drops.getCount(), spaceLeft);
                    this.inventory.set(slot, ItemResource.of(drops), itemAccess.getAmount() + insertCount);
                    drops.shrink(insertCount);
                }
                if (drops.isEmpty()) {
                    break;
                }
            }
        }
        this.progress = 0;
        if (this.shouldWork) {
            useCopper();
        }
    }
    private boolean hasRecipe() {
        Optional<RecipeHolder<Crop_Growth_Recipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack output = recipe.get().value().assemble(new Crop_Groth_Recipe_Input(inventory.getResource(CROPSLOT).toStack(), inventory.getResource(DIRTSLOT).toStack()));
        return !output.isEmpty();
    }

    private Optional<RecipeHolder<Crop_Growth_Recipe>> getCurrentRecipe() {
        return ((ServerLevel) level).recipeAccess().getRecipeFor(Mod_Registrys.Mod_Recipes.CROP_GROWTH_RECIPE_TYPES.get(),
                new Crop_Groth_Recipe_Input(inventory.getResource(CROPSLOT).toStack(), inventory.getResource(DIRTSLOT).toStack()), level);
    }
    private void craftItem() {
        Optional<RecipeHolder<Crop_Growth_Recipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output().create();
        try(Transaction transaction = Transaction.openRoot()) {
            putDropsIn(output);
            transaction.commit();
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