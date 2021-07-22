package mod.unclecat.uc_auramagic.content.block.content;

import mod.unclecat.uc_auramagic.content.Content;
import mod.unclecat.uc_auramagic.content.block.ModBlock;
import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.content.tile_entities.content.UnderInstrumentsConstructionTileEntity;
import mod.unclecat.uc_auramagic.util.game_objects.IKind;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class UnderInstrumentsConstructionBlock extends ModBlock {
    public UnderInstrumentsConstructionBlock(String name, Properties properties) {
        super(name, properties);
    }


    @Override
    public Class<? extends ModTileEntity> getTileEntityClass() {
        return UnderInstrumentsConstructionTileEntity.class;
    }

    // Maybe I will add more matches if needed
    public static UnderInstrumentsConstructionBlock fromMaterial(Material material) {
        if (material == Material.BAMBOO || material == Material.CACTUS || material == Material.ORGANIC || material == Material.WOOL || material == Material.WOOD || material == Material.PLANTS) {
            return Content.WOODEN_UNDER_INSTRUMENTS_CONSTRUCTION_BLOCK;
        } else if (material == Material.CLAY || material == Material.EARTH || material == Material.FIRE || material == Material.GOURD || material == Material.SAND || material == Material.ROCK) {
            return Content.STONE_UNDER_INSTRUMENTS_CONSTRUCTION_BLOCK;
        } else if (material == Material.ANVIL || material == Material.GLASS || material == Material.IRON || material == Material.MISCELLANEOUS) {
            return Content.METAL_UNDER_INSTRUMENTS_CONSTRUCTION_BLOCK;
        } else if (material == Content.MATERIAL_MAGIC) {
            return Content.MAGIC_UNDER_INSTRUMENTS_CONSTRUCTION_BLOCK;
        }

        return null;
    }

    @Deprecated
    public static enum Type implements IKind {
        WOODEN("wooden", new BlockParticleData(ParticleTypes.BLOCK, Blocks.OAK_PLANKS.getDefaultState()), SoundEvents.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR, SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR),
        STONE("stone", new BlockParticleData(ParticleTypes.BLOCK, Blocks.OAK_PLANKS.getDefaultState()), SoundEvents.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR, SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR),
        METAL("metal", new BlockParticleData(ParticleTypes.BLOCK, Blocks.OAK_PLANKS.getDefaultState()), SoundEvents.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR, SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR),
        MAGIC("magic", new BlockParticleData(ParticleTypes.BLOCK, Blocks.OAK_PLANKS.getDefaultState()), SoundEvents.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR, SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR);

        public IParticleData particles;
        public SoundEvent click;
        public SoundEvent destroy;
        public String name;

        Type(String name, IParticleData particles, SoundEvent click, SoundEvent destroy) {
            this.particles = particles;
            this.click = click;
            this.destroy = destroy;
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
