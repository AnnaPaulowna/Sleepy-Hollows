package net.satisfy.sleepy_hollows.core.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.satisfy.sleepy_hollows.Constants;

public class TabRegistry {

    public static final DeferredRegister<CreativeModeTab> SLEEPY_HOLLOWS_TABS = DeferredRegister.create(Constants.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> SLEEPY_HOLLOWS_TAB = SLEEPY_HOLLOWS_TABS.register(Constants.MOD_ID, () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .icon(() -> new ItemStack(ObjectRegistry.HOLLOW_SAPLING.get()))
            .title(Component.translatable("itemGroup.sleepyHollows").withStyle(ChatFormatting.ITALIC))
            .displayItems(((itemDisplayParameters, out) -> {
                out.accept(ObjectRegistry.GRAVESTONE.get());
                out.accept(ObjectRegistry.GRAVESTONE_SLAB.get());
                out.accept(ObjectRegistry.GRAVESTONE_STAIRS.get());
                out.accept(ObjectRegistry.GRAVESTONE_WALL.get());
                out.accept(ObjectRegistry.COBBLED_GRAVESTONE.get());
                out.accept(ObjectRegistry.COBBLED_GRAVESTONE_SLAB.get());
                out.accept(ObjectRegistry.COBBLED_GRAVESTONE_STAIRS.get());
                out.accept(ObjectRegistry.COBBLED_GRAVESTONE_WALL.get());
                out.accept(ObjectRegistry.CHISELED_GRAVESTONE.get());
                out.accept(ObjectRegistry.GRAVESTONE_BRICKS.get());
                out.accept(ObjectRegistry.GRAVESTONE_BRICK_SLAB.get());
                out.accept(ObjectRegistry.GRAVESTONE_BRICK_STAIRS.get());
                out.accept(ObjectRegistry.GRAVESTONE_BRICK_WALL.get());
                out.accept(ObjectRegistry.CRACKED_GRAVESTONE_BRICKS.get());
                out.accept(ObjectRegistry.MOSSY_GRAVESTONE_BRICKS.get());
                out.accept(ObjectRegistry.MOSSY_GRAVESTONE_BRICK_SLAB.get());
                out.accept(ObjectRegistry.MOSSY_GRAVESTONE_BRICK_STAIRS.get());
                out.accept(ObjectRegistry.MOSSY_GRAVESTONE_BRICK_WALL.get());
                out.accept(ObjectRegistry.MOSSY_COBBLED_GRAVESTONE.get());
                out.accept(ObjectRegistry.MOSSY_COBBLED_GRAVESTONE_SLAB.get());
                out.accept(ObjectRegistry.MOSSY_COBBLED_GRAVESTONE_STAIRS.get());
                out.accept(ObjectRegistry.MOSSY_COBBLED_GRAVESTONE_WALL.get());
                out.accept(ObjectRegistry.MOSSY_CHISELED_GRAVESTONE.get());
                out.accept(ObjectRegistry.MOSSY_GRAVESTONE.get());
                out.accept(ObjectRegistry.MOSSY_GRAVESTONE_SLAB.get());
                out.accept(ObjectRegistry.MOSSY_GRAVESTONE_STAIRS.get());
                out.accept(ObjectRegistry.MOSSY_GRAVESTONE_WALL.get());
                out.accept(ObjectRegistry.HOLLOW_LOG.get());
                out.accept(ObjectRegistry.HOLLOW_WOOD.get());
                out.accept(ObjectRegistry.STRIPPED_HOLLOW_WOOD.get());
                out.accept(ObjectRegistry.STRIPPED_HOLLOW_LOG.get());
                out.accept(ObjectRegistry.HOLLOW_PLANKS.get());
                out.accept(ObjectRegistry.HOLLOW_STAIRS.get());
                out.accept(ObjectRegistry.HOLLOW_SLAB.get());
                out.accept(ObjectRegistry.HOLLOW_PRESSURE_PLATE.get());
                out.accept(ObjectRegistry.HOLLOW_BUTTON.get());
                out.accept(ObjectRegistry.HOLLOW_TRAPDOOR.get());
                out.accept(ObjectRegistry.HOLLOW_DOOR.get());
                out.accept(ObjectRegistry.HOLLOW_WINDOW.get());
                out.accept(ObjectRegistry.HOLLOW_FENCE.get());
                out.accept(ObjectRegistry.HOLLOW_FENCE_GATE.get());
                out.accept(ObjectRegistry.HOLLOW_LEAVES.get());
                out.accept(ObjectRegistry.HOLLOW_SAPLING.get());
                out.accept(ObjectRegistry.MOONVEIL_GRASS.get());
                out.accept(ObjectRegistry.TALL_MOONVEIL_GRASS.get());
                out.accept(ObjectRegistry.GRAVE_LILY.get());
                out.accept(ObjectRegistry.DREAMSHADE.get());
                out.accept(ObjectRegistry.TALL_DREAMSHADE.get());
                out.accept(ObjectRegistry.WOODEN_TOMBSTONE.get());
                out.accept(ObjectRegistry.SMALL_TOMBSTONE.get());
                out.accept(ObjectRegistry.MID_TOMBSTONE.get());
                out.accept(ObjectRegistry.BIG_TOMBSTONE.get());

                out.accept(ObjectRegistry.SPECTRAL_ESSENCE.get());


            }))
            .build());

    public static void init() {
        SLEEPY_HOLLOWS_TABS.register();
    }
}
