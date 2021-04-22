package cs5004.animator.view;

import cs5004.animator.controller.PlaybackFeatures;

public interface IViewPlayback extends IViewVisual{

  void displayControlButtons(boolean isPlaying, boolean isLoop);

  void displaySettingPanel();

  void addPlaybackFeatures(PlaybackFeatures playbackFeatures);

  void showId(boolean showId);
}
