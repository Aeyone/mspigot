package net.hylist.profile;

import net.hylist.json.JsonConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KnockbackProfileFactory implements ProfileFactory<KnockbackProfile>{

    @Override
    public KnockbackProfile create(String name) {
        return new CraftKnockbackProfile(name);
    }

    @Override
    public void load(KnockbackProfile profile, JsonConfig config, String path) {
        profile.setFriction(Double.valueOf(config.getString(path + ".friction")));
        profile.setHorizontal(Double.valueOf(config.getString(path + ".horizontal")));
        profile.setVertical(Double.valueOf(config.getString(path + ".vertical")));
        profile.setVerticalLimit(Double.valueOf(config.getString(path + ".verticalLimit")));
        profile.setExtraHorizontal(Double.valueOf(config.getString(path + ".extraHorizontal")));
        profile.setExtraVertical(Double.valueOf(config.getString(path + ".extraVertical")));
        profile.setRecoilMultiplier(Double.valueOf(config.getString(path + ".recoilMultiplier")));
    }

    @Override
    public void save(KnockbackProfile profile, JsonConfig config, String path) {
        config.set(path + ".friction", profile.getFriction());
        config.set(path + ".horizontal", profile.getHorizontal());
        config.set(path + ".vertical", profile.getVertical());
        config.set(path + ".verticalLimit", profile.getVerticalLimit());
        config.set(path + ".extraHorizontal", profile.getExtraHorizontal());
        config.set(path + ".extraVertical", profile.getExtraVertical());
        config.set(path + ".recoilMultiplier", profile.getRecoilMultiplier());
    }

    @Override
    public boolean isCurrentProfile(Player player, KnockbackProfile profile) {
        return player.getKbProfile().getName().equals(profile.getName());
    }

    @Override
    public String updateMessage(KnockbackProfile profile) {
        return (
            "/knockback update " +
            profile.getName() + " " +
            profile.getFriction() + " " +
            profile.getHorizontal() + " " +
            profile.getVertical() + " " +
            profile.getVerticalLimit() + " " +
            profile.getExtraHorizontal() + " " +
            profile.getExtraVertical() + " " +
            profile.getRecoilMultiplier() + " "
        );
    }

    @Override
    public void sendAll(CommandSender sender, KnockbackProfile profile) {
        sender.sendMessage(ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Friction: " + ChatColor.RED + profile.getFriction());
        sender.sendMessage(ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Horizontal: " + ChatColor.RED + profile.getHorizontal());
        sender.sendMessage(ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Vertical: " + ChatColor.RED + profile.getVertical());
        sender.sendMessage(ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Vertical Limit: " + ChatColor.RED + profile.getVerticalLimit());
        sender.sendMessage(ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Extra Horizontal: " + ChatColor.RED + profile.getExtraHorizontal());
        sender.sendMessage(ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Extra Vertical: " + ChatColor.RED + profile.getExtraVertical());
        sender.sendMessage(ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Recoil Multiplier: " + ChatColor.RED + profile.getRecoilMultiplier());
    }
}
