package com.zathrox.explorercraft.core.proxy;

import com.zathrox.explorercraft.client.ClientForgeEventSubscriber;
import com.zathrox.explorercraft.client.render.*;
import com.zathrox.explorercraft.core.Explorercraft;
import com.zathrox.explorercraft.core.config.Config;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import com.zathrox.explorercraft.core.registry.ExplorerEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@OnlyIn(Dist.CLIENT)
public class ClientProxy extends CommonProxy {

    public ClientProxy() {
        super();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.client_config);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        Config.loadConfig(Config.client_config, FMLPaths.CONFIGDIR.get().resolve("explorercraft-client.toml").toString());
    }

    private void clientSetup(FMLClientSetupEvent event) {
        Explorercraft.LOGGER.debug("ClientProxy clientSetup method");

        registerRenderLayers();
        registerEntitRenderer();

        MinecraftForge.EVENT_BUS.register(new ClientForgeEventSubscriber());
    }

    private void registerEntitRenderer() {
        RenderingRegistry.registerEntityRenderingHandler(ExplorerEntities.BRUSH_STOOGE, BrushStoogeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ExplorerEntities.ENDERGHAST, EnderGhastRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ExplorerEntities.ENDERREEPER, EnderreeperRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ExplorerEntities.INFECTED_CREEPER, InfectedCreeperRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ExplorerEntities.INFECTED_SKELETON, InfectedSkeletonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ExplorerEntities.INFECTED_ZOMBIE, InfectedZombieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ExplorerEntities.WIZARD, WizardRenderer::new);

    }

    private void registerRenderLayers() {
        // Fix the texture to be a single text and change the block model back @todo
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.INFECTED_WILLOW_PLANKS, RenderType.getCutoutMipped());
        //== Plants
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.LEEK_WILD, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.LEEKS, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.NOCTILUCAS, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.RICE_TOP, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.RICE_BASE, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.LOTUS_FLOWER, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.LOTUS_STEM, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.DAFFODIL, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.LUPINE, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.GREEN_MUSHROOM, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.PINK_MUSHROOM, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.TALL_CATTAIL, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.CATTAIL, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.SHORT_GRASS, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.WILLOW, RenderType.getCutout());

        //== Potted Plants
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.POTTED_WILD_LEEK, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.POTTED_DAFFODIL, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.POTTED_LUPINE, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.POTTED_GREEN_MUSHROOM, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.POTTED_PINK_MUSHROOM, RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(ExplorerBlocks.POTTED_ASH_SAPLING, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.POTTED_BAMBOO_SAPLING, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.POTTED_CHERRY_SAPLING, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.POTTED_MAPLE_SAPLING, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.POTTED_WILLOW_SAPLING, RenderType.getCutout());

        //== Wood Doors/Trapdoors
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.ASH_TRAPDOOR, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.BAMBOO_TRAPDOOR, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.CHERRY_TRAPDOOR, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.MAPLE_TRAPDOOR, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.WILLOW_TRAPDOOR, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.INFECTED_WILLOW_TRAPDOOR, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.ASH_DOOR, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.BAMBOO_DOOR, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.CHERRY_DOOR, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.MAPLE_DOOR, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.WILLOW_DOOR, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.INFECTED_WILLOW_DOOR, RenderType.getCutout());

        //== Wood Leaves / Saplings
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.ASH_LEAVES, RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.BAMBOO_LEAVES, RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.CHERRY_LEAVES, RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.MAPLE_LEAVES, RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.WILLOW_LEAVES, RenderType.getCutoutMipped());

        RenderTypeLookup.setRenderLayer(ExplorerBlocks.ASH_SAPLING, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.BAMBOO_SAPLING, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.CHERRY_SAPLING, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.MAPLE_SAPLING, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExplorerBlocks.WILLOW_SAPLING, RenderType.getCutout());

    }


    private void registerBlockColours(ColorHandlerEvent.Block event) {
        BlockColors blockColors = event.getBlockColors();

        blockColors.register((state, world, pos, tint_index) -> {
            return world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.get(0.5D, 1.0D);
        }, ExplorerBlocks.LOTUS_FLOWER, ExplorerBlocks.SHORT_GRASS, ExplorerBlocks.WILLOW_LEAVES, ExplorerBlocks.WILLOW);

    }

    private void registerItemColors(ColorHandlerEvent.Item event) {
        ItemColors itemColors = event.getItemColors();

        //itemColors.register((stack, tintIndex) -> {
        //return ExplorerBiomes.FUNGAL_FOREST.getGrassColor((BlockPos) null);
        //}, ExplorerBlocks.LOTUS_FLOWER);

        itemColors.register((stack, tintIndex) -> {
            return GrassColors.get(0.5D, 1.0D);
        }, ExplorerBlocks.WILLOW_LEAVES, ExplorerBlocks.WILLOW);

        itemColors.register((stack, tintIndex) -> {
            return GrassColors.get(0.5D, 1.0D);
        }, ExplorerBlocks.SHORT_GRASS);

        itemColors.register((stack, tintIndex) -> {
            return GrassColors.get(0.5D, 1.0D);
        }, ExplorerBlocks.SHORT_GRASS);
    }

    @Override
    protected void postInit(InterModProcessEvent event) {
        super.postInit(event);

        this.registerBlockColours(new ColorHandlerEvent.Block(Minecraft.getInstance().getBlockColors()));
        this.registerItemColors(new ColorHandlerEvent.Item(Minecraft.getInstance().getItemColors(), Minecraft.getInstance().getBlockColors()));
    }
}