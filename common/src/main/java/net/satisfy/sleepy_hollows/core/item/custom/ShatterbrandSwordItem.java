package net.satisfy.sleepy_hollows.core.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.satisfy.sleepy_hollows.core.registry.MobEffectRegistry;
import net.satisfy.sleepy_hollows.core.registry.ToolTiersRegistry;
import net.satisfy.sleepy_hollows.core.util.EnchantingBehavior;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ShatterbrandSwordItem extends SwordItem implements EnchantingBehavior {
    public ShatterbrandSwordItem(Properties properties) {
        super(ToolTiersRegistry.SPECTRAL, 3, -2.5F, properties);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return !EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.FIRE_ASPECT) && EnchantingBehavior.super.isBookEnchantable(stack, book);
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);

        if (!target.level().isClientSide) {
            if (target.level() instanceof ServerLevel serverLevel) {
                for (int i = 0; i < 20; ++i) {
                    double px = target.getX() + target.getLookAngle().x;
                    double py = target.getY() + target.getBbHeight();
                    double pz = target.getZ() + target.getLookAngle().z;

                    serverLevel.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, px, py, pz, 1, 0.0, 0.0, 0.0, 0.02);
                    serverLevel.sendParticles(ParticleTypes.SOUL, px, py, pz, 1, 0.0, 0.0, 0.0, 0.02);
                    serverLevel.sendParticles(ParticleTypes.WHITE_ASH, px, py, pz, 1, 0.0, 0.0, 0.0, 0.02);
                }
            }
        }

        if (result && !target.level().isClientSide()) {
            target.addEffect(new MobEffectInstance(MobEffectRegistry.INFECTED.get(), 30, 1));
        }

        return result;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag context) {
        tooltip.add(Component.translatable("tooltip.sleepy_hollows.lore.shatterbrand").withStyle(ChatFormatting.GOLD, ChatFormatting.ITALIC));
    }
}