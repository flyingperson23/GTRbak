package gtr.common.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import gtr.common.asm.util.ObfMapping;
import gtr.common.asm.util.TargetClassVisitor;

import java.util.function.Function;

public class SpecialArmorClassVisitor extends TargetClassVisitor {

    public static final String CACHED_TOUGHNESS_FIELD_NAME = "gtr__cachedToughness";
    public static final String CACHED_TOTAL_ARMOR_FIELD_NAME = "gtr__cachedTotalArmor";

    public SpecialArmorClassVisitor(ClassVisitor cv, ObfMapping methodKey, Function<MethodVisitor, MethodVisitor> visitorCreator) {
        super(cv, methodKey, visitorCreator);
    }

    @Override
    public void visitEnd() {
        visitField(Opcodes.ACC_STATIC | Opcodes.ACC_PRIVATE, CACHED_TOUGHNESS_FIELD_NAME, "F", null, null);
        visitField(Opcodes.ACC_STATIC | Opcodes.ACC_PRIVATE, CACHED_TOTAL_ARMOR_FIELD_NAME, "F", null, null);
        super.visitEnd();
    }
}
