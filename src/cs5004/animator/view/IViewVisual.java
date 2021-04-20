package cs5004.animator.view;

/**
 * This is an interface for SwingView. It extends IViewCommon to achieve
 * interface segregation.
 */
public interface IViewVisual extends IViewCommon {

  /**
   * Set the frame name to file name.
   * @param fileName is the name of the input file.
   */
  public void setFileName(String fileName);

  /**
   * Set tempo of the animation.
   * @param tempo tempo is the speed fo the animation.
   */
  public void setTempo(int tempo);

  /** Make the view visible. Called after the view is constructed. */
  public void makeVisible();

  /**
   * Start the animation.
   */
  public void startAnimation();
}
