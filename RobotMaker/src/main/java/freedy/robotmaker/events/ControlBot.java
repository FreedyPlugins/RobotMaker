package freedy.robotmaker.events;

import freedy.robotmaker.RobotMaker;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class ControlBot implements Listener {

    RobotMaker plugin;

    public ControlBot(RobotMaker plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onHand(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        Location loc  = player.getLocation().clone();
        Location nextLoc = loc.clone();
        int hotbar = player.getInventory().getHeldItemSlot();
        if (plugin.isPlayer(player)) {
            if (hotbar == 1) nextLoc.add(3, 0, 0);
            if (hotbar == 8) nextLoc.add(-3, 0, 0);
            plugin.moveBot(nextLoc.clone().add(-2, -2, -2), loc.clone().add(-2, -2, -2)); //pos1 은 백업하고 로봇을 만드는 자리, pos2는 백업을 불러오는 자리.
            player.teleport(nextLoc);
            player.getInventory().setHeldItemSlot(0);
        }
    }
}
