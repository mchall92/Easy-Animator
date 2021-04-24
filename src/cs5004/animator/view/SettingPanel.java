package cs5004.animator.view;

import cs5004.animator.controller.PlaybackFeatures;
import cs5004.animator.model.IModelView;
import cs5004.animator.model.Shape;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

/**
 * This class represents the setting panel of the playback view.
 * Generally speaking, it is divided into two panels, the 'addObject' panel
 * and 'operation' panel. Users can add element and operation to the animator.
 */
public class SettingPanel extends JPanel {

  private IModelView viewModel;

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
  private Color addColor;
  private JPanel addTimePanel;
  private JFormattedTextField addAppearTime;
  private JFormattedTextField addDisappearTime;
  private JPanel addObjectButtonPanel;
  private JButton submitAddObjectButton;
  private JButton clearAddFieldButton;
  private JFrame addObjectSuccessFrame;
  private JButton addObjectSuccessButton;

  private JPanel operationPanel;
  private JPanel operationIdPanel;
  private JComboBox<String> operationChooseIdComboBox;
  private JPanel operationMotionPanel;
  private JComboBox<String> operationMotionComboBox;
  private JPanel operationPositionPanel;
  private JFormattedTextField operationXPositionField;
  private JFormattedTextField operationYPositionField;
  private JPanel operationSizePanel;
  private JFormattedTextField operationSizeFieldOne;
  private JFormattedTextField operationSizeFieldTwo;
  private JLabel operationWidthLabel;
  private JLabel operationHeightLabel;
  private JLabel operationXRadiusLabel;
  private JLabel operationYRadiusLabel;
  private JPanel operationColorChooserPanel;
  private JButton operationColorChooserButton;
  private JLabel operationColorChooserDisplay;
  private Color operationColor;
  private JPanel operationTimePanel;
  private JFormattedTextField operationAppearTimeField;
  private JFormattedTextField operationDisappearTimeField;
  private JPanel operationObjectButtonPanel;
  private JButton submitOperationButton;
  private JButton clearOperationFieldButton;
  private JFrame operationObjectSuccessFrame;
  private JButton operationObjectSuccessButton;

  private NumberFormat intFormat;
  private NumberFormatter intFormatter;
  private NumberFormat positiveIntFormat;
  private NumberFormatter positiveIntFormatter;
  private NumberFormat nonNegativeIntFormat;
  private NumberFormatter nonNegativeIntFormatter;

  /**
   * Initialize the setting panel to be invisible.
   * Initialize the setting panel's alignment.
   * @param flowLayout alignment of the panel
   */
  public SettingPanel(FlowLayout flowLayout) {
    super(flowLayout);
    this.setVisible(false);
  }

  /**
   * Set a model to the panel.
   * @param viewModel model of the view
   */
  public void setViewModel(IModelView viewModel) {
    this.viewModel = viewModel;
  }

  /**
   * set constraints to input and connect this panel to two of its sub-panels.
   */
  public void build() {
    this.setFormatter();
    this.setAddObjectPanel();
    this.setOperationPanel();
  }

  private void setAddObjectPanel() {
    // set up Add Object Panel
    addObjectPanel = new JPanel();
    addObjectPanel.setPreferredSize(new Dimension(610, 240));
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

    // build and add choose color button
    //set default color to white
    addColor = Color.WHITE;
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
    // build clear button
    addObjectButtonPanel = new JPanel();
    addObjectButtonPanel.setPreferredSize(new Dimension(140, 70));
    submitAddObjectButton = new JButton("Add");
    clearAddFieldButton = new JButton("Clear");
    addObjectButtonPanel.add(submitAddObjectButton, BorderLayout.WEST);
    addObjectButtonPanel.add(clearAddFieldButton, BorderLayout.EAST);
    addObjectPanel.add(addObjectButtonPanel);

    // add object successful frame
    addObjectSuccessFrame = new JFrame();
    JPanel addObjectSuccessPanel = new JPanel();
    JLabel confirmLabel = new JLabel("Successfully Added Object!");
    addObjectSuccessButton = new JButton("OK");
    addObjectSuccessPanel.add(confirmLabel);
    addObjectSuccessPanel.add(addObjectSuccessButton);
    addObjectSuccessFrame.add(addObjectSuccessPanel);
    addObjectSuccessFrame.setMinimumSize(new Dimension(200, 100));
  }

