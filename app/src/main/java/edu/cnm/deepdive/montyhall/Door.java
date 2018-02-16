package edu.cnm.deepdive.montyhall;

public class Door {

  enum Contains{
    GOAT, CAR
  }

  enum State{
    OPEN, CLOSED
  }

  private State state;
  private Contains contains;

  public Door(Contains contains) {
    this.contains = contains;
    this.state = State.CLOSED;
  }

  public void open() {
    state = State.OPEN;
  }

  public State getState() {
    return state;
  }

  public Contains getContains() {
    return contains;
  }
}
