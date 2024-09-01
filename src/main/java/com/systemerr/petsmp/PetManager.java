package com.systemerr.petsmp;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PetManager {
    private static List<Pet> pets = new ArrayList<>();

    public static List<Pet> getPets() {
        return pets;
    }

    public static void addPet(Player player, LivingEntity entity) {
        pets.add(new Pet(player, entity));
    }

    public static void updatePets() {
        pets.forEach(pet -> pet.entity().removePotionEffect(pet.effect()));
        pets.forEach(pet -> pet.update(Utils.loopDelay));
    }

    public static void clearPets() {
        pets = new ArrayList<>();
    }
}