  private void setOperationPanel() {
    // set up operation Panel
    operationPanel = new JPanel();
    operationPanel.setPreferredSize(new Dimension(470, 240));
    operationPanel.setBorder(BorderFactory.createTitledBorder("Add An Operation"));
    this.add(operationPanel);

    // build choose ID panel
    operationIdPanel = new JPanel();
    operationIdPanel.setBorder(BorderFactory.createTitledBorder
        ("Choose An Object"));
    operationIdPanel.setPreferredSize(new Dimension(170, 60));
    HashMap<String, Shape> operationIDMap = viewModel.getElementIDAndShape();
    List<String> operationIDList = new ArrayList<>();
    for (String id : operationIDMap.keySet()) {
      operationIDList.add(id);
    }
    String[] operationIDArray = new String[operationIDMap.size()];
    operationIDList.toArray(operationIDArray);
    operationChooseIdComboBox = new JComboBox<>(operationIDArray);
    operationChooseIdComboBox.setPreferredSize
        (new Dimension(150, 25));
    operationIdPanel.add(operationChooseIdComboBox);
    operationPanel.add(operationIdPanel);

    // build action panel
    operationMotionPanel = new JPanel();
    operationMotionPanel.setBorder(BorderFactory.createTitledBorder
        ("Choose An Operation"));
    operationIdPanel.setPreferredSize(new Dimension(200, 60));
    String[] motionArray = new String[]
        {"Move", "Change Size", "Change Color"};
    operationMotionComboBox = new JComboBox<>(motionArray);
    operationMotionComboBox.setPreferredSize
        (new Dimension(150, 25));
    operationMotionPanel.add(operationMotionComboBox);
    operationPanel.add(operationMotionPanel);

    // build move panel, initially visible
    operationPositionPanel = new JPanel();
    operationPositionPanel.setBorder(BorderFactory.createTitledBorder("Move To"));
    operationPositionPanel.setPreferredSize(new Dimension(400, 60));
    JLabel operationXPositionLabel = new JLabel("X");
    JLabel operationYPositionLabel = new JLabel("  Y");
    operationXPositionField = new JFormattedTextField(intFormatter);
    operationXPositionField.setColumns(7);
    operationYPositionField = new JFormattedTextField(intFormatter);
    operationYPositionField.setColumns(7);
    operationPositionPanel.add(operationXPositionLabel);
    operationPositionPanel.add(operationXPositionField);
    operationPositionPanel.add(operationYPositionLabel);
    operationPositionPanel.add(operationYPositionField);
    operationPanel.add(operationPositionPanel);
    operationPositionPanel.setVisible(false);

    // build size panel , initially invisible
    operationSizePanel = new JPanel();
    operationSizePanel.setBorder(BorderFactory.createTitledBorder("Scale To"));
    operationSizePanel.setPreferredSize(new Dimension(400, 60));
    operationWidthLabel = new JLabel("Width");
    operationWidthLabel.setVisible(false);
    operationHeightLabel = new JLabel("  Height");
    operationHeightLabel.setVisible(false);
    operationXRadiusLabel = new JLabel("X Radius");
    operationXRadiusLabel.setVisible(false);
    operationYRadiusLabel = new JLabel("  Y Radius");
    operationYRadiusLabel.setVisible(false);
    operationSizeFieldOne = new JFormattedTextField(positiveIntFormatter);
    operationSizeFieldOne.setColumns(7);
    operationSizeFieldTwo = new JFormattedTextField(positiveIntFormatter);
    operationSizeFieldTwo.setColumns(7);
    operationSizePanel.add(operationWidthLabel);
    operationSizePanel.add(operationXRadiusLabel);
    operationSizePanel.add(operationSizeFieldOne);
    operationSizePanel.add(operationHeightLabel);
    operationSizePanel.add(operationYRadiusLabel);
    operationSizePanel.add(operationSizeFieldTwo);
    operationPanel.add(operationSizePanel);
    operationSizePanel.setVisible(false);

    // choose scale
    Shape currentShape = viewModel.getElementIDAndShape().get(
        operationChooseIdComboBox.getItemAt(
            operationChooseIdComboBox.getSelectedIndex()));

    this.chooseScale(currentShape);
    // choose scale

    // build change color panel, initially invisible
    operationColor = Color.WHITE;
    operationColorChooserPanel = new JPanel();
    operationColorChooserPanel.setBorder(BorderFactory.createTitledBorder("Set Color"));
    operationColorChooserPanel.setPreferredSize(new Dimension(400, 60));
    operationColorChooserButton = new JButton("Choose A Color");
    operationColorChooserPanel.add(operationColorChooserButton);
    operationColorChooserDisplay = new JLabel("     ");
    operationColorChooserDisplay.setOpaque(true); //so that background color shows up
    operationColorChooserDisplay.setBackground(Color.WHITE);
    operationColorChooserPanel.add(operationColorChooserButton);
    operationColorChooserPanel.add(operationColorChooserDisplay);
    operationPanel.add(operationColorChooserPanel);
    operationColorChooserPanel.setVisible(false);

    ///// choose which action panel to display

    this.chooseMotion(operationMotionComboBox.getItemAt(
        operationMotionComboBox.getSelectedIndex()));

    ///// choose which action panel to display

    // build time panel
    operationTimePanel = new JPanel();
    operationTimePanel.setBorder(BorderFactory.createTitledBorder("Set Time"));
    operationTimePanel.setPreferredSize(new Dimension(300, 60));
    JLabel operationAppearTimeLabel = new JLabel("From");
    JLabel operationDisappearTimeLabel = new JLabel("  To");
    operationAppearTimeField = new JFormattedTextField(nonNegativeIntFormatter);
    operationAppearTimeField.setColumns(7);
    operationDisappearTimeField = new JFormattedTextField(nonNegativeIntFormatter);
    operationDisappearTimeField.setColumns(7);
    operationTimePanel.add(operationAppearTimeLabel);
    operationTimePanel.add(operationAppearTimeField);
    operationTimePanel.add(operationDisappearTimeLabel);
    operationTimePanel.add(operationDisappearTimeField);
    operationPanel.add(operationTimePanel);

    // build add and clear buttons
    operationObjectButtonPanel = new JPanel();
    operationObjectButtonPanel.setPreferredSize(new Dimension(140, 70));
    submitOperationButton = new JButton("Add");
    clearOperationFieldButton = new JButton("Clear");
    operationObjectButtonPanel.add(submitOperationButton);
    operationObjectButtonPanel.add(clearOperationFieldButton);
    operationPanel.add(operationObjectButtonPanel);

    // add object successful frame
    operationObjectSuccessFrame = new JFrame();
    JPanel operationSuccessPanel = new JPanel();
    JLabel confirmOperationLabel = new JLabel("Successfully Added Operation!");
    operationObjectSuccessButton = new JButton("OK");
    operationSuccessPanel.add(confirmOperationLabel);
    operationSuccessPanel.add(operationObjectSuccessButton);
    operationObjectSuccessFrame.add(operationSuccessPanel);
    operationObjectSuccessFrame.setMinimumSize(new Dimension(200, 100));
  }

