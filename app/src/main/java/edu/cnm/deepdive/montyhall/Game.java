package edu.cnm.deepdive.montyhall;

import android.widget.Toast;
import edu.cnm.deepdive.montyhall.Door.Contains;
import edu.cnm.deepdive.montyhall.Door.State;
import java.util.ArrayList;
import java.util.Collections;

/**
 * the Game class establishes the fields that are used to determine how the game works, whether you
 * have won or lost and updates the corresponding tallies based on your decisions.
 */
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

  /**
   * the setup method takes the Contains method and assigns a goat or a car to a door and shuffles
   * them around so that they are not the same each turn. This is done at the <stage.Beginning> of
   * each turn.
   */
  public void setup() {
    doors = new ArrayList<>();
    doors.add(new Door(Contains.GOAT));
    doors.add(new Door(Contains.GOAT));
    doors.add(new Door(Contains.CAR));
    Collections.shuffle(doors);
    stage = Stage.BEGINNING;
  }
  /**
   * the <behindDoor> method takes whatever door was selected and determines whether it was a car or
   * a goat.
   */
  public Contains behindDoor(int i) {

    return doors.get(i).getContains();
  }

  /**
   * the <doorState> method determines whether the door is in the open or closed state.
   * @param i   i
   * @return    returns the door open or closed.
   */
  public State doorState(int i) {

    return doors.get(i).getState();
  }

  /**
   * the <selectedDoor> method states that if we are at the beginning and the first door is selected
   * then either door 2 or door 3 is revealed. If door 3 is chosen then it will reveal either door 1
   * or door 2. if door 2 is chosen then either door 3 or door 1 is revealed. at the end of this method
   * you will have chosen one of 3 doors and another will have been revealed to you as a goat.
   * @param i
   */
  public void selectedDoor(int i) {
    if (stage != Stage.BEGINNING) {
      throw new RuntimeException("Invalid Stage");
    }
    chosen_door = doors.get(i);
    if (doors.get(i).getContains() == Contains.CAR) {
      if (chosen_door == doors.get(2)) {
        doors.get(i - 1).open();
      } else {
        doors.get(i + 1).open();
      }
    } else {
      for (Door extraDoor : doors) {
        if (extraDoor == chosen_door) {
          continue;
        }
        if (extraDoor.getContains() == Contains.CAR) {
          continue;
        }
        extraDoor.open();
      }
    }
    for (
        Door door : doors)

    {
      if (door == chosen_door) {
        continue;

      }
      switch_door = door;
    }

    stage = Stage.DOOR_CHOSEN;
  }

  /**
   * the <switchDoors> method allows you the ability to switch to a different door than the one you
   * originally selected as long as that door is still closed. If you switch doors and win then the
   * won_switched tally will be updated.
   */
  public void switchDoors() {
    if (stage != Stage.DOOR_CHOSEN) {
      throw new RuntimeException("Invalid Stage");

    }
    chosen_door = switch_door;
    stage = stage.END;
    for (Door door : doors) {
      if (door.getState() == State.CLOSED) {
        door.open();
      }
      continue;
    }
    total_switched++;
    if (chosen_door.getContains() == Contains.CAR) {
      won_switched++;
    }
  }

  /**
   * the <stayDoor> method allows you keep your original door selection. If you win then the won_stayed
   * tally will be updated.
   */
  public void stayDoor() {
    if (stage != Stage.DOOR_CHOSEN) {
      throw new RuntimeException("Invalid Stage");
    }

    stage = Stage.END;
    for (Door door : doors) {
      if (door.getState() == State.CLOSED) {
        door.open();
      }
      continue;
    }
    total_stayed++;
    if (chosen_door.getContains() == Contains.CAR) {
      won_stayed++;
    }
  }

  /**
   * this method allows you to reset the tally for won_stayed, won_switched, total_switched and
   * total_stayed.
   */
  public void resetTally() {
    won_stayed = 0;
    won_switched = 0;
    total_stayed = 0;
    total_switched = 0;
  }

  /**
   * this method gives you the success rate when you switch your original door choice. If you switch
   * then regardless of whether you win or lose the total_switched tally will be updated. If you also
   * win and switch then the won_switched tally will be updated as well.
   * @return  a new tally.
   */
  public double getSwitchSuccessRate() {
    if (total_switched == 0)
      return 0;
    return (double) won_switched / total_switched * 100;
  }

  /**
   this method gives you the success rate when you stay with your original door choice. If you stay
   * then regardless of whether you win or lose the total_stayed tally will be updated. If you also
   * win and stay then the won_stayed tally will be updated as well.
   * @return  a new tally.
   */
  public double getStaySuccessRate() {
    if (total_stayed == 0)
      return 0;
    return (double) won_stayed / total_stayed * 100;
  }

  /**
   * this method places the contents on the stage.
   * @return stage state.
   */
  public Stage getStage() {
    return stage;
  }

  /**
   * this method determines whether the door is selected.
   * @param i boolean
   * @return  true, if the door you are looking at is the chosen door.
   */
  public boolean isDoorSelected(int i) {
    return chosen_door == doors.get(i);
  }

  /**
   * the <isWin> method determines whether the final door you selected has a car or a goat in it thus
   * determining whether you have won or lost.
   * @return    true if car || return false if goat.
   */
  public boolean isWin() {
      if (chosen_door.getContains() == Contains.CAR) {
        return true;
      }
      return false;
    }
  }



