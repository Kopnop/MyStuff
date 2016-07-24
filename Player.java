package csgomanager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

public class Player implements Serializable {

  protected int overallSkill;
  protected int pistolSkill;
  private int endurance;
  protected String name;
  private HashMap<Map, Integer> mapSkill = new HashMap<>();

  protected int experience;

  Random r = new Random();

  private static int nameOffset;

  public Player() {
    name = "Player" + nameOffset;
    nameOffset++;
    overallSkill = r.nextInt(100);
    experience = r.nextInt(100);
    mapSkill.put(Map.DE_DUST2, r.nextInt(100));
    mapSkill.put(Map.DE_NUKE, r.nextInt(100));
    mapSkill.put(Map.DE_MIRAGE, r.nextInt(100));
    mapSkill.put(Map.DE_TRAIN, r.nextInt(100));
    mapSkill.put(Map.DE_COBBLESTONE, r.nextInt(100));
    mapSkill.put(Map.DE_OVERPASS, r.nextInt(100));
    mapSkill.put(Map.DE_CACHE, r.nextInt(100));
  }

//  public Player(int overallSkill, int pistolSkill, String name, int experience) {
//    this.overallSkill = overallSkill;
//    this.pistolSkill = pistolSkill;
//    this.name = name;
//    this.experience = experience;
//  }



  @Override
  public String toString() {
    return name + ":( skill: " + overallSkill + ", experience: " + experience + ")";
  }

  public int getOverallSkill() {
    return overallSkill;
  }

  public void setOverallSkill(int overallSkill) {
    this.overallSkill = overallSkill;
  }

  public int getPistolSkill() {
    return pistolSkill;
  }

  public void setPistolSkill(int pistolSkill) {
    this.pistolSkill = pistolSkill;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getExperience() {
    return experience;
  }

  public void setExperience(int experience) {
    this.experience = experience;
  }


}
