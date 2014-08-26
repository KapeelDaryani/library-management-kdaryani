import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manage {
	public static JTextField textField; // first name
	public static JTextField textField_1; // last name
	public static JTextField textField_2; // password
	public static JTextField textField_3; //search box id
	public static JTextField textField_4; // box id
	private static Object[] temp = {"","","",""};
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static JPanel managePane(){
		
		JPanel managePane = new JPanel();
		managePane.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		managePane.setBackground(Color.WHITE);
		managePane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GUI.class.getResource("/Pictures/IIT_Logo.png")));
		label.setBounds(548, 11, 256, 57);
		managePane.add(label);
		
		JLabel lblNewLabel = new JLabel("Manage");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 137, 26);
		managePane.add(lblNewLabel);
		
		JLabel label_0 = new JLabel("First Name");
		label_0.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_0.setBounds(10, 172, 150, 26);
		managePane.add(label_0);
		
		textField = new JTextField();
		textField.setBounds(10, 202, 450, 30);
		textField.setFont(new Font("Tahoma", 0, 14));
		managePane.add(textField);
		
		final JLabel label_1 = new JLabel("Last Name");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(10, 252, 150, 26);
		managePane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 282, 450, 30);
		textField_1.setFont(new Font("Tahoma", 0, 14));
		managePane.add(textField_1);
		
		JLabel label_2 = new JLabel("Password");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(10, 341, 150, 26);
		managePane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 371, 450, 30);
		textField_2.setFont(new Font("Tahoma", 0, 14));
		managePane.add(textField_2);
		
		JLabel label_3 = new JLabel("Search by ID");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(10, 80, 150, 26);
		managePane.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(10, 110, 150, 30);
		textField_3.setFont(new Font("Tahoma", 0, 14));
		managePane.add(textField_3);
		
		String[] privilegeArray = {"User","Librarian"};
		final JComboBox privilege = new JComboBox(privilegeArray);
		privilege.setBounds(520, 200, 150, 30);
		privilege.setSelectedIndex(0);
		managePane.add(privilege);
		
		//search button
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(185, 110, 100, 30);
		searchButton.setFocusPainted(false);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(privilege.getSelectedIndex() == 0){
					temp = SQLCalls.manageUser(textField_3.getText());
				}else{
					temp = SQLCalls.manageLib(textField_3.getText());
				}
				textField.setText((String)temp[1]);
				textField_1.setText((String)temp[2]);
				textField_2.setText((String)temp[3]);
				textField_4.setText((String)temp[0]);
			}
		});
		managePane.add(searchButton);
		
		//edit button
		JButton editButton = new JButton("Edit");
		editButton.setBounds(10, 440, 100, 30);
		editButton.setFocusPainted(false);
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				temp[0]= textField_4.getText();
				temp[1]= textField.getText();
				temp[2]= textField_1.getText();
				temp[3]= textField_2.getText();
				//if user is selected make a new user
				if(privilege.getSelectedIndex() == 0){
					if(SQLCalls.editUser(temp)){
						JOptionPane.showMessageDialog(null, "Successfuly updated user : " + textField_4.getText());
					}else{
						JOptionPane.showMessageDialog(null, "FAILED to updated user : " + textField_4.getText());
					}
				}else{//else edit a user
					if(SQLCalls.editLib(temp)){
						JOptionPane.showMessageDialog(null, "Successfuly updated user : " + textField_4.getText());
					}else{
						JOptionPane.showMessageDialog(null, "FAILED to updated user : " + textField_4.getText());
					}
				}
			}
		});
		managePane.add(editButton);
		
		//delete button
		JButton deleteButton = new JButton("Delete");
		deleteButton.setBounds(150, 440, 100, 30);
		deleteButton.setFocusPainted(false);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(privilege.getSelectedIndex() == 0){
					if(SQLCalls.deleteUser(textField_4.getText())){
						JOptionPane.showMessageDialog(null, "Successfuly DELETED user : " + textField_4.getText());
					}else{
						JOptionPane.showMessageDialog(null, "FAILED to DELETE user : " + textField_4.getText());
					}
				}else{
					if(SQLCalls.deleteLib(textField_4.getText())){
						JOptionPane.showMessageDialog(null, "Successfuly DELETED user : " + textField_4.getText());
					}else{
						JOptionPane.showMessageDialog(null, "FAILED to DELETE user : " + textField_4.getText());
					}
				}
			}
		});
		managePane.add(deleteButton);
		
		//add button
		JButton addButton = new JButton("Add");
		addButton.setBounds(290, 440, 100, 30);
		addButton.setFocusPainted(false);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				temp[1]= textField.getText();
				temp[2]= textField_1.getText();
				temp[3]= textField_2.getText();
				if(privilege.getSelectedIndex() == 0){
					JOptionPane.showMessageDialog(null, "Successfuly added user. ID is : " + SQLCalls.addUser(temp));
				}else{
					JOptionPane.showMessageDialog(null, "Successfuly added user. ID is : " + SQLCalls.addLib(temp));
				}
				
			}
		});
		managePane.add(addButton);
		
		//text field for id
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(310, 110, 150, 30);
		textField_4.setFont(new Font("Tahoma", 0, 14));
		managePane.add(textField_4);
				
		JLabel label_4 = new JLabel("Privilege");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(550, 172, 150, 26);
		managePane.add(label_4);
		
		return managePane;
	}
}
