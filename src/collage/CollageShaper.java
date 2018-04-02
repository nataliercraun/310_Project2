package collage;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
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
	
	public CollageShaper(int w, int h) throws IOException
	{
		width = w; height = h;
		
	}
	
	public BufferedImage getShape (String userInput)
	{
		// create BufferedImage for canvas, get g2 from this canvas 
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		
		//create font and transform
		FontRenderContext frc = g2.getFontRenderContext();
		AffineTransform atf = new AffineTransform();
		Font f = new Font("Helvetica", 1, width/10);
		
		//retrieve user's string and create textlayout
		String s = new String(userInput);
		TextLayout tl = new TextLayout(s, f, frc);
		
		//create outline of letters and a bounding rectangle
		Shape outline = tl.getOutline(null);
		Rectangle r = outline.getBounds();
		
		//center it
		atf = g2.getTransform();
		atf.translate(width/2-(r.width/2), height/2+(r.height/2));
		g2.transform(atf);
		g2.setColor(Color.GREEN);
		
		//green outline
		g2.draw(outline);
		g2.setClip(outline);
		
		//green fill
		g2.setColor(Color.GREEN);

		g2.draw(new Rectangle(0, 0, width, height));
		//return image of user's string in green letters
		return image;
	}
	
//test main
//	public static void main(String args[]) throws IOException
//	{
//		CollageShaper cs = new CollageShaper(1028, 768);
//		BufferedImage testGreen = cs.getShape("testing shape");
//		File of = new File("localImages/testGreen.png");
//		ImageIO.write(testGreen, "png", of);
//	}
}

