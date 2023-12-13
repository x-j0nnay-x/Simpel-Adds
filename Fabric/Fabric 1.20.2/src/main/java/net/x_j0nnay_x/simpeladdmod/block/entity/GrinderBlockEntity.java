package net.x_j0nnay_x.simpeladdmod.block.entity;



import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.x_j0nnay_x.simpeladdmod.block.ImplementedInventory;
import net.x_j0nnay_x.simpeladdmod.block.ModBlockEntities;
import net.x_j0nnay_x.simpeladdmod.block.custom.GrinderBlock;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;
import net.x_j0nnay_x.simpeladdmod.recipe.GrinderRecipe;
import net.x_j0nnay_x.simpeladdmod.screen.grinder.GrinderMenu;
import net.x_j0nnay_x.simpeladdmod.until.ModTags;
import org.jetbrains.annotations.Nullable;
import java.util.Optional;
import java.util.stream.IntStream;

public class GrinderBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory, SidedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);

    public static int INPUTSLOT = 0;
    public static int GRINDERSLOT = 1;
    public static int OUTPUTSLOT = 2;
    public  static int UPGRADESLOT = 3;
    public  static int BOOSTSLOT = 4;
    protected final PropertyDelegate data;
    private int progress = 0;
    private int maxProgress = 60;
    private int grindsleft = 0 ;
    private int maxGrinds = 3;
    private int grindEff = 5;
    private int hasBoost = 0;
    public GrinderBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.GRINDER, pPos, pBlockState);
        this.data = new PropertyDelegate() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> GrinderBlockEntity.this.progress;
                    case 1 -> GrinderBlockEntity.this.maxProgress;
                    case 2 -> GrinderBlockEntity.this.grindsleft;
                    case 3 -> GrinderBlockEntity.this.maxGrinds;
                    case 4 -> GrinderBlockEntity.this.grindEff;
                    case 5 -> GrinderBlockEntity.this.hasBoost;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> GrinderBlockEntity.this.progress = pValue;
                    case 1 -> GrinderBlockEntity.this.maxProgress = pValue;
                    case 2 -> GrinderBlockEntity.this.grindsleft = pValue;
                    case 3 -> GrinderBlockEntity.this.maxGrinds = pValue;
                    case 4 -> GrinderBlockEntity.this.grindEff = pValue;
                    case 5 -> GrinderBlockEntity.this.hasBoost = pValue;
                }
            }

            @Override
            public int size() {
                return 6;
            }
        };
    }
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }
    @Override
    public Text getDisplayName() {
        return Text.translatable("block.simpeladdmod.grinder_block");
    }
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return  new GrinderMenu(syncId, playerInventory, this, this.data);
    }
    public boolean canTransferTo(Inventory hopperInventory, int slot, ItemStack stack) {
        return true;
    }
    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 4);
        super.markDirty();
    }
    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
    @Override
    public void readNbt(NbtCompound compound) {
        super.readNbt(compound);
        Inventories.readNbt(compound, inventory);
        progress = compound.getInt("grinder_progress");
        grindsleft = compound.getInt("grinder_grinds_left");
        grindEff = compound.getInt("grinder_effec");

    }

    @Override
    public void writeNbt(NbtCompound compound) {
        super.writeNbt(compound);
        Inventories.writeNbt(compound, inventory);
        compound.putInt("grinder_progress", progress);
        compound.putInt("grinder_grinds_left", grindsleft);
        compound.putInt("grinder_effec", grindEff);

    }
    @Override
    public boolean canInsert(int index, ItemStack stack, @Nullable Direction direction) {
        return (direction == Direction.EAST && index == INPUTSLOT ||
                direction == Direction.WEST && index == INPUTSLOT||
                direction == Direction.SOUTH && index == INPUTSLOT||
                direction == Direction.NORTH && index == INPUTSLOT||
                direction == Direction.UP && index == GRINDERSLOT);
    }

    @Override
    public boolean canExtract(int index, ItemStack stack, Direction direction) {
        return (direction == Direction.DOWN && (index == OUTPUTSLOT));
    }
    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if (slot == GRINDERSLOT) {
            return stack.getItem() == ModItems.GRINDERHEAD;
        }
        if (slot == INPUTSLOT) {
            return  stack.isIn(ModTags.Items.CANGRIND);
        }
        if (slot == UPGRADESLOT){
            return  stack.isIn(ModTags.Items.UPGRADES);
        }
        if (slot == BOOSTSLOT){
            return stack.isOf(ModItems.BOOSTUPGRADE);
        }

        return false;
    }

