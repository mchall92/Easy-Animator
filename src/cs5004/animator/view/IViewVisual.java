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
  void setFileName(String fileName);

  /** Make the view visible. Called after the view is constructed. */
  void makeVisible();

  void repaint(int time);
}
