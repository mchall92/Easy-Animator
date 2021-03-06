package cs5004.animator.controller;

import cs5004.animator.model.Shape;
import cs5004.animator.model.Window;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Builder;
import cs5004.animator.view.IViewPlayback;
import jaco.mp3.player.MP3Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * This class represents a Playback controller which controls the data flow of Playback
 * view and model.
 */
public class PlaybackController implements IController, ActionListener, PlaybackFeatures {

  Window window;
  IViewPlayback view;
  HashMap<String, String> argsMap;
  private Timer timer;
  private int time;
  private int tempo;
  private boolean isLoop;
  private boolean isMuted;
  private boolean showId;
  private MP3Player mp3Player;

  /**
   * Initialize a window and a view to this controller, and settings customized by the user.
   * @param window the model
   * @param view the view
   * @param argsMap the customized settings
   */
  public PlaybackController(Window window, IViewPlayback view, HashMap<String, String> argsMap) {
    this.window = window;
    this.view = view;
    this.argsMap = argsMap;
    this.tempo = Integer.parseInt(argsMap.get("speed"));
    this.timer = new Timer((int) 1000 / tempo, this);
    this.time = 0;
    this.isLoop = false;
    this.showId = false;
    this.isMuted = false;

    // media
    mp3Player = null;
    try {
      File f = new File("audio/Lolly-Limujii(No Copyright Music).mp3");
      String absolute = f.getAbsolutePath();
      System.out.println(absolute);
      mp3Player = new MP3Player(new File(absolute));
    } catch (NullPointerException e) {
      System.out.println("Audio not found.");
    }
    mp3Player.setRepeat(true);
  }

  @Override
  public void setFeatures() {
    System.out.println("All images are either free-for-use from freeicons.io or self-made.");
    this.view.setFileName(this.argsMap.get("fileName"));
    this.view.setViewModel(this.window);
    this.view.addPlaybackFeatures(this);
    this.view.showInitialSpeed(tempo);
    this.view.displayControlButtons(true, isLoop, isMuted);
    this.view.makeVisible();
    this.start();
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    time += 1;
    if (time > window.getEndTime()) {
      if (!isLoop) {
        view.displayControlButtons(false, false, isMuted);
        timer.stop();
        mp3Player.stop();
        time = 0;
      } else {
        time = 0;
        view.displayControlButtons(true, true, isMuted);
      }
    }
    view.repaint(time);
  }

  @Override
  public void start() {
    timer.start();
    if (!isMuted) {
      mp3Player.play();
    }
    view.displayControlButtons(true, isLoop, isMuted);
  }

  @Override
  public void pause() {
    timer.stop();
    mp3Player.pause();
    view.displayControlButtons(false, isLoop, isMuted);
  }

  @Override
  public void stop() {
    time = 0;
    view.repaint(0);
    mp3Player.stop();
    timer.stop();
    view.displayControlButtons(false, isLoop, isMuted);
  }

  @Override
  public void startLooping() {
    isLoop = true;
    view.displayControlButtons(timer.isRunning(), true, isMuted);
  }

  @Override
  public void stopLooping() {
    isLoop = false;
    view.displayControlButtons(timer.isRunning(), false, isMuted);
  }

  @Override
  public void setTempo(int newTempo) {
    timer.setDelay((int) 1000 / newTempo);
  }

  @Override
  public void setTempoX(String x) {
    double times = Double.parseDouble(x.substring(0, x.length() - 1));
    this.setTempo((int) (tempo * times < 1 ? 1 : tempo * times));
    this.view.updateSpeed((int) (tempo * times < 1 ? 1 : tempo * times));
  }

  @Override
  public void toggleSettingPanel() {
    view.displaySettingPanel();
  }

  @Override
  public void showId(boolean showId) {
    view.showId(showId);
    view.repaint(time);
  }

  @Override
  public void toMute() {
    mp3Player.pause();
    this.isMuted = true;
    view.displayControlButtons(timer.isRunning(), isLoop, true);
  }

  @Override
  public void unmute() {
    if (timer.isRunning()) {
      mp3Player.play();
    }
    this.isMuted = false;
    view.displayControlButtons(timer.isRunning(), isLoop, false);
  }

