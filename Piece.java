import java.awt.*;

public class Piece implements Comparable<Piece>{

	private int x, y, targetY;//for animation
	private String name;
	private boolean highlight;
	
	//Constructor
	
	public Piece(String name) {
		this.name = name;
		this.x = 0;
		this.y = 0;
		this.targetY = 0;
		this.highlight = false;
	}
	
	/**
	 * draws the piece
	 * @param g
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		if(highlight) {
			g.setColor(new Color(255, 255, 150));
			g2.fillRoundRect(x-5, y-18, 150, 22, 10, 10);
		}
		g2.setColor(new Color(40, 40, 40));
		g2.setFont(new Font("Serif", Font.PLAIN, 30));
		g.drawString(name, x, y);
	}

	//Getters & Setters
	
	public String getName() {
		return this.name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isHighlight() {
		return highlight;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getTargetY() { 
		return targetY; 
	}
    public void setTargetY(int targetY) { 
    	this.targetY = targetY; 
    }

	@Override
	public String toString() {
		return this.name;
	}
	public void setHighlight(boolean b) {
		this.highlight = b;
	}

	/**
	 * Compares the names of the two Pieces
	 */
	@Override
	public int compareTo(Piece other) {
		return this.name.compareTo(other.name);
	}
	
	
	
}