package gtr.common.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class IC2CropsVisitor extends MethodVisitor implements Opcodes {

    public IC2CropsVisitor(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    public static byte[] patchTileEntityBaseGenerator(byte[] basicClass) {
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(basicClass);
        classReader.accept(classNode, 0);
        MethodNode registerCrops = null;
        MethodNode registerBaseSeeds = null;
        for (MethodNode mn : classNode.methods) {
            if (mn.name.contains("registerCrops")) {
                registerCrops = mn;
            } else if (mn.name.contains("registerBaseSeeds")) {
                registerBaseSeeds = mn;
            }
        }
        if (registerCrops != null) {
            InsnList startInsert = new InsnList();
            startInsert.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "gtr/common/asm/IC2Hooks", "registerCrops", "()V", false));
            startInsert.add(new InsnNode(RETURN));
            registerCrops.instructions.insertBefore(registerCrops.instructions.get(0), startInsert);
        }
        if (registerBaseSeeds != null) {
            InsnList startInsert = new InsnList();
            startInsert.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "gtr/common/asm/IC2Hooks", "registerBaseSeeds", "()V", false));
            startInsert.add(new InsnNode(RETURN));
            registerBaseSeeds.instructions.insertBefore(registerBaseSeeds.instructions.get(0), startInsert);
        }

        CustomClassWriter writer = new CustomClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);

        return writer.toByteArray();
    }

}
