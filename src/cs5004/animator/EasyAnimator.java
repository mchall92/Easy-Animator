package cs5004.animator;

import cs5004.animator.model.Window;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Builder;
import cs5004.animator.view.IView;
import cs5004.animator.view.SvgView;
import cs5004.animator.view.SwingView;
import cs5004.animator.view.TextView;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/** This class is the driver class of the animator. */
public class EasyAnimator {

  /**
   * User input args to this method to construct an animation.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    HashMap<String, String> argsMap = new HashMap<>();
    Readable readable;
    try {
      // argsMap key: fileName, view, speed, out
      argsMap = ArgsParser.parse(args);
      readable = new FileReader(argsMap.get("fileName"));
    } catch (IndexOutOfBoundsException | IllegalArgumentException | FileNotFoundException e) {
      JFrame frame = new JFrame();
      JOptionPane.showMessageDialog(
          frame,
          "Argument is invalid.\nPlease follow required format.",
          "Argument error",
          JOptionPane.ERROR_MESSAGE);
      System.exit(0);
      return;
    }
    AnimationBuilder<Window> builder = new Builder();
    Window win = AnimationReader.parseFile(readable, builder);
    IView view;
    if (argsMap.get("view").equals("visual")) {
      view =
          new SwingView(argsMap.get("fileName"), 1440, 900, Integer.parseInt(argsMap.get("speed")));
      view.setAnimator(win);
      view.makeVisible();
    } else if (argsMap.get("view").equals("svg")) {
      view = new SvgView(argsMap.get("out"), Integer.parseInt(argsMap.get("speed")));
      view.setAnimator(win);
      view.makeVisible();
    } else if (argsMap.get("view").equals("text")) {
      view = new TextView(argsMap.get("out"));
      view.setAnimator(win);
      view.makeVisible();
    }
  }
}
