import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Table extends JPanel{
	Piece[] pieces;
	boolean done;
	int accessCount, mutateCount;
	private final int pageTopY = 40;
	private final int leftPageX = 20;
	private final int rightPageX = 520;
	private final int lineHeight = 25;
	private final int textMarginX = 15;
	
	public Table(Piece[] pieces) {
		this.pieces = pieces;
		done = false;
		setInitialPositions();
	}
	
	public void paintComponent(Graphics g) {//I had trouble finishing this all in time, so I had ChatGPT help me out heavily for this part
		super.paintComponent(g);
		//set up background (the open book)
		int panelWidth = getWidth();
		int panelHeight = getHeight();
		int pageWidth = (panelWidth-60)/2;
		int pageHeight = panelHeight - 70;
		int rightPageX = leftPageX + pageWidth + 20;
		
		//Draw Table
		Graphics2D g2 = (Graphics2D) g;

		// Wood table gradient
		GradientPaint tableGrad = new GradientPaint(
		    0, 0, new Color(120, 70, 40),
		    0, getHeight(), new Color(80, 50, 30)
		);
		g2.setPaint(tableGrad);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		//Draw the Book & Pages
		g2.setColor(new Color(90, 0, 0));
		g2.fillRoundRect(leftPageX - 35, pageTopY - 10, pageWidth * 2 + 90, pageHeight + 50, 40, 40);
			//page gradient
		GradientPaint pageGrad = new GradientPaint(
			    leftPageX, pageTopY, new Color(235, 220, 190),
			    leftPageX, pageTopY + pageHeight, new Color(210, 180, 140)
			);
			g2.setPaint(pageGrad);
			g2.fillRoundRect(leftPageX, pageTopY, pageWidth, pageHeight, 30, 30);
			g2.fillRoundRect(rightPageX, pageTopY, pageWidth, pageHeight, 30, 30);		
			//borders
			g2.setColor(new Color(160, 130, 90));
			g2.drawRoundRect(leftPageX, pageTopY, pageWidth, pageHeight, 30, 30);
			g2.drawRoundRect(rightPageX, pageTopY, pageWidth, pageHeight, 30, 30);
			
		int gutterX = leftPageX + pageWidth;  // start of gutter
		int gutterWidth = 20;

		// Gradient for subtle shadow
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint spine = new GradientPaint(
		    gutterX, 0, new Color(200, 200, 200, 100),   // light gray semi-transparent
		    gutterX + gutterWidth, 0, new Color(245, 222, 179, 0) // fades to page color
		);
		g2d.setPaint(spine);
		g2d.fillRect(gutterX, pageTopY, gutterWidth, pageHeight);
		
		//Actual words
		int entriesPerPage = pieces.length /2;
		g2.setColor(new Color(180, 160, 120));
		//Guide Lines
		for(int i = 0; i < entriesPerPage; i++) {
		    int yLine = pageTopY + 70 + i * lineHeight;
		    g2.drawLine(leftPageX + 10, yLine, leftPageX + pageWidth - 10, yLine);
		    g2.drawLine(rightPageX + 10, yLine, rightPageX + pageWidth - 10, yLine);
		}
		
		for(Piece p : pieces) {
			p.draw(g2);
		}
		//Table of Contents words
		g2.setFont(new Font("Serif", Font.BOLD, 36));
		g2.setColor(new Color(60, 30, 10));
		String title = "Table of    Contents";
		int titleWidth = g2.getFontMetrics().stringWidth(title);
		g2.drawString(title, getWidth()/2 - titleWidth/2, pageTopY + 40);
		
		//Accessors and Modifiers stats
		g2.setFont(new Font("Monospaced", Font.PLAIN, 16));
		g2.setColor(new Color(30, 30, 30));
		g2.drawString("Accesses: " + accessCount, 50, getHeight() - 55);
		g2.drawString("Mutations: " + mutateCount, 50, getHeight() - 35);
		
		//All Sorted! Ending
		if(done) {
		    g2.setFont(new Font("Serif", Font.BOLD, 28));
		    g2.setColor(new Color(0, 120, 0));

		    String msg = "All Sorted!";
		    int w = g2.getFontMetrics().stringWidth(msg);
		    g2.drawString(msg, getWidth() - w - 40, getHeight() - 40);
		}
	}
	
	public void pause(int ms) {
		try
		{
			Thread.sleep(ms);
		} catch(Exception e) {}
	}
	
	
	//Copied straight from ChatGPT
	private void setInitialPositions() {
        int entriesPerPage = pieces.length / 2;
        int lineIndex =0;
        for (int i = 0; i < pieces.length; i++) {
            int pageX = (i < entriesPerPage) ? leftPageX + textMarginX : rightPageX + textMarginX;
            if(i < entriesPerPage) {
            	lineIndex = i;
            }
            else {
            	lineIndex = i-entriesPerPage;
            }
            
            int pageY = pageTopY + lineHeight * lineIndex;
            pieces[i].setX(pageX);
            pieces[i].setY(pageY);
            pieces[i].setTargetY(pageY);
        }
    }
	
	public void bubbleSort() {
		boolean swapped = false;
		int index = 0;
		do {
			swapped = false;
			for(int i = 0; i<pieces.length-index - 1; i++) {
				//highlight the pieces
				pieces[i].setHighlight(true); pieces[i+1].setHighlight(true);
				repaint(); pause(500);
				//swapping method
				if(pieces[i].compareTo(pieces[i+1])>0) {
					accessCount+=2;
					swap(i, i+1);
					swapped = true;
				}
				repaint(); pause(500);
				pieces[i].setHighlight(false); pieces[i+1].setHighlight(false);
				repaint(); pause(500);
				
			} index++;
			
		}while(swapped);
		done = true;
	}
	
	public void selectionSort() {
		for(int i = 0; i<pieces.length-1; i++) {
			int minIndex = i;
			
			for(int j = i+1; j<pieces.length; j++) {
				
				pieces[i].setHighlight(true);pieces[j].setHighlight(true);
				repaint(); pause(500);
				if(pieces[j].compareTo(pieces[minIndex])<0) {
					if(j == minIndex)
						accessCount++;
					else
						accessCount+=2;
					minIndex = j;
				}
				repaint(); pause(500);
				pieces[i].setHighlight(false);pieces[j].setHighlight(false);
				repaint(); pause(500);
			}
			swap(i, minIndex);
		}
		
	}
	
	public void insertionSort() {
		int entriesPerPage = pieces.length/2;
		for(int i = 1; i<pieces.length; i++) {
			Piece temp = pieces[i]; accessCount++;
			temp.setHighlight(true);
			int startX = temp.getX();
	        int startY = temp.getY();
			animateLift(temp);
			
			int j = i -1;
			while(j>-1 && pieces[j].compareTo(temp)>0) {accessCount++;
				pieces[j].setHighlight(true);
				int lineShift = lineHeight;
				animateShiftDown(pieces[j],lineShift);
				pieces[j+1] = pieces[j]; mutateCount++;
				pieces[j].setHighlight(false);
				j--;
			}
			int lineIndex = j + 1;
			int targetX = 0;
			
			if (lineIndex<entriesPerPage) {
				 targetX = leftPageX + textMarginX;
			}
			else {
				targetX = rightPageX + textMarginX;
			}
			int targetY = pageTopY + lineHeight * (lineIndex % entriesPerPage);
	        animateDrop(temp, targetY, targetX);
			pieces[lineIndex] = temp; mutateCount++;
			temp.setHighlight(false);
			repaint();
			pause(300);
		}
		done = true;
	}
	
	private void swap(int i, int j) {
		animateLift(pieces[i]);
		animateLift(pieces[j]);
		Piece temp = pieces[i];accessCount++;
		pieces[i] = pieces[j]; mutateCount++;accessCount++;
		pieces[j] = temp; mutateCount++;
		animateShiftDown(pieces[i], lineHeight);
		animateShiftDown(pieces[j], lineHeight);
		layoutPieces();
	}
	
	// All three methods ChatGPT'd
	private void animateShiftDown(Piece p, int lineHeight) {
	    int frames = 10;
	    int startY = p.getY();
	    int targetY = startY + lineHeight;

	    for(int i = 0; i < frames; i++) {
	        double t = (double)i / frames;
	        double ease = t * t;

	        int newY = (int)(startY + (targetY - startY) * ease);
	        p.setY(newY);

	        repaint();
	        pause(15);
	    }

	    p.setY(targetY);
	}

	private void animateLift(Piece p) {
	    int frames = 12;
	    int startY = p.getY();
	    int targetY = startY - 40; // lift up
	    int startX = p.getX();
	    int targetX = startX + 25; // slight right shift

	    for(int i = 0; i < frames; i++) {
	        double t = (double)i / frames;
	        double ease = t * t; // ease-in

	        int newY = (int)(startY + (targetY - startY) * ease);
	        int newX = (int)(startX + (targetX - startX) * ease);

	        p.setY(newY);
	        p.setX(newX);

	        repaint();
	        pause(20);
	    }
	}
	
	private void animateDrop(Piece p, int targetY, int targetX) {
	    int frames = 12;
	    int startY = p.getY();
	    int startX = p.getX();

	    for(int i = 0; i < frames; i++) {
	        double t = (double)i / frames;
	        double ease = 1 - (1 - t) * (1 - t); // ease-out

	        int newY = (int)(startY + (targetY - startY) * ease);
	        int newX = (int)(startX + (targetX - startX) * ease);

	        p.setY(newY);
	        p.setX(newX);

	        repaint();
	        pause(20);
	    }

	    // snap exactly
	    p.setY(targetY);
	    p.setX(targetX);
	}
	
	public void layoutPieces() {
	    int panelWidth = getWidth();
	    int panelHeight = getHeight();
	    int pageWidth = (panelWidth - 60) / 2;
	    int leftPageX = 20;
	    int rightPageX = leftPageX + pageWidth + 20;
	    int topY = 40;

	    int textMarginX = 15;
	    int textMarginY = 40;
	    int lineHeight = 25;

	    int entriesPerPage = pieces.length / 2;

	    for(int i = 0; i < pieces.length; i++) {
	        Piece p = pieces[i];

	        int pageX;
	        int lineIndex;

	        if(i < entriesPerPage) {
	            pageX = leftPageX + textMarginX;
	            lineIndex = i;
	        } else {
	            pageX = rightPageX + textMarginX;
	            lineIndex = i - entriesPerPage;
	        }

	        int pageY = topY + 50 + textMarginY + lineIndex * lineHeight;

	        p.setX(pageX);
	        p.setY(pageY);
	    }
	}
	
}
