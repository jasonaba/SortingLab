import java.awt.*;

public class Piece {

	private int height, width, x, y;
	private Color color;
	private boolean highlight;
	
	public Piece(int height, int width, int x, int y, Color color) {
		this.height = height;
		this.width = width;
		this.color = color;
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		if(highlight)
			g.setColor(Color.yellow);
		else
			g.setColor(color);
		
		g.fillRect(x, y-height, width, height);
	}

	@Override
	public String toString() {
		return "Height: " + height + "\nWidth: " + width + "\nX: " + x + "\nY: " + y +"\n\n";
	}
	
	
	
}