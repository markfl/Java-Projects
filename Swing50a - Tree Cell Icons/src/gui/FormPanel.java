package gui;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {
	
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel occupationLabel;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField occupationField;
	private JButton okBtn;
	private FormListener formListener;
	private JList ageList;
	private JComboBox empCombo;
	private JCheckBox citizenCheck;
	private JTextField taxField;
	private JLabel taxLabel;
	
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup;
	
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		setMinimumSize(dim);
		
		firstNameLabel = new JLabel("First Name: ");
		lastNameLabel = new JLabel("Last Name: ");
		occupationLabel = new JLabel("Occupation: ");
		firstNameField = new JTextField(10);
		lastNameField = new JTextField(10);
		occupationField = new JTextField(10);
		ageList = new JList();
		empCombo = new JComboBox();
		citizenCheck = new JCheckBox();
		taxField = new JTextField(9);
		taxLabel = new JLabel("Tax ID: ");
		okBtn = new JButton("OK");
		
		// Set up mnemomics
		okBtn.setMnemonic(KeyEvent.VK_O);
		
		firstNameLabel.setDisplayedMnemonic(KeyEvent.VK_I);
		firstNameLabel.setLabelFor(firstNameField);
		lastNameLabel.setDisplayedMnemonic(KeyEvent.VK_L);
		lastNameLabel.setLabelFor(lastNameField);
		occupationLabel.setDisplayedMnemonic(KeyEvent.VK_C);
		occupationLabel.setLabelFor(occupationField);
		
		maleRadio = new JRadioButton("Male");
		femaleRadio = new JRadioButton("Female");
		
		maleRadio.setActionCommand("male");
		femaleRadio.setActionCommand("female");
		
		genderGroup = new ButtonGroup();
		
		maleRadio.setSelected(true);
		
		// Set up gender radios
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		
		// Set up tax ID
		taxLabel.setEnabled(false);
		taxField.setEnabled(false);
		
		citizenCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean isTicked = citizenCheck.isSelected();
				taxLabel.setEnabled(isTicked);
				taxField.setEnabled(isTicked);
				if(!isTicked) {
					taxField.setText("");
				}
			}
		});
		
		// Set up list box
		DefaultListModel ageModel = new DefaultListModel();
		ageModel.addElement(new AgeCategory(0, "Under 18"));
		ageModel.addElement(new AgeCategory(1, "18 to 65"));
		ageModel.addElement(new AgeCategory(2, "65 or over"));
		ageList.setModel(ageModel);
		
		ageList.setPreferredSize(new Dimension(110, 68));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(1);
		
		// Set up combo box
		DefaultComboBoxModel empModel = new DefaultComboBoxModel();
		empModel.addElement("employed");
		empModel.addElement("self-employed");
		empModel.addElement("unemployed");
		empModel.addElement("retired");
		empCombo.setModel(empModel);
		
		
		
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();
				String occupation = occupationField.getText();
				AgeCategory ageCat = (AgeCategory)ageList.getSelectedValue();
				String empCat = (String)empCombo.getSelectedItem();
				boolean usCitizen = citizenCheck.isSelected();
				String taxId = taxField.getText();
				
				String gender = genderGroup.getSelection().getActionCommand();
				
				FormEvent ev = new FormEvent(this, firstName, lastName, occupation, ageCat.getId(), empCat, usCitizen, taxId, gender);
				
				if(formListener != null) {
					formListener.formEventOccurred(ev);
				}
			}
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();
	}
	
	public void layoutComponents() {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		// First Row
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(firstNameLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(firstNameField, gc);
		
		// Second Row
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lastNameLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lastNameField, gc);
		
		// Second Row
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(occupationLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(occupationField, gc);

		// next Row
		gc.weightx = 1;
		gc.weighty = .2;
		gc.gridy++;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Age: "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(ageList, gc);
		
		// Next Row
		gc.weightx = 1;
		gc.weighty = .2;
		gc.gridy++;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Employment: "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(empCombo, gc);
		
		// Next Row
		gc.weightx = 1;
		gc.weighty = .2;
		gc.gridy++;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("US Citizen: "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(citizenCheck, gc);
		
		// Next Row
		gc.weightx = 1;
		gc.weighty = .2;
		gc.gridy++;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(taxLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(taxField, gc);
		
		// next Row
		gc.weightx = 1;
		gc.weighty = .05;
		gc.gridy++;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Gender: "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(maleRadio, gc);
		
		// next Row
		gc.weightx = 1;
		gc.weighty = .2;
		gc.gridy++;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(femaleRadio, gc);
				
		// Next Row
		gc.weightx = 1;
		gc.weighty = 3;
		
		gc.gridx = 1;
		gc.gridy++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(okBtn, gc);
	}
	
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
}

class AgeCategory {
	private int id;
	private String text;
	
	public AgeCategory(int id, String text) {
		this.id = id;
		this.text = text;
	}
	
	public String toString() {
		return text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}