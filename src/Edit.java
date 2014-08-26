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

public class Edit {
	private static JTextField ID;
	
	static JTextField titleBar, editionBar, numcopyBar, pubnameBar, pubaddBar,
	pubdateBar ,authorBar, author1Bar , contribBar , contrib1Bar,articleBar;
	//static JLabel titleLabel; 
	@SuppressWarnings("unchecked")
	public static JPanel editPane(){
		JPanel editPane = new JPanel();
		editPane.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		editPane.setBackground(Color.WHITE);
		editPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GUI.class.getResource("/Pictures/IIT_Logo.png")));
		label.setBounds(548, 11, 256, 57);
		editPane.add(label);
		
		JLabel lblNewLabel = new JLabel("Edit");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 137, 26);
		editPane.add(lblNewLabel);
		
		//search label
				JLabel selectTypeLabel = new JLabel("Enter ID");
				selectTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				selectTypeLabel.setBounds(15, 50, 137, 26);
				editPane.add( selectTypeLabel );
				
				
	
		// type label
				JLabel typeLabel = new JLabel("Type of Document");
				typeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				typeLabel.setBounds(10, 100, 137, 26);
				editPane.add(typeLabel);
				
				
				// Title label
				final JLabel titleLabel = new JLabel("Title");
				titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				titleLabel.setBounds(10, 150, 137, 26);
				editPane.add(titleLabel);
				
				//title bar
				titleBar = new JTextField();
				titleBar.setBounds(150, 150, 300, 30);
				titleBar.setEditable(true);
				editPane.add(titleBar);
				
				// Title label
				final JLabel articleLabel = new JLabel("Article");
				articleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				articleLabel.setBounds(460, 150, 137, 26);
				editPane.add(articleLabel);
				
				final JLabel articleLabel1 = new JLabel("(daily,weekly,monthly,yearly)");
				articleLabel1.setFont(new Font("Tahoma", Font.BOLD, 14));
				articleLabel1.setBounds(460, 190, 237, 26);
				editPane.add(articleLabel1);
				
				//title bar
				articleBar = new JTextField();
				articleBar.setBounds(550, 150, 250, 30);
				articleBar.setEditable(true);
				editPane.add(articleBar);				
				
				//Edition label
				final JLabel editionLabel = new JLabel("Edition");
				editionLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				editionLabel.setBounds(10, 200, 137, 26);
				editPane.add(editionLabel);
				
				//edition bar
				editionBar = new JTextField();
				editionBar.setBounds(150, 200, 300, 30);
				editionBar.setEditable(true);
				editPane.add(editionBar);

				
				//number of copies label
				final JLabel numcopyLabel = new JLabel("Number of Copies");
				numcopyLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				numcopyLabel.setBounds(10, 250, 137, 26);
				editPane.add(numcopyLabel);
				
				//number of copies bar
				numcopyBar = new JTextField();
				numcopyBar.setBounds(150, 250, 300, 30);
				numcopyBar.setEditable(true);
				editPane.add(numcopyBar);
	
				//publisher name label
				final JLabel pubnameLabel = new JLabel("Publisher Name");
				pubnameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				pubnameLabel.setBounds(10, 300, 137, 26);
				editPane.add(pubnameLabel);

				//publisher name bar
				pubnameBar = new JTextField();
				pubnameBar.setBounds(150, 300, 300, 30);
				pubnameBar.setEditable(true);
				editPane.add(pubnameBar);

				//publisher address label
				final JLabel pubaddLabel = new JLabel("Publisher Address");
				pubaddLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				pubaddLabel.setBounds(10, 350, 137, 26);
				editPane.add(pubaddLabel);

				//publisher address bar
				pubaddBar = new JTextField();
				pubaddBar.setBounds(150, 350, 300, 30);
				pubaddBar.setEditable(true);
				editPane.add(pubaddBar);

				//publishing date
				final JLabel pubdateLabel = new JLabel("Publisher Date");
				pubdateLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				pubdateLabel.setBounds(10, 400, 137, 26);
				editPane.add(pubdateLabel);

				//publishing date bar
				pubdateBar = new JTextField();
				pubdateBar.setBounds(150, 400, 300, 30);
				pubdateBar.setEditable(true);
				editPane.add(pubdateBar);

				//entering name note label
				final JLabel noteLabel = new JLabel("Note: For multiple ");
				noteLabel.setFont(new Font("Tahoma", Font.BOLD, 9));
				noteLabel.setBounds(480, 450, 237, 30);
				editPane.add(noteLabel);
				
				final JLabel note1Label = new JLabel("authors edit first and");
				note1Label.setFont(new Font("Tahoma", Font.BOLD, 9));
				note1Label.setBounds(480, 460, 237, 30);
				editPane.add(note1Label);
				
				final JLabel note2Label = new JLabel("lastname comma seperated");
				note2Label.setFont(new Font("Tahoma", Font.BOLD, 9));
				note2Label.setBounds(480, 470, 237, 30);
				editPane.add(note2Label);
						
				final JLabel authorLabel = new JLabel("Author Firstname");
				authorLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				authorLabel.setBounds(10, 450, 137, 26);
				editPane.add(authorLabel);

				//author bar
				authorBar = new JTextField();
				authorBar.setBounds(150, 450, 300, 30);
				authorBar.setEditable(true);
				editPane.add(authorBar);
				
				//author Last name
				final JLabel author1Label = new JLabel("Author Lastname");
				author1Label.setFont(new Font("Tahoma", Font.BOLD, 14));
				author1Label.setBounds(10, 500, 137, 26);
				editPane.add(author1Label);
				
				//Last name author bar
				author1Bar = new JTextField();
				author1Bar.setBounds(150, 500, 300, 30);
				author1Bar.setEditable(true);
				editPane.add(author1Bar);
	
				final JLabel contribLabel = new JLabel("");
				contribLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				contribLabel.setBounds(10, 550, 137, 26);
				editPane.add(contribLabel);

				//author bar
				contribBar = new JTextField();
				contribBar.setBounds(150, 550, 300, 30);
				contribBar.setEditable(false);
				editPane.add(contribBar);
				
				//author Last name
				final JLabel contrib1Label = new JLabel("");
				contrib1Label.setFont(new Font("Tahoma", Font.BOLD, 14));
				contrib1Label.setBounds(10, 600, 137, 26);
				editPane.add(contrib1Label);
				
				//Last name author bar
				contrib1Bar = new JTextField();
				contrib1Bar.setBounds(150, 600, 300, 30);
				contrib1Bar.setEditable(false);
				editPane.add(contrib1Bar);
				
				//text box
				ID = new JTextField();
				ID.setBounds(150, 50, 150, 30);
				ID.setFont(new Font("Tahoma", Font.BOLD, 14));
				editPane.add(ID);
				
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
				editPane.add(typeDropdown);
				
				//search button
				JButton searchButton = new JButton("Search");
				searchButton.setBounds(350, 50, 100, 30);
				searchButton.setFocusPainted(false);
					searchButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						try{
							Integer.parseInt(ID.getText());
							
							if(!ID.getText().equals("")){
								switch((String) typeDropdown.getSelectedItem()){
								case "Book" :
										Object [] temp = {"","","" , "","","","","","","","","","","","",""};
										temp = SQLCalls.searchBookEdit(Integer.parseInt(ID.getText()));
										titleBar.setText(temp[2].toString());
										numcopyBar.setText(temp[1].toString());
										editionBar.setText(temp[3].toString());
										authorBar.setText(temp[4].toString());
										author1Bar.setText(temp[5].toString());
										pubnameBar.setText(temp[6].toString());
										pubaddBar.setText(temp[7].toString());
										pubdateBar.setText(temp[8].toString());
										//Object[] aux = new Object[] {rset.getString("bid"),rset.getString("NumberCopies"),rset.getString("title"),rset.getString("Edition"),rset.getString("FirstName"), 
										//		rset.getString("lastName"),rset.getString("pName"), rset.getString("Address"),rset.getString("pDate")};
									break;
								case "Journal":
									Object [] temp1 = {"","","" , "","","","","","","","","","","","",""}; 
									
									temp1 = SQLCalls.searchJournalsEdit(Integer.parseInt(ID.getText()));
									titleBar.setText(temp1[2].toString());
									numcopyBar.setText(temp1[1].toString());
									editionBar.setText(temp1[11].toString());
									authorBar.setText(temp1[4].toString());
									author1Bar.setText(temp1[5].toString());
									pubnameBar.setText(temp1[6].toString());
									pubaddBar.setText(temp1[7].toString());
									pubdateBar.setText(temp1[8].toString());
									contribBar.setText(temp1[9].toString());
									contrib1Bar.setText(temp1[10].toString());
									articleBar.setText(temp1[3].toString());
									
									
									//aux = new Object[] {rset.getString("jid"),rset.getString("NumberCopies"),rset.getString("jtitle"),rset.getString("atitle"),rset.getString("aFirstName"), 
										//	rset.getString("aLastName"),rset.getString("pName"), rset.getString("Address"),rset.getString("pDate"), rset.getString(""), rset.getString("")};
									break;
								case "Magazine":
									Object [] temp2 = {"","","" , "","","","","","","","","","","","",""};
									temp2 = SQLCalls.searchMagazinesEdit(Integer.parseInt(ID.getText()));
									titleBar.setText(temp2[3].toString());
									numcopyBar.setText(temp2[1].toString());
									editionBar.setText(temp2[2].toString());
									authorBar.setText(temp2[4].toString());
									author1Bar.setText(temp2[5].toString());
									pubnameBar.setText(temp2[6].toString());
									pubaddBar.setText(temp2[7].toString());
									pubdateBar.setText(temp2[8].toString());
									contribBar.setText(temp2[9].toString());
									contrib1Bar.setText(temp2[10].toString());
									articleBar.setText(temp2[11].toString());
									
									//Object[] aux = new Object[] {rset.getString("mid"),rset.getString("NumberCopies"),rset.getString("title"),rset.getString("name"),rset.getString("eFirstName"), 
										//	rset.getString("eLastName"),rset.getString("pName"), rset.getString("Address"),rset.getString("pDate"), rset.getString("cFirstName"), rset.getString("clastName")};
									
									
									
									break;
								}
							}else{
								JOptionPane.showMessageDialog(null,"Make sure to use the documents ID when searching");
							}
						}catch (NumberFormatException e){
							JOptionPane.showMessageDialog(null,"Make sure to use the documents ID when searching");
						}
					}
				});  
				editPane.add(searchButton);	
				
				//edit button
				JButton addButton = new JButton("Edit");
				addButton.setBounds(10, 650, 100, 30);
				addButton.setFocusPainted(false);
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						
						try{
							int intID = Integer.parseInt(ID.getText());
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
												if(SQLCalls.editBook(intID,titleBar.getText(),numcopyBar.getText(),editionBar.getText(),
														pubnameBar.getText(), pubaddBar.getText(),pubdateBar.getText(),
														firstArray, lastArray)){
													JOptionPane.showMessageDialog(null, "Successfully edit book");
												}else{
													JOptionPane.showMessageDialog(null, "Failed to edit book");
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
													if(SQLCalls.editJournal(intID,titleBar.getText(),numcopyBar.getText(),editionBar.getText(),
															pubnameBar.getText(), pubaddBar.getText(),pubdateBar.getText(),
															firstArray, lastArray,contribBar.getText(), contrib1Bar.getText(), articleArray)){
														
														JOptionPane.showMessageDialog(null, "Successfully edit Journal");
									
													}else{
														JOptionPane.showMessageDialog(null, "Failed to edit Journal");
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
												if(SQLCalls.editMagazine(intID,titleBar.getText(),numcopyBar.getText(),editionBar.getText(),
														pubnameBar.getText(), pubaddBar.getText(),pubdateBar.getText(),
														authorBar.getText(), author1Bar.getText(),contribBar.getText(), 
														contrib1Bar.getText(), articleBar.getText())){
													
													JOptionPane.showMessageDialog(null, "Successfully edit magazine");
												}else{
													JOptionPane.showMessageDialog(null, "Failed to edit magazine");
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
						}catch (NumberFormatException e){
							JOptionPane.showMessageDialog(null,"Make sure to use the documents ID when searching");
						}
					
					}
					
				});
				editPane.add(addButton);
				
				JButton deleteButton = new JButton("Delete");
				deleteButton.setBounds(150, 650, 100, 30);
				deleteButton.setFocusPainted(false);
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try{
							int id = Integer.parseInt(ID.getText());
							if(!ID.getText().equals("")){
								switch((String) typeDropdown.getSelectedItem()){
								case "Book" :
										if(SQLCalls.deleteBook(id)){
											JOptionPane.showMessageDialog(null, "successfuly to delete book");
										}else{
											JOptionPane.showMessageDialog(null, "Failed to delete book");
										}
									break;
								case "Journal":
										if(SQLCalls.deleteJournal(id)){
											JOptionPane.showMessageDialog(null, "successfuly to delete journal");
										}else{
											JOptionPane.showMessageDialog(null, "Failed to delete journal");
										}
									break;
								case "Magazine":
										if(SQLCalls.deleteMagazine(id)){
											JOptionPane.showMessageDialog(null, "successfuly to delete Magazine");
										}else{
											JOptionPane.showMessageDialog(null, "Failed to delete Magazine");
										}
									break;
								}
							}else{
								JOptionPane.showMessageDialog(null,"Make sure to use the documents ID when searching");
							}
						}catch (NumberFormatException e){
							JOptionPane.showMessageDialog(null,"Make sure to use the documents ID when searching");
						}
					}
					});
				editPane.add(deleteButton);

		return editPane;
	}
}
