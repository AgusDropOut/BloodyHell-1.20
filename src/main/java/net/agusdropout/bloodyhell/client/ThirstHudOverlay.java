package net.agusdropout.bloodyhell.client;

//public class ThirstHudOverlay {
    //private static final ResourceLocation FILLED_THIRST = new ResourceLocation(BloodyHell.MODID,
    //        "textures/thirst/filled_thirst.png");
    //private static final ResourceLocation EMPTY_THIRST = new ResourceLocation(BloodyHell.MODID,
    //        "textures/thirst/empty_thirst.png");
//
    //public static IGuiOverlay HUD_THIRST = ((gui, poseStack, partialTick, width, height) -> {
    //    int x = width / 2;
    //    int y = height;
//
    //    RenderSystem.setShader(GameRenderer::getPositionTexShader);
    //    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//
    //    for (int i = 0; i < 10; i++) {
    //        int xPos = x - 94 + (i * 9);
    //        int yPos = y - 54;
//
    //        // Dibuja el fondo de sed
    //        RenderSystem.setShaderTexture(0, EMPTY_THIRST);
    //        GameRenderer.blit(poseStack, xPos, yPos, 0, 0, 12, 12, 12, 12);
//
    //        // Dibuja el nivel de sed actual
    //        RenderSystem.setShaderTexture(0, FILLED_THIRST);
    //        if (ClientThirstData.getPlayerThirst() > i) {
    //            GameRenderer.blit(poseStack, xPos, yPos, 0, 0, 12, 12, 12, 12);
    //        } else {
    //            break;  // Si el nivel de sed es menor o igual a 'i', no es necesario dibujar más.
    //        }
    //    }
    //});
//}//