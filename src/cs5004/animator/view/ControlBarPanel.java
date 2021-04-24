package cs5004.animator.view;

import cs5004.animator.controller.PlaybackFeatures;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class represents the control bar of the animator.
 * Functions like play, stop, pause, mute, loop are implemented in this panel.
 */
public class ControlBarPanel extends JPanel {

  private JButton playButton;
  private JButton pauseButton;
  private JButton stopButton;
  private JButton toLoopButton;
  private JButton loopedButton;
  private JButton toMuteButton;
  private JButton unmuteButton;
  private JButton saveSVGButton;
  private JButton saveTextButton;
  private JButton openFileButton;
  private JComboBox<String> comboBox;
  private JButton settingButton;
  private JPanel idCheckBoxPanel;
  private JLabel idCheckBoxLabel;
  private JCheckBox idCheckBox;
  private JPanel timePanel;
  private JLabel showTimeLabel;
  private RealTimePanel realTimePanel;
  private JPanel speedPanel;
  private JLabel showSpeedLabel;
  private RealTimeSpeedPanel realTimeSpeedPanel;
  private int speed;

  /**
   * Set starting status of this panel.
   * @param flowLayout The alignment
   */
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
    setToMuteButton();
    setUnmuteButton();
    setSaveSVGButton();
    setSaveTextButton();
    setOpenFileButton();
    playButton.setHorizontalAlignment(SwingConstants.LEFT);
    pauseButton.setHorizontalAlignment(SwingConstants.LEFT);
    stopButton.setHorizontalAlignment(SwingConstants.LEFT);
    toLoopButton.setHorizontalAlignment(SwingConstants.LEFT);
    loopedButton.setHorizontalAlignment(SwingConstants.LEFT);
    settingButton.setHorizontalAlignment(SwingConstants.LEFT);
  }

  /**
   * Set starting status of this view.
   * @param isPlay switch of playing function
   * @param isLoop switch of looping function
   * @param isMuted switch of muting function
   */
  public void displayControl(boolean isPlay, boolean isLoop, boolean isMuted) {
    this.clear();
    this.add(openFileButton);
    this.add(saveTextButton);
    this.add(saveSVGButton);
    this.showPlay(isPlay);
    this.add(stopButton);
    this.showLoop(isLoop);
    this.showMute(isMuted);
    this.add(settingButton);
    this.add(comboBox, RIGHT_ALIGNMENT);
    this.add(speedPanel);
    this.add(idCheckBoxPanel);
    this.add(timePanel);
  }

  /**
   * Add clicking events to buttons.
   * @param features collection of clicking events
   */
  public void addFeatures(PlaybackFeatures features) {
    playButton.addActionListener(e -> features.start());
    pauseButton.addActionListener(e -> features.pause());
    stopButton.addActionListener(e -> features.stop());
    toLoopButton.addActionListener(e -> features.startLooping());
    loopedButton.addActionListener(e -> features.stopLooping());
    toMuteButton.addActionListener(e -> features.toMute());
    unmuteButton.addActionListener(e -> features.unmute());

    comboBox.addActionListener(e ->
        features.setTempoX(comboBox.getItemAt(comboBox.getSelectedIndex())));
    settingButton.addActionListener(e -> features.toggleSettingPanel());
    idCheckBox.addActionListener(e -> features.showId(idCheckBox.isSelected()));

    saveSVGButton.addActionListener(e -> {
      JFileChooser fileChooser = new JFileChooser(".");

      FileNameExtensionFilter filter = new FileNameExtensionFilter(
          "SVG", "svg");
      fileChooser.setFileFilter(filter);
      int retValue = fileChooser.showSaveDialog(ControlBarPanel.this);
      if (retValue == JFileChooser.APPROVE_OPTION) {
        File svgFile = fileChooser.getSelectedFile();
        features.saveSVGFile(svgFile.getAbsolutePath());
      }
    });

    saveTextButton.addActionListener(e -> {
      JFileChooser fileChooser = new JFileChooser(".");

      FileNameExtensionFilter filter = new FileNameExtensionFilter(
          "txt", "txt");
      fileChooser.setFileFilter(filter);
      int retValue = fileChooser.showSaveDialog(ControlBarPanel.this);
      if (retValue == JFileChooser.APPROVE_OPTION) {
        File txtFile = fileChooser.getSelectedFile();
        features.saveTextFile(txtFile.getAbsolutePath());
      }
    });

    openFileButton.addActionListener(e-> {
      JFileChooser fileChooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
          "text", "txt");
      fileChooser.setFileFilter(filter);

      int retValue = fileChooser.showOpenDialog(ControlBarPanel.this);
      if (retValue == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        features.openFile(file.getAbsolutePath());
      }
    });
  }

  /**
   * Get the realtime panel.
   * @return realtime panel.
   */
  public RealTimePanel getRealTimePanel() {
    return realTimePanel;
  }

  /**
   * Set the initial speed to act like specialized and set the speed panel.
   * @param speed initial speed
   */
  public void showInitialSpeed(int speed) {
    this.speed = speed;
    this.setSpeedPanel();
  }

  /**
   * Update the speed in the realtime speed panel.
   * @param speed the new speed.
   */
  public void updateSpeed(int speed) {
    realTimeSpeedPanel.updateSpeed(speed);
  }

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
    comboBox = new JComboBox<>(choices);
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

  private void setToMuteButton() {
    toMuteButton = new JButton();
    try {
      ImageIcon toMuteIcon = new ImageIcon(getClass().getResource("images/toMute.png"));
      toMuteIcon = new ImageIcon(toMuteIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
      toMuteButton.setIcon(toMuteIcon);
    } catch (NullPointerException e) {
      System.out.println("Image for toMute not found.");
    }
  }

  private void setUnmuteButton() {
    unmuteButton = new JButton();
    try {
      ImageIcon unmuteIcon = new ImageIcon(getClass().getResource("images/unmute.png"));
      unmuteIcon = new ImageIcon(unmuteIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
      unmuteButton.setIcon(unmuteIcon);
    } catch (NullPointerException e) {
      System.out.println("Image for toMute not found.");
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

  private void setSpeedPanel() {
    realTimeSpeedPanel = new RealTimeSpeedPanel(speed);
    speedPanel = new JPanel();
    showSpeedLabel = new JLabel("Speed : ");
    speedPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    speedPanel.add(showSpeedLabel);
    speedPanel.add(realTimeSpeedPanel);
  }

  private void setSaveSVGButton() {
    saveSVGButton = new JButton();
    try {
      ImageIcon svgIcon = new ImageIcon(getClass().getResource("images/svg.png"));
      svgIcon = new ImageIcon(svgIcon.getImage().
          getScaledInstance(30, 30, Image.SCALE_DEFAULT));
      saveSVGButton.setIcon(svgIcon);
    } catch (NullPointerException e) {
      System.out.println("Image for SVG not found.");
    }
  }

  private void setSaveTextButton() {
    saveTextButton = new JButton();
    try {
      ImageIcon textIcon = new ImageIcon(getClass().getResource("images/text.png"));
      textIcon = new ImageIcon(textIcon.getImage().
          getScaledInstance(30, 30, Image.SCALE_DEFAULT));
      saveTextButton.setIcon(textIcon);
    } catch (NullPointerException e) {
      System.out.println("Image for text not found.");
    }
  }

  private void setOpenFileButton() {
    openFileButton = new JButton();
    try {
      ImageIcon openFileIcon = new ImageIcon(getClass().getResource("images/openFile.png"));
      openFileIcon = new ImageIcon(openFileIcon.getImage().
          getScaledInstance(30, 30, Image.SCALE_DEFAULT));
      openFileButton.setIcon(openFileIcon);
    } catch (NullPointerException e) {
      System.out.println("Image for text not found.");
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

  private void showMute(boolean isMuted) {
    if (isMuted) {
      this.add(unmuteButton);
    } else {
      this.add(toMuteButton);
    }
  }
}
