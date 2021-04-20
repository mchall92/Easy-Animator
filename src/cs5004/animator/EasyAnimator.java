package cs5004.animator;

import cs5004.animator.model.Window;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.ArgsParser;
import cs5004.animator.util.Builder;
import cs5004.animator.view.IViewSVG;
import cs5004.animator.view.IViewText;
import cs5004.animator.view.IViewVisual;
import cs5004.animator.view.PlaybackView;
import cs5004.animator.view.SvgView;
import cs5004.animator.view.SwingView;
import cs5004.animator.view.TextView;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/** This class is the driver class of the animator. */
public class EasyAnimator {

  private HashMap<String, String> argsMap;
  private Window window;
  /**
   * User input args to this method to construct an animation.
   *
   * @param args
   */
  public EasyAnimator(String[] args) {
    argsMap = new HashMap<>();
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
    this.window = AnimationReader.parseFile(readable, builder);
  }

  /**
   * Return parsed strings from command line.
   * @return parsed strings from command line.
   */
  public HashMap<String, String> getArgsMap() {
    return argsMap;
  }

  /**
   * Return window model after inputs.
   * @return window model after inputs.
   */
  public Window getWindow() {
    return window;
  }
}
