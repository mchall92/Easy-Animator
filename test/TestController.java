import cs5004.animator.controller.IController;
import cs5004.animator.controller.PlaybackController;
import cs5004.animator.controller.SVGController;
import cs5004.animator.controller.SwingController;
import cs5004.animator.controller.TextController;
import cs5004.animator.model.Window;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Builder;
import cs5004.animator.util.EasyAnimatorSetter;
import cs5004.animator.view.IViewSVG;
import cs5004.animator.view.IViewText;
import cs5004.animator.view.IViewVisual;
import cs5004.animator.view.PlaybackView;
import cs5004.animator.view.SvgView;
import cs5004.animator.view.SwingView;
import cs5004.animator.view.TextView;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/** Tests controllers(mainly playback). */
public class TestController {
  private IController controller;
  private IViewSVG viewSVG;
  private PlaybackView viewPlayback;
  private IViewText viewText;
  private IViewVisual viewVisual;
  private Window window;
  private String[] args;
  private AnimationReader reader;
  private HashMap<String, String> argsMap;

  @Before
  public void setUp() throws IOException {
    viewPlayback = new PlaybackView();
    viewSVG = new SvgView();
    viewText = new TextView();
    viewVisual = new SwingView();
    Builder builder = new Builder();
  }

  @Test
  public void TestSVGController() {
    try {
      args =
          new String[] {
            "-view", "svg",
            "-out", "testTarget/test-trash.svg",
            "-in", "toh-3.txt"
          };
      EasyAnimatorSetter easyAnimatorSetter = new EasyAnimatorSetter(args);
      window = easyAnimatorSetter.getWindow();
      argsMap = easyAnimatorSetter.getArgsMap();
      controller = new SVGController(window, viewSVG, argsMap);
      controller.setFeatures();
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void TestTextController() {
    try {
      args =
          new String[] {
            "-speed", "1",
            "-view", "text",
            "-out", "testTarget/test-trash.svg",
            "-in", "toh-3.txt"
          };
      EasyAnimatorSetter easyAnimatorSetter = new EasyAnimatorSetter(args);
      window = easyAnimatorSetter.getWindow();
      argsMap = easyAnimatorSetter.getArgsMap();
      controller = new TextController(window, viewText, argsMap);
      controller.setFeatures();
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void TestSwingController() {
    try {
      args =
          new String[] {
            "-speed", "1",
            "-view", "visual",
            "-in", "toh-3.txt"
          };
      EasyAnimatorSetter easyAnimatorSetter = new EasyAnimatorSetter(args);
      window = easyAnimatorSetter.getWindow();
      argsMap = easyAnimatorSetter.getArgsMap();
      controller = new SwingController(window, viewVisual, argsMap);
      controller.setFeatures();
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void TestPlayBackController() {
    PlaybackController controller;
    args =
        new String[] {
          "-speed", "1",
          "-view", "playback",
          "-in", "toh-3.txt"
        };
    EasyAnimatorSetter easyAnimatorSetter = new EasyAnimatorSetter(args);
    window = easyAnimatorSetter.getWindow();
    argsMap = easyAnimatorSetter.getArgsMap();
    controller = new PlaybackController(window, viewPlayback, argsMap);
    // start status -> constructor
    assertEquals(controller.getTime(), 0);
    assertFalse(controller.isLoop());
    assertFalse(controller.isShowId());
    assertFalse(controller.isMuted());
    assertEquals(controller.getTempo(), 1);

    // initialization -> go
    controller.setFeatures();
    assertEquals(viewPlayback.getControlBarPanel().getSpeed(), 1);
    assertTrue(viewPlayback.isVisible());
    assertTrue(viewPlayback.getControlBarPanel().isPlaying());
    assertFalse(viewPlayback.getControlBarPanel().isLooping());
    assertFalse(viewPlayback.getControlBarPanel().isMuting());

    // button -> start, pause, ...
    controller.pause();
    assertFalse(viewPlayback.getControlBarPanel().isPlaying());
    controller.start();
    assertTrue(viewPlayback.getControlBarPanel().isPlaying());
    controller.toMute();
    assertTrue(viewPlayback.getControlBarPanel().isMuting());
    controller.unmute();
    assertFalse(viewPlayback.getControlBarPanel().isMuting());
    controller.startLooping();
    assertTrue(viewPlayback.getControlBarPanel().isLooping());
    controller.stopLooping();
    assertFalse(viewPlayback.getControlBarPanel().isLooping());
    controller.stop();
    assertFalse(viewPlayback.getControlBarPanel().isPlaying());

    // speed setter -> setTempoX
    controller.setTempoX("2X");
    assertEquals(viewPlayback.getControlBarPanel().getSpeed(), 1);

    // toggle setting panel -> toggleSettingPanel
    assertFalse(viewPlayback.getSettingPanel().isVisible());
    controller.toggleSettingPanel();
    assertTrue(viewPlayback.getSettingPanel().isVisible());

    // showId
    assertFalse(viewPlayback.getAnimatorPanel().isShowId());
    controller.showId(true);
    assertTrue(viewPlayback.getAnimatorPanel().isShowId());
    controller.showId(false);
    assertFalse(viewPlayback.getAnimatorPanel().isShowId());

    // addObject
    assertFalse(window.getElementIDAndShape().containsKey("11"));
    controller.addObject("11", "20", "20", 1, 1, 1, "oval", "10", "10", "2", "10");
    assertTrue(window.getElementIDAndShape().containsKey("11"));
  }
}
