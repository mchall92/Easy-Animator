package cs5004.animator.view.viewingPanels;

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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

/**
 * This class represents the setting panel of the playback view. Generally speaking, it is divided
 * into two panels, the 'addObject' panel and 'operation' panel. Users can add element and operation
 * to the animator.
 */
public class SettingPanel extends JPanel {

  private IModelView viewModel;

  private JTextField addObjectNameField;
  private JFormattedTextField addXPositionField;
  private JFormattedTextField addYPositionField;
  private JComboBox<String> chooseShapeComboBox;
  private JFormattedTextField addSizeFieldOne;
  private JFormattedTextField addSizeFieldTwo;
  private JButton addColorChooserButton;
  private JLabel colorChooserDisplay;
  private Color addColor;
  private JFormattedTextField addAppearTime;
  private JFormattedTextField addDisappearTime;
  private JButton submitAddObjectButton;
  private JButton clearAddFieldButton;
  private JFrame addObjectSuccessFrame;
  private JButton addObjectSuccessButton;

  private JComboBox<String> operationChooseIdComboBox;
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
  private JFormattedTextField operationAppearTimeField;
  private JFormattedTextField operationDisappearTimeField;
  private JButton submitOperationButton;
  private JButton clearOperationFieldButton;
  private JFrame operationObjectSuccessFrame;
  private JButton operationObjectSuccessButton;

  private JComboBox<String> deleteChooseIdComboBox;
  private JButton deleteObjectButton;
  private JFrame deleteObjectSuccessFrame;
  private JButton deleteObjectSuccessButton;

  // ID comboBox
  HashMap<String, Shape> operationIDMap;
  List<String> operationIDList;
  String[] operationIDArray;

  private NumberFormatter intFormatter;
  private NumberFormatter positiveIntFormatter;
  private NumberFormatter nonNegativeIntFormatter;

  /**
   * Initialize the setting panel to be invisible. Initialize the setting panel's alignment.
   *
   * @param flowLayout alignment of the panel
   */
  public SettingPanel(FlowLayout flowLayout) {
    super(flowLayout);
    this.setVisible(false);
  }

  /**
   * Set a model to the panel.
   *
   * @param viewModel model of the view
   */
  public void setViewModel(IModelView viewModel) {
    this.viewModel = viewModel;
    this.removeAll();
    this.setFormatter();
    this.setAddObjectPanel();
    this.setOperationPanel();
    this.setDeletePanel();
  }

