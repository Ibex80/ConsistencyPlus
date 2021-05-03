package net.consistencyteam.consistency_plus.mixin;

import net.consistencyteam.consistency_plus.base.HasUngrownVariant;
import net.consistencyteam.consistency_plus.base.IsSpreadableGrassBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

import static net.consistencyteam.consistency_plus.ConsistencyPlus.DIRT_ID;
import static net.minecraft.block.Blocks.DIRT;

@Mixin(GrassBlock.class)
public abstract class GrassBlockMixin extends SpreadableBlock implements HasUngrownVariant, IsSpreadableGrassBlock {
	protected GrassBlockMixin(Settings settings) {
		super(settings);
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		grow(state, world, pos, random);
	}
	
	@Override
	public BlockState getUngrownVariant(World world, BlockPos pos) {
		return Registry.BLOCK.get(DIRT_ID).getDefaultState();
	}
}
