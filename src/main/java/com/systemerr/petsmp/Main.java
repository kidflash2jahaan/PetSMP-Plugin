package com.systemerr.petsmp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public final class Main extends JavaPlugin implements Listener {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getWorlds().forEach(world -> world.getWorldBorder().setSize(5000));

        Objects.requireNonNull(this.getCommand("howtoplay")).setExecutor(new HowToPlay());
        Objects.requireNonNull(this.getCommand("partnerships")).setExecutor(new Partnerships());
//        Objects.requireNonNull(this.getCommand("pets")).setExecutor(new PetsCommand());
        
        log.info("PetSMP Plugin Enabled!");

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, this::update, 0, Utils.loopDelay);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.sendTitle("§b§lPetSMP", "§6Type /howtoplay to get started!", Utils.toTicks(1), Utils.toTicks(5), Utils.toTicks(1));
    }


    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getEntity() instanceof Tameable && Utils.random.nextDouble() > 0.10) {
            event.setCancelled(true);
        }
    }

    private void update() {
        PetManager.clearPets();

        Bukkit.getWorlds().forEach(world -> world.getLivingEntities().stream().filter(entity -> entity instanceof Tameable && ((Tameable) entity).isTamed()).forEach(entity -> {
            if (((Tameable) entity).getOwner() instanceof Player) {
                PetManager.addPet((Player) ((Tameable) entity).getOwner(), entity);
            }
        }));

        PetManager.updatePets();
        
//        Utils.postToApi();
    }
}
