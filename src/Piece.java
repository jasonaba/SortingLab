import java.awt.*;

public class Piece implements Comparable{

	private int height, width, x, y, num;//of the rectangle that will be shown for it
	private Color color;
	private String name;
	private boolean highlight;
	
	public Piece(int height, int width, int x, int y, Color color, String name) {
		this.height = height;
		this.width = width;
		this.color = color;
		this.x = x;
		this.y = y;
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
		return "Height: " + height + "\nWidth: " + width + "\nX: " + x + "\nY: " + y +"\n\n";
	}

	/**
	 * 
	 */
	@Override
	public int compareTo(Object other) {
		if(other instanceof Piece) {
			if(this.name.compareTo((Piece) other).getName()){
				
			}
		}
	}
	
	
	
}