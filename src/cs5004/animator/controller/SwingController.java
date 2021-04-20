package cs5004.animator.controller;

import cs5004.animator.model.Window;
import cs5004.animator.view.IViewVisual;
import cs5004.animator.view.SwingView;
import java.util.HashMap;

/**
 * This class implements IController for basic swing output.
 */
public class SwingController implements IController {

  Window window;
  IViewVisual view;
  HashMap<String, String> argsMap;

  public SwingController(Window window, IViewVisual view, HashMap<String, String> argsMap) {
    this.window = window;
    this.view = view;
    this.argsMap = argsMap;
  }

  @Override
  public void go() {
    view.setFileName(argsMap.get("fileName"));
    view.setTempo(Integer.parseInt(argsMap.get("speed")));
    view.setModel(window);
    view.startAnimation();
    view.makeVisible();
  }
}
