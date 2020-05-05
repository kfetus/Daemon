package daemon;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerStarter {

	
	private Logger logger = LogManager.getLogger(getClass());
	
	private ServerSocket sc;
	private ExecutorService es;
	private HashMap<String,ExecutorThread> poolList;
	private boolean state = true;
	private int cnt = 0;
	
	public ServerStarter(int port) throws Exception {
		logger.debug("###### ServerStarter Initialize #######");
		try {
			sc = new ServerSocket(port);
			es = Executors.newFixedThreadPool(50);
			poolList = new HashMap<String,ExecutorThread>();
		} catch (IOException e) {
			logger.error("@@@@@@ ServerStarter Constructor ERROR");
			e.printStackTrace();
			throw e;
		}
	}
	
	public void serverStart() throws Exception {
		logger.debug("###### ServerStarter Start #######");
		try {
			while(state) {
				Socket soc = sc.accept();
				ExecutorThread et = new ExecutorThread(soc);
				++cnt;
				poolList.put(cnt+"", et);
				logger.debug("###### NOW THREAD INFO[{}] ######",poolList);
				Future<?> ft = es.submit(et);
				logger.debug("FUTURE="+ft);
			}
		} catch (Exception e) {
			logger.error("@@@@@@ ServerStarter Start ERROR");
			e.printStackTrace();
			throw e;
		}
		
		
	}
	
}