  /**
   * Set up add object panel.
   */
  private void setAddObjectPanel() {
    // set up Add Object Panel
    JPanel addObjectPanel = new JPanel();
    addObjectPanel.setPreferredSize(new Dimension(550, 240));
    addObjectPanel.setBorder(BorderFactory.createTitledBorder("Add An Object"));
    this.add(addObjectPanel);

    // set up and add ID panel
    JPanel addIdPanel = new JPanel();
    JLabel addIdLabel = new JLabel("Object ID ");
    addObjectNameField = new JTextField();
    addObjectNameField.setColumns(7);
    addIdPanel.add(addIdLabel);
    addIdPanel.add(addObjectNameField);
    addObjectPanel.add(addIdPanel);
    addObjectPanel.add(new JLabel("           "));

    // set up and add Position panel
    JPanel addPositionPanel = new JPanel();
    addPositionPanel.setBorder(BorderFactory.createTitledBorder("Set Position"));
    JLabel addXPositionLabel = new JLabel("X");
    JLabel addYPositionLabel = new JLabel("  Y");
    addXPositionField = new JFormattedTextField(intFormatter);
    addXPositionField.setColumns(5);
    addYPositionField = new JFormattedTextField(intFormatter);
    addYPositionField.setColumns(5);
    addPositionPanel.add(addXPositionLabel);
    addPositionPanel.add(addXPositionField);
    addPositionPanel.add(addYPositionLabel);
    addPositionPanel.add(addYPositionField);
    addObjectPanel.add(addPositionPanel);

    // build and add choose color button
    //set default color to white
    addColor = Color.WHITE;
    JPanel addColorChooserPanel = new JPanel();
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
    JPanel addSizePanel = new JPanel();
    addSizePanel.setBorder(BorderFactory.createTitledBorder("Set Size"));
    JLabel addXRadiusLabel = new JLabel("X Radius");
    JLabel addYRadiusLabel = new JLabel("  Y Radius");
    JLabel addWidthLabel = new JLabel("Width");
    JLabel addHeightLabel = new JLabel("  Height");
    addSizeFieldOne = new JFormattedTextField(positiveIntFormatter);
    addSizeFieldOne.setColumns(5);
    addSizeFieldTwo = new JFormattedTextField(positiveIntFormatter);
    addSizeFieldTwo.setColumns(5);
    addSizePanel.add(addXRadiusLabel);
    addSizePanel.add(addSizeFieldOne);
    addSizePanel.add(addYRadiusLabel);
    addSizePanel.add(addSizeFieldTwo);
    addObjectPanel.add(addSizePanel);

    // build and add choose shape combo box
    JPanel chooseShapePanel = new JPanel();
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
    chooseShapePanel.add(new JLabel("   "));
    addObjectPanel.add(chooseShapePanel);

    // build and add time panel
    JPanel addTimePanel = new JPanel();
    addTimePanel.setBorder(BorderFactory.createTitledBorder("Set Time"));
    JLabel addAppearTimeLabel = new JLabel("From");
    JLabel addYDisappearTimeLabel = new JLabel("  To");
    addAppearTime = new JFormattedTextField(nonNegativeIntFormatter);
    addAppearTime.setColumns(5);
    addDisappearTime = new JFormattedTextField(nonNegativeIntFormatter);
    addDisappearTime.setColumns(5);
    addTimePanel.add(addAppearTimeLabel);
    addTimePanel.add(addAppearTime);
    addTimePanel.add(addYDisappearTimeLabel);
    addTimePanel.add(addDisappearTime);
    addObjectPanel.add(addTimePanel);

    // build submit add object button
    // build clear button
    JPanel addObjectButtonPanel = new JPanel();
    addObjectButtonPanel.setPreferredSize(new Dimension(120, 70));
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
    addObjectSuccessPanel.add(addObjectSuccessButton, BorderLayout.SOUTH);
    addObjectSuccessFrame.add(addObjectSuccessPanel);
    addObjectSuccessFrame.setMinimumSize(new Dimension(300, 150));
    addObjectSuccessFrame.setLocationRelativeTo(null);
  }

  /**
   * Set up operation panel.
   */
  private void setOperationPanel() {
    // set up operation Panel
    JPanel operationPanel = new JPanel();
    operationPanel.setPreferredSize(new Dimension(430, 240));
    operationPanel.setBorder(BorderFactory.createTitledBorder("Add An Operation"));
    this.add(operationPanel);

    // build choose ID panel
    JPanel operationIdPanel = new JPanel();
    operationIdPanel.setBorder(BorderFactory.createTitledBorder("Choose An Object"));
    operationIdPanel.setPreferredSize(new Dimension(170, 60));
    this.updateIdList();
    operationChooseIdComboBox = new JComboBox<>();
    operationChooseIdComboBox.setModel(new DefaultComboBoxModel(operationIDArray));
    operationChooseIdComboBox.setPreferredSize(new Dimension(150, 25));
    operationIdPanel.add(operationChooseIdComboBox);
    operationPanel.add(operationIdPanel);

    // build action panel
    JPanel operationMotionPanel = new JPanel();
    operationMotionPanel.setBorder(BorderFactory.createTitledBorder("Choose An Operation"));
    operationIdPanel.setPreferredSize(new Dimension(200, 60));
    String[] motionArray = new String[]{"Move", "Change Size", "Change Color"};
    operationMotionComboBox = new JComboBox<>(motionArray);
    operationMotionComboBox.setPreferredSize(new Dimension(150, 25));
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
    JPanel operationTimePanel = new JPanel();
    operationTimePanel.setBorder(BorderFactory.createTitledBorder("Set Time"));
    operationTimePanel.setPreferredSize(new Dimension(240, 60));
    JLabel operationAppearTimeLabel = new JLabel("From");
    JLabel operationDisappearTimeLabel = new JLabel("  To");
    operationAppearTimeField = new JFormattedTextField(nonNegativeIntFormatter);
    operationAppearTimeField.setColumns(5);
    operationDisappearTimeField = new JFormattedTextField(nonNegativeIntFormatter);
    operationDisappearTimeField.setColumns(5);
    operationTimePanel.add(operationAppearTimeLabel);
    operationTimePanel.add(operationAppearTimeField);
    operationTimePanel.add(operationDisappearTimeLabel);
    operationTimePanel.add(operationDisappearTimeField);
    operationPanel.add(operationTimePanel);

    // build add and clear buttons
    JPanel operationObjectButtonPanel = new JPanel();
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
    operationSuccessPanel.add(operationObjectSuccessButton, BorderLayout.SOUTH);
    operationObjectSuccessFrame.add(operationSuccessPanel);
    operationObjectSuccessFrame.setMinimumSize(new Dimension(300, 150));
    operationObjectSuccessFrame.setLocationRelativeTo(null);
  }

