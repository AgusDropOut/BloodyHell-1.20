package net.agusdropout.bloodyhell.util.visuals;

import org.joml.Vector3f;

public class ColorHelper {
    public static Vector3f hexToVector3f(int hexColor) {
        float r = ((hexColor >> 16) & 0xFF) / 255.0f;
        float g = ((hexColor >> 8) & 0xFF) / 255.0f;
        float b = (hexColor & 0xFF) / 255.0f;
        return new Vector3f(r, g, b);
    }
    public static int vector3fToHex(Vector3f color) {
        int r = (int) (color.x() * 255.0f);
        int g = (int) (color.y() * 255.0f);
        int b = (int) (color.z() * 255.0f);
        return (r << 16) | (g << 8) | b;
    }
    public static Vector3f brighten(Vector3f color, float factor) {
        float r = Math.min(1.0f, color.x() * factor);
        float g = Math.min(1.0f, color.y() * factor);
        float b = Math.min(1.0f, color.z() * factor);
        return new Vector3f(r, g, b);
    }

    public static Vector3f blend(Vector3f color1, Vector3f color2) {
        float r = (float) Math.sqrt((color1.x() * color1.x() + color2.x() * color2.x()) / 2.0f);
        float g = (float) Math.sqrt((color1.y() * color1.y() + color2.y() * color2.y()) / 2.0f);
        float b = (float) Math.sqrt((color1.z() * color1.z() + color2.z() * color2.z()) / 2.0f);
        return new Vector3f(r, g, b);
    }

    public static Vector3f blendSimple(Vector3f color1, Vector3f color2) {
        float r = (color1.x() + color2.x()) / 2.0f;
        float g = (color1.y() + color2.y()) / 2.0f;
        float b = (color1.z() + color2.z()) / 2.0f;
        return new Vector3f(r, g, b);
    }



}