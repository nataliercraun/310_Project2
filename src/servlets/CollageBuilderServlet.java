package servlets;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import collage.CollageShaper;

/**
 * Servlet implementation class JDBCServlet
 */
@WebServlet("/CollageBuilderServlet")
public class CollageBuilderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CollageShaper collageShaper;
       
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CollageBuilderServlet() {
    		super();
		// TODO Auto-generated constructor stub
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("image/png");
		
		String topic = request.getParameter("topic");
		String shape = request.getParameter("shape");
		String borders = request.getParameter("borders");
		String rotate = request.getParameter("rotate");
		String width = request.getParameter("width");
		int widthInt;
		if(width == null)
		{
			widthInt = 500;
		}
		else if (width.equals("")) {
			widthInt = 500;
		} else {
			widthInt = Integer.parseInt(width);
		}
		String height = request.getParameter("height");
		int heightInt;
		if( height == null)
			heightInt = 500;
		else if (height.equals("")) {
			heightInt = 500;
		} else {
			heightInt = Integer.parseInt(height);
		}
		
		collageShaper = new CollageShaper(widthInt, heightInt);
		
		BufferedImage collageShape = collageShaper.getShape(shape);
		
		ImageIO.write(collageShape, "png", response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}