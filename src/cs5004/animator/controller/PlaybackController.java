package cs5004.animator.controller;

import cs5004.animator.model.Window;
import cs5004.animator.view.IViewPlayback;
import cs5004.animator.view.SettingPanel;
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
  private boolean showId;
  private SettingPanel settingFrame;
  private MP3Player mp3Player;


  public PlaybackController(Window window, IViewPlayback view, HashMap<String, String> argsMap) {
    this.window = window;
    this.view = view;
    this.argsMap = argsMap;
    this.tempo = Integer.parseInt(argsMap.get("speed"));
    this.settingFrame = new SettingPanel();
    this.timer = new Timer((int) 1000 / tempo, this);
    this.time = 0;
    this.isLoop = false;
    this.showId = false;

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
    this.view.setModel(this.window);
    this.view.displayControlButtons(true, isLoop);
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
        view.displayControlButtons(false,false);
        timer.stop();
        time = 0;
      } else {
        time = 0;
        view.displayControlButtons(true,true);
      }
    }
    view.repaint(time);
  }

  @Override
  public void start() {
    timer.start();
    mp3Player.play();
    view.displayControlButtons(true, isLoop);
  }

  @Override
  public void pause() {
    timer.stop();
    mp3Player.pause();
    view.displayControlButtons(false, isLoop);
  }

  @Override
  public void stop() {
    time = 0;
    view.repaint(0);
    mp3Player.stop();
    timer.stop();
    view.displayControlButtons(false, isLoop);
  }

  @Override
  public void startLooping() {
    isLoop = true;
    view.displayControlButtons(timer.isRunning(), true);
  }

  @Override
  public void stopLooping() {
    isLoop = false;
    view.displayControlButtons(timer.isRunning(), false);
  }

  @Override
  public void setTempo(int newTempo) {
    timer.setDelay((int) 1000/newTempo);
  }

  @Override
  public void setTempoX(String x) {
    double times = Double.parseDouble(x.substring(0, x.length() - 1));
    this.setTempo((int) (tempo * times));
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
}
