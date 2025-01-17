package net.satisfy.sleepy_hollows.client.util;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfy.sleepy_hollows.core.network.SleepyHollowsNetwork;
import net.satisfy.sleepy_hollows.core.network.message.SanityPacketMessage;
import net.satisfy.sleepy_hollows.core.registry.MobEffectRegistry;
import net.satisfy.sleepy_hollows.core.registry.TagRegistry;
import net.satisfy.sleepy_hollows.core.util.IEntitySavedData;
import org.jetbrains.annotations.NotNull;

public class SanityManager {

    public enum Modifiers {

        CANDY_CORN(4), DUSK_BERRY(2),
        LUMINOUS_WATER(6), SPECTRAL_PUMPKIN_PIE(10),

        INSIDE_BIOME(-1), OUTSIDE_BIOME(5),
        RESET_SANITY(SanityManager.MAXIMUM_SANITY), DECREASE_SANITY(-2),
        INFECTED_EFFECT(-2), MENTAL_FORTITUDE(SanityManager.MAXIMUM_SANITY);

        final int value;

        Modifiers(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static final String SANITY = "sanity";
    public static final int MINIMUM_SANITY = 0;
    public static final int MAXIMUM_SANITY = 100;

    public static IEntitySavedData getSavedData(Player player) {
        return (IEntitySavedData) player;
    }

    public static CompoundTag getSanityTag(IEntitySavedData playerData) {
        return playerData.impl$getPersistentData();
    }

    public static int getSanity(Player player) {
        CompoundTag nbt = getSanityTag(getSavedData(player));
        if (!nbt.contains(SANITY, Tag.TAG_INT)) nbt.putInt(SANITY, 100);
        return nbt.getInt(SANITY);
    }

    /**
     * @param amount expects a negative integer
     */
    public static void decreaseSanity(Player player, int amount) {

        final int currentSanity = getSanity(player);
        if (SanityManager.isImmune(player)) return;
        final int newSanity = Math.max(MINIMUM_SANITY, currentSanity + amount);
        getSanityTag(getSavedData(player)).putInt(SANITY, safeSanity(newSanity));

    }

    /**
     * @param amount expects a positive integer
     */
    private static void increaseSanity(Player player, int amount) {

        final int currentSanity = getSanity(player);
        final int newSanity = Math.min(MAXIMUM_SANITY, currentSanity + amount);
        getSanityTag(getSavedData(player)).putInt(SANITY, safeSanity(newSanity));

    }

    /**
     * @param player An instance of a Player
     * @param amount A positive or negative integer to change the Sanity by
     */
    public static void changeSanity(Player player, int amount) {
        if (SanityManager.isImmune(player)) return;

        if (amount != 0) {
            if (amount > 0) {
                increaseSanity(player, amount);
            } else {
                decreaseSanity(player, amount);
            }
        }
    }

    /**
     * @param player An instance of a Player
     * @param amount A positive or negative integer to change the Sanity by
     */
    public static void changeLocalSanity(LocalPlayer player, int amount) {
        if (SanityManager.isImmune(player)) return;
        if (amount != 0) {
            if (amount > 0) {
                increaseSanity(player, amount);
            } else {
                decreaseSanity(player, amount);
            }
        }
    }

    private static int safeSanity(int amount) {
        if (amount < MINIMUM_SANITY) amount = MINIMUM_SANITY;
        if (amount > MAXIMUM_SANITY) amount = MAXIMUM_SANITY;
        return amount;
    }

    public static void doBlockCheck(@NotNull ServerPlayer serverPlayer) {

        Level level = serverPlayer.level();
        BlockPos blockPos = serverPlayer.blockPosition();
        BlockState blockState = level.getBlockState(blockPos);

        if (blockState.is(TagRegistry.RESET_SANITY)) {
            changeSanity(serverPlayer, Modifiers.RESET_SANITY.getValue());
            SleepyHollowsNetwork.SANITY_CHANNEL.sendToPlayer(serverPlayer, new SanityPacketMessage(Modifiers.RESET_SANITY.getValue()));
        }

        if (!SanityManager.isImmune(serverPlayer) && blockState.is(TagRegistry.DECREASE_SANITY)) {
            changeSanity(serverPlayer, Modifiers.DECREASE_SANITY.getValue());
            SleepyHollowsNetwork.SANITY_CHANNEL.sendToPlayer(serverPlayer, new SanityPacketMessage(Modifiers.DECREASE_SANITY.getValue()));
        }
    }

    public static boolean isSanityBarVisible(Player player) {
        return getSanity(player) < MAXIMUM_SANITY;
    }

    public static boolean isImmune(Player player) {
        return player.hasEffect(MobEffectRegistry.MENTAL_FORTITUDE.get());
    }
}