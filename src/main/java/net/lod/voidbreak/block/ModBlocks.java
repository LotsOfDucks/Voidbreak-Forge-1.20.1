package net.lod.voidbreak.block;

import net.lod.voidbreak.Voidbreak;
import net.lod.voidbreak.block.custom.BleedingDeepslateBlock;
import net.lod.voidbreak.block.custom.BleedingStoneBlock;
import net.lod.voidbreak.block.custom.ChalkyGrassBlock;
import net.lod.voidbreak.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Voidbreak.MOD_ID);

    public static final RegistryObject<Block> CHALK = registerBlock("chalk",
            () -> new Block(BlockBehaviour.Properties.of()
                    .sound(SoundType.CALCITE)
                    .mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .instrument(NoteBlockInstrument.SNARE)
                    .requiresCorrectToolForDrops()
                    .strength(1.0F, 2.5F)));

    public static final RegistryObject<Block> CHALKY_DIRT = registerBlock("chalky_dirt",
            () -> new Block(BlockBehaviour.Properties.of()
                    .sound(SoundType.GRAVEL)
                    .mapColor(MapColor.TERRACOTTA_WHITE)
                    .strength(0.5F)));

    public static final RegistryObject<ChalkyGrassBlock> CHALKY_GRASS = registerBlock("chalky_grass",
            () -> new ChalkyGrassBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.GRASS)
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .strength(0.6F)
                    .randomTicks()));

    public static final RegistryObject<Block> GNEISS = registerBlock("gneiss",
            () -> new Block(BlockBehaviour.Properties.of()
                    .sound(SoundType.STONE)
                    .mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 6.5F)));

    public static final RegistryObject<BleedingStoneBlock> BLEEDING_STONE = registerBlock("bleeding_stone",
            () -> new BleedingStoneBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.STONE)
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(1.0F, 3.0F)));

    public static final RegistryObject<BleedingDeepslateBlock> BLEEDING_DEEPSLATE = registerBlock("bleeding_deepslate",
            () -> new BleedingDeepslateBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.DEEPSLATE)
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(3.5F, 3.0F)));




    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
