package net.agusdropout.bloodyhell.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class Ritual {
    protected BlockState blockState;
    protected Level level;
    protected BlockPos blockPos;
    protected Player player;
    protected InteractionHand interactionHand;
    protected BlockHitResult blockHitResult;
    protected List<List<Item>> itemsInput;
    protected List<List<Item>> itemsNeeded;
    public Ritual(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult,List<List<Item>> itemsInput) {
        this.blockState = blockState;
        this.level = level;
        this.blockPos = blockPos;
        this.player = player;
        this.interactionHand = interactionHand;
        this.blockHitResult = blockHitResult;
        this.itemsInput = itemsInput;
    }

    //public boolean isRitualReady() {
    //    if (itemsInput.contains(itemsNeeded)){
    //        return true;
    //    }
    //    return false;
    //}

    public boolean performRitual(){
        if (isRitualReady()){
            applyResults();
            return true;
        }
        return false;
    };

    public abstract void applyResults();

    public boolean isRitualReady() {
        List<List<Item>> sortedInput = sortLists(itemsInput);
        List<List<Item>> sortedNeeded = sortLists(itemsNeeded);

        return sortedInput.equals(sortedNeeded); // Verifica igualdad exacta
    }

    // Método auxiliar para ordenar cada pilar y luego ordenar la lista de pilares
    private List<List<Item>> sortLists(List<List<Item>> listOfLists) {
        List<List<Item>> sorted = new ArrayList<>();

        for (List<Item> sublist : listOfLists) {
            List<Item> sortedSublist = new ArrayList<>(sublist);
            Collections.sort(sortedSublist, Comparator.comparing(Item::toString)); // Ordenamos cada pilar
            sorted.add(sortedSublist);
        }

        sorted.sort(Comparator.comparing(Object::toString)); // Ordenamos la lista de pilares
        return sorted;
    }




}
