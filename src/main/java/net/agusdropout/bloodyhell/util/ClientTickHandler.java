package net.agusdropout.bloodyhell.util;

    public final class ClientTickHandler {

        private ClientTickHandler() {}

        public static int ticksInGame = 0;
        public static float partialTicks = 0;

        public static float total() {
            return ticksInGame + partialTicks;
        }

        public static void renderTick(float renderTickTime) {
            partialTicks = renderTickTime;
        }


    }

