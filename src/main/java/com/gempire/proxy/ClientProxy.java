package com.gempire.proxy;

import com.gempire.Gempire;
import com.gempire.client.entity.model.*;
import com.gempire.client.entity.render.*;
import com.gempire.client.screen.*;
import com.gempire.client.ter.ShellTER;
import com.gempire.entities.projectiles.IceShardEntity;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModContainers;
import com.gempire.init.ModEntities;
import com.gempire.init.ModTE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Gempire.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientProxy {

    @SubscribeEvent
    public static void onClientSetup(EntityRenderersEvent.RegisterRenderers event) {
        //RenderingRegistry.registerEntityRenderingHandler(ModEntities.TEST.get(), RenderTestEntity::new);
        event.registerEntityRenderer(ModEntities.PEBBLE.get(), m -> new RenderPebble(m, new ModelPebble<>(m.bakeLayer(ModelPebble.LAYER_LOCATION_P))));
        event.registerEntityRenderer(ModEntities.MICA.get(), m -> new RenderMica(m, new ModelPebble<>(m.bakeLayer(ModelPebble.LAYER_LOCATION_M))));
        event.registerEntityRenderer(ModEntities.SHALE.get(), m -> new RenderShale(m, new ModelPebble<>(m.bakeLayer(ModelPebble.LAYER_LOCATION_S))));
        event.registerEntityRenderer(ModEntities.NACRE.get(), m -> new RenderNacre(m, new ModelPebble<>(m.bakeLayer(ModelPebble.LAYER_LOCATION_N))));
        event.registerEntityRenderer(ModEntities.RUBY.get(), m -> new RenderRuby(m, new ModelRuby<>(m.bakeLayer(ModelRuby.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.SAPPHIRE.get(), m -> new RenderSapphire(m, new ModelSapphire<>(m.bakeLayer(ModelSapphire.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.QUARTZ.get(), m -> new RenderQuartz(m, new ModelQuartz<>(m.bakeLayer(ModelQuartz.LAYER_LOCATION_Q))));
        event.registerEntityRenderer(ModEntities.JASPER.get(), m -> new RenderJasper(m, new ModelQuartz<>(m.bakeLayer(ModelQuartz.LAYER_LOCATION_J))));
        event.registerEntityRenderer(ModEntities.AGATE.get(), m -> new RenderAgate(m, new ModelQuartz<>(m.bakeLayer(ModelQuartz.LAYER_LOCATION_A))));
        event.registerEntityRenderer(ModEntities.TOPAZ.get(), m -> new RenderTopaz(m, new ModelTopaz<>(m.bakeLayer(ModelTopaz.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.OBSIDIAN.get(), m -> new RenderObsidian(m, new ModelObsidian<>(m.bakeLayer(ModelObsidian.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.PEARL.get(), m -> new RenderPearl(m, new ModelPearl<>(m.bakeLayer(ModelPearl.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.NEPHRITE.get(), m -> new RenderNephrite(m, new ModelNephrite<>(m.bakeLayer(ModelNephrite.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.SPODUMENE.get(), m -> new RenderSpodumene(m, new ModelSpodumene<>(m.bakeLayer(ModelSpodumene.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.ZIRCON.get(), m -> new RenderZircon(m, new ModelZircon<>(m.bakeLayer(ModelZircon.LAYER_LOCATION))));
        /*event.registerEntityRenderer(ModEntities.AQUAMARINE.get(), m -> new RenderAquamarine(m, new ModelRuby<>(m.bakeLayer(ModelRuby.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.BISMUTH.get(), m -> new RenderBismuth(m, new ModelRuby<>(m.bakeLayer(ModelRuby.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.BIXBITE.get(), m -> new RenderBixbite(m, new ModelRuby<>(m.bakeLayer(ModelRuby.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.DEMANTOID.get(), m -> new RenderDemantoid(m, new ModelRuby<>(m.bakeLayer(ModelRuby.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.EMERALD.get(), m -> new RenderEmerald(m, new ModelRuby<>(m.bakeLayer(ModelRuby.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.HESSONITE.get(), m -> new RenderHessonite(m, new ModelRuby<>(m.bakeLayer(ModelRuby.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.LAPIS.get(), m -> new RenderLapis(m, new ModelRuby<>(m.bakeLayer(ModelRuby.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.LARIMAR.get(), m -> new RenderLarimar(m, new ModelRuby<>(m.bakeLayer(ModelRuby.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.MELANITE.get(), m -> new RenderMelanite(m, new ModelRuby<>(m.bakeLayer(ModelRuby.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.MORGANITE.get(), m -> new RenderMorganite(m, new ModelRuby<>(m.bakeLayer(ModelRuby.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.PERIDOT.get(), m -> new RenderPeridot(m, new ModelRuby<>(m.bakeLayer(ModelRuby.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.PYROPE.get(), m -> new RenderPyrope(m, new ModelRuby<>(m.bakeLayer(ModelRuby.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.RUTILE.get(), m -> new RenderRutile(m, new ModelRuby<>(m.bakeLayer(ModelRuby.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.SPINEL.get(), m -> new RenderSpinel(m, new ModelRuby<>(m.bakeLayer(ModelRuby.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.TOURMALINE.get(), m -> new RenderTourmaline(m, new ModelRuby<>(m.bakeLayer(ModelRuby.LAYER_LOCATION))));*/

        MenuScreens.register(ModContainers.TANK_CONTAINER.get(), TankScreen::new);
        MenuScreens.register(ModContainers.INJECTOR_CONTAINER.get(), InjectorScreen::new);
        MenuScreens.register(ModContainers.GEM_UI_CONTAINER.get(), GemUIScreen::new);
        MenuScreens.register(ModContainers.SHELL_CONTAINER.get(), ShellScreen::new);
        MenuScreens.register(ModContainers.PEARL_UI_CONTAINER.get(), PearlUIScreen::new);
        MenuScreens.register(ModContainers.ZIRCON_UI_CONTAINER.get(), ZirconUIScreen::new);

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.POWER_CRYSTAL_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SHELL_BLOCK.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WHITE_CHROMA_CRYSTAL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ORANGE_CHROMA_CRYSTAL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAGENTA_CHROMA_CRYSTAL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIGHT_BLUE_CHROMA_CRYSTAL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.YELLOW_CHROMA_CRYSTAL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIME_CHROMA_CRYSTAL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINK_CHROMA_CRYSTAL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GRAY_CHROMA_CRYSTAL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIGHT_GRAY_CHROMA_CRYSTAL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CYAN_CHROMA_CRYSTAL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PURPLE_CHROMA_CRYSTAL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLUE_CHROMA_CRYSTAL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BROWN_CHROMA_CRYSTAL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GREEN_CHROMA_CRYSTAL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RED_CHROMA_CRYSTAL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLACK_CHROMA_CRYSTAL.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ICE_SPIKE.get(), RenderType.cutout());

        event.registerBlockEntityRenderer(ModTE.SHELL_TE.get(), ShellTER::new);
    }

    @SubscribeEvent
    public static void doSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.ICE_SHARD.get(), RenderIceShard::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelPebble.LAYER_LOCATION_P, ModelPebble::createBodyLayer);
        event.registerLayerDefinition(ModelPebble.LAYER_LOCATION_M, ModelPebble::createBodyLayer);
        event.registerLayerDefinition(ModelPebble.LAYER_LOCATION_S, ModelPebble::createBodyLayer);
        event.registerLayerDefinition(ModelPebble.LAYER_LOCATION_N, ModelPebble::createBodyLayer);
        event.registerLayerDefinition(ModelQuartz.LAYER_LOCATION_Q, ModelQuartz::createBodyLayer);
        event.registerLayerDefinition(ModelQuartz.LAYER_LOCATION_J, ModelQuartz::createBodyLayer);
        event.registerLayerDefinition(ModelQuartz.LAYER_LOCATION_A, ModelQuartz::createBodyLayer);
        event.registerLayerDefinition(ModelRuby.LAYER_LOCATION, ModelRuby::createBodyLayer);
        event.registerLayerDefinition(ModelSapphire.LAYER_LOCATION, ModelSapphire::createBodyLayer);
        event.registerLayerDefinition(ModelSpodumene.LAYER_LOCATION, ModelSpodumene::createBodyLayer);
        event.registerLayerDefinition(ModelNephrite.LAYER_LOCATION, ModelNephrite::createBodyLayer);
        event.registerLayerDefinition(ModelPearl.LAYER_LOCATION, ModelPearl::createBodyLayer);
        event.registerLayerDefinition(ModelObsidian.LAYER_LOCATION, ModelObsidian::createBodyLayer);
        event.registerLayerDefinition(ModelZircon.LAYER_LOCATION, ModelZircon::createBodyLayer);
        event.registerLayerDefinition(ModelTopaz.LAYER_LOCATION, ModelTopaz::createBodyLayer);
    }
}
