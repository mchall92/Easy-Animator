package cs5004.animator.controller;

import cs5004.animator.model.Shape;

/**
 * This interface contain methods for PlaybackController to implement.
 */
public interface PlaybackFeatures {
  public void start();
  public void pause();
  public void stop();
  public void startLooping();
  public void stopLooping();
  public void setTempo(int newTempo);
  public void setTempoX(String x);
  public void toggleSettingPanel();
  public void showId(boolean showId);
  public void toMute();
  public void unmute();
  public void addObject(String id, String x, String y, int r, int g,
      int b, String shape, String sizeArg1, String sizeArg2,
      String appearTime, String disappearTime);

  public void test(String test);
}
