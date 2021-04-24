package cs5004.animator.controller;

import cs5004.animator.model.IModelView;
import cs5004.animator.model.Window;
import cs5004.animator.view.IViewVisual;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.Timer;

/**
 * This class controls the data flow between model and visual view.
 */
public class SwingController implements IController, ActionListener {

  Window window;
  IViewVisual view;
  HashMap<String, String> argsMap;
  private Timer timer;
  private int time;
  private int tempo;

  /**
   * This is the constructor for SwingController.
   * @param window window is the model.
   * @param view view is the view.
   * @param argsMap argsMap is a map for command line arguments.
   */
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
    this.view.setViewModel((IModelView) window);
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
