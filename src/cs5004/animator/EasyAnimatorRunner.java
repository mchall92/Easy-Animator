package cs5004.animator;

import cs5004.animator.controller.IController;
import cs5004.animator.controller.PlaybackController;
import cs5004.animator.controller.SVGController;
import cs5004.animator.controller.SwingController;
import cs5004.animator.controller.TextController;
import cs5004.animator.model.Window;
import cs5004.animator.model.WindowImpl;
import cs5004.animator.util.EasyAnimatorSetter;
import cs5004.animator.view.IViewPlayback;
import cs5004.animator.view.IViewSVG;
import cs5004.animator.view.IViewText;
import cs5004.animator.view.IViewVisual;
import cs5004.animator.view.PlaybackView;
import cs5004.animator.view.SvgView;
import cs5004.animator.view.SwingView;
import cs5004.animator.view.TextView;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/** This class is the runner of animator. */
public class EasyAnimatorRunner {
  /**
   * This method is the starter of this animator. It instantiates a view and ad model
   * and pass them to the controller.
   *
   * @param args args is the argument that determines the view mode.
   * @throws IOException IOException is thrown when I/O exception reading the input.
   */
  public static void main(String[] args) {
    System.out.println(Arrays.toString(args));
    EasyAnimatorSetter easyAnimator = new EasyAnimatorSetter(args);
    HashMap<String, String> argsMap = easyAnimator.getArgsMap();
    switch (argsMap.get("view")) {
      case "visual": {
        Window window = easyAnimator.getWindow();
        IViewVisual view = new SwingView();
        IController controller = new SwingController(window, view, argsMap);
        controller.setFeatures();
        break;
      }
      case "svg": {
        Window window = easyAnimator.getWindow();
        IViewSVG view = new SvgView();
        IController controller = new SVGController(window, view, argsMap);
        controller.setFeatures();
        break;
      }
      case "text": {
        Window window = easyAnimator.getWindow();
        IViewText view = new TextView();
        IController controller = new TextController(window, view, argsMap);
        controller.setFeatures();
        break;
      }
      case "playback": {
        if (argsMap.get("out") == null) {
          Window window = easyAnimator.getWindow();
          IViewPlayback view = new PlaybackView();
          IController controller = new PlaybackController(window, view, argsMap);
          controller.setFeatures();
          break;
        } else {
          IViewPlayback view = new PlaybackView();
          Window emptyWindow = new WindowImpl(1000, 1000, 1000, 1000);
          IController controller = new PlaybackController(emptyWindow, view, argsMap);
          controller.setFeatures();
        }
        break;
      }
      default: {
        System.out.println("Should not reach here, there is "
            + "an error in argsParse and EasyAnimatorSetter.");
      }
    }
  }
}
