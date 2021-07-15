package gtr.common.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class IC2BatteryVisitor extends MethodVisitor implements Opcodes {

    public IC2BatteryVisitor(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    public static byte[] patchTileEntityBaseGenerator(byte[] basicClass) {
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(basicClass);
        classReader.accept(classNode, 0);
        MethodNode updateEntityServer = null;
        for (MethodNode mn : classNode.methods) {
            if (mn.name.contains("onUpdate") || mn.name.contains("func_77663_a")) {
                updateEntityServer = mn;
            }
        }
        if (updateEntityServer != null) {
            InsnList startInsert = new InsnList();
            startInsert.add(new VarInsnNode(ALOAD, 0));
            startInsert.add(new VarInsnNode(ALOAD, 1));
            startInsert.add(new VarInsnNode(ALOAD, 2));
            startInsert.add(new VarInsnNode(ALOAD, 3));

            startInsert.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "gtr/common/asm/IC2Hooks", "chargeHotbar", "(Lic2/core/item/ItemBatteryChargeHotbar;Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)V", false));

            boolean flag = true;
            for (int i = 0; i < updateEntityServer.instructions.size() && flag; i++) {
                if (updateEntityServer.instructions.get(i).getOpcode() == RETURN) {
                    updateEntityServer.instructions.insertBefore(updateEntityServer.instructions.get(i), startInsert);
                    flag = false;
                }
            }
        }

        CustomClassWriter writer = new CustomClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);

        return writer.toByteArray();
    }

}
