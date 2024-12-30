package thor;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.structure.Structure;
import org.bukkit.structure.StructureManager;

import java.util.Random;

import static thor.SkyRPG.plugin;
import static thor.SkyRPG.world;

public class GameMap {
    public int number;
    public int X;
    public int Y;
    public int Z;
    public Location lobbyLoc;
    public void createLobby() {
        StructureManager manager = plugin.getServer().getStructureManager();
        Structure structure = manager.createStructure();
        structure.fill(new Location(world, 0, 0, 0), new Location(world, 12, 6, 30), true);
        lobbyLoc = new Location(world, 50+50*number, 0, 50+50*number);
        structure.place(lobbyLoc, true, StructureRotation.NONE, Mirror.NONE, 0, 1, new Random());
    }
    public void setCoords(int n) {
        X=1000*n+1000;
        Y=0;
        Z=1000*n+1000;
    }
    public GameMap(int n) {
        number=n;
        setCoords(n);
        createLobby();
        for (int i = 0; i <= 180; i++) {
            for (int j = 0; j <= 100; j++) {
                for (int k = 0; k <= 140; k++) {
                    Block block = world.getBlockAt(X+i, Y+j, Z+k);
                    if (i==0||i==80||i==100||i==180||k==0||k==140||j==100) {
                        if (block.getType()!= Material.BARRIER) {
                            block.setType(Material.BARRIER);
                        }
                    }
                    else if (j==0&&k>=50&&k<=90&&(i>=50&&i<=70||i>=110&&i<=130)) {
                        if (block.getType()!=Material.BEDROCK) {
                            block.setType(Material.BEDROCK);
                        }
                    }
                    else if (j==1&&k==70&&(i==60||i==120)) {
                        block.setType(Material.RESPAWN_ANCHOR);
                        if (i==60) {
                            block.setMetadata("anchor", new FixedMetadataValue(plugin, n+"1"));
                        }
                        else {
                            block.setMetadata("anchor", new FixedMetadataValue(plugin, n+"2"));
                        }
                        RespawnAnchor anchor = (RespawnAnchor) block.getBlockData();
                        anchor.setCharges(anchor.getMaximumCharges());
                        block.setBlockData(anchor);
                    }
                    else {
                        if (block.getType()!=Material.AIR) {
                            block.setType(Material.AIR);
                        }
                    }
                }
            }
        }
    }
}
