package freedy.robotmaker.commands;

import freedy.robotmaker.RobotMaker;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MakeRobot implements CommandExecutor {


    private RobotMaker plugin;

    public MakeRobot(RobotMaker pugin) {
        this.plugin = pugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Location loc = player.getLocation().clone();
        Location nextLoc = loc.clone();
        if (args.length == 2) {
            int x = Integer.parseInt(args[0]);
            int z = Integer.parseInt(args[1]);
            nextLoc.add(x, 0, z);
            plugin.moveBot(nextLoc.clone().add(-2, -2, -2), loc.clone().add(-2, -2, -2)); //pos1 은 백업하고 로봇을 만드는 자리, pos2는 백업을 불러오는 자리.
            player.teleport(nextLoc);
        }else
            player.sendMessage("사용법: /movebot <go/back> <x> <z>");
        return true;
    }
}
