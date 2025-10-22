package net.agusdropout.bloodyhell.entity.client;

import net.agusdropout.bloodyhell.block.entity.StarLampBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;
import org.joml.Matrix4f;
import com.mojang.math.Axis;

public class StarLampRenderer implements BlockEntityRenderer<StarLampBlockEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("bloodyhell:textures/block/star_lamp.png");

    public StarLampRenderer(BlockEntityRendererProvider.Context ctx) {}

    // =================================================================
    // >>> PARÁMETROS DE AJUSTE DE LA ESTRELLA <<<
    // =================================================================
    private static final float ROTATION_SPEED = 0.02f;
    private static final float PEAK_PULSE_AMOUNT = 0.1f;
    private static final float PEAK_PULSE_SPEED = 0.5f;
    // =================================================================

    @Override
    public void render(StarLampBlockEntity entity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        final float PEAK_BASE_SCALE = entity.getStarPoints(); // control de punta
        float time = (Minecraft.getInstance().level.getGameTime() + partialTick) * ROTATION_SPEED;

        poseStack.pushPose();
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.mulPose(Axis.YP.rotation(time));
        poseStack.mulPose(Axis.XP.rotation(time * 0.7f));
        poseStack.scale(entity.getScale(), entity.getScale(), entity.getScale());

        Matrix4f matrix = poseStack.last().pose();
        VertexConsumer vertex = bufferSource.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));

        Vec3[] baseVerts = getIcosahedronVertices();
        int[][] faces = getIcosahedronFaces();

        // --- 1. Calcular Vértices de Picos ---
        Vec3[] tips = new Vec3[baseVerts.length];
        for (int i = 0; i < baseVerts.length; i++) {
            float scale = PEAK_BASE_SCALE + (float)Math.abs(Math.sin(time * PEAK_PULSE_SPEED + i * 1.5f)) * PEAK_PULSE_AMOUNT;
            tips[i] = baseVerts[i].scale(scale);
        }

        // --- 2. Render de caras facetadas con doble cara ---
        for (int[] f : faces) {
            Vec3 v1_base = baseVerts[f[0]];
            Vec3 v2_base = baseVerts[f[1]];
            Vec3 v3_base = baseVerts[f[2]];

            Vec3 v1_tip = tips[f[0]];
            Vec3 v2_tip = tips[f[1]];
            Vec3 v3_tip = tips[f[2]];

            // Triángulos estelados
            addFaceDouble(vertex, matrix, v1_base, v2_base, v3_tip);
            addFaceDouble(vertex, matrix, v2_base, v3_base, v1_tip);
            addFaceDouble(vertex, matrix, v3_base, v1_base, v2_tip);

            // Cara base (opcional, invertida)
            addFaceDouble(vertex, matrix, v1_base, v2_base, v3_base);
        }

        poseStack.popPose();
    }

    // =================================================================
    // >>> MÉTODOS AUXILIARES <<<
    // =================================================================
    private void addFaceDouble(VertexConsumer vertex, Matrix4f matrix, Vec3 a, Vec3 b, Vec3 c) {
        Vec3 normal = computeNormal(a, b, c);

        // Triángulo frontal
        addVertex(vertex, matrix, a, normal, 0f, 0f);
        addVertex(vertex, matrix, b, normal, 1f, 0f);
        addVertex(vertex, matrix, c, normal, 0.5f, 1f);

        // Triángulo trasero
        Vec3 backNormal = normal.scale(-1);
        addVertex(vertex, matrix, c, backNormal, 0.5f, 1f);
        addVertex(vertex, matrix, b, backNormal, 1f, 0f);
        addVertex(vertex, matrix, a, backNormal, 0f, 0f);
    }

    private Vec3 computeNormal(Vec3 v1, Vec3 v2, Vec3 v3) {
        Vec3 edge1 = v2.subtract(v1);
        Vec3 edge2 = v3.subtract(v1);
        return edge1.cross(edge2).normalize();
    }

    private void addVertex(VertexConsumer vertex, Matrix4f matrix, Vec3 pos, Vec3 normal, float u, float v) {
        vertex.vertex(matrix, (float) pos.x, (float) pos.y, (float) pos.z)
                .color(255, 255, 180, 200)
                .uv(u, v)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(0xF000F0)
                .normal((float) normal.x, (float) normal.y, (float) normal.z)
                .endVertex();
    }

    private Vec3[] getIcosahedronVertices() {
        float t = (float)((1.0 + Math.sqrt(5.0))/2.0);
        float s = 1.0f / (float)Math.sqrt(1 + t*t);
        t *= s;
        return new Vec3[]{
                new Vec3(-s,t,0), new Vec3(s,t,0), new Vec3(-s,-t,0), new Vec3(s,-t,0),
                new Vec3(0,-s,t), new Vec3(0,s,t), new Vec3(0,-s,-t), new Vec3(0,s,-t),
                new Vec3(t,0,-s), new Vec3(t,0,s), new Vec3(-t,0,-s), new Vec3(-t,0,s)
        };
    }

    private int[][] getIcosahedronFaces() {
        return new int[][]{
                {0,11,5},{0,5,1},{0,1,7},{0,7,10},{0,10,11},
                {1,5,9},{5,11,4},{11,10,2},{10,7,6},{7,1,8},
                {3,9,4},{3,4,2},{3,2,6},{3,6,8},{3,8,9},
                {4,9,5},{2,4,11},{6,2,10},{8,6,7},{9,8,1}
        };
    }

    @Override
    public boolean shouldRenderOffScreen(StarLampBlockEntity entity) {
        return true;
    }
}
