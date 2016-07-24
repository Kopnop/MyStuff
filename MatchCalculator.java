package csgomanager;

import java.util.Random;

public class MatchCalculator {

  private static MatchCalculator instance;
  private static Random r = new Random();

  public static MatchCalculator getInstance() {
    if (instance == null) {
      new MatchCalculator();
    }
    return instance;
  }

  // public static MatchCalculator newInstance() {
  // new MatchCalculator();
  // return instance;
  // }

  private MatchCalculator() {
    instance = this;
  }

  public static Team calculateMatch(Team t1, Team t2, Map m) {
//    int roundsTeam1 = 0;
//    int roundsTeam2 = 0;
    int roundsPlayed = 0;
    int maxRounds = 30;
    int roundsNeeded = 16;
    Team winner = null;
    int overtimeCounter = 0;
    TeamInGame teamGame1 = new TeamInGame(t1);
    TeamInGame teamGame2 = new TeamInGame(t2);
    knifeRound(teamGame1, teamGame2);
    do {
      do {
        roundsPlayed++;
        if (calculateNextRound(teamGame1, teamGame2, m)) {
          aftermath(teamGame1, teamGame2);
        } else {
          aftermath(teamGame2, teamGame1);
        }
        System.out.println("Round " + roundsPlayed + ": " + teamGame1.getScore() + " - " + teamGame2.getScore());
        if (roundsPlayed == 15 || roundsPlayed == 30 + 3 * overtimeCounter) {
          System.out.println("Sides switched");
          teamGame1.switchSides();
          teamGame2.switchSides();
        }
      } while ((teamGame1.getScore() != roundsNeeded) && (teamGame2.getScore() != roundsNeeded)
          && (roundsPlayed != maxRounds));

      if (teamGame1.getScore() == roundsNeeded) {
        winner = t1;
      } else if (teamGame2.getScore() == roundsNeeded) {
        winner = t2;
      } else {
        roundsNeeded += 3;
        maxRounds += 6;
        System.out.println("Going into OT" + ++overtimeCounter);
      }
    } while (winner == null);
    // System.out.println(winner.getName() + " won the match with "
    // + (roundsTeam1 == roundsNeeded ? roundsTeam1 : roundsTeam2) + " - "
    // + (roundsTeam1 == roundsNeeded ? roundsTeam2 : roundsTeam1));
    String s = teamGame1.getScore() == roundsNeeded
        ? "Team 1 '" + winner.getName() + "' won the Match with " + teamGame1.getScore() + " - "
            + teamGame2.getScore()
        : "Team 2 " + winner.getName() + " won the Match with " + teamGame2.getScore() + " - " + teamGame1.getScore();
    s += overtimeCounter > 0 ? " in Overtime " + overtimeCounter : "";
    // System.out.println((roundsTeam1 == roundsNeeded
    // ? "Team 1 '" + winner.getName() + "' won the match with " + roundsTeam1 + " - "
    // + roundsTeam2
    // : "Team 2 " + winner.getName() + " won the match with " + roundsTeam2 + " - " + roundsTeam1)
    // + (OvertimeCounter > 0 ? " in Overtime " + OvertimeCounter : ""));
    System.out.println(s);
    return winner;
  }

  public static void aftermath(TeamInGame winner, TeamInGame loser) {
    winner.increaseScore();
    for (PlayerInGame p : winner.getPlayers()) {
      p.adjustMoney(3400);
      winner.resetLossBonus();
    }
    for (PlayerInGame p : loser.getPlayers()) {
      p.adjustMoney(1400 + loser.getLossBonus());
      loser.increaseLossBonus();
    }
  }
  // true, if first team wins
  public static boolean calculateNextRound(TeamInGame t1, TeamInGame t2, Map map) {
    int sumT1 = 0;
    int sumT2 = 0;
    for (Player p : t1.getTeam().getPlayers()) {
      sumT1 += p.getOverallSkill() + p.getExperience();
    }
    t1.getTeam().getSkillOnMap(map);
    for (Player p : t2.getTeam().getPlayers()) {
      sumT2 += p.getOverallSkill() + p.getExperience();
    }

    double t1Win = (double) sumT1 / (sumT1 + sumT2);
    // double t2Win = (double) sumT2 / (sumT1 + sumT2);
    // System.out.println(sumT1 + " " + (sumT1 + sumT2));
    // System.out.println("Team1Win%: " + t1Win);
    if (r.nextDouble() <= t1Win) {
      return true;
    } else {
      return false;
    }
  }

  // true, if t1 won
  public static boolean knifeRound(TeamInGame t1, TeamInGame t2) {
    int sumT1 = 0;
    int sum = 0;
    for (PlayerInGame p : t1.getPlayers()) {
      sumT1 += p.getPlayer().getExperience();
    }
    sum += sumT1;
    for (PlayerInGame p : t2.getPlayers()) {
      sum += p.getPlayer().getExperience();
    }
    if (r.nextDouble() <= ((double) sumT1 / sum)) {
      t1.setSide(Side.CT);
      t2.setSide(Side.T);
      System.out.println("Team 1 won the kniferound and starts as CT.");
      return true;
    } else {
      t1.setSide(Side.T);
      t2.setSide(Side.CT);
      System.out.println("Team 2 won the kniferound and starts as CT.");
      return false;
    }
  }
}
