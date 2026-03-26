import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

import javax.swing.*;

public class SortDriver {

	//I will have a file of random book names in the Bible and sort them based on their names
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub 
				
				//QUESTIOOOOOOOOOOOOON should I sort it based on the name or based on how many chapters there are"
				//I could make it so the text file has the number of chapters as well next tto it but when sorting only print the name (i think), so will that be a good idea?
			//^^^ it might be too late tho
		
		
		Scanner filescanner = new Scanner(new File("SortText.txt"));
		//create ArrayList of all the names
		ArrayList<String> all_names = new ArrayList<String>(66);
		//load all names into ArrayList
		while(filescanner.hasNextLine()) {
			all_names.add(filescanner.nextLine());
			
		}
		//create copy so i can remove from this and continuously repeat this
		ArrayList<String> copy = new ArrayList<>(all_names);
		
		//Create JFrame
		JFrame frame = new JFrame("Visual Sort Lab");
		frame.setSize(400,800);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Number of Titles
		Integer[] options = new Integer[20];
		for(int i = 0; i<20; i++) {
			options[i] = i+1;
		}
		JComboBox<Integer> numberDropdown = new JComboBox<>(options);
		
		//Sort Type
		String[] methods = {"Bubble", "Insertion", "Selection"};
		JComboBox<String> sortBox = new JComboBox<>(methods);
		
		
		//Create Button
		JButton startBut = new JButton("Sort");
		frame.add(new JLabel("Number of Book Titles:"));//amount of titles implemented
		frame.add(numberDropdown);
		frame.add(sortBox);
		frame.add(startBut);
		frame.setVisible(true);
		
		//Button Action
		startBut.addActionListener(e-> {
			String sortMethod = (String)sortBox.getSelectedItem();
			int selected = (int)numberDropdown.getSelectedItem();
			
			//create list of random names
			String[] names = new String[selected];
			for(int i = 0; i<selected; i++) {
				names[i] = all_names.get((int)(Math.random()*all_names.size()));
			}
			Piece[] booknames = new Piece[names.length];
			System.out.println(Arrays.toString(names));
			
			//create list of names in reverse order
			
			//create pieces using these names
			for(int i = 0; i<names.length; i++) {
				booknames[i] = new Piece(names[i]);
			}
			
			Table panel = new Table(booknames);
			System.out.println(Arrays.toString(booknames));
			
			//creates a new panel and deletes the old one (so there isn't any copies)
			frame.getContentPane().removeAll();
			frame.add(panel);
			frame.revalidate();
			frame.repaint();
			
			//sort based on info given
			new Thread(() -> {//necessary for later animation to make ui not freeze
			if(sortMethod.equals("Bubble")) 
				panel.bubbleSort();
			if(sortMethod.equals("Insertion"))
				panel.insertionSort();
			if(sortMethod.equals("Selection"))
				panel.selectionSort();
			}).start();
		});
		
		
		
		//System.out.println(Arrays.asList(all_names).toString());
		
	}

}