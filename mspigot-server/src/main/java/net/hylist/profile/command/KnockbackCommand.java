package net.hylist.profile.command;

import java.util.Arrays;
import net.hylist.profile.craft.CraftKnockbackProfile;
import net.hylist.profile.KnockbackProfile;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.spigotmc.SpigotConfig;

public class KnockbackCommand extends Command {

    public KnockbackCommand() {
        super("minehqKnockback");

        this.setAliases(Arrays.asList("knockback", "kb"));
        this.setUsage(StringUtils.join(new String[]{
                ChatColor.BLUE + ChatColor.STRIKETHROUGH.toString() + StringUtils.repeat("-", 35),
		        ChatColor.RED + "/kb list",
                ChatColor.RED + "/kb create <name>",
                ChatColor.RED + "/kb delete <name>",
                ChatColor.RED + "/kb update <name> <......>",
                ChatColor.RED + "/kb setglobal <name>",
                ChatColor.RED + "/kb reload",
                ChatColor.BLUE + ChatColor.STRIKETHROUGH.toString() + StringUtils.repeat("-", 35)
        }, "\n"));
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "Unknown command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(usageMessage);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "list": {
                SpigotConfig.knockbackManager.sendInfo(sender);
            }
            break;
            case "create": {
                if (args.length > 1) {
                    String name = args[1];

                    for (KnockbackProfile profile : SpigotConfig.knockbackManager.getProfiles()) {
                        if (profile.getName().equalsIgnoreCase(name)) {
                            sender.sendMessage(ChatColor.RED + "A profile with that name already exists.");
                            return true;
                        }
                    }

                    CraftKnockbackProfile profile = new CraftKnockbackProfile(name);

                    SpigotConfig.knockbackManager.getProfiles().add(profile);
                    SpigotConfig.knockbackManager.saveProfiles();

                    sender.sendMessage(ChatColor.GOLD + "New profile created.");
                } else {
                    sender.sendMessage(ChatColor.RED + "Usage: /kb create <name>");
                }
            }
            break;
            case "delete": {
                if (args.length > 1) {
                    final String name = args[1];

                    if (SpigotConfig.knockbackManager.getGlobalProfile().getName().equalsIgnoreCase(name)) {
                        sender.sendMessage(ChatColor.RED + "You can't delete the active global knockback profile.");
                        return true;
                    } else {
                        if (SpigotConfig.knockbackManager.getProfiles().removeIf(profile -> profile.getName().equalsIgnoreCase(name))) {
                            SpigotConfig.knockbackManager.saveProfiles();
                            sender.sendMessage(ChatColor.RED + "Deleted profile.");
                        } else {
                            sender.sendMessage(ChatColor.RED + "A profile with that name couldn't be found.");
                        }

                        return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Usage: /kb delete <name>");
                }
            }
            break;
            case "update": {
                KnockbackProfile profile = SpigotConfig.knockbackManager.getProfileByName(args[1]);
                if (profile == null) {
                    sender.sendMessage(ChatColor.RED + "A profile with that name couldn't be found.");
                    return true;
                }
                if (SpigotConfig.knockbackManager.setAll(profile, args)) {
                    SpigotConfig.knockbackManager.saveProfiles();
                    sender.sendMessage(ChatColor.GREEN + "Updated values.");
                    SpigotConfig.knockbackManager.sendInfo(sender);
                } else {
                    sender.sendMessage(ChatColor.RED + "Wrong syntax.");
                }
            }
            break;
            case "setglobal": {
                if (args.length > 1) {
                    KnockbackProfile profile = SpigotConfig.knockbackManager.getProfileByName(args[1]);

                    if (profile == null) {
                        sender.sendMessage(ChatColor.RED + "A profile with that name couldn't be found.");
                        return true;
                    }

                    SpigotConfig.knockbackManager.setGlobalProfile(profile);
                    SpigotConfig.knockbackManager.saveProfiles();

                    sender.sendMessage(ChatColor.GREEN + "Global profile set to " + profile.getName() + ".");
                    SpigotConfig.knockbackManager.sendInfo(sender);
                    return true;
                }
            }
            break;
            case "reload": {
                SpigotConfig.knockbackManager.loadProfile();
                sender.sendMessage(ChatColor.GREEN + "reloaded.");
            }
            break;
            default: {
                sender.sendMessage(usageMessage);
            }
        }

        return true;
    }

}
