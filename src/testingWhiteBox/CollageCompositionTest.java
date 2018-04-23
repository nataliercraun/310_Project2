package testingWhiteBox;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import collage.CollageMaker;
import collage.CollageShaper;
import collage.ImageSourcer;

public class CollageCompositionTest{
	CollageMaker CMTest;
	CollageShaper CSHelper;
	ImageSourcer ISHelper;
	Vector<BufferedImage> images;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		
	}
	
	@Before
	public void setUp() throws Exception {
		CMTest = new CollageMaker();
		CSHelper = new CollageShaper(1080, 768);
		ISHelper = new ImageSourcer();
		images = ISHelper.getImages("Dog");
	}
	
	@Test
	//Trivial test of constructor. Real correctness will be tested implicitly by other tests
	public void testCollageShaper() {
		assert(CMTest != null);
	}
	
	@Test
	//Test case of collage creation from collage shape
	public void testMakeCollage() throws IOException{
		BufferedImage testCollage = CMTest.makeCollage(CSHelper.getShape("test alpha"), images, false, false, 0);
		File of = new File("localImages/testCollage.png");
		ImageIO.write(testCollage, "png", of);
		assert(testCollage != null);
	}
}
