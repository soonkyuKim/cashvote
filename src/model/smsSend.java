package model;

import java.util.HashMap;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleSend
 * @brief This sample code demonstrate how to send sms through CoolSMS Rest API
 *        PHP ��ó : https://developer.coolsms.co.kr/JAVA_SDK_EXAMPLE_Message
 */
public class smsSend {	
	public void coffeeSend() {
		String api_key = "NCSZ2YID7AJDP1UP";
		String api_secret = "X7ZEO1FMUIMCXE7FCS9ZMFYFRIE8ZFVU";
		Message coolsms = new Message(api_key, api_secret);

		// 4 params(to, from, type, text) are mandatory. must be filled
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", "01047429809"); // ������ȭ��ȣ
		params.put("from", "01042300490"); // �߽���ȭ��ȣ. �׽�Ʈ�ÿ��� �߽�,���� �Ѵ� ���� ��ȣ�� �ϸ� ��
		params.put("type", "SMS");
		params.put("text", userModel.loginId+"��, [ĳ�ú�Ʈ] ��Ÿ���� �Ƹ޸�ī�� ��ȯ���� ���޵Ǿ����ϴ�.");
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
		params.put("to", "01081215383"); // ������ȭ��ȣ
//		params.put("to", "01020004824"); // ������ȭ��ȣ
		params.put("from", "01042300490"); // �߽���ȭ��ȣ. �׽�Ʈ�ÿ��� �߽�,���� �Ѵ� ���� ��ȣ�� �ϸ� ��
		params.put("type", "SMS");
		params.put("text", userModel.loginId+"��, [ĳ�ú�Ʈ] ������ ���� ��ȯ���� ���޵Ǿ����ϴ�.");
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
		params.put("to", "01027891572"); // ������ȭ��ȣ
		params.put("from", "01042300490"); // �߽���ȭ��ȣ. �׽�Ʈ�ÿ��� �߽�,���� �Ѵ� ���� ��ȣ�� �ϸ� ��
		params.put("type", "SMS");
		params.put("text", userModel.loginId+"��, [ĳ�ú�Ʈ] �ƺ� ���� ��ȯ���� ���޵Ǿ����ϴ�.");
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