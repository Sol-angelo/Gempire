package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelPebble;
import com.gempire.client.entity.model.ModelTest;
import com.gempire.client.entity.render.layers.SkinLayer;
import com.gempire.entities.TestEntity;
import com.gempire.entities.gems.EntityPebble;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;

public class RenderPebble extends MobRenderer<EntityPebble, ModelPebble<EntityPebble>> {

    public RenderPebble(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelPebble<>(), .25f);
        this.addLayer(new SkinLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPebble entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/pebble/pebble.png");
    }
}