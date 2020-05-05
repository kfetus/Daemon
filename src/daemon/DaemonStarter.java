package daemon;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaemonStarter {

	
	private static final Logger logger = LogManager.getLogger(DaemonStarter.class);
	
	public static void main(String[] args) {

		logger.debug("###### Daemon START ######");

		OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	    RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
	    int availableProcessors = operatingSystemMXBean.getAvailableProcessors();
	    long prevUpTime = runtimeMXBean.getUptime();

	    logger.debug("Java CPU: " + availableProcessors);
	    logger.debug("prevUpTime [{}]",prevUpTime);

	    try {
			ServerStarter sst = new ServerStarter(30000);
			sst.serverStart();
		} catch (Exception e) {
			logger.error("@@@@@@ Daemon Create Error @@@@@@");
			e.printStackTrace();
		}
	    
	    logger.debug("###### Daemon END ######");
	}

}
