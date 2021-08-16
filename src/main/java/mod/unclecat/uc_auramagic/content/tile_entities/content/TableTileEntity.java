package mod.unclecat.uc_auramagic.content.tile_entities.content;

import com.mojang.blaze3d.matrix.MatrixStack;

import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.TileEntityListInventory;
import mod.unclecat.uc_auramagic.util.helpers.JavaHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class TableTileEntity extends TileEntityListInventory {
    public static TileEntityType<? extends ModTileEntity> TYPE;


    public TableTileEntity() {
        super(TYPE);
    }

    @Override
    protected int getListSize() {
        return 9;
    }

    public void setItemStacksRotated(Direction direction, ItemStack... itemStacks) {
        inventoryList = NonNullList.from(ItemStack.EMPTY, JavaHelper.flattenArray(JavaHelper.rotateTwoDimArray(JavaHelper.asTwoDimArray(3, itemStacks), direction)));
    }


    public static class ThisTER extends TileEntityRenderer<TableTileEntity> {
        public ThisTER(TileEntityRendererDispatcher p_i226006_1_) {
            super(p_i226006_1_);
        }

        @SuppressWarnings("deprecation")
        @Override
        public void func_225616_a_(TableTileEntity te, float f, MatrixStack matrix,
                                   IRenderTypeBuffer buf, int i, int i2) {

            matrix.func_227860_a_();
            matrix.func_227861_a_(0.1625, 1.012, 0.1625);
            matrix.func_227863_a_(new Quaternion(90.0f, 0.0f, 0.0f, true));
            matrix.func_227862_a_(0.32f, 0.32f, 0.32f);

            // TODO: Make it check if model 3D in GUI and move it up a bit if so
            Minecraft.getInstance().getItemRenderer().func_229110_a_(te.getStackInSlot(0), TransformType.FIXED, i, i2, matrix, buf);
            matrix.func_227861_a_(1.05, 0, 0);
            Minecraft.getInstance().getItemRenderer().func_229110_a_(te.getStackInSlot(1), TransformType.FIXED, i, i2, matrix, buf);
            matrix.func_227861_a_(1.05, 0, 0);
            Minecraft.getInstance().getItemRenderer().func_229110_a_(te.getStackInSlot(2), TransformType.FIXED, i, i2, matrix, buf);

            matrix.func_227861_a_(-2.1, 1.05, 0);
            Minecraft.getInstance().getItemRenderer().func_229110_a_(te.getStackInSlot(3), TransformType.FIXED, i, i2, matrix, buf);
            matrix.func_227861_a_(1.05, 0, 0);
            Minecraft.getInstance().getItemRenderer().func_229110_a_(te.getStackInSlot(4), TransformType.FIXED, i, i2, matrix, buf);
            matrix.func_227861_a_(1.05, 0, 0);
            Minecraft.getInstance().getItemRenderer().func_229110_a_(te.getStackInSlot(5), TransformType.FIXED, i, i2, matrix, buf);

            matrix.func_227861_a_(-2.1, 1.05, 0);
            Minecraft.getInstance().getItemRenderer().func_229110_a_(te.getStackInSlot(6), TransformType.FIXED, i, i2, matrix, buf);
            matrix.func_227861_a_(1.05, 0, 0);
            Minecraft.getInstance().getItemRenderer().func_229110_a_(te.getStackInSlot(7), TransformType.FIXED, i, i2, matrix, buf);
            matrix.func_227861_a_(1.05, 0, 0);
            Minecraft.getInstance().getItemRenderer().func_229110_a_(te.getStackInSlot(8), TransformType.FIXED, i, i2, matrix, buf);

            matrix.func_227865_b_();
        }
    }
}
