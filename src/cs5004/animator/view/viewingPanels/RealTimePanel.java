package cs5004.animator.view.viewingPanels;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class represents a panel which can change its panel on real time.
 */
public class RealTimePanel extends JPanel {
  private int time;

  /**
   * Initialize the value of panel to be blank and initialize the time to be 0.
   */
  public RealTimePanel() {
    this.add(new JLabel("             "));
    time = 0;
  }

  /**
   * Change the time showing in the panel.
   * @param time the current time
   */
  public void updateTime(int time) {
    this.time = time;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.BLACK);
    g2d.setFont(new Font(g2d.getFont().toString(), Font.PLAIN, 17));
    g2d.drawString(String.valueOf(time), 0, 19);
  }
}
