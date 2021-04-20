package cs5004.animator.view;

import cs5004.animator.model.Window;

/** This is the interface for views. */
public interface IViewCommon {

  /**
   * Set an animator model to this view.
   * @param window the animator model
   */
  void setModel(Window window);
}
