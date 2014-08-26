//kapeel daryani

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Return {
	public static String[] title = {"ID","Title","Type"};
	public static Object[][] values;
	private static JTextField currentLogIn;

	@SuppressWarnings("serial")
	public static JPanel returnPane(){
		JPanel returnPane = new JPanel();
		returnPane.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		returnPane.setBackground(Color.WHITE);
		returnPane.setLayout(null);
		
		//table
		final JTable table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setBounds(58, 100, 360, 160);	
		table.setRowHeight(19);
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 200, 795, 500);
		//center the values
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		returnPane.add(scrollPane);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GUI.class.getResource("/Pictures/IIT_Logo.png")));
		label.setBounds(548, 11, 256, 57);
		returnPane.add(label);
		
		JLabel lblNewLabel = new JLabel("Return");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 137, 26);
		returnPane.add(lblNewLabel);
		
		//return button
				JButton returnButton = new JButton("Return");
				returnButton.setBounds(10, 150, 100, 30);
				returnButton.setFocusPainted(false);
				returnButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(table.getSelectedRow() != -1){
							if(SQLCalls.returnDocument((String)table.getValueAt(table.getSelectedRow(), 0), (String)table.getValueAt(table.getSelectedRow(), 2))){
								values = SQLCalls.getBorrowedDocuments();
								table.setModel(new DefaultTableModel(values,title){
								    @Override
								    public boolean isCellEditable(int row, int column) {
								       return false;
								    }
								} );
								JOptionPane.showMessageDialog(null, "Document Successfuly Returned");
							}else{
								JOptionPane.showMessageDialog(null, "An error has occured please refresh and try again");
							}
						}
					}
				});
				returnPane.add(returnButton);
				
		//reFRESH button
		JButton refreshButton = new JButton("Refresh");
		refreshButton.setBounds(150, 150, 100, 30);
		refreshButton.setFocusPainted(false);
		refreshButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						values = SQLCalls.getBorrowedDocuments();
						table.setModel(new DefaultTableModel(values,title){
						    @Override
						    public boolean isCellEditable(int row, int column) {
						       return false;
						    }
						} );
					}
				});
		returnPane.add(refreshButton);
				
		
				
		// currently logged in
		JLabel currentLogInLabel = new JLabel("Currently Logged In");
		currentLogInLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		currentLogInLabel.setBounds(588, 120, 150, 26);
		returnPane.add( currentLogInLabel );
		
		//text box
		currentLogIn = new JTextField();
		currentLogIn.setBounds(588, 150, 150, 30);
		currentLogIn.setEditable(false);
		returnPane.add(currentLogIn); 
		
		// AND ENDS HERE YOU MAY ADD FUCTIONS AS NEEDED
		
		return returnPane;
	}
	
	public static void setCurrentLoggedIn(String a){
		currentLogIn.setText(a);
	}
}
