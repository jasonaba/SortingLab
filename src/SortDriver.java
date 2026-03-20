import java.awt.Color;

public class SortDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Piece[] people = new Piece[20];
		
		for(int i = 0; i < people.length; i++) {
			int h = (int)(Math.random()*200)+50;
			Color c = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
			people[i] = new Piece(h, c);
		}
		
	}

}
