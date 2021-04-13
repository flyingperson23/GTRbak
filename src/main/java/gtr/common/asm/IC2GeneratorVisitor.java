package gtr.common.asm;

import gtr.api.util.GTLog;
import gtr.common.asm.util.ObfMapping;
import net.minecraft.server.management.PlayerInteractionManager;
import org.apache.logging.log4j.Level;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
/*
public class IC2GeneratorVisitor extends MethodVisitor implements Opcodes {

    public IC2GeneratorVisitor(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    public static byte[] patchTileEntityBaseGenerator(byte[] basicClass) {
        System.out.println("aaaaaa222222");
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(basicClass);
        System.out.println("33333333333");

        classReader.accept(classNode, 0);
        System.out.println("44444444444");

        //GTLog.logger.log(Level.DEBUG, "Found TileEntityBaseGenerator Class: " + classNode.name);

        System.out.println("55555555");

        MethodNode updateEntityServer = null;
        System.out.println("77777777");

        for (MethodNode mn : classNode.methods) {
            if (mn.name.contains("updateEntityServer")) {
                updateEntityServer = mn;
            }
        }

        if (updateEntityServer == null) System.out.println("ABBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");

        if (updateEntityServer != null) {
            System.out.println("CCCCCCCCCCCCCCCCCCC");
            //GTLog.logger.log(Level.DEBUG, " - Found updateEntityServer");

            InsnList startInsert = new InsnList();
            System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDD");

            startInsert.add(new VarInsnNode(ALOAD, 0));
            System.out.println("EWEEEEEEEE");

            startInsert.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "gtr/common/asm/IC2Hooks", "emitGTEU", "(Lic2/core/block/generator/tileentity/TileEntityBaseGenerator;)V", false));
            System.out.println("FFFFFFF");

            updateEntityServer.instructions.insert(startInsert);
            System.out.println("GGGGGGGG");
        }

        CustomClassWriter writer = new CustomClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        System.out.println("HHHHHH");

        classNode.accept(writer);
        System.out.println("IIIIII");

        return writer.toByteArray();
    }

}
*/

public class IC2GeneratorVisitor extends MethodVisitor implements Opcodes {

    private static final String ARMOR_HOOKS_OWNER = "gtr/common/asm/IC2Hooks";
    private static final String ARMOR_HOOKS_SIGNATURE = "(Lic2/core/block/generator/tileentity/TileEntityBaseGenerator;)V";
    private static final String ARMOR_HOOKS_METHOD_NAME = "updateEntityServer";

    public static final String TARGET_CLASS_NAME = "ic2/core/block/generator/tileentity/TileEntityBaseGenerator";

    public static final ObfMapping TARGET_METHOD = new ObfMapping(TARGET_CLASS_NAME, "updateEntityServer", "(Lic2/core/block/generator/tileentity/TileEntityBaseGenerator;)V");

    public IC2GeneratorVisitor(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }
    
    @Override
    public void visitInsn(int opcode) {
        if (opcode == Opcodes.RETURN) {
            super.visitVarInsn(ALOAD, 0); //this
            super.visitMethodInsn(INVOKESTATIC, ARMOR_HOOKS_OWNER, ARMOR_HOOKS_METHOD_NAME, ARMOR_HOOKS_SIGNATURE, false);
        }
        super.visitInsn(opcode);
    }
}
