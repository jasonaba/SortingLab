import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.*;

public class SortDriver {

	//I will have a file of random book names in the Bible and sort them based on their names
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub 
				
				//QUESTIOOOOOOOOOOOOON should I sort it based on the name or based on how many chapters there are"
				//I could make it so the text file has the number of chapters as well next tto it but when sorting only print the name (i think), so will that be a good idea?
			//^^^ it might be too late tho
		
		//create graphics stuff
				JFrame frame = new JFrame("Visual Sort Lab");
				frame.setSize(400,800);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				
				//prompt user to give me amount of titles being sorted
				Integer[] options = new Integer[20];
				for(int i = 0; i<20; i++) {
					options[i] = i+1;
				}
				JComboBox<Integer> numberDropdown = new JComboBox<>(options);
				
				frame.add(new JLabel("Number of Book Titles:"));
				frame.add(numberDropdown);	
				
				int selected = (int)numberDropdown.getSelectedItem();
				
				frame.setVisible(true);
				
		
		//I don't want the user to have access to the scanner--put this in Table (is that fine to do?)
		Scanner filescanner = new Scanner(new File("SortText.txt"));
		filescanner.useDelimiter("[,\\r\\n]+");//to check after every comma and new line
		//create ArrayList of all the names
		ArrayList<String> all_names = new ArrayList<String>(66);
		//load all names into ArrayList
		while(filescanner.hasNext()) {
			all_names.add(filescanner.next());
			
		}
		//System.out.println(Arrays.asList(all_names).toString());
		//create list of random names
		String[] names = new String[selected];
		for(int i = 0; i<selected; i++) {
			names[i] = all_names.remove((int)(Math.random()*all_names.size()));
		}
		Piece[] booknames = new Piece[names.length];
		System.out.println(Arrays.toString(names));
		
		//create list of names in reverse order
		
		//create pieces using these names
		for(int i = 0; i<names.length; i++) {
			booknames[i] = new Piece(names[i]);
		}
		
		Table panel = new Table(booknames);
		panel.selectionSort();
		System.out.println(Arrays.toString(booknames));
		
		frame.add(panel);
		
		//prompt the user how they want to sort this
		String[] methods = {"Bubble", "Insertion", "Selection"};
		JComboBox<String> sortBox = new JComboBox<>(methods);
		String sortMethod = (String)sortBox.getSelectedItem();
		
		//sort based on info given
		if(sortMethod.equals("Bubble")) 
			panel.bubbleSort();
		if(sortMethod.equals("Insertion"))
			panel.insertionSort();
		if(sortMethod.equals("Selection"))
			panel.selectionSort();
	}

}