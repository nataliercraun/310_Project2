package testingWhiteBox;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import collage.CollageMaker;
import collage.CollageShaper;
public class CollageCompositionTest{
	CollageMaker CMTest;
	CollageShaper CSHelper;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		
	}
	
	@Before
	public void setUp() throws Exception {
		CMTest = new CollageMaker();
		CSHelper = new CollageShaper(1080, 768);
	}
	
	@Test
	//Trivial test of constructor. Real correctness will be tested implicitly by other tests
	public void testCollageShaper() {
		assert(CMTest != null);
	}
	
	@Test
	//Test case of collage creation from collage shape
	public void testMakeCollage(){
		assert (CMTest.makeCollage(CSHelper.getShape("Test string")) != null);
	}
}
