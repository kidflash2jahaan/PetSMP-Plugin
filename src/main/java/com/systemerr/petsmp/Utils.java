package com.systemerr.petsmp;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.Random;

public class Utils {
    public static final Random random = new Random(Objects.requireNonNull(Bukkit.getWorld("world")).getSeed());
    public static final int loopDelay = toTicks(1);
    public static final String api = "api.systemerr.com";

    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    public static void sendMessage(String message, CommandSender sender) {
        if (sender instanceof Player) {
            sender.sendMessage(message);
        } else {
            log.info("\n{}", message);
        }
    }

    public static String howToPlay() {
        try {
            return HttpClient.newHttpClient().send(HttpRequest.newBuilder(URI.create("https://%s/petsmp/howtoplay".formatted(api))).GET().build(), HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String partnerships() {
        try {
            return HttpClient.newHttpClient().send(HttpRequest.newBuilder(URI.create("https://%s/petsmp/partnerships".formatted(api))).GET().build(), HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//    public static String getPets(String username) {
//        try {
//            return HttpClient.newHttpClient().send(HttpRequest.newBuilder(URI.create("http://%s/petsmp/pets/%s".formatted(api, username))).GET().build(), HttpResponse.BodyHandlers.ofString()).body();
//        } catch (IOException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static List<String> getPlayers() {
//        try {
//            return new JSONArray(HttpClient.newHttpClient().send(HttpRequest.newBuilder(URI.create("http://%s/petsmp/players".formatted(api))).GET().build(), HttpResponse.BodyHandlers.ofString()).body()).toList().stream().map(o -> (String) o).toList();
//        } catch (IOException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static void postToApi() {
//        try {
//            Map<String, String> players = new HashMap<>(Map.of());
//            System.out.println(PetManager.getPets());
//            getPlayers().forEach(s -> PetManager.getPets().forEach(pet -> {
//                if (pet.player().getName().equals(s)) {
//                    players.putIfAbsent(s, "");
//                    String player = players.get(s);
//
//                    players.remove(s);
//                    players.put(s, "%s%s\n".formatted(player, pet.entity().getType().getKey().getKey().toUpperCase()));
//                }
//            }));
//
//            System.out.println(players);
//
//            HttpClient.newHttpClient().send(HttpRequest.newBuilder(URI.create("http://%s/petsmp/pets".formatted(api))).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(new JSONArray(Arrays.stream(Bukkit.getOfflinePlayers()).map(OfflinePlayer::getName).toList()).toString())).build(), HttpResponse.BodyHandlers.ofString());
//
//            HttpClient.newHttpClient().send(HttpRequest.newBuilder(URI.create("http://%s/petsmp/players".formatted(api))).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(new JSONArray(Arrays.stream(Bukkit.getOfflinePlayers()).map(OfflinePlayer::getName).toList()).toString())).build(), HttpResponse.BodyHandlers.ofString());
//        } catch (IOException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static int toSeconds(int ticks) {
        return ticks / 20;
    }

    public static int toTicks(int seconds) {
        return seconds * 20;
    }
}