  /**
   * Set up delete object panel.
   */
  public void setDeletePanel() {
    // build and add delete object panel
    JPanel deleteObjectPanel = new JPanel();
    deleteObjectPanel.setBorder(BorderFactory.createTitledBorder("Delete An Object"));
    deleteObjectPanel.setPreferredSize(new Dimension(270, 60));
    this.add(deleteObjectPanel);

    // build and add delete object comboBox
    this.updateIdList();
    deleteChooseIdComboBox = new JComboBox<>();
    deleteChooseIdComboBox.setModel(new DefaultComboBoxModel(operationIDArray));
    deleteChooseIdComboBox.setPreferredSize(new Dimension(150, 25));
    deleteObjectPanel.add(deleteChooseIdComboBox);

    // build and add delete object button
    deleteObjectButton = new JButton("Delete");
    deleteObjectPanel.add(deleteObjectButton);

    // build delete object success frame
    deleteObjectSuccessFrame = new JFrame();
    JPanel deleteSuccessPanel = new JPanel();
    JLabel confirmDeleteLabel = new JLabel("Successfully Deleted The Object!");
    deleteObjectSuccessButton = new JButton("OK");
    deleteSuccessPanel.add(confirmDeleteLabel, BorderLayout.NORTH);
    deleteSuccessPanel.add(deleteObjectSuccessButton, BorderLayout.CENTER);
    deleteObjectSuccessFrame.add(deleteSuccessPanel);
    deleteObjectSuccessFrame.setMinimumSize(new Dimension(300, 150));
    deleteObjectSuccessFrame.setLocationRelativeTo(null);
  }


  /**
   * change the visibility of this panel.
   */
  public void toggleVisible() {
    this.setVisible(!this.isVisible());
  }

