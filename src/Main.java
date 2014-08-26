/* Jorge Martinez
 * Daryani, Kapeel
 * Madeyski, George A.
 * Library Program for cs425
 * 
 * Main
 * -----------
 * This is the Main class for the program.
 * This class is the first called no launch is exports all commands to GUI class
 * 
 */

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;


public class Main {
	public static void main(String[] args) {
		
		
		
		//ensures Text File needed for search history is present if not it creates it
		File f = new File("searchHistory.txt");
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
		
		//launches GUI
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
