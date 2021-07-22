package mod.unclecat.uc_auramagic.content.item.content;

import javax.annotation.Nullable;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.ItemGroupAurmagic;
import mod.unclecat.uc_auramagic.content.block.content.UnderInstrumentsConstructionBlock;
import mod.unclecat.uc_auramagic.content.item.ModItem;
import mod.unclecat.uc_auramagic.content.multiblock.Multiblocks;
import mod.unclecat.uc_auramagic.content.multiblock.creators.instrument_work.InstrumentWorkMultiblockCreationTrigger;
import mod.unclecat.uc_auramagic.content.tile_entities.content.UnderInstrumentsConstructionTileEntity;
import mod.unclecat.uc_auramagic.util.helpers.FXHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InstrumentItem extends ModItem
{
	public InstrumentItem(String name, ItemGroup group, @Nullable Properties properties)
	{
		super(name, group, properties);
	}
	
	public InstrumentItem(String name, Properties properties)
	{
		this(name, ItemGroupAurmagic.INSTANCE, properties);
	}
	
	
	// Overrider this instead
	public ActionResultType onItemUseDecorated(ItemUseContext c)
	{
		return ActionResultType.PASS;
	}
	
	// Don't override this in common case
	@Override
	public ActionResultType onItemUse(ItemUseContext c)
	{	
		try 
		{
			World world = c.getWorld();
			BlockPos pos = c.getPos();
			InstrumentWorkMultiblockCreationTrigger trig = new InstrumentWorkMultiblockCreationTrigger();
			TileEntity te = world.getTileEntity(pos);
			BlockState state = world.getBlockState(pos);
			
			trig.clickedPos = pos;
			trig.player = c.getPlayer();
			
			if (te != null && te instanceof UnderInstrumentsConstructionTileEntity) // It's already the under construction block
			{
				((UnderInstrumentsConstructionTileEntity)te).clickedInstrumentsSequence.add((InstrumentItem) c.getPlayer().getHeldItem(c.getHand()).getItem());	
				trig.clickedInstrumentsSquence = ((UnderInstrumentsConstructionTileEntity)te).clickedInstrumentsSequence;		
				trig = Multiblocks.triggerCreation(trig);
				
				if (trig.wrong && Auramagic.RANDOM.nextInt(8) <= 0) // It breaks
				{
					if (!world.isRemote) world.setBlockState(pos, Blocks.AIR.getDefaultState());
					
					world.playSound(pos.getX(), pos.getY(), pos.getZ(), state.getSoundType().getBreakSound()	, SoundCategory.BLOCKS, 1.0f, 1.0f, true);
					//FXHelper.spawnComplexParticles(new BlockParticleData(ParticleTypes.BLOCK, state), 30, 50, pos.getX(), 1.0, pos.getY(), 1.0, pos.getZ(), 1.0, -0.5, 0.5, -0.5, 0.5, -0.5, 0.5, world, false);
					c.getItem().damageItem(1, c.getPlayer(), (entity) -> {}); // It gets double damage: first time here and second time in the end of the method
				}
				else // Ur lucky
				{
					world.playSound(pos.getX(), pos.getY(), pos.getZ(), state.getSoundType().getHitSound(), SoundCategory.BLOCKS, 1.0f, 1.0f, true);
					if (world.isRemote) for (int i = 0; i < 20; i++) world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, state), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0, 0, 0);
				}
			}
			else // It's not the under construction block still
			{
				trig.clickedInstrumentsSquence.add((InstrumentItem) c.getPlayer().getHeldItem(c.getHand()).getItem());	
				trig = Multiblocks.triggerCreation(trig);
				
				if (trig.wrong) // Execute whatever this item should do not creating multiblock
				{
					System.out.println("hhhhhhh");
					return onItemUseDecorated(c);
				}
				else // Start building multiblock
				{
					System.out.println("fffffff");
					world.setBlockState(pos, UnderInstrumentsConstructionBlock.fromMaterial(world.getBlockState(pos).getMaterial()).getDefaultState());
					if (!world.isRemote) ((UnderInstrumentsConstructionTileEntity)world.getTileEntity(pos)).clickedInstrumentsSequence.add((InstrumentItem) c.getPlayer().getHeldItem(c.getHand()).getItem());	
					if (!world.isRemote) trig.clickedInstrumentsSquence = ((UnderInstrumentsConstructionTileEntity)te).clickedInstrumentsSequence;	
					
					world.playSound(pos.getX(), pos.getY(), pos.getZ(), state.getSoundType().getHitSound(), SoundCategory.BLOCKS, 1.0f, 1.0f, true);
					if (world.isRemote) for (int i = 0; i < 20; i++) world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, state), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0, 0, 0);
				}
			}
			
			c.getItem().damageItem(1, c.getPlayer(), (entity) -> { }); // Decorated variant must damage the item itself
			return ActionResultType.SUCCESS;
			}
		catch (Exception e)
		{
			return ActionResultType.PASS;
		}
	}
}
