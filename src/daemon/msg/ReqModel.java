package daemon.msg;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import daemon.util.DateUtil;
import daemon.util.StringUtil;

public class ReqModel implements Serializable{

	private static final long serialVersionUID = 391298218515958819L;
	
	private Logger logger = LogManager.getLogger(getClass());

	private String header;
	private String body;
	private String msg;
	
	public ReqModel() {
		logger.debug("###### ReqModel Initialize ######");
		makeHeader();
	}
	
	/**
	 * 공통 헤더 정의
	 */
	public void makeHeader() {
		StringBuffer header = new StringBuffer();
		header.append("0000")//전문 길이
		.append("0000")//전문 번호
		.append("A00111")//데이터 1 셋팅 서비스명 같은거
		.append("99")//셋팅 데이터
		.append(DateUtil.getNowDateFormat("yyyyMMddHHmmss"))//송신 시간
		.append("X");//헤더 종료
		this.header = header.toString();
		logger.debug("[{}]",this.header);
		logger.debug("[{}]",this.header.getBytes().length);
	}
	
	/**
	 * 전문 바디 정의. 파라미터로 VO 정의해서 써야 함
	 */
	public void makeBody() {
		StringBuffer body = new StringBuffer();
		body.append(StringUtil.lPadSpace("0000", 5))
		.append(StringUtil.rPadSpace("", 7))
		.append(StringUtil.rPadSpace("a한글4", 7))
		.append(StringUtil.lPadZero("66", 20))
		.append("99")
		.append(DateUtil.getNowDateFormat("yyyyMMddHHmmss"))//송신 시간
		;
		this.body = body.toString();
		logger.debug("[{}]",this.body);
		logger.debug("[{}]",this.body.getBytes().length);
	}
	
	/**
	 * 전문 리턴
	 * @return
	 */
	public String getMsg() {
		this.msg = this.header + this.body;
		logger.debug("[{}]",this.msg);
		logger.debug("[{}]",this.msg.getBytes().length);
		return this.msg;
	}
	
	public static void main(String[] args ) {
		ReqModel req = new ReqModel();
		req.makeBody();
		req.getMsg();
	}
}
