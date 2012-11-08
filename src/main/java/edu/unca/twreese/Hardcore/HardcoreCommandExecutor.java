package edu.unca.twreese.Hardcore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/*
 * This is a sample CommandExectuor
 */
public class HardcoreCommandExecutor implements CommandExecutor {
    @SuppressWarnings("unused")
	private final Hardcore plugin;

    /*
     * This command executor needs to know about its plugin from which it came from
     */
    public HardcoreCommandExecutor(Hardcore plugin) {
        this.plugin = plugin;
    }

    /*
     * On command set the sample message
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0){
        	return false;
        }
        return false;
    }

}
