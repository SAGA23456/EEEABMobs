package com.EEEAB.EEEABMobs;

import com.EEEAB.EEEABMobs.client.ClientProxy;
import com.EEEAB.EEEABMobs.sever.ServerProxy;
import com.EEEAB.EEEABMobs.sever.handler.HandlerServerEvent;
import com.EEEAB.EEEABMobs.sever.util.ModCreativeModeTabGroup;
import com.EEEAB.EEEABMobs.sever.init.NetWorkInit;
import com.EEEAB.EEEABMobs.sever.config.EEConfigHandler;
import com.EEEAB.EEEABMobs.sever.init.*;
import com.EEEAB.EEEABMobs.sever.handler.HandlerCapability;
import com.EEEAB.EEEABMobs.sever.handler.HandlerClientEvent;
import com.EEEAB.EEEABMobs.sever.init.StructuresInit;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(EEEABMobs.MOD_ID)
@Mod.EventBusSubscriber(modid = EEEABMobs.MOD_ID)
public class EEEABMobs {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "eeeabsmobs";
    public static SimpleChannel NETWORK;
    public static ServerProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public EEEABMobs() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemInit.register(bus);
        BlockInit.register(bus);
        BlockEntityInit.register(bus);
        EntityInit.register(bus);
        ModCreativeModeTabGroup.register(bus);
        EffectInit.register(bus);
        ParticleInit.register(bus);
        SoundInit.register(bus);
        StructuresInit.register(bus);

        PROXY.init(bus);
        bus.addListener(this::init);
        bus.addListener(HandlerCapability::registerCapabilities);

        /* 配置文件 */
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EEConfigHandler.SPEC, EEEABMobs.MOD_ID + "-config.toml");

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new HandlerServerEvent());
        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, HandlerCapability::attachEntityCapability);
        //bus.addListener(NetWorkInit::registerMessage);
        //MinecraftForge.EVENT_BUS.register(new HandlerClientEvent());
    }

    public void init(final FMLCommonSetupEvent event){
        PROXY.registerMessage();
    }

}
