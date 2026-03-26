import java.awt.*;

public class Piece implements Comparable{

	private int height, width, x, y, num;//of the rectangle that will be shown for it
	private Color color;
	private String name;
	private boolean highlight;
	
	public Piece(String name) {
		this.name = name;
	}
	
	public void draw(Graphics g) {
		if(highlight)
			g.setColor(Color.yellow);
		else
			g.setColor(color);
		
		g.fillRect(x, y-height, width, height);
	}

	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * 
	 */
	@Override
	public int compareTo(Object other) {
		if(other instanceof Piece) {
			String name = ((Piece)other).getName();
			return this.name.compareTo(name);
		}
		return -999999;//can i make this my sign of an error
		
	}
	
	
	
}