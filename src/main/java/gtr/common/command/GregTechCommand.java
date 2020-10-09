package gtr.common.command;

import com.google.common.collect.Lists;
import gtr.common.command.util.CommandUtil;
import gtr.common.command.worldgen.CommandWorldgen;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;

import java.util.List;

public class GregTechCommand extends CommandTreeBase {

    public GregTechCommand() {
        addSubcommand(new CommandWorldgen());
        addSubcommand(new CommandUtil());
    }

    @Override
    public String getName() {
        return "gtr";
    }

    @Override
    public List<String> getAliases() {
        return Lists.newArrayList("gt");
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "gtr.command.usage";
    }
}
