package net.hylist.profile;

import net.hylist.json.JsonConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PotionProfileFactory implements ProfileFactory<PotionProfile>{
    @Override
    public PotionProfile create(String name) {
        return new CraftPotionProfile(name);
    }

    @Override
    public void load(PotionProfile profile, JsonConfig config, String path) {
        profile.setPotionFall(Float.valueOf(config.getString(path + ".potionFall")));
        profile.setPotionMultiplier(Float.valueOf(config.getString(path + ".potionMultiplier")));
        profile.setPotionOffset(Float.valueOf(config.getString(path + ".potionOffset")));
        profile.setFollowSpeedLevel(Boolean.valueOf(config.getString(path + ".followSpeedLevel")));
        profile.setPotionExtraHorizontal(Double.valueOf(config.getString(path + ".potionExtraHorizontal")));
        profile.setPotionExtraVertical(Double.valueOf(config.getString(path + ".potionExtraVertical")));
    }

    @Override
    public void save(PotionProfile profile, JsonConfig config, String path) {
        config.set(path + ".potionFall", profile.getPotionFall());
        config.set(path + ".potionMultiplier", profile.getPotionMultiplier());
        config.set(path + ".potionOffset", profile.getPotionOffset());
        config.set(path + ".followSpeedLevel", profile.isFollowSpeedLevel());
        config.set(path + ".potionExtraHorizontal", profile.getPotionExtraHorizontal());
        config.set(path + ".potionExtraVertical", profile.getPotionExtraVertical());
    }

    @Override
    public boolean isCurrentProfile(Player player, PotionProfile profile) {
        return player.getPotProfile().getName().equals(profile.getName());
    }

    @Override
    public String updateMessage(PotionProfile profile) {
        return (
            "/potion update " +
            profile.getName() + " " +
            profile.getPotionFall() + " " +
            profile.getPotionMultiplier() + " " +
            profile.getPotionOffset() + " " +
            profile.isFollowSpeedLevel() + " " +
            profile.getPotionExtraHorizontal() + " " +
            profile.getPotionExtraVertical() + " "
        );
    }

    @Override
    public void sendAll(CommandSender sender, PotionProfile profile) {
        sender.sendMessage(ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Fall: " + ChatColor.RED + profile.getPotionFall());
        sender.sendMessage(ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Multiplier: " + ChatColor.RED + profile.getPotionMultiplier());
        sender.sendMessage(ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Offset: " + ChatColor.RED + profile.getPotionOffset());
        sender.sendMessage(ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Following Speed Level: " + ChatColor.RED + profile.isFollowSpeedLevel());
        sender.sendMessage(ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Extra Horizontal: " + ChatColor.RED + profile.getPotionExtraHorizontal());
        sender.sendMessage(ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Extra Vertical: " + ChatColor.RED + profile.getPotionExtraVertical());
    }
}
