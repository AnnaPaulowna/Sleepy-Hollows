package net.satisfy.sleepy_hollows.fabric.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        InitializationStatus.setModMenuInitialized(true);
        return parent -> AutoConfig.getConfigScreen(SleepyHollowsFabricConfig.class, parent).get();
    }
}