package cs5004.animator.controller;

import cs5004.animator.model.IModelView;
import cs5004.animator.model.Window;
import cs5004.animator.view.IViewText;
import java.util.HashMap;

/**
 * This class implements IController for text output controller.
 */
public class TextController implements IController{

  private Window window;
  private IViewText view;
  HashMap<String, String> argsMap;

  /**
   * This is the constructor for TextController.
   * @param window window is the model.
   * @param view view is the view.
   * @param argsMap argsMap is a map for command line arguments.
   */
  public TextController(Window window, IViewText view, HashMap<String, String> argsMap) {
    this.window = window;
    this.view = view;
    this.argsMap = argsMap;
  }

  @Override
  public void go() {
    view.setOutput(argsMap.get("out"));
    view.setViewModel(window);
    view.writeTxt();
  }
}
