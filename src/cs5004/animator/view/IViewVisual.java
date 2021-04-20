package cs5004.animator.view;

/**
 * This is an interface for SwingView. It extends IViewCommon to achieve
 * interface segregation.
 */
public interface IViewVisual extends IViewCommon {
  /** Make the view visible. Called after the view is constructed. */
  void makeVisible();
}
