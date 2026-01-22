package net.hylist.command;

import java.util.Arrays;
import net.hylist.profile.CraftPotionProfile;
import net.hylist.profile.PotionProfile;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.spigotmc.SpigotConfig;

public class PotionCommand extends Command {

    public PotionCommand() {
        super("minehqPotion");

        this.setAliases(Arrays.asList("potion"));
        this.setUsage(StringUtils.join(new String[]{
                ChatColor.BLUE + ChatColor.STRIKETHROUGH.toString() + StringUtils.repeat("-", 35),
		        ChatColor.RED + "/potion list",
                ChatColor.RED + "/potion create <name>",
                ChatColor.RED + "/potion delete <name>",
                ChatColor.RED + "/potion update <name> <f> <m> <o> <fsl> <eh> <ev>",
                ChatColor.RED + "/potion setglobal <name>",
                ChatColor.RED + "/potion reload",
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
                SpigotConfig.potionManager.sendInfo(sender);
            }
            break;
            case "create": {
                if (args.length > 1) {
                    String name = args[1];

                    for (PotionProfile profile : SpigotConfig.potionManager.getProfiles()) {
                        if (profile.getName().equalsIgnoreCase(name)) {
                            sender.sendMessage(ChatColor.RED + "A profile with that name already exists.");
                            return true;
                        }
                    }

                    CraftPotionProfile profile = new CraftPotionProfile(name);

                    SpigotConfig.potionManager.getProfiles().add(profile);
                    SpigotConfig.potionManager.saveProfiles();

                    sender.sendMessage(ChatColor.GOLD + "New profile created.");
                } else {
                    sender.sendMessage(ChatColor.RED + "Usage: /potion create <name>");
                }
            }
            break;
            case "delete": {
                if (args.length > 1) {
                    final String name = args[1];

                    if (SpigotConfig.potionManager.getGlobalProfile().getName().equalsIgnoreCase(name)) {
                        sender.sendMessage(ChatColor.RED + "You can't delete the active global potion profile.");
                        return true;
                    } else {
                        if (SpigotConfig.potionManager.getProfiles().removeIf(profile -> profile.getName().equalsIgnoreCase(name))) {
                            SpigotConfig.potionManager.saveProfiles();
                            sender.sendMessage(ChatColor.RED + "Deleted profile.");
                        } else {
                            sender.sendMessage(ChatColor.RED + "A profile with that name couldn't be found.");
                        }

                        return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Usage: /potion delete <name>");
                }
            }
            break;
            case "update": {
                if (args.length == 8) {
                    PotionProfile profile = SpigotConfig.potionManager.getProfileByName(args[1]);

                    if (profile == null) {
                        sender.sendMessage(ChatColor.RED + "A profile with that name couldn't be found.");
                        return true;
                    }

                    profile.setPotionFall(Float.parseFloat(args[2]));
                    profile.setPotionMultiplier(Float.parseFloat(args[3]));
                    profile.setPotionOffset(Float.parseFloat(args[4]));
                    profile.setFollowSpeedLevel(Boolean.parseBoolean(args[5]));
                    profile.setPotionExtraHorizontal(Double.parseDouble(args[6]));
                    profile.setPotionExtraVertical(Double.parseDouble(args[7]));

                    SpigotConfig.potionManager.saveProfiles();

                    sender.sendMessage(ChatColor.GREEN + "Updated values.");
                } else {
                    sender.sendMessage(ChatColor.RED + "Wrong syntax.");
                }
            }
            break;
            case "setglobal": {
                if (args.length > 1) {
                    PotionProfile profile = SpigotConfig.potionManager.getProfileByName(args[1]);

                    if (profile == null) {
                        sender.sendMessage(ChatColor.RED + "A profile with that name couldn't be found.");
                        return true;
                    }

                    SpigotConfig.potionManager.setGlobalProfile(profile);
                    SpigotConfig.potionManager.saveProfiles();

                    sender.sendMessage(ChatColor.GREEN + "Global profile set to " + profile.getName() + ".");
                    return true;
                }
            }
            break;
            case "reload": {
                SpigotConfig.potionManager.loadProfile();
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
