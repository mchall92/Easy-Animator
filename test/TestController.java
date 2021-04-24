import cs5004.animator.controller.*;
import cs5004.animator.model.Window;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Builder;
import cs5004.animator.util.EasyAnimatorSetter;
import cs5004.animator.view.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class TestController {
    private IController controller;
    private IViewSVG viewSVG;
    private PlaybackView viewPlayback;
    private IViewText viewText;
    private IViewVisual viewVisual;
    private Window window;
    private String[] args;
    private Builder builder;
    private AnimationReader reader;
    private HashMap<String, String> argsMap;

    @Before
    public void setUp() throws IOException {
        viewPlayback = new PlaybackView();
        viewSVG = new SvgView();
        viewText = new TextView();
        viewVisual = new SwingView();
        builder = new Builder();
    }

    @Test
    public void TestSVGController() {
        args = new String[]{
                "speed", "1",
                "view", "svg",
                "out", "../testTarget/test-trash.svg",
                "filename", "toh3.txt"
        };
        EasyAnimatorSetter easyAnimatorSetter = new EasyAnimatorSetter(args);
        window = easyAnimatorSetter.getWindow();
        argsMap = easyAnimatorSetter.getArgsMap();
        controller = new SVGController(window, viewSVG, argsMap);
        controller.go();
    }

    @Test
    public void TestTextController() {
        args = new String[]{
                "speed", "1",
                "view", "text",
                "out", "../testTarget/test-trash.svg",
                "filename", "toh3.txt"
        };
        EasyAnimatorSetter easyAnimatorSetter = new EasyAnimatorSetter(args);
        window = easyAnimatorSetter.getWindow();
        argsMap = easyAnimatorSetter.getArgsMap();
        controller = new TextController(window, viewText, argsMap);
        controller.go();
    }

    @Test
    public void TestSwingController() {
        args = new String[]{
                "speed", "1",
                "view", "visual",
                "out", "null",
                "filename", "toh3.txt"
        };
        EasyAnimatorSetter easyAnimatorSetter = new EasyAnimatorSetter(args);
        window = easyAnimatorSetter.getWindow();
        argsMap = easyAnimatorSetter.getArgsMap();
        controller = new SwingController(window, viewVisual, argsMap);
        controller.go();
    }

    @Test
    public void TestPlayBackControllerStartStatus() {
        PlaybackController controller;
        args = new String[]{
                "speed", "1",
                "view", "playback",
                "out", "playback",
                "filename", "toh3.txt"
        };
        EasyAnimatorSetter easyAnimatorSetter = new EasyAnimatorSetter(args);
        window = easyAnimatorSetter.getWindow();
        argsMap = easyAnimatorSetter.getArgsMap();
        controller = new PlaybackController(window, viewPlayback, argsMap);
        //start status -> constructor
        assertEquals(controller.getTime(), 1);
        assertFalse(controller.isLoop());
        assertFalse(controller.isShowId());
        assertFalse(controller.isMuted());
        assertEquals(controller.getTempo(), 1);
        assertEquals(controller.getMp3Player(), null);

        //initialization -> go
        controller.go();
        assertEquals(viewPlayback.getTitle(), argsMap.get("filename"));
        assertEquals(viewPlayback.getViewModel(), window);
        assertEquals(viewPlayback.getControlBarPanel().getSpeed(), 1);
        assertTrue(viewPlayback.isVisible());
        assertTrue(viewPlayback.getControlBarPanel().isPlaying());
        assertFalse(viewPlayback.getControlBarPanel().isLooping());
        assertFalse(viewPlayback.getControlBarPanel().isMuting());

        //run -> actionPerformed
        for (int i=0; i<window.getEndTime(); i++) {
        }
    }
}
