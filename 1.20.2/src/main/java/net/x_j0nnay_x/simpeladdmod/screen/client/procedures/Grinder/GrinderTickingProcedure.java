package net.x_j0nnay_x.simpeladdmod.screen.client.procedures.Grinder;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.x_j0nnay_x.simpeladdmod.block.ModBlocks;
import net.x_j0nnay_x.simpeladdmod.item.ModItems;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class GrinderTickingProcedure {
    public static int MAXOUTPUT = 64;
    public static int MAXGRINDS = 3;
    public static int MAXTIME = 10;
    public static int OUTPUT_SLOT = 2;
    public static int GRINDER_SLOT = 1;
    public static int INPUT_SLOT = 0;
    public static int DOUBLE = 2;
    public static int TRIPPLE = 3;
    public static int FIBER = 8;
    public static int FIVE = 5;
    public static void execute(LevelAccessor world, double x, double y, double z) {

//checks grinder and uses left
        if ((new Object() {
            public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                BlockEntity _ent = world.getBlockEntity(pos);
                if (_ent != null)
                    _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                return _retval.get();
            }
        }.getItemStack(world, BlockPos.containing(x, y, z), GRINDER_SLOT)).getItem() == ModItems.GRINDERHEAD.get() && new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Grindleft") == 0) {
            {
                BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                if (_ent != null) {
                    final int _slotid = 1;
                    final int _amount = 1;
                    _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                        if (capability instanceof IItemHandlerModifiable) {
                            ItemStack _stk = capability.getStackInSlot(_slotid).copy();
                            if (_stk.hurt(_amount, RandomSource.create(), null)) {
                                _stk.shrink(1);
                                _stk.setDamageValue(0);
                            }
                            ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
                        }
                    });
                }
            }
            if (!world.isClientSide()) {
                BlockPos _bp = BlockPos.containing(x, y, z);
                BlockEntity _blockEntity = world.getBlockEntity(_bp);
                BlockState _bs = world.getBlockState(_bp);
                if (_blockEntity != null)
                    _blockEntity.getPersistentData().putDouble("Grindleft", MAXGRINDS);
                if (world instanceof Level _level)
                    _level.sendBlockUpdated(_bp, _bs, _bs, 3);
            }
        }
 //starts grinder timer
        else if (new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null)
                    return blockEntity.getPersistentData().getDouble(tag);
                return -1;
            }
        }.getValue(world, BlockPos.containing(x, y, z), "Grindleft") > 0) {
            if (new Object() {
                public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                    AtomicInteger _retval = new AtomicInteger(0);
                    BlockEntity _ent = world.getBlockEntity(pos);
                    if (_ent != null)
                        _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                    return _retval.get();
                }
            }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) != 64 && new Object() {
                public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                    AtomicInteger _retval = new AtomicInteger(0);
                    BlockEntity _ent = world.getBlockEntity(pos);
                    if (_ent != null)
                        _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                    return _retval.get();
                }
            }.getAmount(world, BlockPos.containing(x, y, z), INPUT_SLOT) > 0) {
                if (!world.isClientSide()) {
                    BlockPos _bp = BlockPos.containing(x, y, z);
                    BlockEntity _blockEntity = world.getBlockEntity(_bp);
                    BlockState _bs = world.getBlockState(_bp);
                    if (_blockEntity != null)
                        _blockEntity.getPersistentData().putDouble("maxtime", MAXTIME);
                    if (world instanceof Level _level)
                        _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                }
                 if (!world.isClientSide()) {
                    BlockPos _bp = BlockPos.containing(x, y, z);
                    BlockEntity _blockEntity = world.getBlockEntity(_bp);
                    BlockState _bs = world.getBlockState(_bp);
                    if (_blockEntity != null)
                        _blockEntity.getPersistentData().putDouble("Grindtime", (new Object() {
                            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                BlockEntity blockEntity = world.getBlockEntity(pos);
                                if (blockEntity != null)
                                    return blockEntity.getPersistentData().getDouble(tag);
                                return -1;
                            }
                        }.getValue(world, BlockPos.containing(x, y, z), "Grindtime") + 1));
                    if (world instanceof Level _level)
                        _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                    if (_bs.getBlock().getStateDefinition().getProperty("working") instanceof BooleanProperty _booleanProp)
                        world.setBlock(_bp, _bs.setValue(_booleanProp, true), 3);
                }
