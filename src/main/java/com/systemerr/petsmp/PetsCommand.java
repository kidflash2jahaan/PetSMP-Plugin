//package com.systemerr.petsmp;
//
//import org.bukkit.Bukkit;
//import org.bukkit.OfflinePlayer;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.command.TabCompleter;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class PetsCommand implements CommandExecutor, TabCompleter {
//    @Override
//    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
//        if (args.length == 1) {
//            Utils.sendMessage(Utils.getPets(args[0]), sender);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
//        if (args.length == 1) {
//            return Utils.getPlayers();
//        } else {
//            return null;
//        }
//    }
//}
