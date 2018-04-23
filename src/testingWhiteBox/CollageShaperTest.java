package testingWhiteBox;

import junit.framework.TestCase;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import collage.CollageShaper;

public class CollageShaperTest{
	CollageShaper CSTest;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		
	}
	
	@Before
	public void setUp() throws Exception {
		CSTest = new CollageShaper(1024, 768);
	}
	
	@Test
	//Trivial test of constructor. Real correctness will be tested implicitly by other classes
	public void testCollageShaper() {
		assert(CSTest != null);
	}
	
	@Test
	//Test case of image creation from text shape
	public void testGetImage(){
		try {
			assert(CSTest.getShape("shor") != null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
