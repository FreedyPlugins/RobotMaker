package freedy.robotmaker.commands;

import freedy.robotmaker.RobotMaker;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ToggleBot implements CommandExecutor {


    RobotMaker plugin;

    public ToggleBot(RobotMaker plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Location loc = player.getLocation();
        if (plugin.isPlayer(player)) {
            plugin.removePlayer(player);
            plugin.botRemover(loc.clone().add(-2, -2, -2));
            player.sendMessage("봇이 삭제되었습니다");
        } else {
            plugin.addPlayer(player);
            plugin.moveBot(loc.clone().add(-2, -2, -2), loc.clone().add(-2, -2, -2));
            player.sendMessage("봇이 생성되었습니다");
        }
        return true;
    }
}
