package csgomanager;

public class Match {

  TeamInGame team1;
  TeamInGame team2;
  Team winner;
  Map map;
  int roundsPlayed;
  int maxRounds;
  int roundsNeeded;
  int overtimeCounter;
  MatchCalculator mc = MatchCalculator.getInstance();

  public Match(Team t1, Team t2, Map m) {
    this.team1 = new TeamInGame(t1);
    this.team2 = new TeamInGame(t2);
    this.map = m;
    roundsPlayed = 0;
    maxRounds = 30;
    roundsNeeded = 16;
    overtimeCounter = 0;
  }

  public Team calculateMatch() {
    if (roundsPlayed == 0) {
      mc.knifeRound(team1, team2);
    }
    do {
      do {
        roundsPlayed++;
        if (mc.calculateNextRound(team1, team2, map)) {
          mc.aftermath(team1, team2);
        } else {
          mc.aftermath(team2, team1);
        }
        System.out
            .println("Round " + roundsPlayed + ": " + team1.getScore() + " - " + team2.getScore());
        checkHalftime();
      } while (!isOver());

      if (team1.getScore() == roundsNeeded) {
        winner = team1.getTeam();
      } else if (team2.getScore() == roundsNeeded) {
        winner = team2.getTeam();
      } else {
        roundsNeeded += 3;
        maxRounds += 6;
        System.out.println("Going into OT" + ++overtimeCounter);
      }
    } while (winner == null);
    printWinner();
    return winner;
  }

  public void printWinner() {
    // System.out.println(winner.getName() + " won the match with "
    // + (roundsTeam1 == roundsNeeded ? roundsTeam1 : roundsTeam2) + " - "
    // + (roundsTeam1 == roundsNeeded ? roundsTeam2 : roundsTeam1));
    String s = team1.getScore() == roundsNeeded
        ? "Team 1 '" + winner.getName() + "' won the Match with " + team1.getScore() + " - "
            + team2.getScore()
        : "Team 2 " + winner.getName() + " won the Match with " + team2.getScore() + " - "
            + team1.getScore();
    s += overtimeCounter > 0 ? " in Overtime " + overtimeCounter : "";
    // System.out.println((roundsTeam1 == roundsNeeded
    // ? "Team 1 '" + winner.getName() + "' won the match with " + roundsTeam1 + " - "
    // + roundsTeam2
    // : "Team 2 " + winner.getName() + " won the match with " + roundsTeam2 + " - " + roundsTeam1)
    // + (OvertimeCounter > 0 ? " in Overtime " + OvertimeCounter : ""));
    System.out.println(s);
  }

  public void checkHalftime() {
    if (roundsPlayed == 15 || roundsPlayed == 30 + 3 * overtimeCounter) {
      System.out.println("Sides switched");
      team1.switchSides();
      team2.switchSides();
    }
  }

  public boolean isOver() {
    if ((team1.getScore() == roundsNeeded || team2.getScore() == roundsNeeded
        || roundsPlayed == maxRounds)) {
      return true;
    } else {
      return false;
    }
  }

}