  /**
   * change the visibility of this panel.
   */
  public void toggleVisible() {
    this.setVisible(!this.isVisible());
  }

  /**
   *
   * @param features
   */
  public void addFeatures(PlaybackFeatures features) {
    // Add object button
    submitAddObjectButton.addActionListener(e -> {
      try {
        features.addObject(
            addObjectNameField.getText(),
            addXPositionField.getText(),
            addYPositionField.getText(),
            addColor.getRed(), addColor.getGreen(), addColor.getBlue(),
            chooseShapeComboBox.getItemAt(chooseShapeComboBox.getSelectedIndex()),
            addSizeFieldOne.getText(),
            addSizeFieldTwo.getText(),
            addAppearTime.getText(),
            addDisappearTime.getText()
        );
      } catch (IllegalArgumentException addObjectError) {
        this.clearAddObjectFields();
        System.out.println("need to handle two kinds of errors: incorrect"
            + "time and ID existed");
      }
      this.clearAddObjectFields();
      addObjectSuccessFrame.setVisible(true);
    });

    addObjectSuccessButton.addActionListener(e -> addObjectSuccessFrame.setVisible(false));

    // clear button
    clearAddFieldButton.addActionListener(e -> this.clearAddObjectFields());

    // add object choose color button
    addColorChooserButton.addActionListener(e -> {
      addColor = JColorChooser
          .showDialog(this, "Choose A Color", colorChooserDisplay.getBackground());
      colorChooserDisplay.setBackground(addColor);
    });

    // choose ID comboBox
    operationChooseIdComboBox.addActionListener(e -> this.chooseScale(
        viewModel.getElementIDAndShape().get(
        operationChooseIdComboBox.getItemAt(
            operationChooseIdComboBox.getSelectedIndex()
        ))));

    // choose operation comboBox
    operationMotionComboBox.addActionListener(e -> this.chooseMotion(
        operationMotionComboBox.getItemAt(
            operationMotionComboBox.getSelectedIndex()
        )));

    // operation choose color button
    operationColorChooserButton.addActionListener(e -> {
      operationColor = JColorChooser
          .showDialog(this, "Choose A Color",
              operationColorChooserDisplay.getBackground());
      operationColorChooserDisplay.setBackground(operationColor);
    });

    // add operation button
    submitOperationButton.addActionListener(e -> {

      try {
        String motion = operationMotionComboBox.getItemAt(
            operationMotionComboBox.getSelectedIndex());
        String id = operationChooseIdComboBox.getItemAt(
            operationChooseIdComboBox.getSelectedIndex());
        if (motion.equals("Move")) {
          features.move(id, operationXPositionField.getText(),
              operationYPositionField.getText(),
              operationAppearTimeField.getText(),
              operationDisappearTimeField.getText()
          );
        } else if (motion.equals("Change Size")) {
          features.changeSize(id, operationSizeFieldOne.getText(),
              operationSizeFieldTwo.getText(),
              operationAppearTimeField.getText(),
              operationDisappearTimeField.getText());
        } else if (motion.equals("Change Color")) {
          features.changeColor(id, operationColor.getRed(),
              operationColor.getGreen(), operationColor.getBlue(),
              operationAppearTimeField.getText().replace(",", ""),
              operationDisappearTimeField.getText().replace(",", ""));
        }
      } catch (IllegalArgumentException operationException) {
        System.out.println("Three kinds of errors should be caught");
        this.clearOperationFields();
      }
      this.clearOperationFields();
    });

    // clear operation button
    clearOperationFieldButton.addActionListener(e -> this.clearOperationFields());

  }

