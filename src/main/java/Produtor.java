import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Produtor extends Thread {
	private URL myURL;
	private int numero;
	Consumidor cons;

	public Produtor(URL myURL, int numero) {
		super();
		this.myURL = myURL;
		this.numero = numero;
		this.start();
		System.out.println("Produtor Iniciado");
	}

	public synchronized void run() {
		String html = getHTML(this.myURL.toString());

		/*
		 * 
		 * for(int i=0; i<50;i++) { System.out.println("Thread " + numero + " ---> " +
		 * i); COMPROVA��O DA PROGRAMA��O PARALELA try { Thread.sleep(1000); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 */

		try {
			Document doc = Jsoup.parse(html);
			Elements elements = doc.getElementsByTag("img");
			for (Element e : elements) {
				String str = e.toString();
				Pattern p = Pattern.compile("(<img\\b|(?!^)\\G)[^>]*?\\b(src)=([\"']?)([^\"]*)\\3");
				Matcher m = p.matcher(str);

//				URL urlImage;
//				Image img;
				String href = e.attr("href");

				href = processLink(href, this.myURL.toString());
				if (m.find()) {
					String urlAcess = "";
					if (m.group(4).startsWith("https") || m.group(2).startsWith("http")) {
						urlAcess = m.group(4);
						System.out.println("Thread " + numero + ' ' + m.group(2) + ": " + m.group(4));

					} else if (m.group(4).startsWith("//")) {
						urlAcess = "https:" + m.group(4);
						System.out.println("Thread " + numero + ' ' + m.group(2) + ": " + "https:" + m.group(4));

					} else if (m.group(4).startsWith("/")) {
						urlAcess = href + m.group(4);
					}
					
					BufferImg b = BufferImg.getInstance();
					b.getListBuffer().add(urlAcess);
					
				}
			}
		} catch (Exception e) {
			System.out.println("Thread" + numero + "Erro");

		}
	}

	private String getHTML(String url) {

		URL u;
		try {
			u = new URL(url);

			URLConnection conn = u.openConnection();
			conn.setRequestProperty("User-Agent", "BBot/1.0");
			conn.setRequestProperty("Accept-Charset", "UTF-8");

			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String line;
			String html = "";
			while ((line = reader.readLine()) != null) {
				html += line + "\n";
			}
			html = html.trim();

			return html;
		} catch (Exception e) {
			return null;
		}

	}

	private String processLink(String link, String base) {

		try {
			URL u = new URL(base);
			if (link.startsWith("./")) {
				link = link.substring(2, link.length());
				link = u.getProtocol() + "://" + u.getAuthority() + stripFilename(u.getPath()) + link;
			} else if (link.startsWith("#")) {
				link = base + link;
			} else if (link.startsWith("javascript:")) {
				link = null;
			} else if (link.startsWith("../") || (!link.startsWith("http://") && !link.startsWith("https://"))) {
				link = u.getProtocol() + "://" + u.getAuthority() + stripFilename(u.getPath()) + link;
			}
			return link;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private String stripFilename(String path) {
		int pos = path.lastIndexOf("/");
		return pos <= -1 ? path : path.substring(0, pos + 1);
	}
}
