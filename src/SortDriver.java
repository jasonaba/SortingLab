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
		Scanner filescanner = new Scanner(new File("SortText.txt"));
		//create ArrayList of all the names
		ArrayList<String> all_names = new ArrayList<String>(66);
		//load all names into ArrayList
		while(filescanner.hasNext()) {
			all_names.add(filescanner.next());
			
		}
		//System.out.println(Arrays.asList(all_names).toString());
		//create list of random names
		int amount = 10;
		String[] names = new String[amount];
		for(int i = 0; i<amount; i++) {
			names[i] = all_names.remove((int)Math.random()*66);
		}
		
		System.out.println(Arrays.toString(names));
		//create list of names in reverse order
		
		/*
		//create graphics stuff
		JFrame frame = new JFrame("Visual Sort Lab");
		
		Piece[] pieces = new Piece[20];
		
		for(int i = 0; i < pieces.length; i++) {
			int h = (int)(Math.random()*200)+50;//random height from 50 - 250
			Color c = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
			pieces[i] = new Piece(h, 200, 100*i, 400, c);
		}
		System.out.println(Arrays.toString(pieces));
		Table panel = new Table(pieces);
		frame.add(panel);
		frame.setSize(400,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		*/
		
		
	}

}