  /**
   * This method constraints the input rule of number.
   */
  public void setFormatter() {
    // Reference:  https://stackoverflow.com/questions/11093326/
    // restricting-jtextfield-input-to-integers/11093360

    // int
    intFormat = NumberFormat.getInstance();
    intFormatter = new NumberFormatter(intFormat);
    intFormatter.setValueClass(Integer.class);
    intFormatter.setMinimum(Integer.MIN_VALUE);
    intFormatter.setMaximum(Integer.MAX_VALUE);
    intFormatter.setAllowsInvalid(true);
    intFormatter.setCommitsOnValidEdit(true);

    // positive int
    positiveIntFormat = NumberFormat.getInstance();
    positiveIntFormatter = new NumberFormatter(positiveIntFormat);
    positiveIntFormatter.setValueClass(Integer.class);
    positiveIntFormatter.setMinimum(0);
    positiveIntFormatter.setMaximum(Integer.MAX_VALUE);
    positiveIntFormatter.setAllowsInvalid(true);
    positiveIntFormatter.setCommitsOnValidEdit(true);

    // non negative int
    nonNegativeIntFormat = NumberFormat.getInstance();
    nonNegativeIntFormatter = new NumberFormatter(nonNegativeIntFormat);
    nonNegativeIntFormatter.setValueClass(Integer.class);
    nonNegativeIntFormatter.setMinimum(0);
    nonNegativeIntFormatter.setMaximum(Integer.MAX_VALUE);
    nonNegativeIntFormatter.setAllowsInvalid(true);
    nonNegativeIntFormatter.setCommitsOnValidEdit(true);
  }

  public void clearAddObjectFields() {
    addObjectNameField.setText("");
    addXPositionField.setText("");
    addYPositionField.setText("");
    addSizeFieldOne.setText("");
    addSizeFieldTwo.setText("");
    addAppearTime.setText("");
    addDisappearTime.setText("");
    addColor = Color.white;
    chooseShapeComboBox.setSelectedIndex(0);
  }

  private void chooseScale(Shape currShape) {
    if (currShape.toString().equals("Oval")) {
      operationXRadiusLabel.setVisible(true);
      operationYRadiusLabel.setVisible(true);
      operationWidthLabel.setVisible(false);
      operationHeightLabel.setVisible(false);
    } else if (currShape.toString().equals("Rectangle")) {
      operationWidthLabel.setVisible(true);
      operationHeightLabel.setVisible(true);
      operationXRadiusLabel.setVisible(false);
      operationYRadiusLabel.setVisible(false);
    }
  }

  private void chooseMotion(String motion) {
    switch (motion) {
      case "Move":
        operationPositionPanel.setVisible(true);
        operationSizePanel.setVisible(false);
        operationColorChooserPanel.setVisible(false);
        break;
      case "Change Size":
        operationSizePanel.setVisible(true);
        operationPositionPanel.setVisible(false);
        operationColorChooserPanel.setVisible(false);
        break;
      case "Change Color":
        operationColorChooserPanel.setVisible(true);
        operationSizePanel.setVisible(false);
        operationPositionPanel.setVisible(false);
        break;
    }
  }

  private void clearOperationFields() {
    operationChooseIdComboBox.setSelectedIndex(0);
    operationXPositionField.setText("");
    operationYPositionField.setText("");
    operationSizeFieldOne.setText("");
    operationSizeFieldTwo.setText("");
    operationSizeFieldOne.setText("");
    operationSizeFieldTwo.setText("");
    operationAppearTimeField.setText("");
    operationDisappearTimeField.setText("");
    operationColor = Color.white;

  }
}
