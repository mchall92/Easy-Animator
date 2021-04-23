package cs5004.animator.controller;

import cs5004.animator.model.IModelView;
import cs5004.animator.model.Shape;
import cs5004.animator.model.Window;
import cs5004.animator.view.IViewPlayback;
import jaco.mp3.player.MP3Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import javax.swing.Timer;


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
      File f = new File("audio/Lolly – Limujii (No Copyright Music).mp3");
      String absolute = f.getAbsolutePath();
      System.out.println(absolute);
      mp3Player = new MP3Player(new File(absolute));
    } catch(NullPointerException e) {
      System.out.println("Audio not found.");
    }
    mp3Player.setRepeat(true);
  }

  @Override
  public void go() {
    System.out.println("All images are from freeicons.io and free for use.");
    this.view.setFileName(this.argsMap.get("fileName"));
    this.view.addPlaybackFeatures(this);
    this.view.setViewModel((IModelView) this.window);
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
        view.displayControlButtons(false,false, isMuted);
        timer.stop();
        mp3Player.stop();
        time = 0;
      } else {
        time = 0;
        view.displayControlButtons(true,true, isMuted);
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
    timer.setDelay((int) 1000/newTempo);
  }

  @Override
  public void setTempoX(String x) {
    double times = Double.parseDouble(x.substring(0, x.length() - 1));
    this.setTempo((int) (tempo * times));
    this.view.updateSpeed((int) (tempo * times));
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
    int xInt = Integer.parseInt(x);
    int yInt = Integer.parseInt(y);
    int sizeArg1Int = Integer.parseInt(sizeArg1);
    int sizeArg2Int = Integer.parseInt(sizeArg2);
    int appearTimeInt = Integer.parseInt(appearTime);
    int disappearTimeInt = Integer.parseInt(disappearTime);
    Shape objectShape = null;
    for (Shape s : Shape.values()) {
      if (s.toString().equals(shape)) {
        objectShape = s;
      }
    }
    try {
      window.addElement(id, xInt, yInt, r, g, b, objectShape,
          sizeArg1Int, sizeArg2Int, appearTimeInt, disappearTimeInt);
    } catch (IllegalArgumentException e) {
      System.out.println("warning sign for already existed ID");
    }

  }

  @Override
  public void test(String test) {

  }

}
