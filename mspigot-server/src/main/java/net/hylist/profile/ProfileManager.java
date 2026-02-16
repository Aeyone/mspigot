package net.hylist.profile;

import net.hylist.json.JsonConfig;

import net.hylist.profile.factory.ProfileFactory;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.ChatColor;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileManager<T extends Profile>{
    private final ProfileFactory<T> factory;
    private final List<T> profiles = new ArrayList<>();
    private T globalProfile;
    private final File file;
    private JsonConfig config;

    public T getGlobalProfile() {
        return globalProfile;
    }

    public void setGlobalProfile(T globalProfile) {
        this.globalProfile = globalProfile;
    }

    public List<T> getProfiles() {
        return profiles;
    }

    public T getProfileByName(String name) {
        for (T profile : profiles) {
            if (profile.getName().equalsIgnoreCase(name)) {
                return profile;
            }
        }
        return null;
    }

    public ProfileManager(File file, ProfileFactory<T> factory){
        this.file = file;
        this.factory = factory;
        this.loadProfile();
    }

    public void loadProfile() {
        this.config = new JsonConfig(file).load();
        profiles.clear();
        Map<String, Object> profilesMap = (Map<String, Object>) config.get("profiles");

        for (String profileName : profilesMap.keySet()) {
            T profile = factory.create(profileName);
            factory.load(profile, config, "profiles." + profileName);
            profiles.add(profile);
        }

        if (profiles.isEmpty()) {
            profiles.add(factory.create("Default"));
        }

        globalProfile = getProfileByName(config.getString("global-profile", "Default"));

        if (globalProfile == null) {
            globalProfile = profiles.get(0);
        }
    }

    public void saveProfiles() {
        config.clear();
        config.set("global-profile", globalProfile.getName());
        for (T profile : profiles) {
            factory.save(profile, config, "profiles." + profile.getName());
        }
        config.save();
    }


    public void sendInfo(CommandSender sender) {
        sender.sendMessage(ChatColor.BLUE + ChatColor.STRIKETHROUGH.toString() + StringUtils.repeat("-", 35));
        sender.sendMessage("");
        sender.sendMessage(ChatColor.YELLOW + "Profile Type: " + ChatColor.GOLD.toString() + ChatColor.BOLD + globalProfile.getType());
        sender.sendMessage("");

        for (T profile : profiles) {
            boolean isGlobal = globalProfile.getName().equals(profile.getName());
            boolean isCurrent = (sender instanceof Player && factory.isCurrentProfile((Player)sender, profile));

            ChatColor[] colorState = {ChatColor.RED, ChatColor.AQUA, ChatColor.GREEN, ChatColor.DARK_AQUA}; // 00, 01, 10, 11
            ChatColor currentColor = colorState[(isGlobal ? 1 : 0) << 1 | (isCurrent ? 1 : 0)];

            if (sender instanceof Player) {
                TextComponent text = new TextComponent("");

                TextComponent title = new TextComponent(
                    new ComponentBuilder("\u00BB ").color(ChatColor.GOLD).bold(true)
                            .append(profile.getName() + " ").color(currentColor).bold(true)
                            .create()
                );
                title.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, factory.updateMessage(profile)));
                title.setHoverEvent(
                        new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder(profile.getName()).color(currentColor).bold(true)
                                .append(" [Click to edit]\n").color(ChatColor.GREEN).bold(false)
                                .append(String.join("\n", factory.sendAll(profile)))
                                .create()
                ));
                text.addExtra(title);

                if (profile.getName() != globalProfile.getName()) {
                    TextComponent button = new TextComponent();
                    button.setText("[*]");
                    button.setColor(ChatColor.GRAY);
                    button.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + profile.getType() + " setglobal " + profile.getName()));
                    button.setHoverEvent(
                        new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click to set global profile").color(ChatColor.GREEN).create()
                    ));
                    text.addExtra(button);
                }
                ((Player)sender).spigot().sendMessage(text);

            } else {
                sender.sendMessage(currentColor.toString() + ChatColor.BOLD + profile.getName());
                factory.sendAll(profile).forEach(sender::sendMessage);
            }
        }
        sender.sendMessage("");
        sender.sendMessage(ChatColor.BLUE + ChatColor.STRIKETHROUGH.toString() + StringUtils.repeat("-", 35));
    }

    public boolean setAll(T profile, String[] args) {
        return factory.setAll(profile, args);
    }
}
