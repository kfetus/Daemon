package daemon.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StringUtil {

	private static final Logger logger = LogManager.getLogger(StringUtil.class);
	
	/**
	 * 문자 타입 오른쪽  공백 padding
	 * @param data
	 * @param length
	 * @return
	 */
	public static String rPadSpace(String data , int length) {
		if(data != null && data.length() <= length) {
			data = String.format("%1$-"+length+"s", data);
		} else {
			logger.error("@@@@@@ Input Data is Fault @@@@@@");
			data = String.format("%1$-"+length+"s", ""); 
		}
		return data;
	}
	
	/**
	 * 문자 타입 왼쪽 공백 padding
	 * @param data
	 * @param length
	 * @return
	 */
	public static String lPadSpace(String data, int length) {
		if(data != null && data.length() <= length) {
			data = String.format("%1$"+length+"s", data);
		} else {
			logger.error("@@@@@@ Input Data is Fault @@@@@@");
			data = String.format("%1$"+length+"s", ""); 
		}
		return data;
	}
	
	/**
	 * 숫자 타입 왼쪽 0 padding
	 * @param data
	 * @param length
	 * @return
	 */
	public static String lPadZero(String data, int length) {
		if(data != null && data.length() <= length) {
			data = String.format("%0"+length+"d", Long.parseLong(data));
		} else {
			logger.error("@@@@@@ Input Data is Fault @@@@@@");
			data = String.format("%0"+length+"d", 0); 
		}
		return data;
	}
	
	
	public static void main(String[] args) {
		logger.debug("["+rPadSpace("gergsdfds",10)+"]");
		logger.debug("["+lPadSpace("3ergsdfds",10)+"]");
		logger.debug("["+lPadZero("32342423424",10)+"]");
	}
}
