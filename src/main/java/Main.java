import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		String pathname = "/home/euller/eclipse-workspace/WEBCRAWLER/WebCrawler/src/main/resources/"
				+ "urls_random_images";
		File arq = new File(pathname);
		
		Produtor p;
		int numero = 1;

		BufferedReader br = new BufferedReader(new FileReader(arq));
		String urlLinha;
		
		
		Consumidor consumidor = new Consumidor(BufferImg.getInstance());

		while ((urlLinha = br.readLine()) != null) {
			p = new Produtor(new URL(urlLinha), numero, BufferImg.getInstance());
			numero++;
		}

	}

}
