package collage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;


public class CollageMaker {
	
	int tileWidth, tileHeight;
	final int tileSize = 15;
	boolean rotation;
	public CollageMaker()
	{
		rotation = true; 
	}
	public static BufferedImage copyImage(BufferedImage source){
	    BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
	    Graphics g = b.getGraphics();
	    g.drawImage(source, 0, 0, null);
	    g.dispose();
	    return b;
	}
	public BufferedImage makeCollage(BufferedImage shape, Vector<BufferedImage> sourceImages) throws IOException {
		//Set scalars
		BufferedImage copy = CollageMaker.copyImage(shape);
		tileWidth = shape.getWidth()/tileSize;
		tileHeight = shape.getHeight()/tileSize;
		
		//Vector of scaled images
		Vector<BufferedImage> destImages = new Vector<BufferedImage>();
		for(BufferedImage bi : sourceImages)
		{
			destImages.add(destImages.size(), scaleImage(bi));
		}
		
		Graphics g = shape.getGraphics();
		Graphics2D g2d = (Graphics2D) g;
		final int xmax = shape.getWidth();
		final int ymax = shape.getHeight();
		int numPlaced = 0;
		for (int y = 0; y < ymax; y++) {
			for (int x = 0; x < xmax; x++) {
				int xPos = x;
				int yPos = y;
				int pixel = shape.getRGB(x, y);
				if (pixel == -16711936) {
					AffineTransform at = new AffineTransform();
					at.translate(xPos - tileWidth/2, yPos);					
					g2d.drawImage(destImages.elementAt(numPlaced), at, null);
					numPlaced++;
					if(numPlaced >=sourceImages.size())
						numPlaced-= sourceImages.size();
					//testing
					//File of = new File("localImages/testCollage"+numPlaced+".png");
					//ImageIO.write(shape, "png", of);
				}
			}
		}
		Color myWhite = new Color(255, 255, 255); // Color white
		int white = myWhite.getRGB();
		File of = new File("localImages/aaa.png");
		ImageIO.write(copy, "png", of);
		
		for (int y = 0; y < copy.getHeight(); y++) {
			for (int x = 0; x < copy.getWidth(); x++) {
				int rgb = copy.getRGB(x, y);
				if(rgb == 0)
					shape.setRGB(x, y, white);
			}
		}
		
		return shape;
	}
	private BufferedImage scaleImage(BufferedImage orig) {
		// Each image scaled to tileWidth x tileHeight
		
		BufferedImage scaledImage = new BufferedImage(tileWidth, tileHeight, orig.getType());
		Graphics g = scaledImage.getGraphics();
		g.drawImage(orig, 0, 0, tileWidth, tileHeight, null);
		g.dispose();
		return scaledImage;
	}
	
}
