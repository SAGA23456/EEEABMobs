package com.EEEAB.EEEABMobs.sever.init;

import com.EEEAB.EEEABMobs.EEEABMobs;
import com.EEEAB.EEEABMobs.sever.message.MessagePlayerUseAbility;
import com.EEEAB.EEEABMobs.sever.message.MessageUseAbility;
import com.EEEAB.EEEABMobs.sever.message.MessageVertigoEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkRegistry;

public class NetWorkInit {
    public static final String VERSION = "1.0";
    private static int id = 0;

    public static int nextID() {
        return id++;
    }

    public static void registerMessage(final FMLCommonSetupEvent event) {
        EEEABMobs.NETWORK = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(EEEABMobs.MOD_ID, "net"))
                .networkProtocolVersion(() -> VERSION)
                .clientAcceptedVersions(VERSION::equals)
                .serverAcceptedVersions(VERSION::equals)
                .simpleChannel();
        EEEABMobs.NETWORK.messageBuilder(MessageVertigoEffect.class, nextID()).encoder(MessageVertigoEffect::serialize).decoder(MessageVertigoEffect::deserialize).consumerNetworkThread(new MessageVertigoEffect.Handler()).add();
        EEEABMobs.NETWORK.messageBuilder(MessageUseAbility.class,nextID()).encoder(MessageUseAbility::serialize).decoder(MessageUseAbility::deserialize).consumerNetworkThread(new MessageUseAbility.Handler()).add();
        EEEABMobs.NETWORK.messageBuilder(MessagePlayerUseAbility.class,nextID()).encoder(MessagePlayerUseAbility::serialize).decoder(MessagePlayerUseAbility::deserialize).consumerNetworkThread(new MessagePlayerUseAbility.Handler()).add();
    }
}
