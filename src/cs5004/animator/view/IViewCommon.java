package cs5004.animator.view;

import cs5004.animator.model.IModelView;

/** This is the interface for views. */
public interface IViewCommon {

  /**
   * Set an animator model to this view.
   * @param viewModel the animator model (view only)
   */
  void setViewModel(IModelView viewModel);
}