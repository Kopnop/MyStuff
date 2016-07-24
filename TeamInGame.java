package csgomanager;

import java.util.ArrayList;

public class TeamInGame {

  private Team team;
  private ArrayList<PlayerInGame> players = new ArrayList<>();
  private Side side;
  private boolean timeoutAvailable;
  private int lossBonus;
  private int score;

//  public TeamInGame() {
//    this.team = new Team();
//    for (Player p : team.getPlayers()) {
//      players.add(new PlayerInGame(p));
//    }
//    this.timeoutAvailable = true;
//    this.lossBonus = 0;
//  }

  public TeamInGame(Team t) {
    this.team = t;
    for (Player p : t.getPlayers()) {
      players.add(new PlayerInGame(p));
    }
    this.timeoutAvailable = true;
    this.lossBonus = 0;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public Side getSide() {
    return side;
  }

  public void setSide(Side side) {
    this.side = side;
  }

  public void switchSides() {
    if (side.equals(Side.CT)) {
      side = Side.T;
    } else {
      side = Side.CT;
    }
  }

  public boolean isTimeoutAvailable() {
    return timeoutAvailable;
  }

  public void setTimeoutAvailable(boolean timeoutAvailable) {
    this.timeoutAvailable = timeoutAvailable;
  }

  public int getLossBonus() {
    return lossBonus;
  }

  public void setLossBonus(int lossBonus) {
    this.lossBonus = lossBonus;
  }
  
  public void resetLossBonus() {
    lossBonus = 0;
  }

  public void increaseLossBonus() {
    lossBonus += lossBonus < 4 ? 1 : 0;
  }
  
  public ArrayList<PlayerInGame> getPlayers() {
    return players;
  }

  public void setPlayers(ArrayList<PlayerInGame> players) {
    this.players = players;
  }

  public int getScore() {
    return score;
  }
  
  public void setScore(int score) {
    this.score = score;
  }
  
  public void increaseScore() {
    score++;
  }
  
  
}
