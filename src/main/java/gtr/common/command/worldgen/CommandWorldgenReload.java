package gtr.common.command.worldgen;

import gtr.api.util.GTLog;
import gtr.api.worldgen.config.WorldGenRegistry;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import java.io.IOException;

public class CommandWorldgenReload extends CommandBase {

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "gtr.command.worldgen.reload.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        try {
            WorldGenRegistry.INSTANCE.reinitializeRegisteredVeins();
            sender.sendMessage(new TextComponentTranslation("gtr.command.worldgen.reload.success")
                .setStyle(new Style().setColor(TextFormatting.GREEN)));
        } catch (IOException | RuntimeException exception) {
            GTLog.logger.error("Failed to reload worldgen config", exception);
            sender.sendMessage(new TextComponentTranslation("gtr.command.worldgen.reload.failed")
                .setStyle(new Style().setColor(TextFormatting.RED)));
        }
    }
}
