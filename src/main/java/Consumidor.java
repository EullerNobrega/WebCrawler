import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Consumidor extends Thread{
	
	public Consumidor() {
		this.start();
	}
	
//	public void verificaBuffer() {
//		
//	}
	
	public void download(String urlAcess) {
		try (InputStream in = new URL(urlAcess).openStream()) {
			Random r = new Random();
			Files.copy(in, Paths.get("/home/euller/projetos/WebCrawler/src/main/resources/downloads/ "
					+ "Img" + r.ints(1, 0, 10000) + ".png"));

			System.out.println("Imagem salva");
			Thread.sleep(0100);
		} catch (Exception ignore) {
		}
	}
}
