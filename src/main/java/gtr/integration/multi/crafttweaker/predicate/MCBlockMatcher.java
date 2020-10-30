package gtr.integration.multi.crafttweaker.predicate;

import gtr.api.multiblock.BlockWorldState;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IBlockWorldState;

import java.util.function.Predicate;

public class MCBlockMatcher implements IBlockMatcher {

    public final Predicate<BlockWorldState> predicate;

    public MCBlockMatcher(Predicate<BlockWorldState> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean test(IBlockWorldState state) {
        return predicate.test(state.getInternal());
    }

}
