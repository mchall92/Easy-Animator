package cs5004.animator.view;

import cs5004.animator.controller.PlaybackFeatures;

/**
 * This class represents a view with the function of user Interaction.
 */
public interface IViewPlayback extends IViewVisual {

  /**
   * This method controls what the image of buttons are
   * @param isPlaying controls the image of playing button
   * @param isLoop controls the image of looping button
   * @param isMuted controls the image of muting button
   */
  void displayControlButtons(boolean isPlaying, boolean isLoop, boolean isMuted);

  /**
   * This method
   */
  void displaySettingPanel();

  void addPlaybackFeatures(PlaybackFeatures playbackFeatures);

  void showId(boolean showId);

  void showInitialSpeed(int speed);

  void updateSpeed(int speed);
}
