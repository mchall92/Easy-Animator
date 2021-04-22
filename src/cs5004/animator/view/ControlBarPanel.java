package cs5004.animator.view;

import cs5004.animator.controller.PlaybackFeatures;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class ControlBarPanel extends JPanel {

  private JButton playButton;
  private JButton pauseButton;
  private JButton stopButton;
  private JButton toLoopButton;
  private JButton loopedButton;
  private JComboBox<String> comboBox;
  private JButton settingButton;
  private JPanel idCheckBoxPanel;
  private JLabel idCheckBoxLabel;
  private JCheckBox idCheckBox;
  private JPanel timePanel;
  private JLabel showTimeLabel;
  private RealTimePanel realTimePanel;
  private JScrollPane js;


  public ControlBarPanel(FlowLayout flowLayout) {
    super(flowLayout);
    setPauseButton();
    setStopButtons();
    setPlayButton();
    setToLoopButton();
    setLoopedButton();
    setDropDownMenu();
    setSettingButton();
    setIdCheckBox();
    setTimePanel();
    playButton.setHorizontalAlignment(SwingConstants.LEFT);
    pauseButton.setHorizontalAlignment(SwingConstants.LEFT);
    stopButton.setHorizontalAlignment(SwingConstants.LEFT);
    toLoopButton.setHorizontalAlignment(SwingConstants.LEFT);
    loopedButton.setHorizontalAlignment(SwingConstants.LEFT);
    settingButton.setHorizontalAlignment(SwingConstants.RIGHT);

    // addScrollBar
    
  }

  public void displayControl(boolean isPlay, boolean isLoop) {
    this.clear();
    this.showPlay(isPlay);
    this.add(stopButton);
    this.showLoop(isLoop);
    this.add(comboBox, RIGHT_ALIGNMENT);
    this.add(idCheckBoxPanel);
    this.add(timePanel);
    this.add(timePanel);
    this.add(settingButton);
  }

  public void addFeatures(PlaybackFeatures features) {
    playButton.addActionListener(e -> features.start());
    pauseButton.addActionListener(e -> features.pause());
    stopButton.addActionListener(e -> features.stop());
    toLoopButton.addActionListener(e -> features.startLooping());
    loopedButton.addActionListener(e -> features.stopLooping());

    comboBox.addActionListener(e ->
        features.setTempoX(comboBox.getItemAt(comboBox.getSelectedIndex())));
    settingButton.addActionListener(e -> features.toggleSettingPanel());
    idCheckBox.addActionListener(e -> features.showId(idCheckBox.isSelected()));
  }

  public RealTimePanel getRealTimePanel() {
    return realTimePanel;
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

  private void setDropDownMenu() {
    String[] choices = { "0.5X","0.75X", "1X","1.5X","2X"};
    comboBox = new JComboBox<String>(choices);
    comboBox.setSelectedIndex(2);
    comboBox.setVisible(true);
  }

  private void setSettingButton() {
    settingButton = new JButton();
    try {
      ImageIcon settingIcon = new ImageIcon(getClass().getResource("images/setting.png"));
      settingIcon = new ImageIcon(settingIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
      settingButton.setIcon(settingIcon);
    } catch (NullPointerException e) {
      System.out.println("Image for setting not found.");
    }
  }

  private void setIdCheckBox() {
    idCheckBoxPanel = new JPanel();
    idCheckBoxLabel = new JLabel("Show Object ID");
    idCheckBoxPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    idCheckBox = new JCheckBox();
    idCheckBoxPanel.add(idCheckBoxLabel);
    idCheckBoxPanel.add(idCheckBox);
  }

  private void setTimePanel() {
    realTimePanel = new RealTimePanel();
    timePanel = new JPanel();
    showTimeLabel = new JLabel("Time : ");
    timePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    timePanel.add(showTimeLabel);
    timePanel.add(realTimePanel);
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
