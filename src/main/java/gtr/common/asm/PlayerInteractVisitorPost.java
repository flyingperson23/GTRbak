package gtr.common.asm;

import gtr.api.util.GTLog;
import org.apache.logging.log4j.Level;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class PlayerInteractVisitorPost extends MethodVisitor implements Opcodes {


    public static final String TARGET_CLASS_NAME = "net/minecraft/server/management/PlayerInteractionManager";

    private static final String ARMOR_HOOKS_OWNER = "gtr/api/items/toolitem/ToolHooks";
    private static final String ARMOR_HOOKS_SIGNATURE = "()V";
    private static final String ARMOR_HOOKS_METHOD_NAME = "postHarvest";

    public PlayerInteractVisitorPost(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }


    @Override
    public void visitInsn(int opcode) {
        if (opcode == Opcodes.RETURN) {
            super.visitVarInsn(ALOAD, 0);
            super.visitVarInsn(ALOAD, 1);
            for (int i = 0; i < 7; i++) super.visitVarInsn(FLOAD, 2 + i);
            super.visitVarInsn(ALOAD, 9);
            super.visitMethodInsn(INVOKESTATIC, ARMOR_HOOKS_OWNER, ARMOR_HOOKS_METHOD_NAME, ARMOR_HOOKS_SIGNATURE, false);
        }
        super.visitInsn(opcode);
    }






    public static byte[] patchPlayerInteractionManager(byte[] basicClass)
    {
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(basicClass);
        classReader.accept(classNode, 0);
        GTLog.logger.log(Level.INFO, "Found PlayerInteractionManager Class: " + classNode.name);

        MethodNode tryHarvestBlock = null;

        for (MethodNode mn : classNode.methods)
        {
            if (mn.name.equals(MCPNames.method("func_180237_b")))
            {
                tryHarvestBlock = mn;
            }
        }

        if (tryHarvestBlock != null)
        {
            GTLog.logger.log(Level.INFO, " - Found tryHarvestBlock");

            InsnList startInsert = new InsnList();
            startInsert.add(new VarInsnNode(ALOAD, 0));
            startInsert.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "gtr/api/items/toolitem/ToolHooks", "preHarvest", "(Lnet/minecraft/server/management/PlayerInteractionManager;)V", false));

            tryHarvestBlock.instructions.insert(startInsert);

            for (int i = 0; i < tryHarvestBlock.instructions.size(); i++)
            {
                AbstractInsnNode ain = tryHarvestBlock.instructions.get(i);

                if (ain.getOpcode() == Opcodes.IRETURN)
                {
                    InsnList endInsert = new InsnList();
                    endInsert.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "gtr/api/items/toolitem/ToolHooks", "postHarvest", "()V", false));

                    tryHarvestBlock.instructions.insertBefore(ain, endInsert);
                    i += 1;
                }
            }
        }

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);

        return writer.toByteArray();
    }






}
