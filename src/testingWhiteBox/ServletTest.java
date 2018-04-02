package testingWhiteBox;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import servlets.CollageBuilderServlet;

public class ServletTest {
	private CollageBuilderServlet myServlet;
	private HttpServletRequest request;
	private HttpServletResponse response;

	@Before
	// mock request and response
	public void setUp() {
		myServlet = new CollageBuilderServlet();
		request = Mockito.mock(HttpServletRequest.class);
		response = Mockito.mock(HttpServletResponse.class);
	}

	@Test
	// Mock servlet parameter input
	public void testDoGet() throws Exception {

		Mockito.when(request.getParameter("topicString")).thenReturn("expectedTopic");
		Mockito.when(request.getParameter("shapeString")).thenReturn("expectedShape");
		Mockito.when(request.getParameter("bordersOption")).thenReturn("expectedBorders");
		Mockito.when(request.getParameter("rotateOption")).thenReturn("expectedRotate");
		// Mockito.when(request.getParameter("password")).thenReturn("secret");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		Mockito.when(response.getWriter()).thenReturn(writer);

		myServlet.doGet(request, response);

		writer.flush();

		assert (stringWriter.toString() != null);
	}

}
