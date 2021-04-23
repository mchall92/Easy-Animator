package cs5004.animator;


import cs5004.animator.controller.IController;
import cs5004.animator.controller.PlaybackController;
import cs5004.animator.controller.SVGController;
import cs5004.animator.controller.SwingController;
import cs5004.animator.controller.TextController;
import cs5004.animator.model.IViewWindow;
import cs5004.animator.model.Window;
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

public class EasyAnimatorRunner {
  public static void main(String[] args) throws IOException {
    System.out.println(Arrays.toString(args));
    EasyAnimatorSetter easyAnimator = new EasyAnimatorSetter(args);
    HashMap<String, String> argsMap = easyAnimator.getArgsMap();
    Window window = easyAnimator.getWindow();
    IViewWindow 
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
        IViewPlayback view = new PlaybackView();
        IController controller = new PlaybackController(window, view, argsMap);
        view.setModel(window);
        controller.go();
        break;
      }
    }
  }
}
