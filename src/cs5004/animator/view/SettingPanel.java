package cs5004.animator.view;

import cs5004.animator.model.Shape;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SettingPanel extends JPanel {

  JPanel addObjectPanel;
  JPanel addIdPanel;
  JTextField addObjectNameField;
  JPanel addPositionPanel;
  JTextField addXPositionField;
  JTextField addYPositionField;
  JPanel chooseShapePanel;
  JComboBox<String> chooseShapeComboBox;
  JPanel addSizePanel;
  JTextField addSizeFieldOne;
  JTextField addSizeFieldTwo;
  JButton addColorChooserButton;
  JPanel addTimePanel;
  JTextField addAppearTime;
  JTextField addDisappearTime;

  public SettingPanel(FlowLayout flowLayout) {
    super(flowLayout);
    this.setVisible(false);
    this.setAddObjectPanel();
  }

  private void setAddObjectPanel() {
    // set up Add Object Panel
    addObjectPanel = new JPanel();
    addObjectPanel.setPreferredSize(new Dimension(500, 230));
    addObjectPanel.setBorder(BorderFactory.createTitledBorder("Add An Object"));
    this.add(addObjectPanel);

    // set up and add ID panel
    addIdPanel = new JPanel();
    JLabel addIdLabel = new JLabel("Object ID ");
    addObjectNameField = new JTextField();
    addObjectNameField.setColumns(10);
    addIdPanel.add(addIdLabel);
    addIdPanel.add(addObjectNameField);
    addObjectPanel.add(addIdPanel);

    // set up and add Position panel
    addPositionPanel = new JPanel();
    addPositionPanel.setBorder(BorderFactory.createTitledBorder("Set Position"));
    JLabel addXPositionLabel = new JLabel("X");
    JLabel addYPositionLabel = new JLabel("  Y");
    addXPositionField = new JTextField();
    addXPositionField.setColumns(5);
    addYPositionField = new JTextField();
    addYPositionField.setColumns(5);
    addPositionPanel.add(addXPositionLabel);
    addPositionPanel.add(addXPositionField);
    addPositionPanel.add(addYPositionLabel);
    addPositionPanel.add(addYPositionField);
    addObjectPanel.add(addPositionPanel);

    // build and add choose shape combo box
    chooseShapePanel = new JPanel();
    chooseShapePanel.setBorder(BorderFactory.createTitledBorder("Choose A Shape"));
    String[] shapeChoice = {Shape.Oval.toString(), Shape.Rectangle.toString()};
    chooseShapeComboBox = new JComboBox<>(shapeChoice);
    chooseShapeComboBox.setSelectedIndex(0);
    chooseShapeComboBox.setVisible(true);
    chooseShapePanel.add(chooseShapeComboBox);
    chooseShapePanel.add(new JLabel("     "));
    addObjectPanel.add(chooseShapePanel);

    // build and add size panel
    addSizePanel = new JPanel();
    addSizePanel.setBorder(BorderFactory.createTitledBorder("Set Size"));
    JLabel addXRadiusLabel = new JLabel("X Radius");
    JLabel addYRadiusLabel = new JLabel(" Y Radius");
    addSizeFieldOne = new JTextField();
    addSizeFieldOne.setColumns(5);
    addSizeFieldTwo = new JTextField();
    addSizeFieldTwo.setColumns(5);
    addSizePanel.add(addXRadiusLabel);
    addSizePanel.add(addSizeFieldOne);
    addSizePanel.add(addYRadiusLabel);
    addSizePanel.add(addSizeFieldTwo);
    addObjectPanel.add(addSizePanel);

  }

  public void toggleVisible() {
    this.setVisible(!this.isVisible());
  }



}
