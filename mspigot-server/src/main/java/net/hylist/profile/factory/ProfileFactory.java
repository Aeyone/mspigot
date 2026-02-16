package net.hylist.profile.factory;

import net.hylist.json.JsonConfig;
import net.hylist.profile.Profile;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface ProfileFactory<T extends Profile> {
    T create(String name);
    void load(T profile, JsonConfig config, String path);
    void save(T profile, JsonConfig config, String path);
    boolean isCurrentProfile(Player player, T profile);
    String updateMessage(T profile);
    void sendAll(CommandSender sender, T profile);
    boolean setAll(T profile, String[] args);
}
