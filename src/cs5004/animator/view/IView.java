package cs5004.animator.view;

import cs5004.animator.model.Window;

/** This is the interface for views. */
public interface IView {
  /** Make the view visible. Called after the view is constructed. */
  void makeVisible();

  /**
   * Set an animator model to this view.
   * @param window the animator model
   */
  void setAnimator(Window window);
}
