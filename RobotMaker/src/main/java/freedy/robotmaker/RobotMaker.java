package freedy.robotmaker;

import freedy.robotmaker.commands.MakeRobot;
import freedy.robotmaker.commands.SaveRobot;
import freedy.robotmaker.commands.ToggleBot;
import freedy.robotmaker.events.ControlBot;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class RobotMaker extends JavaPlugin {
    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("movebot").setExecutor(new MakeRobot(this));
        getCommand("savebot").setExecutor(new SaveRobot(this));
        getCommand("togglebot").setExecutor(new ToggleBot(this));
        getServer().getPluginManager().registerEvents(new ControlBot(this), this);
        getConfig().set("toggleBot", null);
        saveConfig();
    }

    public void saveRobot(Location pos1) {
        int x1 = pos1.getBlockX();
        int y1 = pos1.getBlockY();
        int z1 = pos1.getBlockZ();
        String xyz;
        Block block;
        World world = pos1.getWorld();
        for (int x = 1; x < 4; x++) {
            for (int y = 0; y < 7; y++) {
                for (int z = 1; z < 4; z++) {
                    xyz = x +","+ y +","+ z;
                    block = new Location(world, x1 + x, y1 + y, z1 + z).getBlock();
                    getConfig().set("blockData." + xyz, block.getType().toString());
                    getConfig().set("backup." + xyz, "AIR");
                }
            }
        }
        saveConfig();
    }
    public void moveBot(Location pos1, Location pos2) { //pos1 (넥스트 블럭)은 백업하고 페이스트 하는 자리, pos2 (프리비어스 블럭)는 백업을 불러오는 자리.
        int x1 = pos1.getBlockX();
        int y1 = pos1.getBlockY();
        int z1 = pos1.getBlockZ();
        int x2 = pos2.getBlockX();
        int y2 = pos2.getBlockY();
        int z2 = pos2.getBlockZ();
        String vecLoc;
        Block block;
        World world = pos1.getWorld();
        for (int x = 1; x < 4; x++) {
            for (int y = 0; y < 7; y++) {
                for (int z = 1; z < 4; z++) {
                    vecLoc = x +","+ y +","+ z;
                    block = new Location(world, x2 + x, y2 + y, z2 + z).getBlock();
                    block.setType(Material.valueOf(getConfig().getString("backup." + vecLoc)));

                    block = new Location(world, x1 + x, y1 + y, z1 + z).getBlock();
                    getConfig().set("backup." + vecLoc, block.getType().toString());
                    block.setType(Material.valueOf(getConfig().getString("blockData." + vecLoc)));
                }
            }
        }
        saveConfig();
    }
    public void botRemover(Location pos2) {
        int x2 = pos2.getBlockX();
        int y2 = pos2.getBlockY();
        int z2 = pos2.getBlockZ();
        String vecLoc;
        Block block;
        World world = pos2.getWorld();
        for (int x = 1; x < 4; x++) {
            for (int y = 0; y < 7; y++) {
                for (int z = 1; z < 4; z++) {
                    vecLoc = x +","+ y +","+ z;
                    block = new Location(world, x2 + x, y2 + y, z2 + z).getBlock();
                    block.setType(Material.valueOf(getConfig().getString("backup." + vecLoc)));
                }
            }
        }
        saveConfig();
    }


    private ArrayList<Player> players = new ArrayList<>();
    public void addPlayer(Player player) {
        players.add(player);
    }
    public boolean isPlayer(Player player) {
        return players.contains(player);
    }
    public void removePlayer(Player player) {
        players.remove(player);
    }
}
