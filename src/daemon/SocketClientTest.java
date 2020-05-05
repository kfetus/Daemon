package daemon;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SocketClientTest {

	
	public static void main(String[] args) {
		
		for( int i = 0; i < 10; i++) {
			Runnable r = new TestThread(i);
			Thread t = new Thread(r);
			t.start();
		}
	}
}


class TestThread implements Runnable {
	private Logger logger = LogManager.getLogger(getClass());
	int cnt = 0;
	
	public TestThread(int cnt) {
		this.cnt = cnt;
	}
	
	public void run() {
		logger.debug("Client test [{{}] Start",cnt);
		
		String tData = cnt+"한글 테스트와  english test !@#$%^&*() {}][?/><end 띠움";
		String data = "0066"+tData.getBytes().length+tData;

		try {
			Socket soc = new Socket("127.0.0.1",30000);
			OutputStream os = soc.getOutputStream();
			os.write(data.getBytes());
			
			byte[] inData = new byte[5];
			InputStream is = soc.getInputStream();
			
			while(is.read(inData) != -1) {
				logger.debug("###### data[{}]######",new String(inData) );
			}
			is.close();
			os.close();
			soc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		logger.debug("Client test [{{}] End",cnt);
	}
}