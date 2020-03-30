package freedy.robotmaker.commands;

import freedy.robotmaker.RobotMaker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SaveRobot implements CommandExecutor {

    private RobotMaker plugin;

    public SaveRobot(RobotMaker plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        plugin.saveRobot(player.getLocation());
        return true;
    }
}
