package net.agusdropout.firstmod.client;

import net.agusdropout.firstmod.thirst.PlayerThirst;

public class ClientThirstData {
    private static int playerThirst;
    public static void set(int thirst){
        ClientThirstData.playerThirst = thirst;
    }
    public static int  getPlayerThirst() {
        return playerThirst;
    }
}
