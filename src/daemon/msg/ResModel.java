package daemon.msg;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResModel implements Serializable{

	private static final long serialVersionUID = 4206531828124612968L;

	private Logger logger = LogManager.getLogger(getClass());
	
	private String header;
	private String body;
	private String fullMsg;
	private String nowMsg;
	
	public ResModel(String data) {
		this.fullMsg = data;
		this.nowMsg  = data;
	}
	
	/**
	 * 현재 전문에서 해당 길이만큼 자르기. 
	 * @TODO nowMsg 셋팅하기.
	 * @param len
	 * @return
	 */
	public String cutMsg(int len) {
		String retStr = "";
		
		logger.debug("###### nowMsg = [{}] ######",nowMsg);
		
		if(len > nowMsg.length()) { len = nowMsg.length();}
		
		logger.debug("###### len = [{}] ######",len);
		for(int i = 0 ; i < len; i++) {
			char temp = nowMsg.charAt(i);
			logger.debug("###### Char Info="+(int)temp +"$"+ temp +"$"+ Character.getType(nowMsg.charAt(i))
			 +"$"+ Integer.toBinaryString((int)temp) +"$"+ Integer.toHexString((int)temp) +"$"+ String.format("0x%02x", (int)temp));
			if( Character.getType(temp) == 5 || (int) temp == 12288) {
				len--;
			}
		}
		logger.debug("###### len = [{}] ######",len);
		retStr = nowMsg.substring(0,len);
		logger.debug("###### retStr = [{}] ######",retStr);
		return retStr;
	}

}
