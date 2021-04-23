package cs5004.animator.view;

import cs5004.animator.controller.PlaybackFeatures;
import cs5004.animator.model.IModelView;

public interface IViewPlayback extends IViewVisual {

  void displayControlButtons(boolean isPlaying, boolean isLoop, boolean isMuted);

  void displaySettingPanel();

  void addPlaybackFeatures(PlaybackFeatures playbackFeatures);

  void showId(boolean showId);

  void showInitialSpeed(int speed);

  void updateSpeed(int speed);
}
