package gtr.api.pipenet.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBlockPipe<PipeType extends Enum<PipeType> & IPipeType<NodeDataType>, NodeDataType> extends ItemBlock {

    protected final BlockPipe<PipeType, NodeDataType, ?> blockPipe;

    public ItemBlockPipe(BlockPipe<PipeType, NodeDataType, ?> block) {
        super(block);
        this.blockPipe = block;
        setHasSubtypes(true);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        //if (blockPipe == null) System.out.println("null");
        //else if (worldIn.getBlockState(pos).getBlock() instanceof BlockPipe) {
        //    worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(BlockPipe.CONNECT_TO, facing.getOpposite()).withProperty(BlockPipe.SHOULD_CONNECT, true));
        //    System.out.println(pos);
        //    worldIn.notifyBlockUpdate(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 3);
        //    System.out.println(facing);
        //} else {
        //    System.out.println("asldfjlaskdfjklasdjf");
        //}
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }
}
