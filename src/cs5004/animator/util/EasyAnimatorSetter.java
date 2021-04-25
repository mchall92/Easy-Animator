package cs5004.animator.util;

import cs5004.animator.model.IModelView;
import cs5004.animator.model.Window;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/** This class is the driver class of the animator. */
public class EasyAnimatorSetter {

  private HashMap<String, String> argsMap;
  private Window window;
  private IModelView viewModel;
  /**
   * User input args to this method to construct an animation.
   *
   * @param args
   */
  public EasyAnimatorSetter(String[] args) {
    if (args.length == 0) {
      return;
    }
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
    if (argsMap == null) {
      argsMap = new HashMap<>();
      argsMap.put("fileName", "empty.txt");
      argsMap.put("speed", "20");
      argsMap.put("view", "playback");
      argsMap.put("out", "notNull");
      return argsMap;
    }
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
