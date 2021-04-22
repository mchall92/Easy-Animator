package cs5004.animator.controller;

import cs5004.animator.model.Window;
import cs5004.animator.view.IViewVisual;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.Timer;

public class SwingController implements IController, ActionListener {

  Window window;
  IViewVisual view;
  HashMap<String, String> argsMap;
  private Timer timer;
  private int time;
  private int tempo;


  public SwingController(Window window, IViewVisual view, HashMap<String, String> argsMap) {
    this.window = window;
    this.view = view;
    this.argsMap = argsMap;
    this.tempo = Integer.parseInt(argsMap.get("speed"));
    timer = new Timer((int) 1000 / tempo, this);
    time = 0;
  }

  @Override
  public void go() {
    System.out.println("All images are from freeicons.io and free for use.");
    this.view.setFileName(this.argsMap.get("fileName"));
    this.view.setModel(this.window);
    this.view.makeVisible();
    timer.start();
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
      timer.stop();
    }
    view.repaint(time);
  }
}
