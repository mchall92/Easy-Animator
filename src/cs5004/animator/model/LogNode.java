package cs5004.animator.model;

/**
 * This class represents a LogNode which contains String representation of a transformation and the
 * time it happens.
 */
public class LogNode {
  private int time;
  private String info;

  public int getTime() {
    return time;
  }

  public String getInfo() {
    return info;
  }

  public LogNode(int time, String info) {
    this.time = time;
    this.info = info;
  }
}
