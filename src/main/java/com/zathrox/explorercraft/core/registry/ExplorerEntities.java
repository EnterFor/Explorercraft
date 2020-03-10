package com.zathrox.explorercraft.core.registry;

import com.google.common.collect.Lists;
import com.zathrox.explorercraft.common.entity.*;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@SuppressWarnings("rawtypes")
public class ExplorerEntities {
    private static List<EntityType> entities = Lists.newArrayList();
    private static List<Item> spawnEggs = Lists.newArrayList();

    public static final EntityType<BrushStoogeEntity> BRUSH_STOOGE = createEntity(BrushStoogeEntity::new, EntityClassification.MONSTER, "brush_stooge", 0.4F, 0.7F, 10092400, 16777215);
    public static final EntityType<EnderGhastEntity> ENDERGHAST = createEntity(EnderGhastEntity::new, EntityClassification.MONSTER, "enderghast", 10F, 4.5F, 4801171, 6078066);
    public static final EntityType<EnderreeperEntity> ENDERREEPER = createEntity(EnderreeperEntity::new, EntityClassification.MONSTER, "enderreeper", 0.6F, 1.99F, 3801171, 7078066);
    public static final EntityType<InfectedCreeperEntity> INFECTED_CREEPER = createEntity(InfectedCreeperEntity::new, EntityClassification.MONSTER, "infected_creeper", 0.6F, 1.7F, 894731, 0xdc1c1c);
    public static final EntityType<InfectedSkeletonEntity> INFECTED_SKELETON = createEntity(InfectedSkeletonEntity::new, EntityClassification.MONSTER, "infected_skeleton", 0.6F, 1.99F, 16777215, 10092400);
    public static final EntityType<InfectedZombieEntity> INFECTED_ZOMBIE = createEntity(InfectedZombieEntity::new, EntityClassification.MONSTER, "infected_zombie", 0.6F, 1.99F, 16777234, 10092500);
    public static final EntityType<WizardEntity> WIZARD = createEntity(WizardEntity::new, EntityClassification.MONSTER, "wizard", 0.6F, 1.99F, 4869992, 16433238);

    private static <T extends Entity> EntityType<T> createEntity(EntityType.IFactory<T> factory, EntityClassification entityClassification, String name, float width, float height, int eggPrimary, int eggSecondary) {
        ResourceLocation location = new ResourceLocation(Explorercraft.MOD_ID, name);

        EntityType<T> entity = EntityType.Builder.create(factory, entityClassification)
                .size(width, height).setTrackingRange(64)
                .setShouldReceiveVelocityUpdates(true)
                .setUpdateInterval(3)
                .build(location.toString());

        entity.setRegistryName(location);

        entities.add(entity);
        spawnEggs.add(createSpawnEggForEntity(entity, eggPrimary, eggSecondary, ExplorerItemGroups.EXPLORERCRAFT));

        return entity;
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        for (EntityType entity : entities) {
            event.getRegistry().register(entity);
        }

        EntitySpawnPlacementRegistry.register(BRUSH_STOOGE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        EntitySpawnPlacementRegistry.register(ENDERGHAST, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EnderGhastEntity::spawnConditions);
        EntitySpawnPlacementRegistry.register(ENDERREEPER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EnderreeperEntity::spawnEnderreeper);
        EntitySpawnPlacementRegistry.register(INFECTED_CREEPER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        EntitySpawnPlacementRegistry.register(INFECTED_SKELETON, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        EntitySpawnPlacementRegistry.register(INFECTED_ZOMBIE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        EntitySpawnPlacementRegistry.register(WIZARD, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canSpawnOn);
    }

    @SubscribeEvent
    public static void registerSpawnEggs(RegistryEvent.Register<Item> event) {
        for (Item spawnEgg : spawnEggs) {
            event.getRegistry().register(spawnEgg);
        }
    }

    public static Item createSpawnEggForEntity(@SuppressWarnings("rawtypes") EntityType entityType, int eggColor1, int eggColor2, ItemGroup itemGroup) {
        return new SpawnEggItem(entityType, eggColor1, eggColor2, new Item.Properties().group(itemGroup)).setRegistryName(entityType.getRegistryName() + "_spawn_egg");
    }

    private static void registerEntityWorldSpawn(EntityType<?> type, int weight, int minCount, int maxCount, Biome... biomes) {

        for(Biome biome : biomes) {
            if(biome != null) {
                biome.getSpawns(type.getClassification()).add(new Biome.SpawnListEntry(type, weight, minCount, maxCount));
            }
        }
    }

    public static void registerEntityWorldSpawns() {

    }
}