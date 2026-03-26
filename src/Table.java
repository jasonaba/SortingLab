import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Table extends JPanel{
	Piece[] pieces;
	
	public Table(Piece[] pieces) {
		this.pieces = pieces;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Piece p : pieces) {
			p.draw(g);
		}
	}
	
	public void bubbleSort() {
		boolean swapped = false;
		int modified = 0;
		int accessed = 0;
		do {
			swapped = false;
			for(int i = 0; i<pieces.length-1; i++) {
				if(pieces[i].compareTo(pieces[i+1])>0) {
					swap(i, i+1);
					swapped = true;
				}
			}
			
		}while(swapped);
		
	}
	
	public void selectionSort() {
		for(int i = 0; i<pieces.length-1; i++) {
			int minIndex = i;
			
			for(int j = i+1; j<pieces.length; j++) {
				if(pieces[j].compareTo(pieces[minIndex])<0) {
					minIndex = j;
				}
			}
			swap(i, minIndex);
		}
		
	}
	
	public void insertionSort() {
		for(int i = 1; i<pieces.length; i++) {
			Piece temp = pieces[i];
			int j = i -1;
			while(j>-1 && pieces[j].compareTo(temp)>0) {
				pieces[j+1] = pieces[j];
				j--;
			}
			pieces[j+1] = temp;
		}
}
	private void swap(int i, int j) {
		Piece temp = pieces[i];
		pieces[i] = pieces[j];
		pieces[j] = temp;
	}

}