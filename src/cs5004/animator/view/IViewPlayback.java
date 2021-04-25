package cs5004.animator.view;

import cs5004.animator.controller.PlaybackFeatures;

/**
 * This class represents a view with the function of user Interaction.
 */
public interface IViewPlayback extends IViewVisual {

  /**
   * This method controls what the image of buttons are.
   * @param isPlaying controls the image of playing button
   * @param isLoop controls the image of looping button
   * @param isMuted controls the image of muting button
   */
  void displayControlButtons(boolean isPlaying, boolean isLoop, boolean isMuted);

  /**
   * This method displays the setting panel.
   */
  void displaySettingPanel();

  /**
   * This method adds features to buttons in playback view.
   * @param playbackFeatures features added to buttons
   */
  void addPlaybackFeatures(PlaybackFeatures playbackFeatures);

  /**
   * This method shows Object Ids.
   * @param showId showId
   */
  void showId(boolean showId);

  /**
   * This method shows initial speed.
   * @param speed speed
   */
  void showInitialSpeed(int speed);

  /**
   * This method updates speed.
   * @param speed speed
   */
  void updateSpeed(int speed);
}
