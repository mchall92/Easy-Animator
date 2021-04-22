package cs5004.animator.view;

import cs5004.animator.controller.PlaybackFeatures;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ControlBarPanel extends JPanel {

  private JButton playButton;
  private JButton pauseButton;
  private JButton stopButton;
  private JButton toLoopButton;
  private JButton loopedButton;

  public ControlBarPanel(FlowLayout flowLayout) {
    super(flowLayout);
    setPauseButton();
    setStopButtons();
    setPlayButton();
    setToLoopButton();
    setLoopedButton();
    playButton.setHorizontalAlignment(SwingConstants.LEFT);
    pauseButton.setHorizontalAlignment(SwingConstants.LEFT);
    stopButton.setHorizontalAlignment(SwingConstants.LEFT);
    toLoopButton.setHorizontalAlignment(SwingConstants.LEFT);
    loopedButton.setHorizontalAlignment(SwingConstants.LEFT);
  }

  public void displayButtons(boolean isPlay, boolean isLoop) {
    this.clear();
    this.showPlay(isPlay);
    this.add(stopButton);
    this.showLoop(isLoop);
  }

  public void addFeatures(PlaybackFeatures features) {
    playButton.addActionListener(e -> features.start());
    pauseButton.addActionListener(e -> features.pause());
    stopButton.addActionListener(e -> features.stop());
    toLoopButton.addActionListener(e -> features.startLooping());
    loopedButton.addActionListener(e -> features.stopLooping());
  }

  // buttons setup
  private void setPauseButton() {
    pauseButton = new JButton();
    try {
      ImageIcon pauseIcon = new ImageIcon(getClass().getResource("images/pause.png"));
      pauseIcon = new ImageIcon(pauseIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
      pauseButton.setIcon(pauseIcon);
    } catch (NullPointerException e) {
      System.out.println("Image for pause not found.");
    }
  }

  private void setPlayButton() {
    playButton = new JButton();
    try {
      ImageIcon playIcon = new ImageIcon(getClass().getResource("images/play.png"));
      playIcon = new ImageIcon(playIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
      playButton.setIcon(playIcon);
    } catch (NullPointerException e) {
      System.out.println("Image for play not found.");
    }
  }

  private void setStopButtons() {
    stopButton = new JButton();
    try {
      ImageIcon stopIcon = new ImageIcon(getClass().getResource("images/stop.png"));
      stopIcon = new ImageIcon(stopIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
      stopButton.setIcon(stopIcon);
    } catch (NullPointerException e) {
      System.out.println("Image for stop not found.");
    }
  }

  private void setToLoopButton() {
    toLoopButton = new JButton();
    try {
      ImageIcon toLoopIcon = new ImageIcon(getClass().getResource("images/toLoop.png"));
      toLoopIcon = new ImageIcon(toLoopIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
      toLoopButton.setIcon(toLoopIcon);
    } catch (NullPointerException e) {
      System.out.println("Image for toLoop not found.");
    }
  }

  private void setLoopedButton() {
    loopedButton = new JButton();
    try {
      ImageIcon loopedIcon = new ImageIcon(getClass().getResource("images/looped.png"));
      loopedIcon = new ImageIcon(loopedIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
      loopedButton.setIcon(loopedIcon);
    } catch (NullPointerException e) {
      System.out.println("Image for looped not found.");
    }
  }

  // helper methods

  private void clear() {
    this.removeAll();
    this.revalidate();
    this.repaint();
  }

  private void showPlay(boolean isPlay) {
    if (isPlay) {
      this.add(pauseButton);
    } else {
      this.add(playButton);
    }
  }

  private void showLoop(boolean isLoop) {
    if (isLoop) {
      this.add(loopedButton);
    } else {
      this.add(toLoopButton);
    }
  }
}
