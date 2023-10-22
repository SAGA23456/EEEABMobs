package com.EEEAB.EEEABMobs.sever;

import com.EEEAB.EEEABMobs.EEEABMobs;
import com.EEEAB.EEEABMobs.sever.config.EEConfigHandler;
import com.EEEAB.EEEABMobs.sever.handler.HandlerServerEvent;
import com.EEEAB.EEEABMobs.sever.message.MessagePlayerUseAbility;
import com.EEEAB.EEEABMobs.sever.message.MessageUseAbility;
import com.EEEAB.EEEABMobs.sever.message.MessageVertigoEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkRegistry;

public class ServerProxy {
    public static final String VERSION = "1.0";
    private static int id = 0;

    public static int nextID() {
        return id++;
    }

    public  void registerMessage() {
        EEEABMobs.NETWORK = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(EEEABMobs.MOD_ID, "net"))
                .networkProtocolVersion(() -> VERSION)
                .clientAcceptedVersions(VERSION::equals)
                .serverAcceptedVersions(VERSION::equals)
                .simpleChannel();
        EEEABMobs.NETWORK.messageBuilder(MessageVertigoEffect.class, nextID()).encoder(MessageVertigoEffect::serialize).decoder(MessageVertigoEffect::deserialize).consumerNetworkThread(new MessageVertigoEffect.Handler()).add();
        EEEABMobs.NETWORK.messageBuilder(MessageUseAbility.class,nextID()).encoder(MessageUseAbility::serialize).decoder(MessageUseAbility::deserialize).consumerNetworkThread(new MessageUseAbility.Handler()).add();
        EEEABMobs.NETWORK.messageBuilder(MessagePlayerUseAbility.class,nextID()).encoder(MessagePlayerUseAbility::serialize).decoder(MessagePlayerUseAbility::deserialize).consumerNetworkThread(new MessagePlayerUseAbility.Handler()).add();
    }

    public void init(IEventBus bus){
    }

    public Object getISTERProperties() {
        return null;
    }
}
