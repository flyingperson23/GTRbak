package gtr.common.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class IC2GeneratorVisitor extends MethodVisitor implements Opcodes {

    public IC2GeneratorVisitor(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    public static byte[] patchTileEntityBaseGenerator(byte[] basicClass) {
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(basicClass);
        classReader.accept(classNode, 0);
        MethodNode updateEntityServer = null;
        for (MethodNode mn : classNode.methods) {
            if (mn.name.contains("updateEntityServer")) {
                updateEntityServer = mn;
            }
        }
        if (updateEntityServer != null) {
            InsnList startInsert = new InsnList();
            startInsert.add(new VarInsnNode(ALOAD, 0));
            startInsert.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "gtr/common/asm/IC2Hooks", "emitGTEU", "(Lic2/core/block/generator/tileentity/TileEntityBaseGenerator;)V", false));
            updateEntityServer.instructions.insertBefore(updateEntityServer.instructions.get(0), startInsert);
        }

        CustomClassWriter writer = new CustomClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);

        return writer.toByteArray();
    }

    public static byte[] patchTileEntityElectric(byte[] basicClass) {
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(basicClass);
        classReader.accept(classNode, 0);
        MethodNode updateEntityServer = null;
        for (MethodNode mn : classNode.methods) {
            if (mn.name.contains("updateEntityServer")) {
                updateEntityServer = mn;
            }
        }
        if (updateEntityServer != null) {
            InsnList startInsert = new InsnList();
            startInsert.add(new VarInsnNode(ALOAD, 0));
            startInsert.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "gtr/common/asm/IC2Hooks", "emitGTEU2", "(Lic2/core/block/wiring/TileEntityElectricBlock;)V", false));
            updateEntityServer.instructions.insertBefore(updateEntityServer.instructions.get(0), startInsert);
        }

        CustomClassWriter writer = new CustomClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);

        return writer.toByteArray();
    }

}
