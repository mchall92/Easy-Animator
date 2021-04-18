package cs5004.animator.view;

import cs5004.animator.model.Window;

/**
 * This is the interface for views
 */
public interface IView {
    /**
     * Make the view visible. Called after the view is constructed
     */
    void makeVisible();

    void setAnimator(Window window);


}
