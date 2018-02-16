package edu.cnm.deepdive.montyhall;

/**
 * We were pre-assigned variables for our enum Contains and for enum State.
 */
public class Door {

  enum Contains{
    GOAT, CAR
  }

  enum State{
    OPEN, CLOSED
  }

  private State state;
  private Contains contains;

  /**
   * the Door method defines a Contains parameter based put in and defines the state parameter as
   * closed by default.
   * @param contains
   */
  public Door(Contains contains) {
    this.contains = contains;
    this.state = State.CLOSED;
  }

  /**
   * the open method defines the state parameter as open
   */
  public void open() {
    state = State.OPEN;
  }

  /**
   * the getState method gets the state of the door (open or closed).
   * @return  state
   */
  public State getState() {
    return state;
  }

  /**
   * the getContains method gets whatever the selected door contains (car or goat).
   * @return  contains
   */
  public Contains getContains() {
    return contains;
  }
}
