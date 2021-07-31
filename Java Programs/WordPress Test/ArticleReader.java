import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ArticleReader {
	
	public ArrayList<Article> readFromJSON(String fname) {
		ArrayList<Article> articles = new ArrayList<Article>();
		try {
			FileReader reader = new FileReader(new File(fname));
			//JSON parser object that reads the data from the file, and understands it format
			JSONParser parser = new JSONParser();
			JSONObject all = (JSONObject) parser.parse(reader);
			JSONArray arr = (JSONArray) all.get("articles"); // Creates an array from the key called Articles
			Iterator itr =arr.iterator(); // itr will help us navigate through all the JSON objects in the JSONArray (arr).
											// We will use a while look to go through the array
											// We will recover jsonObjects which we can convert to Article objects
			JSONObject articleObject;
			String title, author, text;
			while(itr.hasNext()){
				articleObject = (JSONObject) itr.next();
				title = articleObject.get("Title").toString();
				author = articleObject.get("Author").toString();
				text = articleObject.get("Text").toString();
				articles.add(new Article(title,author,text));
			}
			reader.close();
			return articles;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
}
