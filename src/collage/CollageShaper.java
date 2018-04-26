package collage;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;

public class CollageShaper {
	public int width;
	public int height;
	Shape ol;
	public CollageShaper(int w, int h) throws IOException
	{
		width = w; height = h;
	}
	
	public BufferedImage getShape (String userInput) throws IOException
	{
		// create BufferedImage for canvas, get g2 from this canvas 
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		
		//create font and transform
		FontRenderContext frc = g2.getFontRenderContext();
		AffineTransform atf = new AffineTransform();
		Font f = new Font("Helvetica", 1, 210 );
		FontMetrics fm = g2.getFontMetrics(f);
		int stringWidth = fm.stringWidth(userInput);
		
		while(stringWidth < width )
		{
			f = f.deriveFont(f.getSize() + 1);
			fm = g2.getFontMetrics(f);
			stringWidth = fm.stringWidth(userInput);
		}
		
		//dynamic sizing of font based on string length and window size
		while(stringWidth > width)
		{
			f = f.deriveFont((float) (f.getSize() * 0.8));
			fm = g2.getFontMetrics(f);
			stringWidth = fm.stringWidth(userInput);
		}
		
		
		
		//retrieve user's string and create textlayout
		String s = new String(userInput);
		TextLayout tl = new TextLayout(s, f, frc);
		
		//create outline of letters and a bounding rectangle
		Shape outline = tl.getOutline(null);
		Rectangle r = outline.getBounds();
		ol = outline;

		//center it
		atf = g2.getTransform();
		atf.translate(width/2-(r.width/2), height/2+(r.height/2));
		g2.transform(atf);
		g2.setColor(Color.GREEN);
		tl.draw(g2, 0, 0);
		
		//testing
		//File of = new File("localImages/testShape.png");
		//ImageIO.write(image, "png", of);
		return image;
	}
	
	public Shape getOutline()
	{
		return ol;
	}
	
//test main
//	public static void main(String args[]) throws IOException
//	{
//		CollageShaper cs = new CollageShaper(1028, 768);
//		BufferedImage testGreen = cs.getShape("short");
//		File of = new File("localImages/testGreen.png");
//		ImageIO.write(testGreen, "png", of);
//	}
}

