package cs5004.animator.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RealTimeSpeedPanel extends JPanel {
  private int speed;

  public RealTimeSpeedPanel(int speed) {
    this.add(new JLabel("      "));
    this.speed = speed;
  }

  public void updateSpeed(int speed) {
    this.speed = speed;
    this.repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.BLACK);
    g2d.setFont(new Font(g2d.getFont().toString(), Font.PLAIN, 17));
    g2d.drawString(String.valueOf(speed), 0, 19);
  }
}
