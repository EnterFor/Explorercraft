package com.zathrox.explorercraft.core.util;

import com.zathrox.explorercraft.core.Explorercraft;
import com.zathrox.explorercraft.core.util.provider.ExplorercraftRecipesProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExplorercraftDataGenerator {

    @SubscribeEvent
    public static void data(GatherDataEvent event) {
        final DataGenerator generator = event.getGenerator();
        generator.addProvider(new ExplorercraftRecipesProvider(generator)); // Generate recipes
        if (event.includeServer()) {
            //generator.addProvider(new ExplorercraftItemTagsProvider(generator)); // Generate item tags
            generator.addProvider(new ExplorercraftRecipesProvider(generator)); // Generate recipes
        }
    }

}