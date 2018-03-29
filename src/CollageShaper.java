import java.awt.image.*;
import java.io.File;
import java.io.IOException;

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
	int width;
	int height;
	Image img;
	public CollageShaper(int w, int h) throws IOException
	{
		width = w; height = h;
		img = ImageIO.read(new File("localImages/greenKey.jpg"));
	}
	
	public BufferedImage getShape (String userInput)
	{
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		FontRenderContext frc = g2.getFontRenderContext();
		AffineTransform atf = new AffineTransform();
		Font f = new Font("Helvetica", 1, width/10);
		
		String s = new String(userInput);
		TextLayout tl = new TextLayout(s, f, frc);
		
		Shape outline = tl.getOutline(null);
		Rectangle r = outline.getBounds();
		
		atf = g2.getTransform();
		atf.translate(width/2-(r.width/2), height/2+(r.height/2));
		g2.transform(atf);
		g2.setColor(Color.GREEN);
		g2.draw(outline);
		g2.setClip(outline);
		g2.drawImage(img, r.x, r.y, r.width, r.height, null);
		return image;
	}
	
	public static void main(String args[]) throws IOException
	{
		CollageShaper cs = new CollageShaper(1028, 768);
		BufferedImage testGreen = cs.getShape("testing shape");
		File of = new File("localImages/testGreen.png");
		ImageIO.write(testGreen, "png", of);
	}
}


