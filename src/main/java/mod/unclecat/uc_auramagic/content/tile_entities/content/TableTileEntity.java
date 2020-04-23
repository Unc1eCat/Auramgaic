package mod.unclecat.uc_auramagic.content.tile_entities.content;

import com.mojang.blaze3d.matrix.MatrixStack;

import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.TileEntityListInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntityType;

public class TableTileEntity extends TileEntityListInventory
{
	public static TileEntityType<? extends ModTileEntity> TYPE;
	
	
	public TableTileEntity()
	{
		super(TYPE);
	}

	@Override
	protected int getListSize()
	{
		return 9;
	}
	
	
	public static class ThisTER extends TileEntityRenderer<TableTileEntity>
	{
		public ThisTER(TileEntityRendererDispatcher p_i226006_1_)
		{
			super(p_i226006_1_);
		}

		@SuppressWarnings("deprecation")
		@Override
		public void func_225616_a_(TableTileEntity te, float f, MatrixStack matrix,
				IRenderTypeBuffer buf, int i, int i2)
		{
			matrix.func_227860_a_();
			matrix.func_227861_a_(0.1, 1, 0.1);
			matrix.func_227862_a_(0.4f, 0.4f, 0.4f);
			matrix.func_227863_a_(new Quaternion(90.0f, 0.0f, 0.0f, true));
			//IBakedModel model = Minecraft.getInstance().getItemRenderer().getItemModelWithOverrides(te.getStackInSlot(0), te.world, null);
         Minecraft.getInstance().getItemRenderer().func_229110_a_(te.getStackInSlot(0), TransformType.FIXED, i2, OverlayTexture.field_229196_a_, matrix, buf);
         //Minecraft.getInstance().getItemRenderer().func_229111_a_(te.getStackInSlot(0), TransformType.FIXED, false, matrix, buf, i2, OverlayTexture.field_229196_a_, model);
         matrix.func_227865_b_();
		}
	}
}
