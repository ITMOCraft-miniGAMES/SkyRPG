package thor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;

import static thor.SkyRPG.plugin;

public class Game {
    public int number;
    public GameMap map;
    public Scoreboard scoreboard;
    public Team team1;
    public Team team2;
    public Objective objective;
    public ArrayList<Player> players;
    public int gameStep = 0;
    public int time = 30;
    public int CAPACITY = 20;
    public boolean canAddPlayer() {
        return gameStep==0&&players.size()<CAPACITY;
    }
    public Game(int n) {
        number=n;
        map = new GameMap(n);
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        scoreboard=manager.getNewScoreboard();
        objective=scoreboard.registerNewObjective("game"+n, "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        team1 = scoreboard.registerNewTeam(n+"1");
        team1.setColor(ChatColor.YELLOW);
        team1.setAllowFriendlyFire(false);
        team2 = scoreboard.registerNewTeam(n+"2");
        team2.setColor(ChatColor.GREEN);
        team2.setAllowFriendlyFire(false);
        players = new ArrayList<>();
    }
    public boolean addPlayer(Player player) {
        if (!canAddPlayer()) {
            return false;
        }
        time=30;
        player.setScoreboard(scoreboard);
        Team team = scoreboard.registerNewTeam(String.valueOf(number));
        if ((players.size()&1)==0) {
            team1.addPlayer(player);
            player.sendMessage(ChatColor.GREEN+"Вы попали в команду жёлтых! ");
            team.setColor(ChatColor.YELLOW);
        }
        else {
            team2.addPlayer(player);
            player.sendMessage(ChatColor.GREEN+"Вы попали в команду зелёных! ");
            team.setColor(ChatColor.GREEN);
        }
        team.addPlayer(player);
        player.setMetadata("team", new FixedMetadataValue(plugin, team));
        players.add(player);
        return true;
    }
    public void iter() {
        if (gameStep==0) {
            time--;
            if (time==0) {
                gameStep=1;
            }
        }
    }
}
