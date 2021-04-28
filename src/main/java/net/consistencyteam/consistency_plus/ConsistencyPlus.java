package net.consistencyteam.consistency_plus;

import com.mojang.serialization.Lifecycle;
import net.consistencyteam.consistency_plus.blocks.dirt.DirtBlock;
import net.consistencyteam.consistency_plus.registry.Blocks;
import net.consistencyteam.consistency_plus.registry.Items;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsistencyPlus implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    
    @Override
	public void onInitialize() {
        LOGGER.info("Consistency+ Main - Starting initialization");
        LOGGER.debug("Consistency+ Main - Beginning block initialization");
        Blocks.init();
        
        // making dirt not stupid
        Registry.BLOCK.set(Registry.BLOCK.getRawId(net.minecraft.block.Blocks.DIRT), RegistryKey.of(RegistryKey.ofRegistry(new Identifier("block")),
                new Identifier("minecraft", "dirt")),
                new DirtBlock(AbstractBlock.Settings.of(Material.SOIL, MaterialColor.DIRT).strength(0.5F).sounds(BlockSoundGroup.GRAVEL)),
                Lifecycle.stable());
        
        Registry.ITEM.set(Registry.ITEM.getRawId(net.minecraft.item.Items.DIRT), RegistryKey.of(Registry.ITEM_KEY, new Identifier("dirt")),
                new BlockItem(Registry.BLOCK.get(Registry.BLOCK.getRawId(net.minecraft.block.Blocks.DIRT)), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)),
                Lifecycle.stable());
        
        LOGGER.debug("Consistency+ Main - Blocks initialized");
        LOGGER.debug("Consistency+ Main - Beginning item initialization");
        Items.init();
        LOGGER.debug("Consistency+ Main - Items initialized");
        LOGGER.info("Consistency+ Main - Finished initialization");
        LOGGER.info("Consistency+ Info - Version RC-0.3");
    }
}
