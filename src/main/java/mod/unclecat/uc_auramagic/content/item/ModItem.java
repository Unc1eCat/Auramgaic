package mod.unclecat.uc_auramagic.content.item;

import javax.annotation.Nullable;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.ItemGroupAurmagic;
import mod.unclecat.uc_auramagic.datagen.api.IItemModelSource;
import mod.unclecat.uc_auramagic.datagen.providers.ItemModelsProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

public class ModItem extends Item implements IItemModelSource {
    public ModItem(String name, ItemGroup group, @Nullable Properties properties) {
        super(properties == null ? new Item.Properties().group(group) : properties.group(group));
        setRegistryName(Auramagic.prefix(name));
    }

    public ModItem(String name, Properties properties) {
        this(name, ItemGroupAurmagic.INSTANCE, properties);
    }

    @Override
    public void generateItemModels(ItemModelsProvider provider) {
        try {
            provider.withExistingParent(getRegistryName().getPath(), ItemModelsProvider.GENERATED).texture("layer0", new ResourceLocation(getRegistryName().getNamespace(), "item/" + getRegistryName().getPath()));
        } catch (Exception e)
        {
            Auramagic.LOG.error(String.format("Failed to generate item model for item {}, skipping", getRegistryName()));
            e.printStackTrace();
        }
    }
}
