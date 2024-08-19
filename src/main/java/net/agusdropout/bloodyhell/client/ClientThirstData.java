package net.agusdropout.bloodyhell.client;

import net.agusdropout.bloodyhell.thirst.PlayerThirst;

public class ClientThirstData {
    private static int playerThirst;
    public static void set(int thirst){
        ClientThirstData.playerThirst = thirst;
    }
    public static int  getPlayerThirst() {
        return playerThirst;
    }
}
