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
	
	private void bubbleSort(Piece[] arr) {
		
	}
	
	private void selectionSort(Piece[] arr) {
		
	}
	
	private void insertionSort(Piece[] arr) {
	
}

}
