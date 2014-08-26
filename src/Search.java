/* Martinez, Jorge
 * Daryani, Kapeel
 * Madeyski, George A.
 * Library Program for cs425
 * 
 * Search
 * -----------
 * This is the Search Class that creates the Search Pannel
 * 
 */

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class Search {

	private static String[] tableTitle = {"BID","Number Of Copies","Title","Edition","Author First", "Author Second","Publisher Name", "Publisher Address","Publisher Date"};;
	private static Object[][] tableContent = {{}};
	private static String[] searchByArray = {"Title","Author","Publisher"};
	@SuppressWarnings("rawtypes")
	private static JComboBox searchIn = new JComboBox();
	
	
	//creates Search Tab
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	public static JPanel SearchPane() throws FileNotFoundException, UnsupportedEncodingException, IOException{//file is safe because of code in main
		JPanel searchPane = new JPanel();
		searchPane.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		searchPane.setBackground(Color.WHITE);
		searchPane.setLayout(null);
		
		//IIT LOGO
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GUI.class.getResource("/Pictures/IIT_Logo.png")));
		label.setBounds(548, 11, 256, 57);
		searchPane.add(label);
		
		//search label
		JLabel lblNewLabel = new JLabel("Search");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 137, 26);
		searchPane.add(lblNewLabel);
		
		//Combo box for search bar
		final JComboBox searchBar = new JComboBox(getSearchHistory());
		searchBar.setBounds(10, 50, 300, 30);
		searchBar.setEditable(true);
		searchPane.add(searchBar);	
		
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
		searchPane.add(scrollPane);
		
		//search label
		JLabel searchByLabel = new JLabel("Search By");
		searchByLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchByLabel.setBounds(15, 130, 137, 26);
		searchPane.add( searchByLabel);
		
		//Combo box for search by
		final JComboBox searchBy = new JComboBox(searchByArray);
		searchBy.setBounds(10, 160, 150, 30);
		searchPane.add(searchBy);
		
		//search label
		JLabel searchInLabel = new JLabel("Search In");
		searchInLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchInLabel.setBounds(210, 130, 150, 26);
		searchPane.add(searchInLabel);		
		
		//Combo box for search in
		String[] searchInArray = {"Books","Journals","Magazines"};
		searchIn = new JComboBox(searchInArray);
		searchIn.setBounds(200, 160, 150, 30);
		searchIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int temp = searchIn.getSelectedIndex();
				switch (temp){
				case 0: 
					searchBy.removeAllItems();
					String[] searchByArray = {"Title","Author","Publisher"};
					for(int i=0;i<searchByArray.length;i++ ){
						searchBy.addItem(searchByArray[i]);
					}
					
					String[] a = {"BID","Number Of Copies","Title","Edition","Author First", "Author Last","Publisher Name", "Publisher Address","Publisher Date"};
					tableTitle = a;
					break;
				case 1:
					searchBy.removeAllItems();
					String[] searchByArray1 = {"Journal Title","Article Title","Article Author","Publisher"};
					for(int i=0;i<searchByArray1.length;i++ ){
						searchBy.addItem(searchByArray1[i]);
					}
					
					String[] b = {"JID","Number Of Copies","Journal Name","Article Name","Issue","Author First", "Author Last","Publisher Name", "Publisher Address","Publisher Date"};
					tableTitle = b;
					break;
				case 2:
					searchBy.removeAllItems();
					String[] searchByArray2 = {"Magazine Title","Issue Name","Editor","Contributor"};
					for(int i=0;i<searchByArray2.length;i++ ){
						searchBy.addItem(searchByArray2[i]);
					}
					
					String[] c = {"MID","Number Of Copies","Magazine Name","Issue Name","period","Editor First", "Editor Last","Contributor First", "Contributor Last"};
					tableTitle = c;
					
					break;
				}
			}
		});
		searchPane.add(searchIn);

		//borrow button
		JButton borrowButton = new JButton("borrow");
		borrowButton.setBounds(500, 160, 100, 30);
		borrowButton.setFocusPainted(false);
		borrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow() != -1){
					GUI.setTab("borrow");

					Borrow.setTypeDropDown((String)searchIn.getSelectedItem());
					Borrow.setID((String)table.getValueAt(table.getSelectedRow(), 0));
					Borrow.setType((String)searchIn.getSelectedItem());
					Borrow.setNumCopies((String)table.getValueAt(table.getSelectedRow(), 1));
					Borrow.setTitle((String)table.getValueAt(table.getSelectedRow(), 2));
					
					switch((String)searchIn.getSelectedItem()){
					case "Books":
						Borrow.setSecond((String)table.getValueAt(table.getSelectedRow(), 4) + " " + (String)table.getValueAt(table.getSelectedRow(), 5));
						break;
					case "Journals":
						Borrow.setSecond((String)table.getValueAt(table.getSelectedRow(), 7));
						break;
					case "Magazines":
						Borrow.setSecond((String)table.getValueAt(table.getSelectedRow(), 5) + " " + (String)table.getValueAt(table.getSelectedRow(), 6));
					}
					
					
				}
			}
		});
		searchPane.add(borrowButton);
		
		//edit button
		JButton editButton = new JButton("edit");
		editButton.setBounds(650, 160, 100, 30);
		editButton.setFocusPainted(false);
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow() != -1){
					System.out.println(table.getValueAt(table.getSelectedRow(),0));
				}
			}
		});
		searchPane.add(editButton);
		
		//search button
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(325, 50, 100, 30);
		searchButton.setFocusPainted(false);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//stores what was typed in the jcombobox into a file called searchHistory.txt
				PrintWriter searchHistoryWriter = null;
				try {
					searchHistoryWriter = new PrintWriter(new FileWriter("searchHistory.txt", true));
				} catch (IOException e) {
					System.err.println(e);
				}
				searchHistoryWriter.println((String)searchBar.getSelectedItem());
				searchHistoryWriter.close();
				
				//removes everything from combobox and enters the data 1 by one
				//this was the most effective way to update the information
				searchBar.removeAllItems();
				String[] temp;
				try {
					temp = getSearchHistory();
					for(int i = 0; i<getSearchHistory().length;i++){
						searchBar.addItem(temp[i]);
					}
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					System.err.println(e);
				}
				//sets the currently selected item to last thing typed in before hitting search
				searchBar.setSelectedIndex(0);
						
				// search
				tableContent = SQLCalls.search((String)searchBar.getSelectedItem(), (String)searchBy.getSelectedItem(), (String)searchIn.getSelectedItem());
				
				//update table
				table.setModel(new DefaultTableModel(tableContent,tableTitle){
					
				    @Override
				    public boolean isCellEditable(int row, int column) {
				       return false;
				    }
				} );

				table.getColumnModel().getColumn(0).setPreferredWidth(10);
				table.getColumnModel().getColumn(1).setPreferredWidth(10);
				table.getColumnModel().getColumn(2).setPreferredWidth(100);
			}
		});
		searchPane.add(searchButton);
		
		//Clear History button
		JButton clearHistoryButton = new JButton("Clear History");
		clearHistoryButton.setBounds(11, 90, 120, 30);
		clearHistoryButton.setFocusPainted(false);
		clearHistoryButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent arg0) {
			//opens a new blank file in place of the search history
			PrintWriter searchHistoryClear = null;
			try {
				searchHistoryClear = new PrintWriter("searchHistory.txt");
			} catch (FileNotFoundException e) {
				System.err.println(e);
			}
			searchHistoryClear.print("");//prints nothing note: no \r key
			searchHistoryClear.close();//closes and overrides the old searchHistory.txt file
			searchBar.removeAllItems();//removes all items from combobox
		}});
		searchPane.add(clearHistoryButton);
		return searchPane;
	}
	
	
	//gets search history from file
	@SuppressWarnings("resource")
	private static String[] getSearchHistory() throws FileNotFoundException, UnsupportedEncodingException{//file is safe because of code in main
		// get search history file
		Scanner searchHistoryFile = new Scanner(new File("searchHistory.txt"));
		List searchHistoryList = new List();
		int i = 0;
		while(searchHistoryFile.hasNext()){//store the data in an endless list can be n entries long with history
			searchHistoryList.add(searchHistoryFile.nextLine(), i);
			i++;
		}
		return convertListToArray(searchHistoryList);//sends the list to be converted to an array then returns that array
	}
	
	//converts list to arrays
	private static String[] convertListToArray(List a){//note this reverses the list and the array
		//reversing was done so get most recent searches on the front of the array or the top of the list
		//note this is using a jump to front heuristic which is not stable but seemed the most logical for the type of list  
		String[] temp = new String[a.getItemCount()];
		int i = 0;
		int j = a.getItemCount() - 1;
		for(i = 0; i<a.getItemCount(); i++){
			temp[i] = a.getItem(j) ;
			j--;
		}
		return temp;
	}
	

}
