// Realizado por Carmen, Samuel y Javier Bernal //

import java.net.URL;
import java.io.InputStreamReader;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.rometools.rome.feed.synd.SyndEntry;
import java.util.stream.Stream;
import java.util.*;

public class parserRome {
	public static void main(String[] args) {
		
		Hashtable<String, String> hURL = new Hashtable<String, String>();
		hURL.put("elp", "http://ep00.epimg.net/rss/tags/ultimas_noticias.xml");
		hURL.put("bbc", "http://feeds.bbci.co.uk/news/rss.xml");
		hURL.put("lav","https://www.lavanguardia.com/mvc/feed/rss/home");
		hURL.put("cnn", "http://rss.cnn.com/rss/edition_europe.rss");
		hURL.put("abc", "https://sevilla.abc.es/rss/feeds/Sevilla_Sevilla.xml");
		hURL.put("elm", "https://e00-elmundo.uecdn.es/elmundo/rss/espana.xml");

    		Hashtable<String, String> hTitulo = new Hashtable<String, String>();
    		hTitulo.put("elp", "El país");
    		hTitulo.put("bbc", "BBC Headlines");
    		hTitulo.put("lav", "La vanguardia");
    		hTitulo.put("cnn", "CNN Headlines");
    		hTitulo.put("abc", "ABC: Sevilla");
    		hTitulo.put("elm", "El mundo");
		
		boolean ok = false;

		try {
			Enumeration<String> t1 = hTitulo.keys();
			String key; 

			while (t1.hasMoreElements()) {
				key = t1.nextElement();

				String url = hURL.get(key);

				URL feedURL = new URL(url);

				SyndFeedInput input = new SyndFeedInput();
				SyndFeed feed = input.build(new XmlReader(feedURL));

				System.out.println("\n--------------------------------------------------");
				System.out.println(hTitulo.get(key) + " ---------- " + feed.getTitle());

				List<SyndEntry> synd = new ArrayList<SyndEntry>();
				synd = feed.getEntries();

				Stream<SyndEntry> stream = synd.stream();
				stream
				.limit(5)
				.forEach(n -> System.out.println("\nTitulo: " + n.getTitle() + "\n------------------------------\nLink: " + n.getLink() + "\nDescripción:\n" + n.getDescription().getValue() + "\n------------------------------"));
				System.out.println("--------------------------------------------------");
			}

			ok = true;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ERROR: " + ex.getMessage());
		}

		if (!ok) {
			System.out.println();
			System.out.println("FeedReader reads and prints any RSS/Atom feed type,");
			System.out.println("The first parameter must be the URL of the feed to read,");
			System.out.println();
		}

	}
}
