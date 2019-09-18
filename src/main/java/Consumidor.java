import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Consumidor extends Thread{
	private Thread threadImg;
	
	public void verificaBuffer() {
		
	}
	
	public void download(String url) {
		try {
			BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
			FileOutputStream fos = new FileOutputStream("Imagem " + ".png");
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			
			while((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				fos.write(dataBuffer, 0, bytesRead);
			}
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
