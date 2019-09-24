import java.util.ArrayList;
import java.util.List;

public class BufferImg {
	private static BufferImg instace;
	private static List<String> ListBuffer;


	public static BufferImg getInstance() {
		if(instace == null) {
			System.out.println("Buffer Compartilhado Aberto");
			instace = new BufferImg();
			ListBuffer = new ArrayList<String>();
			return instace;
		}
		return instace;
	}

	public List<String> getListBuffer() {
		return ListBuffer;
	}

	public void setListBuffer(List<String> listBuffer) {
		ListBuffer = listBuffer;
	}
	
}
