package gtr.integration.multi.crafttweaker.expanders;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.block.IBlockState;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IBlockInfo;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IICubeRenderer;
import gtr.integration.multi.crafttweaker.predicate.IBlockMatcher;
import stanhebben.zenscript.annotations.ZenCaster;
import stanhebben.zenscript.annotations.ZenExpansion;

@ZenExpansion("crafttweaker.block.IBlockState")
@ZenRegister
public class ExpandState {

    @ZenCaster
    public static IBlockMatcher asIBlockMatcher(IBlockState self) {
        return IBlockMatcher.statePredicate(self);
    }

    @ZenCaster
    public static IBlockInfo asIBlockInfo(IBlockState self) {
        return IBlockInfo.fromState(self);
    }

    @ZenCaster
    public static IICubeRenderer asIICubeRenderer(IBlockState self) {
        return IICubeRenderer.fromState(self);
    }

}
