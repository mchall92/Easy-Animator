package cs5004.animator.view;

import javax.swing.JPanel;

public class SettingPanel extends JPanel {

  public SettingPanel() {
    this.setVisible(false);
  }

  public void toggleVisible() {
    this.setVisible(!this.isVisible());
  }
}
