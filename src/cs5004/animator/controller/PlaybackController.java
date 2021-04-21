package cs5004.animator.controller;

import cs5004.animator.model.Window;
import cs5004.animator.view.IViewVisual;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class PlaybackController implements IController, ActionListener {

  Window window;
  IViewVisual view;
  HashMap<String, String> argsMap;

  public PlaybackController(Window window, IViewVisual view, HashMap<String, String> argsMap) {
    this.window = window;
    this.view = view;
    this.argsMap = argsMap;
  }

  @Override
  public void go() {
    System.out.println("All Images are from freeicons.io and free for use.");
    view.setFileName(argsMap.get("fileName"));
    view.setTempo(Integer.parseInt(argsMap.get("speed")));
    view.setModel(window);
    view.startAnimation();
    view.makeVisible();
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
