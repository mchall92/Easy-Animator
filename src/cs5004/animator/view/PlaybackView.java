package cs5004.animator.view;

import cs5004.animator.controller.PlaybackFeatures;
import cs5004.animator.model.IModelView;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

/**
 * This class represents a view which provides the User Interaction functions to users.
 */
public class PlaybackView extends JFrame implements IViewPlayback {

  private AnimatorPanel animatorPanel;
  private ControlBarPanel controlBarPanel;
  private SettingPanel settingPanel;
  private RealTimePanel realTimePanel;
  private JScrollPane js;
  private IModelView viewModel;


  public AnimatorPanel getAnimatorPanel() {
    return animatorPanel;
  }

  public ControlBarPanel getControlBarPanel() {
    return controlBarPanel;
  }

  public SettingPanel getSettingPanel() {
    return settingPanel;
  }

  public RealTimePanel getRealTimePanel() {
    return realTimePanel;
  }

  public JScrollPane getJs() {
    return js;
  }

  public IModelView getViewModel() {
    return viewModel;
  }

  /**
   * This method constructs a new SwingView.
   * It consists of three panels: Animator panel, Control-Bar panel and Setting Panel.
   */
  public PlaybackView() {
    super();
    this.setSize(1440, 900);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    // add Animator Panel
    animatorPanel = new AnimatorPanel();
    animatorPanel.setPreferredSize(new Dimension(1400, 800));
    this.add(animatorPanel, BorderLayout.CENTER);

    // add Scroll bars for animator panel
    js = new JScrollPane(
            animatorPanel,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    js.setPreferredSize(new Dimension(1440, 900));
    this.add(js);

    // add control bar panel and display
    controlBarPanel = new ControlBarPanel(new FlowLayout(FlowLayout.LEFT));
    controlBarPanel.setPreferredSize(new Dimension(1440, 50));
    this.add(controlBarPanel, BorderLayout.NORTH);

    // add setting panel
    settingPanel = new SettingPanel(new FlowLayout(FlowLayout.LEFT));
    settingPanel.setPreferredSize(new Dimension(1440, 250));
    this.add(settingPanel, BorderLayout.SOUTH);

    // get real time panel
    realTimePanel = controlBarPanel.getRealTimePanel();

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
  public void setViewModel(IModelView viewModel) {
    animatorPanel.setViewModel(viewModel);
    settingPanel.setViewModel(viewModel);
    settingPanel.build();
  }

  /** Make the view visible. Called after the view is constructed */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void displayControlButtons(boolean isPlaying, boolean isLoop, boolean isMuted) {
    controlBarPanel.displayControl(isPlaying, isLoop, isMuted);
  }

  @Override
  public void displaySettingPanel() {
    settingPanel.toggleVisible();
  }

  @Override
  public void addPlaybackFeatures(PlaybackFeatures playbackFeatures) {
    // send playbackFeatures to ControlBarPanel
    controlBarPanel.addFeatures(playbackFeatures);

    // send playbackFeatures to SettingPanel
    settingPanel.addFeatures(playbackFeatures);
  }

  @Override
  public void repaint(int time) {
    animatorPanel.updateTime(time);
    animatorPanel.repaint();
    realTimePanel.updateTime(time);
    realTimePanel.repaint();
  }

  @Override
  public void showId(boolean showId) {
    animatorPanel.showId(showId);
  }

  @Override
  public void showInitialSpeed(int speed) {
    controlBarPanel.showInitialSpeed(speed);
  }

  @Override
  public void updateSpeed(int speed) {
    controlBarPanel.updateSpeed(speed);
  }
}

