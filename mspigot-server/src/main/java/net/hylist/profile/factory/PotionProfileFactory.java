package net.hylist.profile.factory;

import com.google.common.collect.ImmutableList;
import net.hylist.json.JsonConfig;
import net.hylist.profile.craft.CraftPotionProfile;
import net.hylist.profile.PotionProfile;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

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
        profile.setPotionSize(Float.valueOf(config.getString(path + ".potionSize")));
        profile.setSplashTick(Integer.valueOf(config.getString(path + ".splashTick")));
        profile.setSplashDistance(Float.valueOf(config.getString(path + ".splashDistance")));
    }

    @Override
    public void save(PotionProfile profile, JsonConfig config, String path) {
        config.set(path + ".potionFall", profile.getPotionFall());
        config.set(path + ".potionMultiplier", profile.getPotionMultiplier());
        config.set(path + ".potionOffset", profile.getPotionOffset());
        config.set(path + ".followSpeedLevel", profile.isFollowSpeedLevel());
        config.set(path + ".potionExtraHorizontal", profile.getPotionExtraHorizontal());
        config.set(path + ".potionExtraVertical", profile.getPotionExtraVertical());
        config.set(path + ".potionSize", profile.getPotionSize());
        config.set(path + ".splashTick", profile.getSplashTick());
        config.set(path + ".splashDistance", profile.getSplashDistance());
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
            profile.getPotionExtraVertical() + " " +
            profile.getPotionSize() + " " +
            profile.getSplashTick() + " " +
            profile.getSplashDistance()
        );
    }

    @Override
    public List<String> sendAll(PotionProfile profile) {
        return ImmutableList.of(
            ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Fall: " + ChatColor.RED + profile.getPotionFall(),
            ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Multiplier: " + ChatColor.RED + profile.getPotionMultiplier(),
            ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Offset: " + ChatColor.RED + profile.getPotionOffset(),
            ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Following Speed Level: " + ChatColor.RED + profile.isFollowSpeedLevel(),
            ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Extra Horizontal: " + ChatColor.RED + profile.getPotionExtraHorizontal(),
            ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Extra Vertical: " + ChatColor.RED + profile.getPotionExtraVertical(),
            ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Size: " + ChatColor.RED + profile.getPotionSize(),
            ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Splash Tick: " + ChatColor.RED + profile.getSplashTick(),
            ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Splash Distance: " + ChatColor.RED + profile.getSplashDistance()
        );
    }

    @Override
    public boolean setAll(PotionProfile profile, String[] args) {
        if (args.length == 11) {
            profile.setPotionFall(Float.parseFloat(args[2]));
            profile.setPotionMultiplier(Float.parseFloat(args[3]));
            profile.setPotionOffset(Float.parseFloat(args[4]));
            profile.setFollowSpeedLevel(Boolean.parseBoolean(args[5]));
            profile.setPotionExtraHorizontal(Double.parseDouble(args[6]));
            profile.setPotionExtraVertical(Double.parseDouble(args[7]));
            profile.setPotionSize(Float.parseFloat(args[8]));
            profile.setSplashTick(Integer.parseInt(args[9]));
            profile.setSplashDistance(Float.parseFloat(args[10]));
            return true;
        } else {
            return false;
        }
    }
}
