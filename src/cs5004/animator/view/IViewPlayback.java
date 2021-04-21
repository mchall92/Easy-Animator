package cs5004.animator.view;

import java.awt.event.ActionListener;

public interface IViewPlayback extends IViewVisual{

  /**
   * Pass actionEvent to Control Bar Panel to set button listeners.
   * @param actionEvent actionEvent to be passed to control bar panel.
   */
  public void passControlBarButtonListener(ActionListener actionEvent);

  public void pauseAnimation();

  public boolean isAnimatorPlaying();
}
