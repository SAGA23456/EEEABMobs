package com.EEEAB.EEEABMobs.client.render.util;

import com.EEEAB.EEEABMobs.EEEABMobs;
import com.EEEAB.EEEABMobs.client.model.block.ModelBlockTombstone;
import com.EEEAB.EEEABMobs.sever.init.BlockInit;
import com.EEEAB.EEEABMobs.sever.init.ItemInit;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EEItemStackRender extends BlockEntityWithoutLevelRenderer {
    private static final ModelBlockTombstone TOMBSTONE_MODEL = new ModelBlockTombstone();
    private static final ResourceLocation TOMBSTONE_TEXTURE = new ResourceLocation(EEEABMobs.MOD_ID, "textures/entity/block/tombstone.png");

    public EEItemStackRender() {
        super(null, null);
    }


    @Override
    public void renderByItem(ItemStack itemStackIn, ItemDisplayContext transformType, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        //VertexConsumer vertexConsumer;
        if (itemStackIn.getItem() == ItemInit.findBlockItem(BlockInit.TOMBSTONE)) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5F, 1.5F, 0.5F);
            matrixStackIn.scale(1.0F, -1.0F, -1.0F);
            TOMBSTONE_MODEL.resetToDefaultPose();
            TOMBSTONE_MODEL.renderToBuffer(matrixStackIn, bufferIn.getBuffer(RenderType.entityCutoutNoCull(TOMBSTONE_TEXTURE)), combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);
            matrixStackIn.popPose();
        }
    }
}
