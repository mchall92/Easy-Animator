package cs5004.animator.model.buildersrc;

/** This class represents a transition in builder. */
public abstract class Trans {
  protected String name;

  /**
   * Getter of name.
   *
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   * Constructor.
   *
   * @param name id of the element
   */
  public Trans(String name) {
    this.name = name;
  }
}
