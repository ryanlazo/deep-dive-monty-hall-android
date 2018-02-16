package edu.cnm.deepdive.montyhall;

import edu.cnm.deepdive.montyhall.Door.Contains;
import edu.cnm.deepdive.montyhall.Door.State;
import java.util.ArrayList;


public class Game {

  enum Stage {
    BEGINNING, DOOR_CHOSEN, END
  }

  private Stage stage;

  private int won_stayed;
  private int total_stayed;
  private int won_switched;
  private int total_switched;

  private ArrayList<Door> doors;
  private Door switch_door;
  private Door chosen_door;

  public void setup(){

  }

  public Contains behindDoor(int i){
    return doors.get(i).getContains();
  }

  public State doorState(int i){
    return doors.get(i).getState();
  }

  public void openDoor(int i) {
    if (stage != Stage.BEGINNING)throw new RuntimeException("Invalid Stage");

  }

  public void switchDoors() {
    if (stage != Stage.DOOR_CHOSEN)throw new RuntimeException("Invalid Stage");

  }

  public void stayDoor() {
    if (stage != Stage.DOOR_CHOSEN)throw new RuntimeException("Invalid Stage");

  }

  public void resetTally(){
    won_stayed = 0;
    won_switched = 0;
    total_stayed = 0;
    total_switched = 0;
  }

  public double getSwitchSuccessRate(){
    if (total_switched == 0)return 0;
    return (double)won_switched/total_switched*100;
  }

  public double getStaySuccessRate(){
    if (total_stayed == 0)return 0;
    return (double)won_stayed/total_stayed*100;
  }

  public Stage getStage() {
    return stage;
  }

  public boolean isDoorSelected(int i) {
    return chosen_door == doors.get(i);
  }
}