//Iron ore
                if (new Object() {
                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.getBlockEntity(pos);
                        if (blockEntity != null)
                            return blockEntity.getPersistentData().getDouble(tag);
                        return -1;
                    }
                }.getValue(world, BlockPos.containing(x, y, z), "Grindtime") >= new Object() {
                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.getBlockEntity(pos);
                        if (blockEntity != null)
                            return blockEntity.getPersistentData().getDouble(tag);
                        return -1;
                    }
                }.getValue(world, BlockPos.containing(x, y, z), "maxtime")) {
                    if (((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), INPUT_SLOT)).getItem() == Blocks.IRON_ORE.asItem() || (new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), INPUT_SLOT)).getItem() == Blocks.DEEPSLATE_IRON_ORE.asItem()) && new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) <= MAXOUTPUT - TRIPPLE && ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), OUTPUT_SLOT)).getItem() == Items.RAW_IRON || new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) == 0)) {
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindleft", ((new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                        BlockEntity blockEntity = world.getBlockEntity(pos);
                                        if (blockEntity != null)
                                            return blockEntity.getPersistentData().getDouble(tag);
                                        return -1;
                                    }
                                }.getValue(world, BlockPos.containing(x, y, z), "Grindleft")) - 1));
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = INPUT_SLOT;
                                final int _amount = 1;
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable) {
                                        ItemStack _stk = capability.getStackInSlot(_slotid).copy();
                                        _stk.shrink(_amount);
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
                                    }
                                });
                            }
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = OUTPUT_SLOT;
                                final ItemStack _setstack = new ItemStack(Items.RAW_IRON);
                                _setstack.setCount((int) (new Object() {
                                    public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                                        AtomicInteger _retval = new AtomicInteger(0);
                                        BlockEntity _ent = world.getBlockEntity(pos);
                                        if (_ent != null)
                                            _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                                        return _retval.get();
                                    }
                                }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) + TRIPPLE));
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable)
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
                                });
                            }
                        }
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindtime", 0);
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                    }
//Copper Ore
                    else if (((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), INPUT_SLOT)).getItem() == Blocks.COPPER_ORE.asItem() || (new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), INPUT_SLOT)).getItem() == Blocks.DEEPSLATE_COPPER_ORE.asItem()) && new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) <= MAXOUTPUT - TRIPPLE && ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), OUTPUT_SLOT)).getItem() == Items.RAW_COPPER || new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) == 0)) {
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindleft", ((new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                        BlockEntity blockEntity = world.getBlockEntity(pos);
                                        if (blockEntity != null)
                                            return blockEntity.getPersistentData().getDouble(tag);
                                        return -1;
                                    }
                                }.getValue(world, BlockPos.containing(x, y, z), "Grindleft")) - 1));
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = INPUT_SLOT;
                                final int _amount = 1;
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable) {
                                        ItemStack _stk = capability.getStackInSlot(_slotid).copy();
                                        _stk.shrink(_amount);
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
                                    }
                                });
                            }
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = OUTPUT_SLOT;
                                final ItemStack _setstack = new ItemStack(Items.RAW_COPPER);
                                _setstack.setCount((int) (new Object() {
                                    public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                                        AtomicInteger _retval = new AtomicInteger(0);
                                        BlockEntity _ent = world.getBlockEntity(pos);
                                        if (_ent != null)
                                            _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                                        return _retval.get();
                                    }
                                }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) + TRIPPLE));
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable)
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
                                });
                            }
                        }
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindtime", 0);
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                    }
//Gold Ore
                    else if (((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), INPUT_SLOT)).getItem() == Blocks.GOLD_ORE.asItem() || (new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), INPUT_SLOT)).getItem() == Blocks.DEEPSLATE_GOLD_ORE.asItem()) && new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) <= MAXOUTPUT - TRIPPLE && ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), OUTPUT_SLOT)).getItem() == Items.RAW_GOLD || new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) == 0)) {
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindleft", ((new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                        BlockEntity blockEntity = world.getBlockEntity(pos);
                                        if (blockEntity != null)
                                            return blockEntity.getPersistentData().getDouble(tag);
                                        return -1;
                                    }
                                }.getValue(world, BlockPos.containing(x, y, z), "Grindleft")) - 1));
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = INPUT_SLOT;
                                final int _amount = 1;
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable) {
                                        ItemStack _stk = capability.getStackInSlot(_slotid).copy();
                                        _stk.shrink(_amount);
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
                                    }
                                });
                            }
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = OUTPUT_SLOT;
                                final ItemStack _setstack = new ItemStack(Items.RAW_GOLD);
                                _setstack.setCount((int) (new Object() {
                                    public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                                        AtomicInteger _retval = new AtomicInteger(0);
                                        BlockEntity _ent = world.getBlockEntity(pos);
                                        if (_ent != null)
                                            _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                                        return _retval.get();
                                    }
                                }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) + TRIPPLE));
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable)
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
                                });
                            }
                        }
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindtime", 0);
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                    }
//Raw Iron
                    else if ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), INPUT_SLOT)).getItem() == Items.RAW_IRON && new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) <= MAXOUTPUT - DOUBLE && ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), OUTPUT_SLOT)).getItem() == ModItems.IRONDUST.get() || new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) == 0)) {
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindleft", ((new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                        BlockEntity blockEntity = world.getBlockEntity(pos);
                                        if (blockEntity != null)
                                            return blockEntity.getPersistentData().getDouble(tag);
                                        return -1;
                                    }
                                }.getValue(world, BlockPos.containing(x, y, z), "Grindleft")) - 1));
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = INPUT_SLOT;
                                final int _amount = 1;
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable) {
                                        ItemStack _stk = capability.getStackInSlot(_slotid).copy();
                                        _stk.shrink(_amount);
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
                                    }
                                });
                            }
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = OUTPUT_SLOT;
                                final ItemStack _setstack = new ItemStack(ModItems.IRONDUST.get());
                                _setstack.setCount((int) (new Object() {
                                    public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                                        AtomicInteger _retval = new AtomicInteger(0);
                                        BlockEntity _ent = world.getBlockEntity(pos);
                                        if (_ent != null)
                                            _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                                        return _retval.get();
                                    }
                                }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) + DOUBLE));
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable)
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
                                });
                            }
                        }
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindtime", 0);
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                    }
//Raw Gold
                    else if ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), INPUT_SLOT)).getItem() == Items.RAW_GOLD && new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) <= MAXOUTPUT - DOUBLE && ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), OUTPUT_SLOT)).getItem() == ModItems.GOLDDUST.get() || new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) == 0)) {
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindleft", ((new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                        BlockEntity blockEntity = world.getBlockEntity(pos);
                                        if (blockEntity != null)
                                            return blockEntity.getPersistentData().getDouble(tag);
                                        return -1;
                                    }
                                }.getValue(world, BlockPos.containing(x, y, z), "Grindleft")) - 1));
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = INPUT_SLOT;
                                final int _amount = 1;
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable) {
                                        ItemStack _stk = capability.getStackInSlot(_slotid).copy();
                                        _stk.shrink(_amount);
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
                                    }
                                });
                            }
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = OUTPUT_SLOT;
                                final ItemStack _setstack = new ItemStack(ModItems.GOLDDUST.get());
                                _setstack.setCount((int) (new Object() {
                                    public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                                        AtomicInteger _retval = new AtomicInteger(0);
                                        BlockEntity _ent = world.getBlockEntity(pos);
                                        if (_ent != null)
                                            _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                                        return _retval.get();
                                    }
                                }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) + DOUBLE));
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable)
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
                                });
                            }
                        }
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindtime", 0);
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                    }
//Raw Copper
                    else if ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), INPUT_SLOT)).getItem() == Items.RAW_COPPER && new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) <= MAXOUTPUT - DOUBLE && ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), OUTPUT_SLOT)).getItem() == ModItems.COPPERDUST.get() || new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) == 0)) {
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindleft", ((new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                        BlockEntity blockEntity = world.getBlockEntity(pos);
                                        if (blockEntity != null)
                                            return blockEntity.getPersistentData().getDouble(tag);
                                        return -1;
                                    }
                                }.getValue(world, BlockPos.containing(x, y, z), "Grindleft")) - 1));
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = INPUT_SLOT;
                                final int _amount = 1;
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable) {
                                        ItemStack _stk = capability.getStackInSlot(_slotid).copy();
                                        _stk.shrink(_amount);
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
                                    }
                                });
                            }
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = OUTPUT_SLOT;
                                final ItemStack _setstack = new ItemStack(ModItems.COPPERDUST.get());
                                _setstack.setCount((int) (new Object() {
                                    public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                                        AtomicInteger _retval = new AtomicInteger(0);
                                        BlockEntity _ent = world.getBlockEntity(pos);
                                        if (_ent != null)
                                            _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                                        return _retval.get();
                                    }
                                }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) + DOUBLE));
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable)
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
                                });
                            }
                        }
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindtime", 0);
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                    }
//Netherite
                    else if ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), INPUT_SLOT)).getItem() == Blocks.ANCIENT_DEBRIS.asItem() && new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) <= MAXOUTPUT - DOUBLE && ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), OUTPUT_SLOT)).getItem() == ModItems.NETHERITEDUST.get() || new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) == 0)) {
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindleft", ((new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                        BlockEntity blockEntity = world.getBlockEntity(pos);
                                        if (blockEntity != null)
                                            return blockEntity.getPersistentData().getDouble(tag);
                                        return -1;
                                    }
                                }.getValue(world, BlockPos.containing(x, y, z), "Grindleft")) - 1));
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = INPUT_SLOT;
                                final int _amount = 1;
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable) {
                                        ItemStack _stk = capability.getStackInSlot(_slotid).copy();
                                        _stk.shrink(_amount);
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
                                    }
                                });
                            }
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = OUTPUT_SLOT;
                                final ItemStack _setstack = new ItemStack(ModItems.NETHERITEDUST.get());
                                _setstack.setCount((int) (new Object() {
                                    public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                                        AtomicInteger _retval = new AtomicInteger(0);
                                        BlockEntity _ent = world.getBlockEntity(pos);
                                        if (_ent != null)
                                            _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                                        return _retval.get();
                                    }
                                }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) + DOUBLE));
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable)
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
                                });
                            }
                        }
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindtime", 0);
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                    }
//Obsidian
                    else if ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), INPUT_SLOT)).getItem() == Blocks.OBSIDIAN.asItem() && new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) <= MAXOUTPUT - DOUBLE && ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), OUTPUT_SLOT)).getItem() == ModItems.OBSIDAINDUST.get() || new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) == 0)) {
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindleft", ((new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                        BlockEntity blockEntity = world.getBlockEntity(pos);
                                        if (blockEntity != null)
                                            return blockEntity.getPersistentData().getDouble(tag);
                                        return -1;
                                    }
                                }.getValue(world, BlockPos.containing(x, y, z), "Grindleft")) - 1));
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = INPUT_SLOT;
                                final int _amount = 1;
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable) {
                                        ItemStack _stk = capability.getStackInSlot(_slotid).copy();
                                        _stk.shrink(_amount);
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
                                    }
                                });
                            }
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = OUTPUT_SLOT;
                                final ItemStack _setstack = new ItemStack(ModItems.OBSIDAINDUST.get());
                                _setstack.setCount((int) (new Object() {
                                    public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                                        AtomicInteger _retval = new AtomicInteger(0);
                                        BlockEntity _ent = world.getBlockEntity(pos);
                                        if (_ent != null)
                                            _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                                        return _retval.get();
                                    }
                                }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) + DOUBLE));
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable)
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
                                });
                            }
                        }
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindtime", 0);
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                    }
//Netherite Shards
                    else if ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), INPUT_SLOT)).getItem() == ModItems.NEHTERITE_SHARD_RAW.get() && new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) <= MAXOUTPUT - DOUBLE && ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), OUTPUT_SLOT)).getItem() == ModItems.NEHTERITE_SHARD_DUST.get() || new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) == 0)) {
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindleft", ((new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                        BlockEntity blockEntity = world.getBlockEntity(pos);
                                        if (blockEntity != null)
                                            return blockEntity.getPersistentData().getDouble(tag);
                                        return -1;
                                    }
                                }.getValue(world, BlockPos.containing(x, y, z), "Grindleft")) - 1));
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = INPUT_SLOT;
                                final int _amount = 1;
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable) {
                                        ItemStack _stk = capability.getStackInSlot(_slotid).copy();
                                        _stk.shrink(_amount);
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
                                    }
                                });
                            }
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = OUTPUT_SLOT;
                                final ItemStack _setstack = new ItemStack(ModItems.NEHTERITE_SHARD_DUST.get());
                                _setstack.setCount((int) (new Object() {
                                    public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                                        AtomicInteger _retval = new AtomicInteger(0);
                                        BlockEntity _ent = world.getBlockEntity(pos);
                                        if (_ent != null)
                                            _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                                        return _retval.get();
                                    }
                                }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) + DOUBLE));
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable)
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
                                });
                            }
                        }
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindtime", 0);
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                    }
//Debri Ore
                    else if ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), INPUT_SLOT)).getItem() == ModBlocks.DEEPSLATE_DEBRI_ORE.get().asItem() && new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) <= MAXOUTPUT - TRIPPLE && ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), OUTPUT_SLOT)).getItem() == ModItems.NEHTERITE_SHARD_RAW.get() || new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) == 0)) {
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindleft", ((new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                        BlockEntity blockEntity = world.getBlockEntity(pos);
                                        if (blockEntity != null)
                                            return blockEntity.getPersistentData().getDouble(tag);
                                        return -1;
                                    }
                                }.getValue(world, BlockPos.containing(x, y, z), "Grindleft")) - 1));
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = INPUT_SLOT;
                                final int _amount = 1;
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable) {
                                        ItemStack _stk = capability.getStackInSlot(_slotid).copy();
                                        _stk.shrink(_amount);
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
                                    }
                                });
                            }
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = OUTPUT_SLOT;
                                final ItemStack _setstack = new ItemStack(ModItems.NEHTERITE_SHARD_RAW.get());
                                _setstack.setCount((int) (new Object() {
                                    public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                                        AtomicInteger _retval = new AtomicInteger(0);
                                        BlockEntity _ent = world.getBlockEntity(pos);
                                        if (_ent != null)
                                            _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                                        return _retval.get();
                                    }
                                }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) + TRIPPLE));
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable)
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
                                });
                            }
                        }
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindtime", 0);
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                    }
//Blaze Powder
                    else if ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), INPUT_SLOT)).getItem() == Items.BLAZE_ROD && new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) <= MAXOUTPUT - TRIPPLE && ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), OUTPUT_SLOT)).getItem() == Items.BLAZE_POWDER || new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) == 0)) {
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindleft", ((new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                        BlockEntity blockEntity = world.getBlockEntity(pos);
                                        if (blockEntity != null)
                                            return blockEntity.getPersistentData().getDouble(tag);
                                        return -1;
                                    }
                                }.getValue(world, BlockPos.containing(x, y, z), "Grindleft")) - 1));
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = INPUT_SLOT;
                                final int _amount = 1;
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable) {
                                        ItemStack _stk = capability.getStackInSlot(_slotid).copy();
                                        _stk.shrink(_amount);
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
                                    }
                                });
                            }
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = OUTPUT_SLOT;
                                final ItemStack _setstack = new ItemStack(Items.BLAZE_POWDER);
                                _setstack.setCount((int) (new Object() {
                                    public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                                        AtomicInteger _retval = new AtomicInteger(0);
                                        BlockEntity _ent = world.getBlockEntity(pos);
                                        if (_ent != null)
                                            _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                                        return _retval.get();
                                    }
                                }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) + TRIPPLE));
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable)
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
                                });
                            }
                        }
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindtime", 0);
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                    }
//Bone Meal
                    else if ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), INPUT_SLOT)).getItem() == Items.BONE && new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) <= MAXOUTPUT - FIVE && ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), OUTPUT_SLOT)).getItem() == Items.BONE_MEAL || new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) == 0)) {
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindleft", ((new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                        BlockEntity blockEntity = world.getBlockEntity(pos);
                                        if (blockEntity != null)
                                            return blockEntity.getPersistentData().getDouble(tag);
                                        return -1;
                                    }
                                }.getValue(world, BlockPos.containing(x, y, z), "Grindleft")) - 1));
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = INPUT_SLOT;
                                final int _amount = 1;
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable) {
                                        ItemStack _stk = capability.getStackInSlot(_slotid).copy();
                                        _stk.shrink(_amount);
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
                                    }
                                });
                            }
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = OUTPUT_SLOT;
                                final ItemStack _setstack = new ItemStack(Items.BONE_MEAL);
                                _setstack.setCount((int) (new Object() {
                                    public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                                        AtomicInteger _retval = new AtomicInteger(0);
                                        BlockEntity _ent = world.getBlockEntity(pos);
                                        if (_ent != null)
                                            _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                                        return _retval.get();
                                    }
                                }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) + FIVE));
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable)
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
                                });
                            }
                        }
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindtime", 0);
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                    }
//Wood Fiber
                    else if ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), INPUT_SLOT)).is(ItemTags.LOGS)  && new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) <= MAXOUTPUT - FIBER && ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), OUTPUT_SLOT)).getItem() == ModItems.WOODFIBER.get() || new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) == 0)) {
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindleft", ((new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                        BlockEntity blockEntity = world.getBlockEntity(pos);
                                        if (blockEntity != null)
                                            return blockEntity.getPersistentData().getDouble(tag);
                                        return -1;
                                    }
                                }.getValue(world, BlockPos.containing(x, y, z), "Grindleft")) - 1));
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = INPUT_SLOT;
                                final int _amount = 1;
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable) {
                                        ItemStack _stk = capability.getStackInSlot(_slotid).copy();
                                        _stk.shrink(_amount);
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
                                    }
                                });
                            }
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = OUTPUT_SLOT;
                                final ItemStack _setstack = new ItemStack(ModItems.WOODFIBER.get());
                                _setstack.setCount((int) (new Object() {
                                    public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                                        AtomicInteger _retval = new AtomicInteger(0);
                                        BlockEntity _ent = world.getBlockEntity(pos);
                                        if (_ent != null)
                                            _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                                        return _retval.get();
                                    }
                                }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) + FIBER));
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable)
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
                                });
                            }
                        }
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindtime", 0);
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                    }
//Wool to string
                    else if ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), INPUT_SLOT)).is(ItemTags.WOOL)  && new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) <= MAXOUTPUT - FIVE && ((new Object() {
                        public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
                            return _retval.get();
                        }
                    }.getItemStack(world, BlockPos.containing(x, y, z), OUTPUT_SLOT)).getItem() == Items.STRING || new Object() {
                        public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                            AtomicInteger _retval = new AtomicInteger(0);
                            BlockEntity _ent = world.getBlockEntity(pos);
                            if (_ent != null)
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                            return _retval.get();
                        }
                    }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) == 0)) {
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindleft", ((new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                        BlockEntity blockEntity = world.getBlockEntity(pos);
                                        if (blockEntity != null)
                                            return blockEntity.getPersistentData().getDouble(tag);
                                        return -1;
                                    }
                                }.getValue(world, BlockPos.containing(x, y, z), "Grindleft")) - 1));
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = INPUT_SLOT;
                                final int _amount = 1;
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable) {
                                        ItemStack _stk = capability.getStackInSlot(_slotid).copy();
                                        _stk.shrink(_amount);
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
                                    }
                                });
                            }
                        }
                        {
                            BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
                            if (_ent != null) {
                                final int _slotid = OUTPUT_SLOT;
                                final ItemStack _setstack = new ItemStack(Items.STRING);
                                _setstack.setCount((int) (new Object() {
                                    public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
                                        AtomicInteger _retval = new AtomicInteger(0);
                                        BlockEntity _ent = world.getBlockEntity(pos);
                                        if (_ent != null)
                                            _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
                                        return _retval.get();
                                    }
                                }.getAmount(world, BlockPos.containing(x, y, z), OUTPUT_SLOT) + FIVE));
                                _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                                    if (capability instanceof IItemHandlerModifiable)
                                        ((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
                                });
                            }
                        }
                        if (!world.isClientSide()) {
                            BlockPos _bp = BlockPos.containing(x, y, z);
                            BlockEntity _blockEntity = world.getBlockEntity(_bp);
                            BlockState _bs = world.getBlockState(_bp);
                            if (_blockEntity != null)
                                _blockEntity.getPersistentData().putDouble("Grindtime", 0);
                            if (world instanceof Level _level)
                                _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                    }
//End Statment
        }
            } else {
                if (!world.isClientSide()) {
                    BlockPos _bp = BlockPos.containing(x, y, z);
                    BlockEntity _blockEntity = world.getBlockEntity(_bp);
                    BlockState _bs = world.getBlockState(_bp);
                    if (_blockEntity != null)
                        _blockEntity.getPersistentData().putDouble("Grindtime", 0);
                    if (world instanceof Level _level)
                        _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                    if (_bs.getBlock().getStateDefinition().getProperty("working") instanceof BooleanProperty _booleanProp)
                        world.setBlock(_bp, _bs.setValue(_booleanProp, false), 3);
                }
            }
        }
    }
}