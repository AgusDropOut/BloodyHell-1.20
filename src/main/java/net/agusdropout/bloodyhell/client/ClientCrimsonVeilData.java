package net.agusdropout.bloodyhell.client;

public class ClientCrimsonVeilData {
    private static int playerCrimsonVeil;
    public static void set(int crimsonVeil){
        ClientCrimsonVeilData.playerCrimsonVeil = crimsonVeil;
    }
    public static int  getPlayerCrimsonVeil() {
        return playerCrimsonVeil;
    }
}
