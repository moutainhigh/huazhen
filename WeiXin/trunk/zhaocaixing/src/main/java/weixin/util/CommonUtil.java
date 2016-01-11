package weixin.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weixin.pojo.Token;

/**
 * 通用工具类
 * 
 * @author Administrator
 * @date 2015-11-18
 */
public class CommonUtil {
	private static Logger log =  LoggerFactory.getLogger(CommonUtil.class);
	/**
	 * 发送https请求
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod	请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject（通过JSONObject.get(key)的方式获取JSON对象的属性值）
	 */
	public static JSONObject httpsRequest(String requestUrl,String requestMethod,String outputStr){
		JSONObject jsonObject  = null;
		try {
			TrustManager[] tm = {new MyX509TrustManager()};
			SSLContext  sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			//设置请求方法
			conn.setRequestMethod(requestMethod);
			//当outputStr不为null是，向输出流写数据
			if(null!=outputStr){
				OutputStream outputStream = conn.getOutputStream();
				//注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			
			//从输入流读取返回的内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while((str = bufferedReader.readLine()) !=null){
				buffer.append(str);
			}
			
			//释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("链接超时：{}", ce);
		} catch (Exception e) {
			log.error("https请求异常：{}", e);
		}
		return jsonObject;
	}
	/**
	 * 获取接口访问凭证
	 * @param appid 凭证
	 * @param appsecret 秘钥
	 * @return token
	 */
	public static Token getToken(String appid,String appsecret){
		Token token = null;
		String requestUrl = Constant.TOKEN_URL.replace("APPID",appid).replace("APPSECRET", appsecret);
		//发起get请求获取凭证
		JSONObject jsonObject = httpsRequest(requestUrl, Constant.REQUEST_METHOD_GET, null);
		if(jsonObject!=null){
			try {
				token = new Token();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (Exception e) {
				token = null;
				//获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return token;
	}
}