package cs5004.animator;


import cs5004.animator.controller.IController;
import cs5004.animator.controller.SVGController;
import cs5004.animator.controller.SwingController;
import cs5004.animator.controller.TextController;
import cs5004.animator.model.Window;
import cs5004.animator.view.IViewSVG;
import cs5004.animator.view.IViewText;
import cs5004.animator.view.IViewVisual;
import cs5004.animator.view.PlaybackView;
import cs5004.animator.view.SvgView;
import cs5004.animator.view.SwingView;
import cs5004.animator.view.TextView;
import java.util.Arrays;
import java.util.HashMap;

public class EasyAnimatorRunner {
  public static void main(String[] args) {
    System.out.print(Arrays.toString(args));
    EasyAnimator easyAnimator = new EasyAnimator(args);
    HashMap<String, String> argsMap = easyAnimator.getArgsMap();
    Window window = easyAnimator.getWindow();
    switch (argsMap.get("view")) {
      case "visual": {
        IViewVisual view = new SwingView();
        IController controller = new SwingController(window, view, argsMap);
        controller.go();
        break;
      }
      case "svg": {
        IViewSVG view = new SvgView();
        IController controller = new SVGController(window, view, argsMap);
        controller.go();
        break;
      }
      case "text": {
        IViewText view = new TextView();
        IController controller = new TextController(window, view, argsMap);
        controller.go();
        break;
      }
      case "playback": {
        IViewVisual view = new PlaybackView();
        view.setModel(window);
        break;
      }
    }
  }
}
