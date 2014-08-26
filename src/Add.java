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

public class Add {
	
	static JTextField titleBar, editionBar, numcopyBar, pubnameBar, pubaddBar,
	pubdateBar ,authorBar, author1Bar , contribBar , contrib1Bar,articleBar;
	//static JLabel titleLabel; 
	@SuppressWarnings("unchecked")
	public static JPanel addPane(){
		JPanel addPane = new JPanel();
		addPane.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		addPane.setBackground(Color.WHITE);
		addPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GUI.class.getResource("/Pictures/IIT_Logo.png")));
		label.setBounds(548, 11, 256, 57);
		addPane.add(label);
		
		JLabel lblNewLabel = new JLabel("Add");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 137, 26);
		addPane.add(lblNewLabel);
	
		// type label
				JLabel typeLabel = new JLabel("Type of Document");
				typeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				typeLabel.setBounds(10, 100, 137, 26);
				addPane.add(typeLabel);
				
				
				// Title label
				final JLabel titleLabel = new JLabel("Title");
				titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				titleLabel.setBounds(10, 150, 137, 26);
				addPane.add(titleLabel);
				
				//title bar
				titleBar = new JTextField();
				titleBar.setBounds(150, 150, 300, 30);
				titleBar.setEditable(true);
				addPane.add(titleBar);
				
				// Title label
				final JLabel articleLabel = new JLabel("Article");
				articleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				articleLabel.setBounds(460, 150, 137, 26);
				addPane.add(articleLabel);
				
				final JLabel articleLabel1 = new JLabel("(daily,weekly,monthly,yearly)");
				articleLabel1.setFont(new Font("Tahoma", Font.BOLD, 14));
				articleLabel1.setBounds(460, 190, 237, 26);
				addPane.add(articleLabel1);
				
				//title bar
				articleBar = new JTextField();
				articleBar.setBounds(550, 150, 250, 30);
				articleBar.setEditable(true);
				addPane.add(articleBar);				
				
				//Edition label
				final JLabel editionLabel = new JLabel("Edition");
				editionLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				editionLabel.setBounds(10, 200, 137, 26);
				addPane.add(editionLabel);
				
				//edition bar
				editionBar = new JTextField();
				editionBar.setBounds(150, 200, 300, 30);
				editionBar.setEditable(true);
				addPane.add(editionBar);

				
				//number of copies label
				final JLabel numcopyLabel = new JLabel("Number of Copies");
				numcopyLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				numcopyLabel.setBounds(10, 250, 137, 26);
				addPane.add(numcopyLabel);
				
				//number of copies bar
				numcopyBar = new JTextField();
				numcopyBar.setBounds(150, 250, 300, 30);
				numcopyBar.setEditable(true);
				addPane.add(numcopyBar);
	
				//publisher name label
				final JLabel pubnameLabel = new JLabel("Publisher Name");
				pubnameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				pubnameLabel.setBounds(10, 300, 137, 26);
				addPane.add(pubnameLabel);

				//publisher name bar
				pubnameBar = new JTextField();
				pubnameBar.setBounds(150, 300, 300, 30);
				pubnameBar.setEditable(true);
				addPane.add(pubnameBar);

				//publisher address label
				final JLabel pubaddLabel = new JLabel("Publisher Address");
				pubaddLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				pubaddLabel.setBounds(10, 350, 137, 26);
				addPane.add(pubaddLabel);

				//publisher address bar
				pubaddBar = new JTextField();
				pubaddBar.setBounds(150, 350, 300, 30);
				pubaddBar.setEditable(true);
				addPane.add(pubaddBar);

				//publishing date
				final JLabel pubdateLabel = new JLabel("Publisher Date");
				pubdateLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				pubdateLabel.setBounds(10, 400, 137, 26);
				addPane.add(pubdateLabel);

				//publishing date bar
				pubdateBar = new JTextField();
				pubdateBar.setBounds(150, 400, 300, 30);
				pubdateBar.setEditable(true);
				addPane.add(pubdateBar);

				//entering name note label
				final JLabel noteLabel = new JLabel("");
				noteLabel.setFont(new Font("Tahoma", Font.BOLD, 9));
				noteLabel.setBounds(480, 450, 237, 30);
				addPane.add(noteLabel);
				
				final JLabel note1Label = new JLabel("");
				note1Label.setFont(new Font("Tahoma", Font.BOLD, 9));
				note1Label.setBounds(480, 460, 237, 30);
				addPane.add(note1Label);
				
				final JLabel note2Label = new JLabel("");
				note2Label.setFont(new Font("Tahoma", Font.BOLD, 9));
				note2Label.setBounds(480, 470, 237, 30);
				addPane.add(note2Label);
						
				final JLabel authorLabel = new JLabel("Author Firstname");
				authorLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				authorLabel.setBounds(10, 450, 137, 26);
				addPane.add(authorLabel);

				//author bar
				authorBar = new JTextField();
				authorBar.setBounds(150, 450, 300, 30);
				authorBar.setEditable(true);
				addPane.add(authorBar);
				
				//author Last name
				final JLabel author1Label = new JLabel("Author Lastname");
				author1Label.setFont(new Font("Tahoma", Font.BOLD, 14));
				author1Label.setBounds(10, 500, 137, 26);
				addPane.add(author1Label);
				
				//Last name author bar
				author1Bar = new JTextField();
				author1Bar.setBounds(150, 500, 300, 30);
				author1Bar.setEditable(true);
				addPane.add(author1Bar);
	
				final JLabel contribLabel = new JLabel("");
				contribLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				contribLabel.setBounds(10, 550, 137, 26);
				addPane.add(contribLabel);

				//author bar
				contribBar = new JTextField();
				contribBar.setBounds(150, 550, 300, 30);
				contribBar.setEditable(false);
				addPane.add(contribBar);
				
				//author Last name
				final JLabel contrib1Label = new JLabel("");
				contrib1Label.setFont(new Font("Tahoma", Font.BOLD, 14));
				contrib1Label.setBounds(10, 600, 137, 26);
				addPane.add(contrib1Label);
				
				//Last name author bar
				contrib1Bar = new JTextField();
				contrib1Bar.setBounds(150, 600, 300, 30);
				contrib1Bar.setEditable(false);
				addPane.add(contrib1Bar);
				
				//Combo box for search by
				String[] searchByArray = {"Book","Journal","Magazine"};
				@SuppressWarnings("rawtypes")
				final JComboBox typeDropdown = new JComboBox(searchByArray);
				typeDropdown.setBounds(150, 100, 150, 30);
				typeDropdown.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int temp = typeDropdown.getSelectedIndex();
						
						switch (temp){
						case 0: 
							titleLabel.setText("Book Title");
							editionLabel.setText("Edition");
							numcopyLabel.setText("Number of Copies");
							pubnameLabel.setText("Publisher Name");
							pubaddLabel.setText("Publisher Address");
							pubdateLabel.setText("Publishing Date");
							authorLabel.setText("Author First Name");
							author1Label.setText("Author Last Name");
							noteLabel.setText("Note: For multiple");
							note1Label.setText("authors write each name as");
							note2Label.setText("a comma seperated list");
							contribLabel.setText("");
							contrib1Label.setText("");
							articleLabel.setText("");
							articleLabel1.setText("");
							articleBar.setEditable(false);
							author1Bar.setEditable(true);
							contribBar.setEditable(false);
							contrib1Bar.setEditable(false);
							break;
						case 1:
							titleLabel.setText("Journal Title");
							editionLabel.setText("Issue");
							numcopyLabel.setText("Number of Copies");
							pubnameLabel.setText("Publisher Name");
							pubaddLabel.setText("Publisher Address");
							pubdateLabel.setText("Publishing Date");
							authorLabel.setText("Author First Name");
							author1Label.setText("Author Last Name");
							contribLabel.setText("Editor First");
							contrib1Label.setText("Editor Last");
							articleLabel.setText("Article");
							noteLabel.setText("Note: For multiple articles and");
							note1Label.setText("authors write each name as");
							note2Label.setText("a comma seperated list");
							articleLabel1.setText("");
							articleBar.setEditable(true);
							author1Bar.setEditable(true);
							contribBar.setEditable(true);
							contrib1Bar.setEditable(true);
							break;
						case 2:
							titleLabel.setText("Magazine Title");
							editionLabel.setText("Issue Name");
							numcopyLabel.setText("Number of Copies");
							pubnameLabel.setText("Publisher Name");
							pubaddLabel.setText("Publisher Address");
							pubdateLabel.setText("Publishing Date");
							authorLabel.setText("Editor First Name");
							author1Label.setText("Editor Last Name");
							contribLabel.setText("Contributor First");
							contrib1Label.setText("Contributor Last");
							articleLabel.setText("Period");
							noteLabel.setText("");
							note1Label.setText("");
							note2Label.setText("");
							articleLabel1.setText("(daily,weekly,monthly,yearly)");
							articleBar.setEditable(true);
							author1Bar.setEditable(true);
							contribBar.setEditable(true);
							contrib1Bar.setEditable(true);
							break;
						}
					}
				});
				typeDropdown.setSelectedIndex(0);
				addPane.add(typeDropdown);
				
				//add button
				JButton addButton = new JButton("Add");
				addButton.setBounds(10, 650, 100, 30);
				addButton.setFocusPainted(false);
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						switch((String)typeDropdown.getSelectedItem()){
						
						case "Book":
							if(!titleBar.getText().equals("") && !numcopyBar.getText().equals("") && !editionBar.getText().equals("")){
								if(!pubnameBar.getText().equals("") && !pubaddBar.getText().equals("") && !pubdateBar.getText().equals("")){
									if(!authorBar.getText().equals("") && !author1Bar.getText().equals("")){
										String first  = authorBar.getText();
										String last = author1Bar.getText();
										  
										String[] firstArray = first.split(",");
										String[] lastArray = last.split(",");
										  
										if(firstArray.length == lastArray.length){
											//everything is checked nothing is null author first and last match in lenght
											//call sql command
											if(SQLCalls.addBook(titleBar.getText(),numcopyBar.getText(),editionBar.getText(),
													pubnameBar.getText(), pubaddBar.getText(),pubdateBar.getText(),
													firstArray, lastArray)){
												JOptionPane.showMessageDialog(null, "Successfully added book");
											}else{
												JOptionPane.showMessageDialog(null, "Failed to added book");
											}
										}else{
											JOptionPane.showMessageDialog(null, "Number of Author first and last names don't match");
										}
									}else{
										JOptionPane.showMessageDialog(null, "Author information can not be null");
									}
								}else{
									JOptionPane.showMessageDialog(null, "Publisher information can not be null");
								}
							}else{
								JOptionPane.showMessageDialog(null, "Book information can not be null");
							}
							
							break;
						
						case "Journal":
							if(!titleBar.getText().equals("") && !numcopyBar.getText().equals("") && !editionBar.getText().equals("")){
								if(!pubnameBar.getText().equals("") && !pubaddBar.getText().equals("") && !pubdateBar.getText().equals("")){
									if(!authorBar.getText().equals("") && !author1Bar.getText().equals("") && !articleBar.getText().equals("")){
										String first  = authorBar.getText();
										String last = author1Bar.getText();
										String article = articleBar.getText();
										  
										String[] firstArray = first.split(",");
										String[] lastArray = last.split(",");
										String[] articleArray = article.split(",");
										  
										if(firstArray.length == lastArray.length && lastArray.length == articleArray.length){
											
											if(!contribBar.getText().equals("") && !contrib1Bar.getText().equals("")){
												
												//everything is checked nothing is null author first and last match in lenght
												//call sql command
												if(SQLCalls.addJournal(titleBar.getText(),numcopyBar.getText(),editionBar.getText(),
													pubnameBar.getText(), pubaddBar.getText(),pubdateBar.getText(),
													firstArray, lastArray,contribBar.getText(), contrib1Bar.getText(), articleArray)){
													
													JOptionPane.showMessageDialog(null, "Successfully added Journal");
								
												}else{
													JOptionPane.showMessageDialog(null, "Failed to added Journal");
												}
											}else{
												JOptionPane.showMessageDialog(null, "Editor must not be null");
											}
										}else{
											JOptionPane.showMessageDialog(null, "Number of Article + Author first and last names don't match");
										}
									}else{
										JOptionPane.showMessageDialog(null, "Author information can not be null");
									}
								}else{
									JOptionPane.showMessageDialog(null, "Publisher information can not be null");
								}
							}else{
								JOptionPane.showMessageDialog(null, "Journal information can not be null");
							}
						break;
						
						case "Magazine":
							if(!titleBar.getText().equals("") && !numcopyBar.getText().equals("") && !editionBar.getText().equals("") && !articleBar.getText().equals("")){
								if(!pubnameBar.getText().equals("") && !pubaddBar.getText().equals("") && !pubdateBar.getText().equals("")){
									if(!authorBar.getText().equals("") && !author1Bar.getText().equals("")){
										if(!contribBar.getText().equals("") && !contrib1Bar.getText().equals("")){
											//call sql command
											if(SQLCalls.addMagazine(titleBar.getText(),numcopyBar.getText(),editionBar.getText(),
													pubnameBar.getText(), pubaddBar.getText(),pubdateBar.getText(),
													authorBar.getText(), author1Bar.getText(),contribBar.getText(), 
													contrib1Bar.getText(), articleBar.getText())){
												
												JOptionPane.showMessageDialog(null, "Successfully added magazine");
								
											}else{
												JOptionPane.showMessageDialog(null, "Failed to added magazine");
											}
										}else{
											JOptionPane.showMessageDialog(null, "Contributors must not be null");
										}
									}else{
										JOptionPane.showMessageDialog(null, "Editor information can not be null");
									}
								}else{
									JOptionPane.showMessageDialog(null, "Publisher information can not be null");
								}
							}else{
								JOptionPane.showMessageDialog(null, "Magazine information can not be null");
							}
						break;
						}		
					}
				
				});
				addPane.add(addButton);
				


		return addPane;
	}
}
