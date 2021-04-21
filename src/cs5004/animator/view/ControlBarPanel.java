package cs5004.animator.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ControlBarPanel extends JPanel {

  private JButton playButton;
  private JButton pauseButton;
  private JButton stopButton;
  private JButton toLoopButton;
  private JButton LoopedButton;

  public ControlBarPanel(FlowLayout flowLayout) {
    super(flowLayout);
    setPauseButton();
    setStopButtons();
    setPlayButton();
    playButton.setHorizontalAlignment(SwingConstants.LEFT);
    pauseButton.setHorizontalAlignment(SwingConstants.LEFT);
    stopButton.setHorizontalAlignment(SwingConstants.LEFT);
  }

  private void clear() {
    this.removeAll();
    this.revalidate();
    this.repaint();
  }

  public void playingView() {
    this.clear();
    this.add(pauseButton);
    this.add(stopButton);
  }

  public void pausingView() {
    this.clear();
    this.add(playButton, BorderLayout.WEST);
    this.add(stopButton, BorderLayout.WEST);
  }

  public void setPauseButton() {
    pauseButton = new JButton();
    try {
      ImageIcon pauseIcon = new ImageIcon(getClass().getResource("images/pause.png"));
      pauseIcon = new ImageIcon(pauseIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
      pauseButton.setIcon(pauseIcon);
    } catch (NullPointerException e) {
      System.out.println("Image for pause not found.");
    }
  }

  public void setPlayButton() {
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

  public void setControlBarButtonListener(ActionListener actionEvent) {
    playButton.addActionListener(actionEvent);
    pauseButton.addActionListener(actionEvent);
    stopButton.addActionListener(actionEvent);
    toLoopButton.addActionListener(actionEvent);
    LoopedButton.addActionListener(actionEvent);
  }
}