// processing


    public void tick(World pLevel, BlockPos pPos, BlockState pState) {
        if(world.isClient()) {
            return;
        }
        if(inventory.get(BOOSTSLOT).isOf(ModItems.BOOSTUPGRADE)){
            this.hasBoost = 1;
        }
        if (inventory.get(BOOSTSLOT).isEmpty()){
            this.hasBoost = 0;
        }
        if (inventory.get(UPGRADESLOT).isOf(ModItems.SPEEDUPGRADE_1)) {
            this.maxProgress = 40;
        }if (inventory.get(UPGRADESLOT).isOf(ModItems.SPEEDUPGRADE_2)) {
            this.maxProgress = 24;
        }if (inventory.get(UPGRADESLOT).isOf(ModItems.SPEEDUPGRADE_3)) {
            this.maxProgress = 10;
        }if (inventory.get(UPGRADESLOT).isEmpty()){
            this.maxProgress = 60;
        }
        pState = (BlockState)pState.with(GrinderBlock.WORKING, isWorking());
        pLevel.setBlockState(pPos, pState, Block.NOTIFY_ALL);

        if(hasRecipe()) {
                if (grindsleft > 0) {
                    increaseCraftingProgress();
                    setCachedState(pState);
                    if (hasProgressFinished()) {
                        useGrind();
                        craftItem();
                        resetProgress();
                    }
                } else {
                    resetGrinds();
                }

            } else {

                resetProgress();
            }

    }
    private void resetGrindEff(){
        grindEff = 5;
    }

    private void useGrind(){

        if (inventory.get(BOOSTSLOT).isOf(ModItems.BOOSTUPGRADE)){
            if (grindEff > 0) {
                grindEff--;
            }else {
                grindsleft--;
                resetGrindEff();
            }
        }else {
            grindsleft--;
        }
    }
    private void resetGrinds() {
        if(inventory.get(GRINDERSLOT).isOf(ModItems.GRINDERHEAD)){
            if(inventory.get(GRINDERSLOT).getDamage() >= inventory.get(GRINDERSLOT).getMaxDamage()){
                inventory.set(GRINDERSLOT, ItemStack.EMPTY);
            }else{
                inventory.get(GRINDERSLOT).setDamage(inventory.get(GRINDERSLOT).getDamage() + 1);
                grindsleft = maxGrinds;
            }
        }else {
            grindsleft = 0;
        }
    }
    private boolean canWork(){
        return  hasRecipe();
    }
    private boolean isWorking() {
        if (canWork()){
            if(grindsleft > 0 || inventory.get(GRINDERSLOT).isOf(ModItems.GRINDERHEAD)){
                return true;
            }
        }
        return false;
    }
    private void resetProgress() {
        progress = 0;
    }
    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void craftItem() {
        Optional<RecipeEntry<GrinderRecipe>> recipe = getCurrentRecipe();
        this.removeStack(INPUTSLOT, 1);

        this.setStack(OUTPUTSLOT, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(OUTPUTSLOT).getCount() + recipe.get().value().getResult(null).getCount()));

    }
    private boolean hasRecipe() {
        Optional<RecipeEntry<GrinderRecipe>> recipe = getCurrentRecipe();

        return recipe.isPresent() && canInsertOutputAmount(recipe.get().value().getResult(null))
                && canInsertOutputItem(recipe.get().value().getResult(null).getItem());
}
    private Optional<RecipeEntry<GrinderRecipe>> getCurrentRecipe() {
        SimpleInventory inventory = new SimpleInventory(this.inventory.size());
        for(int i = 0; i < inventory.size(); i++) {
            inventory.setStack(i, this.getStack(i));
        }
        return getWorld().getRecipeManager().getFirstMatch(GrinderRecipe.Type.INSTANCE, inventory, getWorld());

    }
    private boolean canInsertOutputItem(Item item) {
        return this.getStack(OUTPUTSLOT).getItem() == item || this.getStack(OUTPUTSLOT).isEmpty();
    }

    private boolean canInsertOutputAmount(ItemStack result) {
        return this.getStack(OUTPUTSLOT).getCount() + result.getCount() <= getStack(OUTPUTSLOT).getMaxCount();
    }


}