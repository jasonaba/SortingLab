import java.awt.Color;
import java.util.Arrays;

public class SortDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Piece[] people = new Piece[20];
		
		for(int i = 0; i < people.length; i++) {
			int h = (int)(Math.random()*200)+50;//random height from 50 - 250
			Color c = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
			people[i] = new Piece(h, 200, 100*i, 400, c);
		}
		System.out.println(Arrays.toString(people));
	}

}