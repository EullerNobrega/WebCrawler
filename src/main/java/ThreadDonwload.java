import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class ThreadDonwload extends Thread {
	private String urlAcess;
	
	
	public ThreadDonwload(String urlAcess) {
		this.start();
		System.out.println("Nova Thread Download Iniciada");
		this.urlAcess = urlAcess;
	}
	
	public synchronized void run() {
		try (InputStream in = new URL(urlAcess).openStream()) {
			Random r = new Random();
			Files.copy(in, Paths.get("/web-crawler/src/main/resources/donwloads/ " + "Img"
					+ r.ints(1, 0, 10000) + ".png"));

			System.out.println("Imagem salva");
		} catch (Exception ignore) {
		}
	}
	
}
