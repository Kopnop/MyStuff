package csgomanager;

public class PlayerInGame {

  private Player player;
  
  private int money;

  private double mood;

  private int equipment;

  public PlayerInGame() {
    this.player = new Player();
    money = 800;
    mood = 0.5;
    equipment = 0;
  }
  
  public PlayerInGame(Player p) {
    this.player = p;
    money = 800;
    mood = 0.5;
    equipment = 0;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public int getMoney() {
    return money;
  }
  
  public void setMoney(int money) {
    this.money = money;
  }
  
  public void adjustMoney(int delta) {
    if (delta < 0) {
      money = money - delta < 0 ? 0 : money - delta;
    } else {
      money = money + delta > 16000 ? 16000 : money + delta;
    }
  }

  public double getMood() {
    return mood;
  }

  public void setMood(double mood) {
    this.mood = mood;
  }

  public int getEquipment() {
    return equipment;
  }

  public void setEquipment(int equipment) {
    this.equipment = equipment;
  }
  
  
}
