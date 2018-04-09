package testingWhiteBox;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import collage.CollageMaker;

public class CollageCompositionTest{
	CollageMaker CMTest;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		
	}
	
	@Before
	public void setUp() throws Exception {
		CMTest = new CollageMaker();
	}
	
	@Test
	//Trivial test of constructor. Real correctness will be tested implicitly by other tests
	public void testCollageShaper() {
		assert(CMTest != null);
	}
	
	@Test
	//Test case of collage creation from collage shape
	public void testMakeCollage(){
		//Not implemented
		//assert(CMTest.makeCollage(new BufferedImage("testShape.jpg") != null);
		
	}
}