  @Override
  public void addObject(String id, String x, String y, int r, int g,
      int b, String shape, String sizeArg1, String sizeArg2,
      String appearTime, String disappearTime) {

    int xInt = Integer.parseInt(x.replace(",", ""));
    int yInt = Integer.parseInt(y.replace(",", ""));
    int sizeArg1Int = Integer.parseInt(sizeArg1.replace(",", ""));
    int sizeArg2Int = Integer.parseInt(sizeArg2.replace(",", ""));
    int appearTimeInt = Integer.parseInt(appearTime.replace(",", ""));
    int disappearTimeInt = Integer.parseInt(disappearTime.replace(",", ""));
    Shape objectShape = null;
    for (Shape s : Shape.values()) {
      if (s.toString().equals(shape)) {
        objectShape = s;
      }
    }
    window.addElement(id, xInt, yInt, r, g, b, objectShape,
        sizeArg1Int, sizeArg2Int, appearTimeInt, disappearTimeInt);
  }

  @Override
  public void move(String id, String x, String y,
      String appearTime, String disappearTime) {

    window.move(id, Integer.parseInt(x.replace(",", "")),
        Integer.parseInt(y.replace(",", "")),
        Integer.parseInt(appearTime.replace(",", "")),
        Integer.parseInt(disappearTime.replace(",", "")));
    view.repaint(time);
  }

  @Override
  public void changeSize(String id, String argsOne, String argsTwo, String appearTime,
      String disappearTime) {
    window.scale(id, Integer.parseInt(argsOne.replace(",", "")),
        Integer.parseInt(argsTwo.replace(",", "")),
        Integer.parseInt(appearTime.replace(",", "")),
        Integer.parseInt(disappearTime.replace(",", "")));
    view.repaint(time);
  }

  @Override
  public void changeColor(String id, int r, int g, int b, String appearTime,
      String disappearTime) {
    window.changeColor(id, r, g, b,
        Integer.parseInt(appearTime.replace(",", "")),
        Integer.parseInt(disappearTime.replace(",", "")));
    view.repaint(time);
  }

  /**
   * Delete element/object from model.
   *
   * @param id id is the element to be deleted.
   */
  @Override
  public void deleteObject(String id) {
    window.removeElement(id);
    view.repaint(time);
  }

  /**
   * Save an SVG file at absolutePath.
   *
   * @param absolutePath absolutePath is the absolute path to save the file.
   */
  @Override
  public void saveSVGFile(String absolutePath) {
    if (!absolutePath.endsWith(".svg")) {
      absolutePath += ".svg";
    }
    File file = new File(absolutePath);
    try {
      FileWriter fileWriter = new FileWriter(file);
      fileWriter.write(window.toSvgString(tempo));
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Save an text file at absolutePath.
   *
   * @param absolutePath absolutePath is the absolute path to save the file.
   */
  @Override
  public void saveTextFile(String absolutePath) {
    if (!absolutePath.endsWith(".txt")) {
      absolutePath += ".txt";
    }
    File file = new File(absolutePath);
    try {
      FileWriter fileWriter = new FileWriter(file);
      fileWriter.write(window.toString());
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Open a text file from an absolutePath.
   *
   * @param absolutePath absolutePath is the absolute path to open the file.
   */
  @Override
  public void openFile(String absolutePath) {
    try {
      Readable readable = new FileReader(absolutePath);
      AnimationBuilder<Window> builder = new Builder();
      this.window = AnimationReader.parseFile(readable, builder);
    } catch (IndexOutOfBoundsException | IllegalArgumentException | FileNotFoundException e) {
      JFrame frame = new JFrame();
      JOptionPane.showMessageDialog(
          frame,
          "An Error Occurs When Reading File.",
          "Open File Error",
          JOptionPane.ERROR_MESSAGE);
    }
    this.time = 0;
    mp3Player.stop();
    this.view.setFileName(absolutePath.substring(
        absolutePath.lastIndexOf("/") + 1));
    this.view.setViewModel(this.window);
    this.view.addPlaybackFeatures(this);
    this.start();
  }

  public Window getWindow() {
    return window;
  }

  public IViewPlayback getView() {
    return view;
  }

  public int getTime() {
    return time;
  }

  public boolean isShowId() {
    return showId;
  }

  public int getTempo() {
    return tempo;
  }

  public boolean isLoop() {
    return isLoop;
  }

  public boolean isMuted() {
    return isMuted;
  }
}
