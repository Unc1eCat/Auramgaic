package mod.unclecat.uc_auramagic.content;

import java.util.*;
import java.util.Map.Entry;

import mod.unclecat.uc_auramagic.Auramagic;
import mod.unclecat.uc_auramagic.content.block.ModBlock;
import mod.unclecat.uc_auramagic.content.block.ModSlabBlock;
import mod.unclecat.uc_auramagic.content.block.ModStairsBlock;
import mod.unclecat.uc_auramagic.content.block.content.*;
import mod.unclecat.uc_auramagic.content.experience_gem.EnumExperienceColor;
import mod.unclecat.uc_auramagic.content.item.ModItem;
import mod.unclecat.uc_auramagic.content.item.content.InstrumentItem;
import mod.unclecat.uc_auramagic.content.item.content.ItemExperienceGem;
import mod.unclecat.uc_auramagic.content.item.content.ItemExperienceShard;
import mod.unclecat.uc_auramagic.content.item.content.ItemHammer;
import mod.unclecat.uc_auramagic.content.item.content.ItemWrench;
import mod.unclecat.uc_auramagic.content.multiblock.creators.instrument_work.LoomCreator;
import mod.unclecat.uc_auramagic.content.recipies.content.RecipeExperienceBlock;
import mod.unclecat.uc_auramagic.content.recipies.content.RecipeExperienceShard;
import mod.unclecat.uc_auramagic.content.tile_entities.ModTileEntity;
import mod.unclecat.uc_auramagic.util.helpers.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Auramagic.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Content {
    public static List<Object> additionalGameObjects = new ArrayList<Object>();

    /***** ITEMS ******/
    // Colored experience items
    public static ItemExperienceGem EXPERIENCE_GEM_FIRE = new ItemExperienceGem(EnumExperienceColor.FIRE);
    public static ItemExperienceGem EXPERIENCE_GEM_AIR = new ItemExperienceGem(EnumExperienceColor.AIR);
    public static ItemExperienceGem EXPERIENCE_GEM_EARTH = new ItemExperienceGem(EnumExperienceColor.EARTH);
    public static ItemExperienceGem EXPERIENCE_GEM_AQUA = new ItemExperienceGem(EnumExperienceColor.AQUA);
    public static ItemExperienceShard EXPERIENCE_SHARD_FIRE = new ItemExperienceShard(EnumExperienceColor.FIRE);
    public static ItemExperienceShard EXPERIENCE_SHARD_AIR = new ItemExperienceShard(EnumExperienceColor.AIR);
    public static ItemExperienceShard EXPERIENCE_SHARD_EARTH = new ItemExperienceShard(EnumExperienceColor.EARTH);
    public static ItemExperienceShard EXPERIENCE_SHARD_AQUA = new ItemExperienceShard(EnumExperienceColor.AQUA);
    public static BlockExperienceBlock EXPERIENCE_BLOCK_FIRE = new BlockExperienceBlock(EnumExperienceColor.FIRE);
    public static BlockExperienceBlock EXPERIENCE_BLOCK_AIR = new BlockExperienceBlock(EnumExperienceColor.AIR);
    public static BlockExperienceBlock EXPERIENCE_BLOCK_EARTH = new BlockExperienceBlock(EnumExperienceColor.EARTH);
    public static BlockExperienceBlock EXPERIENCE_BLOCK_AQUA = new BlockExperienceBlock(EnumExperienceColor.AQUA);

    // Gems
    public static ModItem RUBY = new ModItem("ruby", new Item.Properties());
    public static ModItem SAPPHIRE = new ModItem("sapphire", new Item.Properties());
    public static ModItem TOPAZ = new ModItem("topaz", new Item.Properties());
    public static ModItem AMBER = new ModItem("amber", new Item.Properties());
    public static ModItem AMETHYST = new ModItem("amethyst", new Item.Properties());

    // Instruments
    public static ItemHammer HAMMER = new ItemHammer();
    public static ItemWrench WRENCH = new ItemWrench();
    public static InstrumentItem SCREW_DRIVER = new InstrumentItem("screw_driver", new Item.Properties().maxStackSize(1).maxDamage(34));

    // Materials
    public static ModItem METAL_STICK = new ModItem("metal_stick", null);
    public static ModItem METAL_PLATE = new ModItem("metal_plate", null);
    public static ModItem AURA_FILAMENT = new ModItem("aura_filament", null);
    public static ModItem AURA_TISSUE = new ModItem("aura_tissue", null);
    public static ModItem CLOTH = new ModItem("cloth", null);


    /***** BLOCKS ******/
    // Gem ores
    public static CommonNoSmeltOre RUBY_ORE = new CommonNoSmeltOre("ruby_ore", 3.0F, 2, new ItemStack(RUBY, 1), 3, 3);
    public static CommonNoSmeltOre SAPPHIRE_ORE = new CommonNoSmeltOre("sapphire_ore", 3.0F, 2, new ItemStack(SAPPHIRE, 1), 3, 3);
    public static CommonNoSmeltOre TOPAZ_ORE = new CommonNoSmeltOre("topaz_ore", 3.0F, 2, new ItemStack(TOPAZ, 1), 3, 3);
    public static CommonNoSmeltOre AMBER_ORE = new CommonNoSmeltOre("amber_ore", 3.0F, 2, new ItemStack(AMBER, 1), 3, 3);
    public static CommonNoSmeltOre AMETHYST_ORE = new CommonNoSmeltOre("amethyst_ore", 3.0F, 2, new ItemStack(AMETHYST, 1), 3, 3);

    // Fundament
    public static ArmaturedBlock OVERWORLD_FUNDAMENT = new ArmaturedBlock("overworld_fundament", Block.Properties.from(Blocks.STONE).hardnessAndResistance(2.5f, 7f), Content.BROKEN_OVERWORLD_FUNDAMENT, ItemGroupAurmagic.INSTANCE, null);
    public static ArmaturedBlock NETHER_FUNDAMENT = new ArmaturedBlock("nether_fundament", Block.Properties.from(Blocks.STONE).hardnessAndResistance(2.5f, 7f), Content.BROKEN_NETHER_FUNDAMENT, ItemGroupAurmagic.INSTANCE, null);
    public static ArmaturedBlock END_FUNDAMENT = new ArmaturedBlock("end_fundament", Block.Properties.from(Blocks.STONE).hardnessAndResistance(2.5f, 7f), Content.BROKEN_END_FUNDAMENT, ItemGroupAurmagic.INSTANCE, null);
    public static ModBlock BROKEN_OVERWORLD_FUNDAMENT = new ModBlock("broken_overworld_fundament", Block.Properties.from(Blocks.STONE).hardnessAndResistance(2.5f, 7f), ItemGroupAurmagic.INSTANCE, null);
    public static ModBlock BROKEN_NETHER_FUNDAMENT = new ModBlock("broken_nether_fundament", Block.Properties.from(Blocks.STONE).hardnessAndResistance(2.5f, 7f), ItemGroupAurmagic.INSTANCE, null);
    public static ModBlock BROKEN_END_FUNDAMENT = new ModBlock("broken_end_fundament", Block.Properties.from(Blocks.STONE).hardnessAndResistance(2.5f, 7f), ItemGroupAurmagic.INSTANCE, null);

    // Loom
    public static LoomBlock LOOM_BLOCK = new LoomBlock();

    // Elderly stone
    public static ArmaturedBlock ARMOURED_ELDERLY_BLOCK = new ArmaturedBlock("armoured_elderly_stone", Block.Properties.from(Blocks.STONE), Content.ELDERLY_STONE, ItemGroupAurmagic.INSTANCE, null);
    public static ModBlock ELDERLY_STONE = new ModBlock("elderly_stone", Block.Properties.from(Blocks.STONE), ItemGroupAurmagic.INSTANCE, null);
    public static ModBlock ELDERLY_STONE_BRICKS = new ModBlock("elderly_stone_bricks", Block.Properties.from(Blocks.STONE_BRICKS), ItemGroupAurmagic.INSTANCE, null);
    public static ModSlabBlock ELDERLY_STONE_SLAB = new ModSlabBlock("elderly_stone_slab", Block.Properties.from(Blocks.STONE), ItemGroupAurmagic.INSTANCE, null);
    public static ModSlabBlock ELDERLY_STONE_BRICkS_SLAB = new ModSlabBlock("elderly_stone_bricks_slab", Block.Properties.from(Blocks.STONE_BRICKS), ItemGroupAurmagic.INSTANCE, null);
    public static ModStairsBlock ELDERLY_STONE_STAIRS = new ModStairsBlock("elderly_stone_stairs", Block.Properties.from(Blocks.STONE_BRICKS), ItemGroupAurmagic.INSTANCE, null, Content.ELDERLY_STONE, null);
    public static ModStairsBlock ELDERLY_STONE_BRICKS_STAIRS = new ModStairsBlock("elderly_stone_bricks_stairs", Block.Properties.from(Blocks.STONE_BRICKS), ItemGroupAurmagic.INSTANCE, null, Content.ELDERLY_STONE_BRICKS, null);

    // Etc.
    public static ArmatureBlock ARMATURE = new ArmatureBlock();
    public static BlockTable WOODEN_TABLE = new BlockTable("wooden_table", Block.Properties.from(Blocks.OAK_PLANKS).harvestTool(ToolType.AXE).harvestLevel(1));
    public static BlockTable STONE_TABLE = new BlockTable("stone_table", Block.Properties.from(Blocks.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1));
    public static UnderInstrumentsConstructionBlock WOODEN_UNDER_INSTRUMENTS_CONSTRUCTION_BLOCK = new UnderInstrumentsConstructionBlock("wooden_under_instruments_construction_block", Block.Properties.from(Blocks.OAK_PLANKS).hardnessAndResistance(3.0f));
    public static UnderInstrumentsConstructionBlock STONE_UNDER_INSTRUMENTS_CONSTRUCTION_BLOCK = new UnderInstrumentsConstructionBlock("stone_under_instruments_construction_block", Block.Properties.from(Blocks.STONE).hardnessAndResistance(3.0f));
    public static UnderInstrumentsConstructionBlock METAL_UNDER_INSTRUMENTS_CONSTRUCTION_BLOCK = new UnderInstrumentsConstructionBlock("metal_under_instruments_construction_block", Block.Properties.from(Blocks.IRON_BLOCK).hardnessAndResistance(3.0f));
    public static UnderInstrumentsConstructionBlock MAGIC_UNDER_INSTRUMENTS_CONSTRUCTION_BLOCK = new UnderInstrumentsConstructionBlock("magic_under_instruments_construction_block", Block.Properties.from(Blocks.GLOWSTONE).hardnessAndResistance(3.0f));


    /***** RECIPIES *****/
    public static RecipeExperienceBlock RECIPE_EXPERIENCE_BLOCK = new RecipeExperienceBlock(new ResourceLocation(Auramagic.prefix("experience_block")));
    public static RecipeExperienceShard RECIPE_EXPERIENCE_SHARD = new RecipeExperienceShard(new ResourceLocation(Auramagic.prefix("experience_shard")));


    /***** MATERIALS ******/
    public static final Material MATERIAL_MAGIC = (new Material.Builder(MaterialColor.AIR)).build();

    /***** MULTIBLOCK CREATORS *****/
    public static LoomCreator LOOM_CREATOR = new LoomCreator();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        RegistryHelper.registerAllViaEvent(event);
        RegistryHelper.registerAllFromAdditionalsGameObjects(event);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        RegistryHelper.registerAllViaEvent(event);
        RegistryHelper.registerAllFromAdditionalsGameObjects(event);
    }

    @SubscribeEvent
    public static void registerRecipieSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        RegistryHelper.registerAllViaEvent(event);
        RegistryHelper.registerAllFromAdditionalsGameObjects(event);
    }

    // Don't bully me for shitcode in the method pls. I'll probably change it. Sry for long game loading
    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
        List<ModBlock> list = RegistryHelper.getAllInstancesOf(ModBlock.class);
        Map<Class<? extends ModTileEntity>, List<Block>> types = new HashMap<Class<? extends ModTileEntity>, List<Block>>();

        for (ModBlock i : list) {
            Set<Class<? extends ModTileEntity>> tes = i.getTileEntityClasses();

            if (tes == null || tes.isEmpty()) continue;

            for (Class<? extends ModTileEntity> j : tes) {
                List<Block> validBlocks = types.get(j);

                if (validBlocks == null) {
                    validBlocks = new ArrayList<Block>();
                    types.put(j, validBlocks);
                }

                validBlocks.add(i);
            }


        }

        Set<Entry<Class<? extends ModTileEntity>, List<Block>>> set = types.entrySet();

        for (Entry<Class<? extends ModTileEntity>, List<Block>> i : set) {
            RegistryHelper.registerTileEntityTypeFromMapEntry(i, event.getRegistry());
        }
    }
}
