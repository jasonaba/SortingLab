import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

import javax.swing.*;

public class SortDriver {

	//I will have a file of random book names in the Bible and sort them based on their names
	public static void main(String[] args) throws FileNotFoundException{
		// TODO Auto-generated method stub 
		boolean run;
		do {
			run = false;
			begin();
		}while(run);
		}
	public static void begin() throws FileNotFoundException {
		Scanner filescanner = new Scanner(new File("SortText.txt"));
		//create ArrayList of all the names
		ArrayList<String> all_names = new ArrayList<String>(66);
		//load all names into ArrayList
		while(filescanner.hasNextLine()) {
			all_names.add(filescanner.nextLine());
			
		}
		//create copy of list so i can remove from this and continuously repeat this
		ArrayList<String> copy = new ArrayList<>(all_names);
		
		//Create JFrame
		JFrame frame = new JFrame("Visual Sort Lab");
		frame.setSize(1600,620);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Control Panel
		JPanel controlPanel = new JPanel();

		
		//Number of Titles
		Integer[] options = new Integer[20];
		for(int i = 0; i<20; i++) {
			options[i] = i+1;
		}
		JComboBox<Integer> numberDropdown = new JComboBox<>(options);
		//Reverse/Not Reversed
		String[] y_n = {"Yes", "No"};
		JComboBox<String> reverse = new JComboBox<>(y_n);
		//Sort Type
		String[] methods = {"Bubble", "Insertion", "Selection"};
		JComboBox<String> sortBox = new JComboBox<>(methods);
		
		
		//Create Button
		JButton startBut = new JButton("Sort");
		
		controlPanel.add(new JLabel("Number of Book Titles:"));//amount of titles implemented
		controlPanel.add(numberDropdown);
		controlPanel.add(new JLabel("Sorting Method:"));
		controlPanel.add(sortBox);
		controlPanel.add(new JLabel("Reverse?"));
		controlPanel.add(reverse);
		controlPanel.add(startBut);
		
		frame.add(controlPanel, BorderLayout.CENTER);
		frame.setVisible(true);
		JPanel displayPanel = new JPanel(new BorderLayout());
		frame.add(displayPanel, BorderLayout.CENTER);
		
		//Button Action
		startBut.addActionListener(e-> {
			JButton yesButton = new JButton("Restart");//for later
			
			displayPanel.add(yesButton, BorderLayout.SOUTH);
			displayPanel.revalidate();
			displayPanel.repaint();
			// copy-pasted from chatgpt
			
			
			String sortMethod = (String)sortBox.getSelectedItem();
			int selected = (int)numberDropdown.getSelectedItem();
			String reverseMethod = (String)reverse.getSelectedItem();
			
			//create list of random names
			String[] names = new String[selected];
			for(int i = 0; i<selected; i++) {
				names[i] = copy.remove((int)(Math.random()*copy.size()));
			}
			Piece[] booknames = new Piece[names.length];
			
			//if reeverse order
			if(reverseMethod.equals("Yes")) {
				Collections.sort(Arrays.asList(names));
				Collections.reverse(Arrays.asList(names));
			}
			
			//create pieces using these names
			for(int i = 0; i<names.length; i++) {
				booknames[i] = new Piece(names[i]);
				booknames[i].setX(50 + i*100);
			}
			
			Table panel = new Table(booknames);
			
			
			
			//replaces the center display content
			displayPanel.removeAll();
			displayPanel.add(panel,BorderLayout.CENTER);
			displayPanel.revalidate();
			displayPanel.repaint();
			
			SwingUtilities.invokeLater(()->panel.layoutPieces());
			
			//sort based on info given 
			
			new Thread(() -> {//necessary to make ui not freeze
			if(sortMethod.equals("Bubble")) {
				panel.bubbleSort();
				System.out.println(Arrays.toString(booknames));
			}
			if(sortMethod.equals("Insertion")) {
				panel.insertionSort();
				System.out.println(Arrays.toString(booknames));
				
			}
			if(sortMethod.equals("Selection")) {
				panel.selectionSort();
				System.out.println(Arrays.toString(booknames));
			}
			SwingUtilities.invokeLater(() -> {
				try {
					restart(frame);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}); 
			}).start();
		});
	}
		//if user wants to restart
	public static void restart(JFrame frame) throws FileNotFoundException{
		int result = JOptionPane.showConfirmDialog(
				frame,
				"Do you want to restart?",
				"Restart",
				JOptionPane.YES_NO_OPTION
				);
		if(result == JOptionPane.YES_OPTION) {
			begin();
		}
	}
	
}