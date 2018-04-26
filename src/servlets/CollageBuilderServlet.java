package servlets;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import collage.CollageMaker;
import collage.CollageShaper;
import collage.ImageSourcer;
import collage.CollageMaker;


/**
 * Servlet implementation class JDBCServlet
 */
@WebServlet("/CollageBuilderServlet")
public class CollageBuilderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CollageShaper collageShaper;
	ImageSourcer imageSourcer;
	CollageMaker collageMaker;
       

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CollageBuilderServlet() {
    		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String topic = request.getParameter("topic");
		System.out.println(topic);
		String shape = request.getParameter("shape");
		System.out.println(shape);
		String borders = request.getParameter("borders");
		boolean bordersBoolean = borders.equals("on");
		String rotate = request.getParameter("rotate");
		boolean rotationBoolean = rotate.equals("on");
		String width = request.getParameter("width");
		String filter = request.getParameter("filter");
		int filterNum;
		if (filter.equals("grayscale50")) {
			filterNum = 1;
		} else if (filter.equals("grayscale100")) {
			filterNum = 2;
		} else if (filter.equals("sepia")) {
			filterNum = 3;
		} else {
			filterNum = 0;
		}
		
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
		//Init pieces of collage process
		imageSourcer = new ImageSourcer();
		collageShaper = new CollageShaper(widthInt, heightInt);
		imageSourcer = new ImageSourcer();
		collageMaker = new CollageMaker();

		
		//get shape
		BufferedImage collageShape = collageShaper.getShape(shape);
		Vector<BufferedImage> images = imageSourcer.getImages(topic);
		BufferedImage collage = collageMaker.makeCollage(collageShape, images, rotationBoolean, bordersBoolean, filterNum);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		ImageIO.write(collage, "png", baos);
		baos.flush();
		byte[] imageInByteArray = baos.toByteArray();
		baos.close();
		String b64String = "data:image/png;base64, " + javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
		
		try {
			PrintWriter pw = response.getWriter();
		    pw.print(b64String);
		}
		catch (FileNotFoundException e) {
		    e.getMessage();
		}
		
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}