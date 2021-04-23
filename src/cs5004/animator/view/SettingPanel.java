package cs5004.animator.view;

import cs5004.animator.controller.PlaybackFeatures;
import cs5004.animator.model.Shape;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

public class SettingPanel extends JPanel {

  private JPanel addObjectPanel;
  private JPanel addIdPanel;
  private JTextField addObjectNameField;
  private JPanel addPositionPanel;
  private JFormattedTextField addXPositionField;
  private JFormattedTextField addYPositionField;
  private JPanel chooseShapePanel;
  private JComboBox<String> chooseShapeComboBox;
  private JPanel addSizePanel;
  private JLabel addXRadiusLabel;
  private JLabel addYRadiusLabel;
  private JLabel addWidthLabel;
  private JLabel addHeightLabel;
  private JFormattedTextField addSizeFieldOne;
  private JFormattedTextField addSizeFieldTwo;
  private JPanel addColorChooserPanel;
  private JButton addColorChooserButton;
  private JLabel colorChooserDisplay;
  private Color color;
  private JPanel addTimePanel;
  private JFormattedTextField addAppearTime;
  private JFormattedTextField addDisappearTime;
  private JButton submitAddObjectButton;

  private NumberFormat intFormat;
  private NumberFormatter intFormatter;
  private NumberFormat positiveIntFormat;
  private NumberFormatter positiveIntFormatter;
  private NumberFormat nonNegativeIntFormat;
  private NumberFormatter nonNegativeIntFormatter;

  public SettingPanel(FlowLayout flowLayout) {
    super(flowLayout);
    this.setVisible(false);
    setFormatter();
    this.setAddObjectPanel();
  }

  private void setAddObjectPanel() {
    // set up Add Object Panel
    addObjectPanel = new JPanel();
    addObjectPanel.setPreferredSize(new Dimension(600, 230));
    addObjectPanel.setBorder(BorderFactory.createTitledBorder("Add An Object"));
    this.add(addObjectPanel);

    // set up and add ID panel
    addIdPanel = new JPanel();
    JLabel addIdLabel = new JLabel("Object ID ");
    addObjectNameField = new JTextField();
    addObjectNameField.setColumns(7);
    addIdPanel.add(addIdLabel);
    addIdPanel.add(addObjectNameField);
    addObjectPanel.add(addIdPanel);
    addObjectPanel.add(new JLabel("           "));

    // set up and add Position panel
    addPositionPanel = new JPanel();
    addPositionPanel.setBorder(BorderFactory.createTitledBorder("Set Position"));
    JLabel addXPositionLabel = new JLabel("X");
    JLabel addYPositionLabel = new JLabel("  Y");
    addXPositionField = new JFormattedTextField(intFormatter);
    addXPositionField.setColumns(7);
    addYPositionField = new JFormattedTextField(intFormatter);
    addYPositionField.setColumns(7);
    addPositionPanel.add(addXPositionLabel);
    addPositionPanel.add(addXPositionField);
    addPositionPanel.add(addYPositionLabel);
    addPositionPanel.add(addYPositionField);
    addObjectPanel.add(addPositionPanel);

    // build and add choose shape combo box
    chooseShapePanel = new JPanel();
    chooseShapePanel.setBorder(BorderFactory.createTitledBorder("Choose A Shape"));
    String[] shapeChoiceArray;
    List<String> shapeChoice = new ArrayList<>();
    for (Shape s : Shape.values()) {
      shapeChoice.add(s.toString());
    }
    shapeChoiceArray = new String[shapeChoice.size()];
    shapeChoice.toArray(shapeChoiceArray);
    chooseShapeComboBox = new JComboBox<>(shapeChoiceArray);
    chooseShapeComboBox.setSelectedIndex(0);
    chooseShapeComboBox.setVisible(true);
    chooseShapePanel.add(chooseShapeComboBox);
    chooseShapePanel.add(new JLabel("    "));
    addObjectPanel.add(chooseShapePanel);

    // build and add size panel
    addSizePanel = new JPanel();
    addSizePanel.setBorder(BorderFactory.createTitledBorder("Set Size"));
    addXRadiusLabel = new JLabel("X Radius");
    addYRadiusLabel = new JLabel("  Y Radius");
    addWidthLabel = new JLabel("Width");
    addHeightLabel = new JLabel("  Height");
    addSizeFieldOne = new JFormattedTextField(positiveIntFormatter);
    addSizeFieldOne.setColumns(7);
    addSizeFieldTwo = new JFormattedTextField(positiveIntFormatter);
    addSizeFieldTwo.setColumns(7);
    addSizePanel.add(addXRadiusLabel);
    addSizePanel.add(addSizeFieldOne);
    addSizePanel.add(addYRadiusLabel);
    addSizePanel.add(addSizeFieldTwo);
    addObjectPanel.add(addSizePanel);

    // build and add choose color button
    //set default color to white
    color = Color.WHITE;
    addColorChooserPanel = new JPanel();
    addColorChooserPanel.setBorder(BorderFactory.createTitledBorder("Set Color"));
    addColorChooserButton = new JButton("Choose A Color");
    addColorChooserPanel.add(addColorChooserButton);
    colorChooserDisplay = new JLabel("     ");
    colorChooserDisplay.setOpaque(true); //so that background color shows up
    colorChooserDisplay.setBackground(Color.WHITE);
    addColorChooserPanel.add(addColorChooserButton);
    addColorChooserPanel.add(colorChooserDisplay);
    addObjectPanel.add(addColorChooserPanel);

    // build and add time panel
    addTimePanel = new JPanel();
    addTimePanel.setBorder(BorderFactory.createTitledBorder("Set Time"));
    JLabel addAppearTimeLabel = new JLabel("From");
    JLabel addYDisappearTimeLabel = new JLabel("  To");
    addAppearTime = new JFormattedTextField(nonNegativeIntFormatter);
    addAppearTime.setColumns(7);
    addDisappearTime = new JFormattedTextField(nonNegativeIntFormatter);
    addDisappearTime.setColumns(7);
    addTimePanel.add(addAppearTimeLabel);
    addTimePanel.add(addAppearTime);
    addTimePanel.add(addYDisappearTimeLabel);
    addTimePanel.add(addDisappearTime);
    addObjectPanel.add(addTimePanel);

    // build submit add object button
    submitAddObjectButton = new JButton("Add");
    addObjectPanel.add(submitAddObjectButton);
  }

