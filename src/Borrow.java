/* Martinez, Jorge
 * Daryani, Kapeel
 * Madeyski, George A.
 * Library Program for cs425
 * 
 * borrow
 * -----------
 * This is the borrow Class that creates the borrow Panel
 * 
 */

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

public class Borrow {
	private static JTextField ID;
	private static JTextField currentLogIn;
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static JTextField textField_4; 
	@SuppressWarnings("rawtypes")
	private static JComboBox selectType = new JComboBox();
	
	@SuppressWarnings({ "unchecked" })
	public static JPanel borrowPane(){
		JPanel borrowPane = new JPanel();
		borrowPane.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		borrowPane.setBackground(Color.WHITE);
		borrowPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GUI.class.getResource("/Pictures/IIT_Logo.png")));
		label.setBounds(548, 11, 256, 57);
		borrowPane.add(label);
		
		JLabel lblNewLabel = new JLabel("Borrow");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 137, 26);
		borrowPane.add(lblNewLabel);
		
		//search label
		JLabel selectTypeLabel = new JLabel("Select Type");
		selectTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		selectTypeLabel.setBounds(15, 40, 137, 26);
		borrowPane.add( selectTypeLabel );
		
		//search label
		JLabel IDLabel = new JLabel("Enter ID");
		IDLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		IDLabel.setBounds(180, 40, 137, 26);
		borrowPane.add( IDLabel );
		
		//text box
		ID = new JTextField();
		ID.setBounds(180, 70, 150, 30);
		ID.setFont(new Font("Tahoma", Font.BOLD, 14));
		borrowPane.add(ID);
		
		// currently logged in
		JLabel currentLogInLabel = new JLabel("Currently Logged In");
		currentLogInLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		currentLogInLabel.setBounds(588, 120, 150, 26);
		borrowPane.add( currentLogInLabel );
		
		//text box
		currentLogIn = new JTextField();
		currentLogIn.setBounds(550, 150, 225, 30);
		currentLogIn.setEditable(false);
		currentLogIn.setFont(new Font("Tahoma", 0, 14));
		borrowPane.add(currentLogIn);
		
		JLabel label_0 = new JLabel("Title");
		label_0.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_0.setBounds(10, 172, 150, 26);
		borrowPane.add(label_0);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(10, 202, 450, 30);
		textField.setFont(new Font("Tahoma", 0, 14));
		borrowPane.add(textField);
		
		final JLabel label_1 = new JLabel("Author");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(10, 252, 150, 26);
		borrowPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(10, 282, 450, 30);
		textField_1.setFont(new Font("Tahoma", 0, 14));
		borrowPane.add(textField_1);
		
		JLabel label_2 = new JLabel("Type");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(10, 341, 150, 26);
		borrowPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(10, 371, 450, 30);
		textField_2.setFont(new Font("Tahoma", 0, 14));
		borrowPane.add(textField_2);
		
		JLabel label_3 = new JLabel("Number of Avaliable Copies");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(10, 430, 250, 26);
		borrowPane.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(10, 460, 250, 30);
		textField_3.setFont(new Font("Tahoma", 0, 14));
		borrowPane.add(textField_3);
		
		JLabel label_4 = new JLabel("ID");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(300, 430, 250, 26);
		borrowPane.add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(300, 460, 150, 30);
		textField_4.setFont(new Font("Tahoma", 0, 14));
		borrowPane.add(textField_4);
		
		
		JButton button = new JButton("Borrow");
		button.setFocusPainted(false);
		button.setBounds(588, 460, 144, 40);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					if(Integer.parseInt(textField_3.getText()) > 0){
						if(SQLCalls.Borrow(textField_4.getText(), textField_2.getText())){
							JOptionPane.showMessageDialog(null, "You successfully borrowed that document");
							textField.setText(" ");
							textField_1.setText(" ");
							textField_2.setText(" ");
							textField_3.setText(" ");
							textField_4.setText(" ");
						}
					}else{
						JOptionPane.showMessageDialog(null, "There are no available copies of that document");
					}
				}catch(NumberFormatException e){
	    			//do nothing if there is no data
	    		}
			
			}
		});
		borrowPane.add(button);
		
		//Combo box for search by
		String[] searchByArray = {"Books","Journals","Magazines"};
		for(int i=0;i<searchByArray.length;i++ ){
			selectType.addItem(searchByArray[i]);
		}
		selectType.setBounds(10, 70, 150, 30);
		selectType.setSelectedIndex(0);
		selectType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int temp = selectType.getSelectedIndex();
				
				switch(temp){
				case 0:
					label_1.setText("Author");
					textField.setText(" ");
					textField_1.setText(" ");
					textField_2.setText(" ");
					textField_3.setText(" ");
					textField_4.setText(" ");
					break;
				case 1:
					label_1.setText("Publisher");
					textField.setText(" ");
					textField_1.setText(" ");
					textField_2.setText(" ");
					textField_3.setText(" ");
					textField_4.setText(" ");
					break;
				case 2 :
					label_1.setText("Editor");
					textField.setText(" ");
					textField_1.setText(" ");
					textField_2.setText(" ");
					textField_3.setText(" ");
					textField_4.setText(" ");
					break;
				}
				
			}
		});
		borrowPane.add(selectType);
		
		//search button
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(350, 70, 100, 30);
		searchButton.setFocusPainted(false);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] data = SQLCalls.searchBorrow(ID.getText(),(String) selectType.getSelectedItem());
				
				switch((String) selectType.getSelectedItem()){
				case "Books" :
					textField.setText((String)data[2]);
					textField_1.setText((String)data[4] + " " + (String)data[5]);
					textField_2.setText("Books");
					textField_3.setText((String)data[1]);
					textField_4.setText((String)data[0]);
					break;
				case "Journals":
					textField.setText((String)data[2]);
					textField_1.setText((String)data[7]);
					textField_2.setText("Journals");
					textField_3.setText((String)data[1]);
					textField_4.setText((String)data[0]);
					break;
				case "Magazines":
					textField.setText((String)data[2]);
					textField_1.setText((String)data[5] + " " + (String)data[6]);
					textField_2.setText("Magazines");
					textField_3.setText((String)data[1]);
					textField_4.setText((String)data[0]);
					break;
				}
			}
		});
		borrowPane.add(searchButton);
		
		return borrowPane;
	}

	
	public static void setTitle(String a){
		textField.setText(a);
	}
	
	public static void setSecond(String a){
		textField_1.setText(a);
	}
	
	public static void setType(String a){
		textField_2.setText(a);
	}
	
	public static void setNumCopies(String a){
		textField_3.setText(a);
	}
	
	public static void setCurrentLoggedIn(String a){
		currentLogIn.setText(a);
	}
	
	public static void setID(String a){
		ID.setText(a);
		textField_4.setText(a);
	}
	
	public static void setTypeDropDown(String a){
		switch(a.toLowerCase()){
		case "books": selectType.setSelectedIndex(0);
					break;
		case "journals": selectType.setSelectedIndex(1);
					break;
		case "magazines": selectType.setSelectedIndex(2);
					break;
		}
	}
	
	
}
