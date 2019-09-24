public class Consumidor extends Thread {

	private BufferImg buffer;
	private ThreadDonwload td;
	private int qtdImgBuffer, qtdAtual;

	public Consumidor(BufferImg buffer) {
		this.start();
		this.buffer = buffer;
		System.out.println("Consumidor Iniciado");
		this.qtdImgBuffer = 0;

	}

	public synchronized void run() {
		while (true) {
			qtdImgBuffer = buffer.getListBuffer().size();
			if (qtdImgBuffer == qtdAtual) {
				System.out.println("Sem novas Imgs");
			} else if (qtdImgBuffer > qtdAtual) {
				td = new ThreadDonwload(buffer.getListBuffer().get(qtdImgBuffer - 1));
				System.out.println("Nova Imagem adicionada");
				this.qtdAtual++;
			}else {
				System.out.println("QtdImgBuffer = " + qtdImgBuffer);
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
