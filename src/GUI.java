/* Jorge Martinez
 * Daryani, Kapeel
 * Madeyski, George A.
 * Library Program for cs425
 * 
 * GUI
 * -----------
 * This is the GUI class is is probably one of the biggest in the program
 * 
 * This class is responsible for drawing the GUI and Moving the task of pannel creation to each panel
 * 
 * It then either does the action requested by the input or exports the job to outside classes such
 * 
 * as the SQLCALLS class
 * 
 */


import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Insets;
import java.io.IOException;
import java.awt.Toolkit;
import javax.swing.border.EtchedBorder;


@SuppressWarnings("serial")
public class GUI extends JFrame {
	//**stub remove this terrible code bellow**
	public boolean adminLoggedIn = false; //used to check if user has logged in
	public boolean userLoggedIn = false; // used to check if admin has logged in
	//**end of stub**
	private static JTabbedPane tabs;

	//creates the main window and handles the code to ensure users can only access the respective windows
	public GUI() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {//file is safe because of code in main
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		UIManager.getDefaults().put("TabbedPane.contentBorderInsets", new Insets(0,0,0,0));
		UIManager.getDefaults().put("TabbedPane.tabsOverlapBorder", true);
		UIManager.put("TabbedPane.selected", Color.DARK_GRAY);
		UIManager.put("TabbedPane.background", Color.BLACK);
		UIManager.put("TabbedPane.foreground", Color.WHITE);
		UIManager.put("Button.background", Color.RED);
		UIManager.put("Button.foreground", Color.WHITE);
		UIManager.put("Button.select", Color.BLACK);
		UIManager.put("ComboBox.background", Color.WHITE);
		UIManager.put("ComboBox.foreground", Color.BLACK);
		UIManager.put("ComboBox.selectionBackground", Color.RED);
		UIManager.put("ComboBox.selectionForeground", Color.WHITE);
		
		setTitle("IIT Library");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/Pictures/IITIcon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		setBackground(Color.BLACK);

		//create tab pane used for main navigation
		tabs = new JTabbedPane();
		tabs.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tabs.setTabPlacement(JTabbedPane.LEFT);
		
		tabs.addTab("Search", new ImageIcon(GUI.class.getResource("/Pictures/Search.png")),Search.SearchPane());
		tabs.add("User Log In Required",null);
		tabs.addTab("Borrow", new ImageIcon(GUI.class.getResource("/Pictures/Borrow.png")),Borrow.borrowPane());
		tabs.addTab("Return", new ImageIcon(GUI.class.getResource("/Pictures/Return.png")),Return.returnPane());
		tabs.add("Librarian Log In Required",null);
		tabs.addTab("Add    ", new ImageIcon(GUI.class.getResource("/Pictures/Add.png")),Add.addPane());
		tabs.addTab("Edit     ", new ImageIcon(GUI.class.getResource("/Pictures/Edit.png")),Edit.editPane());
		tabs.addTab("Manage", new ImageIcon(GUI.class.getResource("/Pictures/Manage.png")),Manage.managePane());
		//ACTION TO BE PERFOMED WHEN CLICKING TABS
		tabs.addChangeListener(new ChangeListener() {	
					public void stateChanged(ChangeEvent e) {
						//if the user is not logged in and they select either borrow or return take them to the login window
						if(!(userLoggedIn)){
							if((tabs.getTitleAt(tabs.getSelectedIndex()) == "Borrow") || (tabs.getTitleAt(tabs.getSelectedIndex()) == "Return")){
								//if the login returns true (successful login take them to the page and remember there login status)
								if(SQLCalls.checkUser()){
									userLoggedIn = true;
								}else{
								//else return them back the search tab
									tabs.setSelectedIndex(0);
								}
							}
						}
						//if the admin is not logged in and they select either add edit or delete take them to the login window
						if(!(adminLoggedIn)){
							if((tabs.getTitleAt(tabs.getSelectedIndex()) == "Add    ") || (tabs.getTitleAt(tabs.getSelectedIndex()) == "Edit     ") || (tabs.getTitleAt(tabs.getSelectedIndex()) == "Manage")){
								//if the login returns true (successful login take them to the page and remember there login status)
								if(SQLCalls.checkAdmin()){
									adminLoggedIn = true;
								}else{
									//else return them back the search tab
									tabs.setSelectedIndex(0);
								}
							}
						}
					}
				});
		setContentPane(tabs);
	}
	
	public static void setTab(String a){
		switch(a.toLowerCase()){
		case "search": tabs.setSelectedIndex(0);
					break;
		case "borrow": tabs.setSelectedIndex(2);
					break;
		case "return": tabs.setSelectedIndex(3);
					break;
		case "add": tabs.setSelectedIndex(5);
					break;
		case "edit": tabs.setSelectedIndex(6);
					break;
		case "delete": tabs.setSelectedIndex(7);
					break;
		}
	}
}
