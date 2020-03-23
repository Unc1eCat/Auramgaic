package mod.unclecat.uc_auramagic.content.block.content;

import mod.unclecat.uc_auramagic.content.ItemGroupAurmagic;
import mod.unclecat.uc_auramagic.content.ColorHandler.IDynamicColor;
import mod.unclecat.uc_auramagic.content.block.ModBlock;
import mod.unclecat.uc_auramagic.content.experience_gem.EnumExperienceColor;
import net.minecraft.block.Blocks;

public class BlockExperienceBlock extends ModBlock implements IDynamicColor
{
	public EnumExperienceColor kind;
	
	
	public BlockExperienceBlock(EnumExperienceColor kind)
	{
		super(kind.appendToName("experience_block"), Properties.from(Blocks.EMERALD_BLOCK).lightValue(6), ItemGroupAurmagic.INSTANCE, null);
		
		this.kind = kind;
	}
}
