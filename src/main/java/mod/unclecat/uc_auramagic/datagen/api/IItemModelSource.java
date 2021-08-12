package mod.unclecat.uc_auramagic.datagen.api;

import mod.unclecat.uc_auramagic.datagen.providers.ItemModelsProvider;

public interface IItemModelSource {
    void generateItemModels(ItemModelsProvider provider);
}
