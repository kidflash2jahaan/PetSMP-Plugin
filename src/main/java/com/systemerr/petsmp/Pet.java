package com.systemerr.petsmp;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public record Pet(Player player, LivingEntity entity, PotionEffectType effect, String id) {
    public Pet(Player player, LivingEntity entity) {
        PotionEffectType effect = switch (entity.getType()) {
            case CAT -> PotionEffectType.ABSORPTION;
            case DONKEY, HORSE, MULE, SKELETON_HORSE -> PotionEffectType.SPEED;
            case LLAMA, TRADER_LLAMA, WOLF -> PotionEffectType.STRENGTH;
            case PARROT -> PotionEffectType.JUMP_BOOST;
            default -> null;
        };

        this(player, entity, effect, player.getUniqueId().toString() + entity.getType().getKey().toString());
    }

    public void update(int duration) {
        if (Math.abs(entity.getX() - player.getX()) > 10 || Math.abs(entity.getZ() - player.getZ()) > 10) {
            Location tpLocation = new Location(player().getWorld(), player.getX(), entity.getY(), player.getZ());

            while (true) {
                if (tpLocation.getBlock().getType().isAir()) {
                    if (tpLocation.clone().add(0, -1, 0).getBlock().getType().isAir()) {
                        tpLocation.add(0, -1, 0);
                    } else {
                        break;
                    }
                } else {
                    tpLocation.add(0, 1, 0);
                }
            }
            entity.teleport(tpLocation);
        }

        if (effect != null) {
            int amplifier = 0;

            if (player.getPotionEffect(effect) != null) {
                amplifier = Objects.requireNonNull(player.getPotionEffect(effect)).getAmplifier() + 1;
            }

            player.addPotionEffect(new PotionEffect(effect, duration, amplifier));
            entity.addPotionEffect(new PotionEffect(effect, duration, amplifier));
            entity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, duration, 0));
        }

        entity.customName(Component.text(String.format("%s's %s", player.getName(), entity.getType().getKey().getKey().substring(0, 1).toUpperCase() + entity.getType().getKey().getKey().substring(1))));
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Pet && id.equals(((Pet) object).id);
    }
}