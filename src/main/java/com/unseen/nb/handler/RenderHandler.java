package com.unseen.nb.handler;

import com.unseen.nb.client.animation.model.BasicModelEntity;
import com.unseen.nb.client.entity.RenderModEntity;
import com.unseen.nb.client.entity.model.ModelPiglin;
import com.unseen.nb.client.entity.render.RenderPiglin;
import com.unseen.nb.client.entity.render.RenderPiglinBrute;
import com.unseen.nb.client.entity.render.RenderPiglinZombie;
import com.unseen.nb.client.entity.render.RenderStrider;
import com.unseen.nb.common.entity.entities.EntityPiglin;
import com.unseen.nb.common.entity.entities.EntityPiglinBrute;
import com.unseen.nb.common.entity.entities.EntityPiglinZombie;
import com.unseen.nb.common.entity.entities.EntityStrider;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import java.util.function.Function;

/**
 * Render Handler for entities
 */
public class RenderHandler {

    public static void registerEntityRenderers() {
        //Piglin
    RenderingRegistry.registerEntityRenderingHandler(EntityPiglin.class, RenderPiglin::new);
    //Strider
    RenderingRegistry.registerEntityRenderingHandler(EntityStrider.class, RenderStrider::new);
    //Piglin Brute
        RenderingRegistry.registerEntityRenderingHandler(EntityPiglinBrute.class, RenderPiglinBrute::new);
        //Piglin Zombie
        RenderingRegistry.registerEntityRenderingHandler(EntityPiglinZombie.class, RenderPiglinZombie::new);
    }



    private static <T extends Entity, U extends BasicModelEntity, V extends RenderModEntity> void registerModEntityRenderer(Class<T> entityClass, U model, String... textures) {
        registerModEntityRenderer(entityClass, (manager) -> new RenderModEntity(manager, model, textures));
    }

    private static <T extends Entity, U extends BasicModelEntity, V extends RenderModEntity> void registerModEntityRenderer(Class<T> entityClass, Function<RenderManager, Render<? super T>> renderClass) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, new IRenderFactory<T>() {
            @Override
            public Render<? super T> createRenderFor(RenderManager manager) {
                return renderClass.apply(manager);
            }
        });
    }
}
