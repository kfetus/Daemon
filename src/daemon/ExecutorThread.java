package daemon;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExecutorThread extends Thread {

	
	private Socket taskSock;
	private Logger logger = LogManager.getLogger(getClass());

	public ExecutorThread(Socket socket) {
		logger.debug("###### Thread Initialize ######");
		this.taskSock = socket;
	}

	public void run() {
		logger.debug("###### Thread Run Start ######");
		InputStream is = null;
		OutputStream os = null;
		byte[] byteLen = new byte[4];
		
		try {
			is = taskSock.getInputStream();
			while(true) {
				is.read(byteLen);
				String transLength = new String(byteLen);
				int iLength = Integer.parseInt(transLength);
				byte[] trans = new byte[iLength];
				
				int iReqLen = is.read(trans);
				logger.debug("###### Input Full Data [{}] ######",new String(trans));
				if(iLength != iReqLen) {
					break;
				} else {
					logger.debug("###### Input Data [{}] ######",new String(trans));
				}
				if(is.available() == 0) break;
			}
			logger.debug("###### Socket Read End ######");
			
			os = taskSock.getOutputStream();
			os.write("CLOSE".getBytes());
			is.close();
			os.close();
			taskSock.close();
		} catch (IOException e) {
			logger.error("@@@@@@ Thread Socket Read Write Error @@@@@@");
			e.printStackTrace();
		} finally {
			try {
				if(is != null) is.close();
				if(os != null) os.close();
				if(taskSock != null) taskSock.close();
			} catch (IOException e) {
				logger.error("@@@@@@ Thread Socket Close Error @@@@@@");
				e.printStackTrace();
			}
		}
		
		logger.debug("###### Thread Run End ######");
	}

}
