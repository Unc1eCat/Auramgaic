package mod.unclecat.uc_auramagic.content.tile_entities.content;

import java.util.ArrayList;
import java.util.List;

import mod.unclecat.uc_auramagic.content.item.content.InstrumentItem;
import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class UnderInstrumentsConstructionTileEntity extends ModTileEntity
{
	public static TileEntityType<UnderInstrumentsConstructionTileEntity> TYPE;
	
//	public BlockState state;
	public List<InstrumentItem> clickedInstrumentsSequence = new ArrayList<InstrumentItem>();
	
	
	public UnderInstrumentsConstructionTileEntity(TileEntityType<?> tileEntityTypeIn)
	{
		super(TYPE);
	}
	public UnderInstrumentsConstructionTileEntity()
	{
		super(TYPE);
	}


	@Override
	public CompoundNBT writeData(CompoundNBT nbt)
	{
		ListNBT list = new ListNBT();
		
		for (InstrumentItem i : clickedInstrumentsSequence)
		{
			list.add(StringNBT.func_229705_a_(i.getRegistryName().toString()));
		}
				
		nbt.put("clickedInstrumentsSequence", list);
		
		return nbt;
	}

	@Override
	public void readData(CompoundNBT nbt)
	{
		ListNBT list = (ListNBT) nbt.get("clickedInstrumentsSequence");
		
		for (INBT i : list)
		{
			clickedInstrumentsSequence.add((InstrumentItem) ForgeRegistries.ITEMS.getValue(new ResourceLocation(((StringNBT)i).getString())));
		}
	}
	
	
	// TODOIMPORTANT: Mess with implementation of IBakedModel
//	class ThisTER extends TileEntityRenderer<UnderInstrumentsConstructionTileEntity>
//	{
//		public ThisTER(TileEntityRendererDispatcher p_i226006_1_)
//		{
//			super(p_i226006_1_);
//		}
//
//		@Override
//		public void func_225616_a_(UnderInstrumentsConstructionTileEntity te, float f, MatrixStack matrix,
//				IRenderTypeBuffer buf, int i, int i2)
//		{	
//			Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(te.state, matrix, buf, i, OverlayTexture.field_229196_a_, EmptyModelData.INSTANCE);
//		}	
//	}
}
