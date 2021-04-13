package gtr.common.render;

import codechicken.lib.render.CCModel;
import codechicken.lib.vec.*;
import gtr.api.util.GTUtility;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3i;

public class ShapeModelGenerator {

    public static CCModel[] generateFullBlockVariants(CCModel originalModel) {
        CCModel[] result = new CCModel[3];
        for (int i = 0; i < 3; i++) {
            Transformation rotation = Rotation.sideRotations[i * 2].at(Vector3.center);
            CCModel rotatedModel = originalModel.copy().apply(rotation);
            result[i] = rotatedModel;
        }
        return result;
    }

    public static CCModel[] generateRotatedVariants(CCModel originalModel) {
        CCModel[] result = new CCModel[6];
        for (int i = 0; i < 6; i++) {
            Transformation rotation = Rotation.sideRotations[i].at(Vector3.center);

            Vec3i v = EnumFacing.VALUES[i].getDirectionVec();
            Transformation test = new Rotation(Math.toRadians(45), v.getX(), v.getY(), v.getZ());
            Transformation test2 = new Translation(GTUtility.getPipeTranslation(EnumFacing.VALUES[i]));
            CCModel rotatedModel = originalModel.copy().apply(rotation).apply(test).apply(test2);

            result[i] = rotatedModel;
        }
        return result;
    }

    public static CCModel generateModel(int angles, double height, double radius, double segmentHeight) {
        int amountOfSegments = (int) Math.ceil(height / segmentHeight);
        CCModel initialModel = CCModel.quadModel(angles * 4 * amountOfSegments);
        double radiansPerAngle = (Math.PI * 2) / (angles * 1.0);
        for (int i = 0; i < angles; i++) {
            Vector3 first = generatePoint(radiansPerAngle, i, radius);
            Vector3 second = generatePoint(radiansPerAngle, i + 1, radius);
            Vector3 firstTop = first.copy().add(0.0, segmentHeight, 0.0);
            Vector3 secondTop = second.copy().add(0.0, segmentHeight, 0.0);
            double width = first.copy().subtract(second).mag();
            double heightLeft = height;
            for (int j = 0; j < amountOfSegments; j++) {
                double actualHeight = firstTop.y - first.y;
                double textureHeight = 1.0 * (actualHeight / segmentHeight);
                double textureWidth = (textureHeight / actualHeight) * width;
                int offset = i * amountOfSegments * 4 + j * 4;
                initialModel.verts[offset] = new Vertex5(first.copy(), 0.0, 0.0);
                initialModel.verts[offset + 1] = new Vertex5(firstTop.copy(), 0.0, textureHeight);
                initialModel.verts[offset + 2] = new Vertex5(secondTop.copy(), textureWidth, textureHeight);
                initialModel.verts[offset + 3] = new Vertex5(second.copy(), textureWidth, 0.0);

                heightLeft -= actualHeight;
                double nextSegmentHeight = Math.min(segmentHeight, heightLeft);
                first.add(0.0, actualHeight, 0.0);
                second.add(0.0, actualHeight, 0.0);
                firstTop.y = first.y + nextSegmentHeight;
                secondTop.y = second.y + nextSegmentHeight;
            }
        }
        return initialModel.computeNormals();
    }

    private static Vector3 generatePoint(double anglePerNumber, int number, double radius) {
        double angle = anglePerNumber * number;
        double x = 0.5 + Math.cos(angle) * radius;
        double z = 0.5 + Math.sin(angle) * radius;
        return new Vector3(x, 0.0, z);
    }
}