  /**
   * Add features to the setting panel.
   *
   * @param features the features passed in by controller
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
        this.updateIdList();
        this.updateComboBoxes();
        addObjectSuccessFrame.setVisible(true);
      } catch (IllegalArgumentException addObjectError) {
        this.clearAddObjectFields();
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(
            frame,
            addObjectError.getMessage().startsWith("For input") ?
            "Fields cannot be empty." : addObjectError.getMessage(),
            "Add Object Error",
            JOptionPane.ERROR_MESSAGE);
      }
      this.clearAddObjectFields();
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
        operationObjectSuccessFrame.setVisible(true);
      } catch (IllegalArgumentException operationException) {
        this.clearOperationFields();
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(
            frame,
            operationException.getMessage().startsWith("For input") ?
                "Fields cannot be empty." : operationException.getMessage(),
            "Add Object Error",
            JOptionPane.ERROR_MESSAGE);
      }
      this.clearOperationFields();
    });

    // successful add operation frame button
    operationObjectSuccessButton.addActionListener(e ->
        operationObjectSuccessFrame.setVisible(false));

    // clear operation button
    clearOperationFieldButton.addActionListener(e -> this.clearOperationFields());

    // delete object button
    deleteObjectButton.addActionListener(e -> {
      try {
        features.deleteObject(deleteChooseIdComboBox.getItemAt(
            deleteChooseIdComboBox.getSelectedIndex()
        ));
        this.updateIdList();
        this.updateComboBoxes();
        deleteObjectSuccessFrame.setVisible(true);
      } catch (IllegalArgumentException deleteFailException) {
        System.out.print("need to hand delete fail error message.");
      }
    });

    // confirm successful deletion button
    deleteObjectSuccessButton.addActionListener(e ->
        deleteObjectSuccessFrame.setVisible(false));
  }

  /**
   * This method constraints the input rule of number.
   */
  public void setFormatter() {
    // Reference:  https://stackoverflow.com/questions/11093326/
    // restricting-jtextfield-input-to-integers/11093360

    // int
    NumberFormat intFormat = NumberFormat.getInstance();
    intFormatter = new NumberFormatter(intFormat);
    intFormatter.setValueClass(Integer.class);
    intFormatter.setMinimum(Integer.MIN_VALUE);
    intFormatter.setMaximum(Integer.MAX_VALUE);
    intFormatter.setAllowsInvalid(true);
    intFormatter.setCommitsOnValidEdit(true);

    // positive int
    NumberFormat positiveIntFormat = NumberFormat.getInstance();
    positiveIntFormatter = new NumberFormatter(positiveIntFormat);
    positiveIntFormatter.setValueClass(Integer.class);
    positiveIntFormatter.setMinimum(0);
    positiveIntFormatter.setMaximum(Integer.MAX_VALUE);
    positiveIntFormatter.setAllowsInvalid(true);
    positiveIntFormatter.setCommitsOnValidEdit(true);

    // non negative int
    NumberFormat nonNegativeIntFormat = NumberFormat.getInstance();
    nonNegativeIntFormatter = new NumberFormatter(nonNegativeIntFormat);
    nonNegativeIntFormatter.setValueClass(Integer.class);
    nonNegativeIntFormatter.setMinimum(0);
    nonNegativeIntFormatter.setMaximum(Integer.MAX_VALUE);
    nonNegativeIntFormatter.setAllowsInvalid(true);
    nonNegativeIntFormatter.setCommitsOnValidEdit(true);
  }

  /**
   * clear Add field.
   */
  public void clearAddObjectFields() {
    addObjectNameField.setText("");
    addXPositionField.setText("");
    addYPositionField.setText("");
    addSizeFieldOne.setText("");
    addSizeFieldTwo.setText("");
    addAppearTime.setText("");
    addDisappearTime.setText("");
    chooseShapeComboBox.setSelectedIndex(0);
    addColor = Color.white;
    colorChooserDisplay.setBackground(addColor);
  }

  /**
   * Determine which parameters to show.
   *
   * @param currShape currShape is the shape of current object in ComboBox.
   */
  private void chooseScale(Shape currShape) {
    if (currShape != null && currShape.toString().equals("Oval")) {
      operationXRadiusLabel.setVisible(true);
      operationYRadiusLabel.setVisible(true);
      operationWidthLabel.setVisible(false);
      operationHeightLabel.setVisible(false);
    } else if (currShape != null && currShape.toString().equals("Rectangle")) {
      operationWidthLabel.setVisible(true);
      operationHeightLabel.setVisible(true);
      operationXRadiusLabel.setVisible(false);
      operationYRadiusLabel.setVisible(false);
    }
  }

  /**
   * This method determines which motion to show on panel.
   *
   * @param motion motion is the motion currently selected in motion comboBox.
   */
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
      default:
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
    operationColorChooserDisplay.setBackground(operationColor);
  }

  /**
   * Update current list of elements in model.
   */
  private void updateIdList() {
    operationIDMap = viewModel.getElementIDAndShape();
    operationIDList = new ArrayList<>();
    operationIDList.addAll(operationIDMap.keySet());
    operationIDArray = new String[operationIDMap.size()];
    operationIDList.toArray(operationIDArray);
  }

  /**
   * Update comboBoxes to reflect current elements in model.
   */
  private void updateComboBoxes() {
    operationChooseIdComboBox.setModel(new DefaultComboBoxModel(operationIDArray));
    deleteChooseIdComboBox.setModel(new DefaultComboBoxModel(operationIDArray));
  }
}