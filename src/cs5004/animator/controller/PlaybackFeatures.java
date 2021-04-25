package cs5004.animator.controller;

/**
 * This interface contain methods for PlaybackController to implement.
 */
public interface PlaybackFeatures {
  /**
   * Start the animation.
   */
  void start();

  /**
   * Pause the animation.
   */
  void pause();

  /**
   * Stop the animation.
   */
  void stop();

  /**
   * Turn on the loop mode.
   */
  void startLooping();

  /**
   * Turn off the loop mode.
   */
  void stopLooping();

  /**
   * Set speed of animation.
   * @param newTempo new speed
   */
  void setTempo(int newTempo);

  /**
   * Set speed in times format(1X, 1.5X, 2X)
   * @param x the speed ratio in times format
   */
  void setTempoX(String x);

  /**
   * Stretch/fold the setting panel.
   */
  void toggleSettingPanel();

  /**
   * Show the object Id.
   * @param showId Switcher of Whether the object Id should be shown.
   */
  void showId(boolean showId);

  /**
   * Mute the Mp3 sound.
   */
  void toMute();

  /**
   * Unmute the Mp3 sound.
   */
  void unmute();

  /**
   * Add an object in model format.
   * @param id The id of the element
   * @param x x coordinate
   * @param y y coordinate
   * @param r red percentage of color
   * @param g green percentage of color
   * @param b blue percentage of color
   * @param shape shape of the element
   * @param sizeArg1 direction1 size argument
   * @param sizeArg2 direction2 size argument
   * @param appearTime At which time this element appears
   * @param disappearTime At which time this element disappears
   */
  void addObject(String id, String x, String y, int r, int g,
      int b, String shape, String sizeArg1, String sizeArg2,
      String appearTime, String disappearTime);

  /**
   * Move an object in model format.
   * @param id id of the element
   * @param x target x coordinate
   * @param y target y coordinate
   * @param fromTime original time
   * @param toTime target time
   */
  void move(String id, String x, String y,
      String fromTime, String toTime);

  /**
   * Change the size of an object in model format.
   * @param id id of the element
   * @param argsOne first argument of size
   * @param argsTwo second argument of size
   * @param fromTime original time
   * @param toTime target time
   */
  void changeSize(String id, String argsOne, String argsTwo,
      String fromTime, String toTime);

  /**
   * Change the color of an object in model format.
   * @param id id of the element
   * @param r red arg
   * @param g green arg
   * @param b blue arg
   * @param fromTime original time
   * @param toTime target time
   */
  void changeColor(String id, int r, int g, int b,
      String fromTime, String toTime);

  /**
   * Delete element/object from model.
   * @param id id is the element to be deleted.
   */
  void deleteObject(String id);

  /**
   * Save an SVG file at absolutePath.
   * @param absolutePath absolutePath is the absolute path to save the file.
   */
  void saveSVGFile(String absolutePath);

  /**
   * Save an text file at absolutePath.
   * @param absolutePath absolutePath is the absolute path to save the file.
   */
  void saveTextFile(String absolutePath);

  /**
   * Open a text file from an absolutePath.
   * @param absolutePath absolutePath is the absolute path to open the file.
   */
  void openFile(String absolutePath);
}
