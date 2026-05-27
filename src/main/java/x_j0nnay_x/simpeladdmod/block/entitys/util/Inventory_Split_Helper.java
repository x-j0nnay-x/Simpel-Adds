package x_j0nnay_x.simpeladdmod.block.entitys.util;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

import java.util.ArrayList;
import java.util.List;

public final class Inventory_Split_Helper {

    private Inventory_Split_Helper() {
    }

    public static boolean rebalanceByItemGroups(ItemStacksResourceHandler stacks, int[] inputSlots) {
        List<InputGroup> groups = collectInputGroups(stacks, inputSlots);
        if (groups.isEmpty()) {
            return false;
        }

        if (!allocateSlotsPerGroup(groups, inputSlots.length)) {
            return false;
        }

        ItemStack[] target = new ItemStack[inputSlots.length];
        boolean[] used = new boolean[inputSlots.length];
        for (int i = 0; i < target.length; i++) {
            target[i] = ItemStack.EMPTY;
        }

        // Assign concrete slot indices, preferring the group's current slots.
        for (InputGroup group : groups) {
            int slotsNeeded = group.allocatedSlotCount;

            for (int relativeIndex : group.originalRelativeIndices) {
                if (slotsNeeded <= 0) break;
                if (!used[relativeIndex]) {
                    group.assignedRelativeIndices.add(relativeIndex);
                    used[relativeIndex] = true;
                    slotsNeeded--;
                }
            }

            for (int relativeIndex = 0; relativeIndex < inputSlots.length && slotsNeeded > 0; relativeIndex++) {
                if (!used[relativeIndex]) {
                    group.assignedRelativeIndices.add(relativeIndex);
                    used[relativeIndex] = true;
                    slotsNeeded--;
                }
            }

            if (slotsNeeded > 0) {
                return false;
            }
        }

        // Distribute items evenly in each group.
        for (InputGroup group : groups) {
            int slotCount = group.assignedRelativeIndices.size();
            if (slotCount <= 0) continue;

            int base = group.totalItemCount / slotCount;
            int remainder = group.totalItemCount % slotCount;

            for (int i = 0; i < slotCount; i++) {
                int relativeIndex = group.assignedRelativeIndices.get(i);
                int count = base + (i < remainder ? 1 : 0);
                target[relativeIndex] = count > 0 ? group.template.copyWithCount(count) : ItemStack.EMPTY;
            }
        }

        boolean changed = false;
        for (int relativeIndex = 0; relativeIndex < inputSlots.length; relativeIndex++) {
            int absoluteSlot = inputSlots[relativeIndex];
            int currentAmount = stacks.getAmountAsInt(absoluteSlot);
            ItemStack current = currentAmount > 0
                    ? stacks.getResource(absoluteSlot).toStack().copyWithCount(currentAmount)
                    : ItemStack.EMPTY;
            ItemStack desired = target[relativeIndex];

            if (!sameStackAndCount(current, desired)) {
                stacks.set(absoluteSlot, ItemResource.of(desired), desired.getCount());
                changed = true;
            }
        }

        return changed;
    }

    private static boolean allocateSlotsPerGroup(List<InputGroup> groups, int totalSlots) {
        int usedSlots = 0;

        // Minimum slots needed so no slot exceeds stack limit.
        for (InputGroup group : groups) {
            int minRequired = Math.max(1, ceilDiv(group.totalItemCount, group.stackLimitPerSlot));
            group.allocatedSlotCount = minRequired;
            usedSlots += minRequired;
        }

        if (usedSlots > totalSlots) {
            // Not enough slots to represent all items without overstacking.
            return false;
        }

        int remainingSlots = totalSlots - usedSlots;

        // Spread extra slots by highest current load.
        while (remainingSlots > 0) {
            InputGroup best = null;
            double bestLoad = -1.0;

            for (InputGroup group : groups) {
                int maxSpreadSlots = Math.min(totalSlots, group.totalItemCount); // each used slot should have >= 1 item
                if (group.allocatedSlotCount >= maxSpreadSlots) {
                    continue;
                }

                double load = (double) group.totalItemCount / (double) group.allocatedSlotCount;
                if (load > bestLoad) {
                    bestLoad = load;
                    best = group;
                }
            }

            if (best == null) {
                break;
            }

            best.allocatedSlotCount++;
            remainingSlots--;
        }

        return true;
    }

    private static int ceilDiv(int a, int b) {
        return (a + b - 1) / b;
    }

    private static List<InputGroup> collectInputGroups(ItemStacksResourceHandler stacks, int[] inputSlots) {
        List<InputGroup> groups = new ArrayList<>();

        for (int relativeIndex = 0; relativeIndex < inputSlots.length; relativeIndex++) {
            int absoluteSlot = inputSlots[relativeIndex];
            int amount = stacks.getAmountAsInt(absoluteSlot);
            if (amount <= 0) continue;

            ItemStack stack = stacks.getResource(absoluteSlot).toStack().copyWithCount(amount);
            InputGroup group = findMatchingGroup(groups, stack);
            if (group == null) {
                group = new InputGroup(stack.copyWithCount(1), stack.getMaxStackSize());
                groups.add(group);
            }

            group.totalItemCount += amount;
            group.originalRelativeIndices.add(relativeIndex);
        }

        return groups;
    }

    private static InputGroup findMatchingGroup(List<InputGroup> groups, ItemStack stack) {
        for (InputGroup group : groups) {
            if (ItemStack.isSameItemSameComponents(group.template, stack)) {
                return group;
            }
        }
        return null;
    }

    private static boolean sameStackAndCount(ItemStack a, ItemStack b) {
        if (a.isEmpty() && b.isEmpty()) return true;
        if (a.isEmpty() != b.isEmpty()) return false;
        return ItemStack.isSameItemSameComponents(a, b) && a.getCount() == b.getCount();
    }

    private static final class InputGroup {
        final ItemStack template;
        final int stackLimitPerSlot;

        int totalItemCount = 0;
        int allocatedSlotCount = 0;

        final List<Integer> originalRelativeIndices = new ArrayList<>();
        final List<Integer> assignedRelativeIndices = new ArrayList<>();

        InputGroup(ItemStack template, int stackLimitPerSlot) {
            this.template = template;
            this.stackLimitPerSlot = stackLimitPerSlot;
        }
    }
}
