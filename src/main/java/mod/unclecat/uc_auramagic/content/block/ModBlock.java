package mod.unclecat.uc_auramagic.content.block;

import java.util.*;
import java.util.concurrent.ExecutionException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.Content;
import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.datagen.api.IItemModelSource;
import mod.unclecat.uc_auramagic.datagen.api.ILootTableSource;
import mod.unclecat.uc_auramagic.datagen.providers.ItemModelsProvider;
import mod.unclecat.uc_auramagic.util.helpers.BlockHelper;
import mod.unclecat.uc_auramagic.util.helpers.LootTableHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;

public class ModBlock extends Block implements ILootTableSource, IItemModelSource {
    public ModBlock(String name, Properties properties, @Nullable ItemGroup group, @Nullable Item.Properties itemProperties) {
        super(properties);
        setRegistryName(Auramagic.prefix(name));

        Content.additionalGameObjects.add(new BlockItem(this, (itemProperties == null ? new Item.Properties().group(group) : itemProperties.group(group))).setRegistryName(this.getRegistryName()));
    }

    public ModBlock(String name, Properties properties) {
        super(properties);
        setRegistryName(Auramagic.prefix(name));
    }

    @Override
    protected void fillStateContainer(Builder<Block, BlockState> builder) {
        builder.add(BlockHelper.getPropertiesFromBlock(this).toArray(new IProperty<?>[]{}));
    }

    @Nonnull
    public List<IProperty<?>> getExternalProperties() {
        return Lists.newArrayList();
    }

    public <T extends Comparable<T>> void setDefaultValue(IProperty<T> property, T value) {
        setDefaultState(getDefaultState().with(property, value));
    }

    /// WARNING!!! If it returns more than one class then you must implement your own logic in the "createTileEntity" method
    @Nonnull
    public Set<Class<? extends ModTileEntity>> getTileEntityClasses() {
        return Collections.emptySet();
    }

    @SuppressWarnings("unchecked")
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        Set<Class<? extends ModTileEntity>> classes = getTileEntityClasses();

        if (classes.size() > 1) {
            Auramagic.LOG.error("If \"getTileEntityClasses\" returns more than one class then you must override \"createTileEntity\" because default implementation of \"createTileEntity\" does not support this", new UnsupportedOperationException("If \"getTileEntityClasses\" returns more than one class then you must override \"createTileEntity\" because default implementation of \"createTileEntity\" does not support this"));
            return null;
        }

        try {
            return classes.iterator().next().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            Auramagic.LOG.error("Could not create tile entity for block " + this);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return getTileEntityClasses().size() == 1;
    }

//	@Override
//	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
//	{
//		return Arrays.asList(new ItemStack(Item.getItemFromBlock(this))); // Frick loot tables. Loot tables suck. Only hardcoded stuff. Yey deprecated featuresssssssssssssssss
//	}

    @Override
    public Map<ResourceLocation, LootTable> generateLootTables() {
        Map<ResourceLocation, LootTable> ret = new HashMap<ResourceLocation, LootTable>();
        ret.put(new ResourceLocation(getRegistryName().getNamespace(), "blocks/" + getRegistryName().getPath()), LootTableHelper.createStandardTableFromBlock(getRegistryName().toString(), this));
        return ret;
    }

    @Override
    public void generateItemModels(ItemModelsProvider provider) {
        try {
            if (asItem() != Items.AIR) {
                provider.withExistingParent(asItem().getRegistryName().getPath(), new ResourceLocation(asItem().getRegistryName().getNamespace(), "block/" + asItem().getRegistryName().getPath()));
            }
        } catch (Exception e) {
            Auramagic.LOG.error(String.format("Failed to generate item model for block {}, skipping", getRegistryName()));
            e.printStackTrace();
        }
    }
}
