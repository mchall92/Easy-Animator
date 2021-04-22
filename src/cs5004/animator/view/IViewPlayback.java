package cs5004.animator.view;

import cs5004.animator.controller.PlaybackFeatures;

public interface IViewPlayback extends IViewVisual{

  void displayButtons(boolean isPlaying, boolean isLoop);

  void addPlaybackFeatures(PlaybackFeatures playbackFeatures);
}