  public void toggleVisible() {
    this.setVisible(!this.isVisible());
  }

  public void addFeatures(PlaybackFeatures features) {
    // Add object button
    submitAddObjectButton.addActionListener(e -> {
      if (Integer.parseInt(addAppearTime.getText().replace(",", "")) >=
          Integer.parseInt(addDisappearTime.getText().replace(",", ""))) {
        throw new IllegalArgumentException("error frame should be thrown");
      }
      features.addObject(
          addObjectNameField.getText().replace(",", ""),
          addXPositionField.getText().replace(",", ""),
          addYPositionField.getText().replace(",", ""),
          color.getRed(), color.getGreen(), color.getBlue(),
          chooseShapeComboBox.getItemAt(chooseShapeComboBox.getSelectedIndex()),
          addSizeFieldOne.getText().replace(",", ""),
          addSizeFieldTwo.getText().replace(",", ""),
          addAppearTime.getText().replace(",", ""),
          addDisappearTime.getText().replace(",", "")
      );
    });

    // choose color button
    addColorChooserButton.addActionListener(e -> {
      color = JColorChooser
          .showDialog(this, "Choose A Color", colorChooserDisplay.getBackground());
      colorChooserDisplay.setBackground(color);
    });



  }

  public void setFormatter() {
    // Reference:  https://stackoverflow.com/questions/11093326/
    // restricting-jtextfield-input-to-integers/11093360

    // int
    intFormat = NumberFormat.getInstance();
    intFormatter = new NumberFormatter(intFormat);
    intFormatter.setValueClass(Integer.class);
    intFormatter.setMinimum(Integer.MIN_VALUE);
    intFormatter.setMaximum(Integer.MAX_VALUE);
    intFormatter.setAllowsInvalid(false);
    intFormatter.setCommitsOnValidEdit(true);

    // non negative int
    positiveIntFormat = NumberFormat.getInstance();
    positiveIntFormatter = new NumberFormatter(positiveIntFormat);
    positiveIntFormatter.setValueClass(Integer.class);
    positiveIntFormatter.setMinimum(0);
    positiveIntFormatter.setMaximum(Integer.MAX_VALUE);
    positiveIntFormatter.setAllowsInvalid(false);
    positiveIntFormatter.setCommitsOnValidEdit(true);

    // positive int
    nonNegativeIntFormat = NumberFormat.getInstance();
    nonNegativeIntFormatter = new NumberFormatter(nonNegativeIntFormat);
    nonNegativeIntFormatter.setValueClass(Integer.class);
    nonNegativeIntFormatter.setMinimum(0);
    nonNegativeIntFormatter.setMaximum(Integer.MAX_VALUE);
    nonNegativeIntFormatter.setAllowsInvalid(false);
    nonNegativeIntFormatter.setCommitsOnValidEdit(true);
  }



}
