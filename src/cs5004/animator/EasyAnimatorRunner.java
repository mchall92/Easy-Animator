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

/**
 * This is the starter class for our EasyAnimator.
 */

public class EasyAnimatorRunner {

  /**
   * This runs our EasyAnimator by instantiating model and view and pass them
   * to our controller.
   * @param args args is the argument that decides how our animator view will run.
   *
   * @throws IOException if there is an I/O error when reading input file.
   */
  public static void main(String[] args) throws IOException {
    System.out.println(Arrays.toString(args));
    EasyAnimatorSetter easyAnimator = new EasyAnimatorSetter(args);
    HashMap<String, String> argsMap = easyAnimator.getArgsMap();
    switch (argsMap.get("view")) {
      case "visual": {
        Window window = easyAnimator.getWindow();
        IViewVisual view = new SwingView();
        IController controller = new SwingController(window, view, argsMap);
        controller.go();
        break;
      }
      case "svg": {
        Window window = easyAnimator.getWindow();
        IViewSVG view = new SvgView();
        IController controller = new SVGController(window, view, argsMap);
        controller.go();
        break;
      }
      case "text": {
        Window window = easyAnimator.getWindow();
        IViewText view = new TextView();
        IController controller = new TextController(window, view, argsMap);
        controller.go();
        break;
      }
      case "playback": {
        if (argsMap.get("out") == null) {
          Window window = easyAnimator.getWindow();
          IViewPlayback view = new PlaybackView();
          IController controller = new PlaybackController(window, view, argsMap);
          controller.go();
          break;
        } else {
          IViewPlayback view = new PlaybackView();
          Window emptyWindow = new WindowImpl(1000, 1000, 1000, 1000);
          IController controller = new PlaybackController(emptyWindow, view, argsMap);
          controller.go();
        }
      }
      default: {
        System.out.println("Should not reach here, error with argsParse and "
            + "EasyAnimatorSetter.");
      }
    }
  }
}
