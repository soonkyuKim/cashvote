package model;

import java.util.HashMap;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleSend
 * @brief This sample code demonstrate how to send sms through CoolSMS Rest API
 *        PHP 출처 : https://developer.coolsms.co.kr/JAVA_SDK_EXAMPLE_Message
 */
public class smsSend {	
	public void coffeeSend() {
		String api_key = "NCSZ2YID7AJDP1UP";
		String api_secret = "X7ZEO1FMUIMCXE7FCS9ZMFYFRIE8ZFVU";
		Message coolsms = new Message(api_key, api_secret);

		// 4 params(to, from, type, text) are mandatory. must be filled
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", "01047429809"); // 수신전화번호
		params.put("from", "01042300490"); // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
		params.put("type", "SMS");
		params.put("text", userModel.loginId+"님, [캐시보트] 스타벅스 아메리카노 교환권이 지급되었습니다.");
		params.put("app_version", "test app 1.2"); // application name and version

		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
	
	public void airpotSend() {
		String api_key = "NCSZ2YID7AJDP1UP";
		String api_secret = "X7ZEO1FMUIMCXE7FCS9ZMFYFRIE8ZFVU";
		Message coolsms = new Message(api_key, api_secret);

		// 4 params(to, from, type, text) are mandatory. must be filled
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", "01081215383"); // 수신전화번호
//		params.put("to", "01020004824"); // 수신전화번호
		params.put("from", "01042300490"); // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
		params.put("type", "SMS");
		params.put("text", userModel.loginId+"님, [캐시보트] 에어팟 프로 교환권이 지급되었습니다.");
		params.put("app_version", "test app 1.2"); // application name and version

		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
	
	public void macproSend() {
		String api_key = "NCSZ2YID7AJDP1UP";
		String api_secret = "X7ZEO1FMUIMCXE7FCS9ZMFYFRIE8ZFVU";
		Message coolsms = new Message(api_key, api_secret);

		// 4 params(to, from, type, text) are mandatory. must be filled
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", "01027891572"); // 수신전화번호
		params.put("from", "01042300490"); // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
		params.put("type", "SMS");
		params.put("text", userModel.loginId+"님, [캐시보트] 맥북 프로 교환권이 지급되었습니다.");
		params.put("app_version", "test app 1.2"); // application name and version

		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
}