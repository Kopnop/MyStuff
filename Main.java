package csgomanager;

import java.util.Random;

public class Main {

  private static Random r = new Random();

  public static void main(String[] args) {
    Team t1 = new Team("Fnatic");
    Team t2 = new Team("NiP");
    System.out.println(t1);
    System.out.println(t2);
    // System.out.println("Gewonnen hat " + MatchCalculator.calulateMatch(t1, t2, Map.DE_INFERNO));
//    MatchCalculator.calculateMatch(t1, t2, Map.DE_DUST2);
    Match match = new Match(t1, t2, Map.DE_CACHE);
    match.calculateMatch();
  }
}
