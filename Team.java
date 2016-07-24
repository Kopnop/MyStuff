package csgomanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class Team implements Serializable {

  private HashMap<Map, Integer> mapSkill = new HashMap<>();
  protected ArrayList<Player> players = new ArrayList<>(5);
  protected ArrayList<Player> benchedPlayers = new ArrayList<>();
  protected String name;
  protected double forceBuyProbability;
  private Random r = new Random();

//  public Team() {
//    for (int i = 0; i < 5; i++) {
//      players.add(new Player());
//    }
//  }

  public Team(String name) {
    for (int i = 0; i < 5; i++) {
      players.add(new Player());
    }
    this.name = name;
    mapSkill.put(Map.DE_DUST2, r.nextInt(100));
    mapSkill.put(Map.DE_NUKE, r.nextInt(100));
    mapSkill.put(Map.DE_MIRAGE, r.nextInt(100));
    mapSkill.put(Map.DE_TRAIN, r.nextInt(100));
    mapSkill.put(Map.DE_COBBLESTONE, r.nextInt(100));
    mapSkill.put(Map.DE_OVERPASS, r.nextInt(100));
    mapSkill.put(Map.DE_CACHE, r.nextInt(100));
  }
  
  /* DE_DUST2("Dust 2", 0.5), 
  DE_NUKE("Nuke", 0.8), 
  DE_MIRAGE("Mirage", 0.6), 
  DE_TRAIN("Train", 0.75), 
  DE_COBBLESTONE("Cobblestone", 0.55), 
  DE_OVERPASS("Overpass", 0.7), 
  DE_CACHE("Cache", 0.65);
   */

  public Team(ArrayList<Player> players, ArrayList<Player> benchedPlayers, String name,
      double forceBuyProbability) {
    this.players = players;
    this.benchedPlayers = benchedPlayers;
    this.name = name;
    this.forceBuyProbability = forceBuyProbability;
  }

  @Override
  public String toString() {
    String s = name + ": [";
    for (Player p : players) {
      s += p.toString() + "; ";
    }
    return s + "]";
  }

  public String getName() {
    return name;
  } 

  public ArrayList<Player> getPlayers() {
    return players;
  }

  public void setPlayers(ArrayList<Player> players) {
    this.players = players;
  }
  
  public void addPlayer(Player p) {
    players.add(p);
  }
  
  public void removePlayer(Player p) {
    players.remove(p);
  }

  public double getForceBuyProbability() {
    return forceBuyProbability;
  }

  public void setForceBuyProbability(double forceBuyProbability) {
    this.forceBuyProbability = forceBuyProbability;
  }

  public void setName(String name) {
    this.name = name;
  }

  public HashMap<Map, Integer> getMapSkill() {
    return mapSkill;
  }

  public void setMapSkill(HashMap<Map, Integer> mapSkill) {
    this.mapSkill = mapSkill;
  }
  
  public void changeSkillOnMap(Map mapToChange, int newSkill) {
    if (newSkill < 0 || newSkill > 100) {
      return;
    }
    for (Entry<Map, Integer> entry : mapSkill.entrySet()) {
      if (entry.getKey().equals(mapToChange)) {
        entry.setValue(newSkill);
        return;
      }
    }
  }
  
  public int getSkillOnMap(Map currMap) {
    for (Entry<Map, Integer> entry : mapSkill.entrySet()) {
      if (entry.getKey().equals(currMap)) {
        return entry.getValue();
      }
    }
    //shouldnt reach this
    return -1;
  }
}
