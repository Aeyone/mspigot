package net.hylist.profile.factory;

import com.google.common.collect.ImmutableList;
import net.hylist.json.JsonConfig;
import net.hylist.profile.craft.CraftKnockbackProfile;
import net.hylist.profile.KnockbackProfile;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

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
        profile.setSlowdown(Double.valueOf(config.getString(path + ".slowdown")));
        profile.setCancelSprint(Boolean.valueOf(config.getString(path + ".cancelSprint")));
    }

    @Override
    public void save(KnockbackProfile profile, JsonConfig config, String path) {
        config.set(path + ".friction", profile.getFriction());
        config.set(path + ".horizontal", profile.getHorizontal());
        config.set(path + ".vertical", profile.getVertical());
        config.set(path + ".verticalLimit", profile.getVerticalLimit());
        config.set(path + ".extraHorizontal", profile.getExtraHorizontal());
        config.set(path + ".extraVertical", profile.getExtraVertical());
        config.set(path + ".slowdown", profile.getSlowdown());
        config.set(path + ".cancelSprint", profile.isCancelSprint());
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
            profile.getSlowdown() + " " +
            profile.isCancelSprint()
        );
    }

    @Override
    public List<String> sendAll(KnockbackProfile profile) {
        return ImmutableList.of(
            ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Friction: " + ChatColor.RED + profile.getFriction(),
            ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Horizontal: " + ChatColor.RED + profile.getHorizontal(),
            ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Vertical: " + ChatColor.RED + profile.getVertical(),
            ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Vertical Limit: " + ChatColor.RED + profile.getVerticalLimit(),
            ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Extra Horizontal: " + ChatColor.RED + profile.getExtraHorizontal(),
            ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Extra Vertical: " + ChatColor.RED + profile.getExtraVertical(),
            ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Slowdown: " + ChatColor.RED + profile.getSlowdown(),
            ChatColor.GOLD + "-> " + ChatColor.YELLOW + "Cancel Sprint: " + ChatColor.RED + profile.isCancelSprint()
        );
    }

    @Override
    public boolean setAll(KnockbackProfile profile, String[] args) {
        if (args.length == 10) {
            profile.setFriction(Double.parseDouble(args[2]));
            profile.setHorizontal(Double.parseDouble(args[3]));
            profile.setVertical(Double.parseDouble(args[4]));
            profile.setVerticalLimit(Double.parseDouble(args[5]));
            profile.setExtraHorizontal(Double.parseDouble(args[6]));
            profile.setExtraVertical(Double.parseDouble(args[7]));
            profile.setSlowdown(Double.parseDouble(args[8]));
            profile.setCancelSprint(Boolean.parseBoolean(args[9]));
            return true;
        } else {
            return false;
        }
    }
}
