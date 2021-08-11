package mod.unclecat.uc_auramagic.datagen.providers;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.datagen.api.IItemModelSource;
import mod.unclecat.uc_auramagic.util.helpers.RegistryHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;

public class ItemModelsProvider extends ItemModelProvider {

    public static ResourceLocation GENERATED = new ResourceLocation("minecraft:item/generated");

    public ItemModelsProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        RegistryHelper.getAllInstancesOf(IItemModelSource.class).forEach(i -> {
            try {
                i.generateItemModels(this);
            } catch (Exception e)
            {
                Auramagic.LOG.error("Failed to generate an item model, skipping");
                e.printStackTrace();
            }
        });
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
