package collage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;


public class CollageMaker {
	
	public static BufferedImage copyImage(BufferedImage source){
	    BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
	    Graphics g = b.getGraphics();
	    g.drawImage(source, 0, 0, null);
	    g.dispose();
	    return b;
	}
	
	public static BufferedImage toGrayScale(BufferedImage master) {
        BufferedImage gray = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Automatic converstion....
        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        op.filter(master, gray);

        return gray;
    }
	
	public static BufferedImage toBlackAndWhite(BufferedImage master) {
        BufferedImage bw = new BufferedImage(master.getWidth(), master.getHeight(),
                BufferedImage.TYPE_BYTE_BINARY);

        Graphics2D g = bw.createGraphics();
        g.drawImage(master, 0, 0, null);
        return bw;
    }
	
	public static BufferedImage toSepia(BufferedImage master) {
		//level of sepia-ness, 0 is BW
		int sepiaDepth = 20;
		
        BufferedImage sepiaImage = new BufferedImage(master.getWidth(), master.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        for(int x = 0; x < master.getWidth(); x++)
        {
        	for(int y = 0; y < master.getHeight(); y++)
        	{
        		int rgb = master.getRGB(x, y);
        		
        		int R = (rgb >> 16) & 0x000000FF;
        		int G = (rgb >>8 ) & 0x000000FF;
        		int B = (rgb) & 0x000000FF;
        		
        		int tr = (int) (0.393*R + 0.769*G + 0.189*B);
        		int tg = (int) (0.349*R + 0.686*G + 0.168*B);
        		int tb = (int) (0.272*R + 0.534*G + 0.131*B);
        		
        		if(tr > 255)
        			R = 255;
        		else
        			R = tr;
        		
        		if(tg > 255)
        			G = 255;
        		else
        			G = tg;
        		
        		if(tb > 255)
        			B = 255;
    			else
    				B = tb;
        		
        		//adjust and normalize
        		R = R + sepiaDepth*2;
        		G = G + sepiaDepth;
        		B -= 20;
        		
        		if(R > 255)
        			R = 255;
        		if(G > 255)
        			G = 255;
        		if(B < 0 )
        			B = 0;
        		Color sepiaColor = new Color(R, G, B);
        		sepiaImage.setRGB(x, y, sepiaColor.getRGB());
        	}
        }
        //Graphics2D g = bw.createGraphics();
        //g.drawImage(master, 0, 0, null);
        return sepiaImage;
    }
	
	int tileWidth, tileHeight;
	final int tileSize = 15;
	boolean rotation;
	public CollageMaker()
	{
		rotation = true; 
	}
	
	
	
	public BufferedImage makeCollage(BufferedImage shape, Vector<BufferedImage> sourceImages, boolean rotation, boolean borders, int filter) throws IOException {
		/*
		 * filters:
		 * 0: none
		 * 1: grayscale
		 * 2: B&W
		 * 3: Sepia
		*/
		
		//Set scalars
		BufferedImage copy = CollageMaker.copyImage(shape);
		tileWidth = shape.getWidth()/tileSize;
		tileHeight = shape.getHeight()/tileSize;
		
		//Vector of scaled images
		Vector<BufferedImage> destImages = new Vector<BufferedImage>();
		int i = 0;
		for(BufferedImage bi : sourceImages)
		{
			System.out.println("In source Images: " + i++);
			if (bi != null) {
				if(borders)
					bi = borderImage(bi);
				destImages.add(destImages.size(), scaleImage(bi));
			}
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
					at.translate(xPos, yPos);
					if(rotation)
					{
						double rotationValue = Math.random() * Math.PI / 8;
						
						if (rotationValue < 0) {
							xPos -= Math.abs(Math.sin(rotationValue) * tileHeight);
						} else if (rotationValue > 0) {
							yPos -= Math.abs(Math.sin(rotationValue) * tileWidth);
						}
						
						at.rotate(rotationValue);

					}
					g2d.drawImage(destImages.elementAt(numPlaced), at, null);
					numPlaced++;
					numPlaced = numPlaced % destImages.size();
					
					//testing
					//File of = new File("localImages/testCollage"+numPlaced+".png");
					//ImageIO.write(shape, "png", of);
				}
			}
		}
		Color myWhite = new Color(255, 255, 255); // Color white
		int white = myWhite.getRGB();
//		File of = new File("localImages/aaa.png");
//		ImageIO.write(copy, "png", of);
		
		if(filter == 3)
			shape = toSepia(shape);
		
		for (int y = 0; y < copy.getHeight(); y++) {
			for (int x = 0; x < copy.getWidth(); x++) {
				int rgb = copy.getRGB(x, y);
				if(rgb == 0)
					shape.setRGB(x, y, white);
			}
		}
		
		if(filter == 1)
			return toGrayScale(shape);
		else if(filter == 2)
			return toBlackAndWhite(shape);
		else if(filter == 3)
			return shape;
		else
			return shape;
	}
	private BufferedImage scaleImage(BufferedImage orig) {
		// Each image scaled to tileWidth x tileHeight
		BufferedImage scaledImage;
		if (orig.getType() == 0) 
			scaledImage = new BufferedImage(tileWidth, tileHeight, 5);
		else
			scaledImage = new BufferedImage(tileWidth, tileHeight, orig.getType());
		Graphics g = scaledImage.getGraphics();
		g.drawImage(orig, 0, 0, tileWidth, tileHeight, null);
		g.dispose();
		return scaledImage;
	}
	
	private BufferedImage borderImage(BufferedImage orig)
	{
		Graphics g = orig.getGraphics();
		Graphics2D g2d = (Graphics2D)g;
		float thickness = 24.0f;
		g2d.setColor(Color.white);
		g2d.setStroke(new BasicStroke(thickness));
		g2d.drawRect(0, 0, orig.getWidth(), orig.getHeight());
		return orig;
	}
	
}
