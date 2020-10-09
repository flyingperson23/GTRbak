package gtr.common.asm;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.Name;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.SortingIndex;

import javax.annotation.Nullable;
import java.util.Map;

@Name("GTRLoadingPlugin")
@MCVersion(ForgeVersion.mcVersion)
@TransformerExclusions("gtr.common.asm.")
@SortingIndex(1001)
public class GTCELoadingPlugin implements IFMLLoadingPlugin {

    public static boolean IN_MCP = false;

    @Override
    public String[] getASMTransformerClass() {
        return new String[] {"gtr.common.asm.GTCETransformer"};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        IN_MCP = !(Boolean) data.get("runtimeDeobfuscationEnabled");
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
