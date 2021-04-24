package cs5004.animator.controller;

import cs5004.animator.model.Shape;

/**
 * This interface contain methods for PlaybackController to implement.
 */
public interface PlaybackFeatures {
  void start();
  void pause();
  void stop();
  void startLooping();
  void stopLooping();
  void setTempo(int newTempo);
  void setTempoX(String x);
  void toggleSettingPanel();
  void showId(boolean showId);
  void toMute();
  void unmute();
  void addObject(String id, String x, String y, int r, int g,
      int b, String shape, String sizeArg1, String sizeArg2,
      String appearTime, String disappearTime);
  void move(String id, String x, String y,
      String appearTime, String disappearTime);
  void changeSize(String id, String argsOne, String argsTwo,
      String appearTime, String disappearTime);
  void changeColor(String id, int r, int g, int b,
      String appearTime, String disappearTime);
}
