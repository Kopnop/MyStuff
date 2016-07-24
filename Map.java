package csgomanager;

public enum Map {

  //DE_INFERNO("Inferno", 0.6), 
  DE_DUST2("Dust 2", 0.5), 
  DE_NUKE("Nuke", 0.8), 
  DE_MIRAGE("Mirage", 0.6), 
  DE_TRAIN("Train", 0.75), 
  DE_COBBLESTONE("Cobblestone", 0.55), 
  DE_OVERPASS("Overpass", 0.7), 
  DE_CACHE("Cache", 0.65);

  private double CTWinRate;
  private double TWinRate;
  private String name;

  Map(String s, double ct) {
    this.name = s;
    this.CTWinRate = ct;
    this.TWinRate = 1 - ct;
  }

  public boolean changeWinRates(double ct, double t) {
    if (ct + t == 1) {
      CTWinRate = ct;
      TWinRate = t;
      return true;
    }
    System.err.println("The sum of the winrates is not 1.");
    return false;
  }

  public double getCTWinRate() {
    return CTWinRate;
  }

  public void setCTWinRate(double cTWinRate) {
    CTWinRate = cTWinRate;
  }

  public double getTWinRate() {
    return TWinRate;
  }

  public void setTWinRate(double tWinRate) {
    TWinRate = tWinRate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
