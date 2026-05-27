package x_j0nnay_x.simpeladdmod.block.entitys;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
import x_j0nnay_x.simpeladdmod.init.Mod_Constants;
import x_j0nnay_x.simpeladdmod.menu.Netherite_Crafter_Menu;


public class Netherite_Crafter_Block_Entity extends BlockEntity implements MenuProvider {

    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(5) {
        @Override
        protected void onContentsChanged(int index, ItemStack previousContents) {
            super.onContentsChanged(index, previousContents);
            Netherite_Crafter_Block_Entity.this.setChanged();
        }
    };
    private final ResourceHandler<ItemResource> sideHandler = RangedResourceHandler.of(inventory, SCRAP_SLOT, BLAZE_SLOT);
    private final ResourceHandler<ItemResource> bottomHandler = RangedResourceHandler.ofSingleIndex(inventory, OUTPUT_SLOT);

    public static int SCRAP_SLOT = 0;
    public static int GOLD_SLOT = 1;
    public static int BLAZE_SLOT = 2;
    public static int OUTPUT_SLOT = 3;
    public static int UPGRADE_SLOT = 4;
    protected final ContainerData data;
    private int progress = 0;
    private int max_Progress;
    private int blazeUse = 0;
    private int maxBlazeuse = 8;

    public Netherite_Crafter_Block_Entity(BlockPos worldPosition, BlockState blockState) {
        super(Mod_Registrys.Mod_Block_Entity.NETHERITE_CRAFTER_BLOCK_ENTITY.get(), worldPosition, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int dataId) {
                return switch (dataId) {
                    case 0 -> Netherite_Crafter_Block_Entity.this.progress;
                    case 1 -> Netherite_Crafter_Block_Entity.this.max_Progress;
                    case 2 -> Netherite_Crafter_Block_Entity.this.blazeUse;
                    case 3 -> Netherite_Crafter_Block_Entity.this.maxBlazeuse;
                    default -> 0;
                };
            }

            @Override
            public void set(int dataId, int value) {
                switch (dataId) {
                    case 0 -> Netherite_Crafter_Block_Entity.this.progress = value;
                    case 1 -> Netherite_Crafter_Block_Entity.this.max_Progress = value;
                    case 2 -> Netherite_Crafter_Block_Entity.this.blazeUse = value;
                    case 3 -> Netherite_Crafter_Block_Entity.this.maxBlazeuse = value;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.simpeladdmod.netherite_crafter");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new Netherite_Crafter_Menu(containerId, inventory, this, this.inventory, this.data);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt(Mod_Constants.MOD_CUSTOM +"netherite_crafter_progress", this.progress);
        output.putInt(Mod_Constants.MOD_CUSTOM +"netherite_crafter_blaze_uses", this.blazeUse);
        output.putChild("inventory", inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        progress = input.getIntOr(Mod_Constants.MOD_CUSTOM +"netherite_crafter_progress", 0);
        blazeUse = input.getIntOr(Mod_Constants.MOD_CUSTOM +"netherite_crafter_blaze_uses", 0);
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
            case DOWN -> bottomHandler;
            case UP, WEST, EAST,NORTH, SOUTH -> sideHandler;
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
        if(hasRecipe()) {
            if (hasSpace() && blazeUse > 0) {
                setChanged(level, pos, state);
                level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.LIT, isWorking()));
                increaseCraftingProgress();
                if (hasProgressFinished()) {
                    craftItem();
                    resetProgress();
                }
            }else {
                level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.LIT, isWorking()));
                refillBlaze();
            }
        }else{
            resetProgress();
            level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.LIT, isWorking()));
        }
    }

    private void setUpGrades(){
        if(inventory.getResource(this.UPGRADE_SLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_1.get())) {
            this.max_Progress = 60;
        }
        if(inventory.getResource(this.UPGRADE_SLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_2.get())) {
            this.max_Progress = 36;
        }
        if(inventory.getResource(this.UPGRADE_SLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_3.get())) {
            this.max_Progress = 15;
        }
        if(inventory.getResource(this.UPGRADE_SLOT).is(Mod_Registrys.Mod_Items.SPEED_UPGRADE_4.get())) {
            this.max_Progress = 5;
        }
        if(inventory.getResource(this.UPGRADE_SLOT).isEmpty()){
            this.max_Progress = 90;
        }

    }

    private void  refillBlaze(){
        if(!inventory.getResource(BLAZE_SLOT).isEmpty()) {
            try (Transaction transaction = Transaction.openRoot()) {
                this.inventory.extract(inventory.getResource(BLAZE_SLOT), 1, transaction);
                transaction.commit();
            }
            blazeUse = maxBlazeuse;
        }
    }

    private boolean isWorking(){
        if(hasRecipe() && blazeUse > 0){
            return true;
        }
        return false;
    }

    private boolean hasRecipe(){
        return(this.inventory.getResource(SCRAP_SLOT).is(Items.NETHERITE_SCRAP) && this.inventory.getResource(GOLD_SLOT).is(Items.GOLD_INGOT));
    }

    private void resetProgress() {
        progress = 0;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasProgressFinished() {
        return progress >= max_Progress;
    }

    private void craftItem() {
        ItemStack output = new ItemStack(Items.NETHERITE_INGOT);
        try(Transaction transaction = Transaction.openRoot()) {
            ItemAccess itemAccess = ItemAccess.forHandlerIndex(inventory, OUTPUT_SLOT);
            this.inventory.extract(inventory.getResource(SCRAP_SLOT), 1, transaction);
            this.inventory.extract(inventory.getResource(GOLD_SLOT), 1, transaction);
            inventory.set(OUTPUT_SLOT, ItemResource.of(output), itemAccess.getAmount() + output.getCount());
            transaction.commit();
            this.blazeUse --;
        }

    }

    private boolean hasSpace(){
        return this.inventory.getResource(OUTPUT_SLOT).toStack().getCount() < 64;
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