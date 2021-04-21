package cs5004.animator.controller;

import cs5004.animator.model.Window;
import cs5004.animator.view.IViewPlayback;
import cs5004.animator.view.IViewVisual;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class PlaybackController implements IController, ActionListener {

  Window window;
  IViewPlayback view;
  HashMap<String, String> argsMap;

  public PlaybackController(Window window, IViewPlayback view, HashMap<String, String> argsMap) {
    this.window = window;
    this.view = view;
    this.argsMap = argsMap;
  }

  @Override
  public void go() {
    System.out.println("All Images are from freeicons.io and free for use.");
    this.view.setFileName(this.argsMap.get("fileName"));
    view.setTempo(Integer.parseInt(this.argsMap.get("speed")));
    this.view.setModel(this.window);
    this.view.startAnimation();
    this.view.passControlBarButtonListener(this);
    this.view.makeVisible();
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "p":
        if (view.isAnimatorPlaying()) {
          view.pauseAnimation();
        } else {
          view.startAnimation();
        }
    }
  }
}
