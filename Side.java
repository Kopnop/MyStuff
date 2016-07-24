package csgomanager;

public enum Side {

  CT("Counter-Terrorist"), 
  T("Terrorist");

  String name;

  Side(String s) {
    name = s;
  }
}
