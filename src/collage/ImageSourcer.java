package collage;


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageSourcer {

	// Configuration Variables
	private final int REQUIRED_IMAGES = 80; // Number of images needed to build collage
	private final String API_KEY = "AIzaSyDAIJUGXTW2xycBMmHd1rSTMl8T0p2jUjc"; // Google API key
	private final String SEARCH_ENGINE_KEY = "004843956391315063069:wnj8zpugysm"; // Custom Search Engine key provided by Google
	private final String SEARCH_FILETYPES = "png,jpg"; // Desired file types for search
	private final int GOOGLE_SEARCH_LIMIT = 10; // Google only allows 10 images returned per query
			
	// Passed in: String searchText: the search term that we want to search Google Images for - provided by user in client
	// Returns: Vector<BufferedImage> images of image results from Google. Will be null if < REQUIRED_IMAGES images is found
	public Vector<BufferedImage> getImages(String searchText) {
		// We run the code to fetch GOOGLE_SEARCH_LIMIT items several times, 
		// offsetting the start index by GOOGLE_SEARCH_LIMIT each time. 
		
		// Setting up data structures
		BufferedReader bufferedReader; // BufferedReader to read from Google API's input stream
		StringBuilder stringBuilder = null; // StringBuilder to build a string from the input stream
		JSONObject json = null; // JSON object holding form of the data
		Vector<String> imageURLs = new Vector<String>(); // Vector to hold all 30 image urls
		Vector<BufferedImage> images = new Vector<BufferedImage>(); // Vector to hold all 30 images (to be returned later)
		
		try {
			// Loop to grab GOOGLE_SEARCH_LIMIT images and append URLs to vector
			for (
				int offset = 0;
				offset <= (REQUIRED_IMAGES-1) / GOOGLE_SEARCH_LIMIT * GOOGLE_SEARCH_LIMIT; 
				// REQUIRED_IMAGES rounded down to multiple of GOOGLE_SEARCH_LIMIT
				offset += GOOGLE_SEARCH_LIMIT
			) {
				// Setting up search parameters
				String query = "";
				try {
					query = URLEncoder.encode(searchText, "UTF-8");  // url encoding the raw search string
				} catch (UnsupportedEncodingException e) {

				}
				
				String key = API_KEY; 
				String searchEngineKey  = SEARCH_ENGINE_KEY;
				String fileType = SEARCH_FILETYPES;
				String searchType = "image";
				String startIndex = String.valueOf(offset + 1);
				// The offset + 1 is the index of the Google results that we want to start returning our 
				// GOOGLE_SEARCH_LIMIT results from. Results are 1-indexed.
				// e.g. when offset = 0, start = 1, which means we are returning results 1-GOOGLE_SEARCH_LIMIT.
				
				// Creating connection with Google Custom Search API - see API documentation for parameter details
				HttpURLConnection conn = null;
				try {
					URL url = new URL ("https://www.googleapis.com/customsearch/v1?key=" + key + "&cx=" + searchEngineKey + "&q=" + query + "&fileType=" + fileType + "&searchType=" + searchType + "&alt=json&start=" + startIndex);
					conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Accept", "application/json");
				} catch (IOException e1) {
					
				}
				
				// String builder to read data from Google's input stream and convert into a JSON object for parsing
				stringBuilder = new StringBuilder();
				
				// Retrieve result from Google connection's input stream
				try {
					bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())));		
					String line; // Variable to store line data from BufferedReader reading from input stream
					
					// Read each line and append to our string builder
					while((line = bufferedReader.readLine()) != null) {
						stringBuilder.append(line);
					}
					// Terminate connection with Google
					conn.disconnect();
				} catch (IOException e1) {
					
				}
				// Convert result to JSON object and then parse that JSON object to extract data
				String imageUrl = null; // Variable to hold current image URL as we iterate through data
				try {
					json = new JSONObject(stringBuilder.toString());
					for (int i = 0; i < GOOGLE_SEARCH_LIMIT; i++) {	
						imageUrl = json.getJSONArray("items").getJSONObject(i).getString("link");
						imageURLs.addElement(imageUrl);
					}
				} catch (JSONException e) {
					
				}
			}
		} catch (Exception e)
		{
			// If any exception occurs in the image sourcing, assume Google failure 
			
		}
		// Check that at least REQUIRED_IMAGES image URLs were found
		if (imageURLs.size() >= REQUIRED_IMAGES) {
			// Loop through imageURLs until REQUIRED_IMAGES-th index
			for (int i = 0; i < REQUIRED_IMAGES; i++) {
				try {
					// Convert from URL to BufferedImg
					URL imageURL;
					imageURL = new URL(imageURLs.get(i));
					BufferedImage img = ImageIO.read(imageURL);
					// Store in Vector<BufferedImage>
					System.out.println("Img TYPE: " + img.getType());
					images.add(img);
				} catch (MalformedURLException e) {
					
				} catch (IOException e) {
					
				}
			}
			return images;
		}
		else {
			return null; // Return null if < REQUIRED_IMAGES images were found
		}
	}
}
