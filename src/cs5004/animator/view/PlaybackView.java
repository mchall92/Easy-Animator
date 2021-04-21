package cs5004.animator.view;

import cs5004.animator.model.Window;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class PlaybackView extends JFrame implements IViewVisual {

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

    // add control bar panel
    ControlBarPanel controlBarPanel = new ControlBarPanel(new FlowLayout(FlowLayout.LEFT));
    controlBarPanel.setPreferredSize(new Dimension(720, 50));
    this.add(controlBarPanel, BorderLayout.NORTH);
    animatorPanel.setControlBar(controlBarPanel);
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

  /**
   * Set tempo of the animation.
   *
   * @param tempo tempo is the speed fo the animation.
   */
  @Override
  public void setTempo(int tempo) {
    animatorPanel.setTempo(tempo);
  }


  /** Make the view visible. Called after the view is constructed */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void startAnimation() {
    animatorPanel.start();
  }
}

