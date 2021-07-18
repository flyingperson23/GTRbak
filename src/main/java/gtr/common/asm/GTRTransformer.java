package gtr.common.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import gtr.common.asm.util.*;

public class GTRTransformer implements IClassTransformer, Opcodes {

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        String internalName = transformedName.replace('.', '/');
        if (internalName.equals(LayerArmorBaseVisitor.TARGET_CLASS_NAME)) {
            ClassReader classReader = new ClassReader(basicClass);
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            classReader.accept(new TargetClassVisitor(classWriter, LayerArmorBaseVisitor.TARGET_METHOD, LayerArmorBaseVisitor::new), 0);
            return classWriter.toByteArray();
        }
        if (internalName.equals(LayerCustomHeadVisitor.TARGET_CLASS_NAME)) {
            ClassReader classReader = new ClassReader(basicClass);
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            classReader.accept(new TargetClassVisitor(classWriter, LayerCustomHeadVisitor.TARGET_METHOD, LayerCustomHeadVisitor::new), 0);
            return classWriter.toByteArray();
        }
        if (internalName.equals(SpecialArmorApplyVisitor.TARGET_CLASS_NAME)) {
            ClassReader classReader = new ClassReader(basicClass);
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            classReader.accept(new SpecialArmorClassVisitor(classWriter, SpecialArmorApplyVisitor.TARGET_METHOD, SpecialArmorApplyVisitor::new), 0);
            return classWriter.toByteArray();
        }

        if (transformedName.equals("net.minecraft.server.management.PlayerInteractionManager")) {
            return PlayerInteractVisitorPost.patchPlayerInteractionManager(basicClass);
        }

        if (transformedName.equals("ic2.core.block.generator.tileentity.TileEntityBaseGenerator")) {
            return IC2GeneratorVisitor.patchTileEntityBaseGenerator(basicClass);
        }
        if (transformedName.equals("ic2.core.block.wiring.TileEntityElectricBlock")) {
            return IC2GeneratorVisitor.patchTileEntityElectric(basicClass);
        }
        if (transformedName.equals("ic2.core.item.ItemBatteryChargeHotbar")) {
            return IC2BatteryVisitor.patchTileEntityBaseGenerator(basicClass);
        }
        if (internalName.equals(JEIVisitor.TARGET_CLASS_NAME)) {
            ClassReader classReader = new ClassReader(basicClass);
            ClassWriter classWriter = new ClassWriter(0);
            classReader.accept(new TargetClassVisitor(classWriter, JEIVisitor.TARGET_METHOD, JEIVisitor::new), 0);
            return classWriter.toByteArray();
        }
        if (internalName.equals(SaveFormatOldLoadVisitor.TARGET_CLASS_NAME)) {
            ClassReader classReader = new ClassReader(basicClass);
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            classReader.accept(new TargetClassVisitor(classWriter, SaveFormatOldLoadVisitor.TARGET_METHOD, SaveFormatOldLoadVisitor::new), 0);
            return classWriter.toByteArray();
        }
        if (internalName.equals(CompoundDataFixerGetVersionVisitor.TARGET_CLASS_NAME)) {
            ClassReader classReader = new ClassReader(basicClass);
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            classReader.accept(new TargetClassVisitor(classWriter, CompoundDataFixerGetVersionVisitor.TARGET_METHOD, CompoundDataFixerGetVersionVisitor::new), 0);
            return classWriter.toByteArray();
        }

        return basicClass;
    }

}
