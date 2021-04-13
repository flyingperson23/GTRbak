package gtr.common.asm;

import gtr.api.util.GTLog;
import org.apache.logging.log4j.Level;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class PlayerInteractVisitorPost extends MethodVisitor implements Opcodes {

    public PlayerInteractVisitorPost(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    public static byte[] patchPlayerInteractionManager(byte[] basicClass)
    {
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(basicClass);
        classReader.accept(classNode, 0);
        GTLog.logger.log(Level.DEBUG, "Found PlayerInteractionManager Class: " + classNode.name);

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
            GTLog.logger.log(Level.DEBUG, " - Found tryHarvestBlock");

            InsnList startInsert = new InsnList();
            startInsert.add(new VarInsnNode(ALOAD, 0));
            startInsert.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "gtr/api/items/toolitem/ToolHooks", "gtPreHarvest", "(Lnet/minecraft/server/management/PlayerInteractionManager;)V", false));

            tryHarvestBlock.instructions.insert(startInsert);

            for (int i = 0; i < tryHarvestBlock.instructions.size(); i++)
            {
                AbstractInsnNode ain = tryHarvestBlock.instructions.get(i);

                if (ain.getOpcode() == Opcodes.IRETURN)
                {
                    InsnList endInsert = new InsnList();
                    endInsert.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "gtr/api/items/toolitem/ToolHooks", "gtPostHarvest", "()V", false));

                    tryHarvestBlock.instructions.insertBefore(ain, endInsert);
                    i += 1;
                }
            }
        }

        CustomClassWriter writer = new CustomClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);

        return writer.toByteArray();
    }

}
