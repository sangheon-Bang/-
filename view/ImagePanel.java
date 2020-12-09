package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

class ImagePanel extends JPanel {
	Image image;
	int w,h;
	
	ImagePanel(Image img, int w,int h) {
		this.image = img;
		this.w=w;
		this.h=h;
	}
	
	ImagePanel(Image img) {
		this.image = img;
		this.w=0;
	}
	
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			if (w==0) g.drawImage(image, 0, 0, this);
			else {
				int iw=image.getWidth(null), ih=image.getHeight(null);
				if((float)w/(float)iw>(float)h/(float)ih){
					w=(int)(iw*(float)h/(float)ih);
				}
				else {
					h=(int)(ih*(float)w/(float)iw);
				}
				g.drawImage(image, 0, 0,w,h, this);
			}
		}
	}

	public void update(Graphics g) {
		paintComponent(g);
	}

}
