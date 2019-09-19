public class Consumidor extends Thread {

	private BufferImg buffer;
	private ThreadDonwload td;
	private int qtdImgBuffer, qtdAtual;

	public Consumidor() {
		this.start();
		this.buffer = BufferImg.getInstance();
		this.qtdImgBuffer = 0;

	}

	public void run() {
		while (true) {
			qtdImgBuffer = buffer.getListBuffer().size();
			if (qtdImgBuffer == qtdAtual) {
				System.out.println("Sem novas Imgs");
			} else if (qtdImgBuffer > qtdAtual) {
				td = new ThreadDonwload(buffer.getListBuffer().get(qtdImgBuffer - 1));
				this.qtdAtual++;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public BufferImg getBuffer() {
		return buffer;
	}

	public void setBuffer(BufferImg buffer) {
		this.buffer = buffer;
	}

	public int getQtdImgBuffer() {
		return qtdImgBuffer;
	}

	public void setQtdImgBuffer(int qtdImgBuffer) {
		this.qtdImgBuffer = qtdImgBuffer;
	}

	public int getQtdAtual() {
		return qtdAtual;
	}

	public void setQtdAtual(int qtdAtual) {
		this.qtdAtual = qtdAtual;
	}

	public ThreadDonwload getTd() {
		return td;
	}

	public void setTd(ThreadDonwload td) {
		this.td = td;
	}

}
