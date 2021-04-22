package cs5004.animator.view;

import cs5004.animator.controller.PlaybackFeatures;
import cs5004.animator.model.Window;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class PlaybackView extends JFrame implements IViewPlayback {

  private AnimatorPanel animatorPanel;
  private ControlBarPanel controlBarPanel;
  private JScrollPane js;


  /**
   * This method constructs a new SwingView.
   */
  public PlaybackView() throws IOException {
    super();
    this.setSize(720, 450);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    // add Animator Panel
    animatorPanel = new AnimatorPanel();
    animatorPanel.setPreferredSize(new Dimension(720, 450));
    this.add(animatorPanel, BorderLayout.CENTER);

    // add Scroll bars for animator panel
    js = new JScrollPane(
            animatorPanel,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    js.setPreferredSize(new Dimension(720, 450));
    this.add(js);

    // add control bar panel and display
    controlBarPanel = new ControlBarPanel(new FlowLayout(FlowLayout.LEFT));
    controlBarPanel.setPreferredSize(new Dimension(720, 50));
    this.add(controlBarPanel, BorderLayout.NORTH);
    this.pack();

  }

  /**
   * Set the frame name to file name.
   *
   * @param fileName is the name of the input file.
   */
  @Override
  public void setFileName(String fileName) {
    this.setTitle(fileName);
  }

  @Override
  public void setModel(Window window) {
    animatorPanel.setAnimator(window);
  }

  /** Make the view visible. Called after the view is constructed */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  public void displayButtons(boolean isPlaying, boolean isLoop) {
    controlBarPanel.displayButtons(isPlaying, isLoop);
  }

  @Override
  public void addPlaybackFeatures(PlaybackFeatures playbackFeatures) {
    // send playbackFeatures to ControlBarPanel
    controlBarPanel.addFeatures(playbackFeatures);
  }

  @Override
  public void repaint(int time) {
    animatorPanel.updateTime(time);
    animatorPanel.repaint();
  }
}

