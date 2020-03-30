package freedy.robotmaker.events;

import freedy.robotmaker.RobotMaker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;

public class QuitEvent implements Listener {
    RobotMaker plugin;

    public QuitEvent(RobotMaker plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onQuit(PlayerJoinEvent event) {
        plugin.removePlayer(event.getPlayer());
    }
}
