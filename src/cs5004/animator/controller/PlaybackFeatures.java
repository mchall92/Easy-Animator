package cs5004.animator.controller;

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
}
