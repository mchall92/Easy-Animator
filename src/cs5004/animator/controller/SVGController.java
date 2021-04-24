package cs5004.animator.controller;

import cs5004.animator.model.IModelView;
import cs5004.animator.model.Window;
import cs5004.animator.view.IViewSVG;
import java.util.HashMap;

/**
 * This class implements IController for SVG output controller.
 */
public class SVGController implements IController {

  private Window window;
  private IViewSVG view;
  HashMap<String, String> argsMap;

  /**
   * This is the constructor for SVGController.
   * @param window window is the model.
   * @param view view is the view.
   * @param argsMap argsMap is a map for command line arguments.
   */
  public SVGController(Window window, IViewSVG view, HashMap<String, String> argsMap) {
    this.window = window;
    this.view = view;
    this.argsMap = argsMap;
  }

  @Override
  public void go() {
    view.setViewModel(window);
    view.setOutput(argsMap.get("out"));
    view.setTempo(Integer.parseInt(argsMap.get("speed")));
    view.writeSVG();
  }
}
