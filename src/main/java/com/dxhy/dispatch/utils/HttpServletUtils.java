package com.dxhy.dispatch.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.dxhy.dispatch.constants.SystemConstants.charset;
import static com.dxhy.dispatch.utils.StringUtils.stringConcatenation;


/**
 * 
 * http Servlet 工具类
 *
 * @author 赵睿
 * @version 1.0 Created on 2016年5月30日 上午9:16:42
 */
public class HttpServletUtils {
	private static Logger logger = LoggerFactory.getLogger(HttpServletUtils.class);

    /**
     *
     * 发送httpGet请求
     *
     * @param requestUrl 请求路径
     * @return  返回请求结果
     * @throws ClientProtocolException
     * @throws IOException
     *             String
     * @author 李志永
     * @date Created on 2016年7月26日 上午11:06:48
     */
    public static String reqeustGet(String requestUrl) throws IOException {
        logger.info("http Get请求方式开始,请求路径为:{}", requestUrl);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        logger.info("http请求地址是:{}", requestUrl);
        // 发送httpGet请求
        HttpGet get = new HttpGet(requestUrl);
        // 接收请求返回的数据
        CloseableHttpResponse response = httpclient.execute(get);
        return dealResponse(requestUrl,response);
    }

    /**
     *
     * 发送httpGet请求-携带cookie
     *
     * @param requestUrl
     * @param jsessionId
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     *             String
     * @author: 李志永
     * @date: Created on 2016年7月26日 上午11:06:48
     */
    public static String reqeustGet(String requestUrl,String jsessionId) throws IOException {
        logger.info("http Get请求方式开始,请求路径为:{}", requestUrl);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        logger.info("http请求地址是:{}", requestUrl);
        // 发送httpGet请求
        HttpGet get = new HttpGet(requestUrl);
        if(jsessionId!=null){
            get.setHeader("Cookie", "JSESSIONID=" + jsessionId);
        }
        // 接收请求返回的数据
        CloseableHttpResponse response = httpclient.execute(get);
        return dealResponse(requestUrl,response);

    }



	/**
	 * 
	 * <p>
	 * 使用post请求方式请求
	 * </p>
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 *             String
	 * @author: 赵睿
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @date: Created on 2016年5月30日 上午10:00:47
	 */
	public static String requestPost(String url, List<NameValuePair> params)
			throws IOException {
		logger.info("使用post请求方式请求开始。。。");
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 参入发送路径
		HttpPost post = new HttpPost(url);
		// 用post请求
		post.setEntity(new UrlEncodedFormEntity(params, charset));
		// 接收请求返回的数据
		CloseableHttpResponse response = httpclient.execute(post);
		return  dealResponse(url, response);
	}
	/**
	 * 
	 * <p>
	 * 使用post请求方式请求
	 * </p>
	 * 
	 * @param url
	 * @param map
	 * @return
	 * @throws Exception
	 *             String
	 * @author: 赵睿
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @date: Created on 2016年5月30日 上午10:56:07
	 */
	public static String requestPost(String url, Map<String, String> map) throws IOException {
		return requestPost(url, toNameValuePairs(map));
	}

	public static String requestPost(String url, Map<String, String> map,String jSessionId) throws IOException {
		return requestPost(url, toNameValuePairs(map),jSessionId);
	}


	/**
	 * 
	 * <p>
	 * 使用post请求方式请求
	 * </p>
	 * 
	 * @param url
	 * @param json
	 * @return
	 * @throws Exception
	 *             String
	 * @author: 赵睿
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @date: Created on 2016年5月30日 上午10:00:47
	 */
	public static String requestPost(String url, String json) throws IOException {
		logger.info("使用post请求方式请求开始。。。");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 参入发送路径
		HttpPost post = new HttpPost(url);
		StringEntity se = new StringEntity(json, "UTF-8");
		// 用post请求
		se.setContentType("application/json;charset=UTF-8");
		post.setEntity(se);
		logger.info("使用post请求方式请求数据为:{}", post);
		// 接收请求返回的数据
		CloseableHttpResponse response = httpclient.execute(post);
		return dealResponse(url, response);
	}

    /**
     * 使用post请求方式请求-携带cookie
     * @param url
     * @param params
     * @param jsessionId
     * @return
     * @throws IOException
     */
    public static String requestPost(String url,List<NameValuePair> params,String jsessionId) throws IOException {
        logger.info("使用post请求方式请求开始。。。");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 参入发送路径
        HttpPost post = new HttpPost(url);
        // 用post请求
        post.setEntity(new UrlEncodedFormEntity(params, charset));
        if(jsessionId!=null){
            post.setHeader("Cookie", "JSESSIONID=" + jsessionId);
        }
        // 接收请求返回的数据
        CloseableHttpResponse response = httpclient.execute(post);
        return  dealResponse(url, response);
    }

	/**
	 * 使用post请求方式请求-携带cookie
	 * @param url
	 * @param params
	 * @param jsessionId
	 * @return
	 * @throws IOException
	 */
	public static String requestPost(String url,List<NameValuePair> params,String jsessionId,int statusCode) throws IOException {
		logger.info("使用post请求方式请求开始。。。");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 参入发送路径
		HttpPost post = new HttpPost(url);
		// 用post请求
		post.setEntity(new UrlEncodedFormEntity(params, charset));
		if(jsessionId!=null){
			post.setHeader("Cookie", "JSESSIONID=" + jsessionId);
		}
		// 接收请求返回的数据
		CloseableHttpResponse response = httpclient.execute(post);
		String result;// 取出请求返回状态
		StatusLine statusLine = response.getStatusLine();
		if (statusLine.getStatusCode() == statusCode) {
			// 获取http返回的报文实体
			HttpEntity httpEntity = response.getEntity();
			result = EntityUtils.toString(httpEntity);
			logger.debug("使用post请求方式请求返回的报文实体数据为:{}", result);
		} else {
			throw new IOException(stringConcatenation("请求失败！请求链接为：", url, "，返回的请求代码为：", statusLine.getStatusCode() + "",
					"，错误原因为:", statusLine.getReasonPhrase()));
		}
		return result;
	}



	public static String getClientCookies(URL url) throws  IOException{
        HttpURLConnection connection=(HttpURLConnection) url.openConnection();
        String cookieVal=connection.getHeaderField("Set-Cookie");
        String sessionId;
        if(cookieVal!=null){
            sessionId=cookieVal.substring(cookieVal.indexOf("=")+1,cookieVal.indexOf(";"));
            return sessionId;
        }else{
            logger.error("无法获得cookie，请求url为{}",url);
            throw new IOException("无法获得cookie");
        }
    }

	/**
	 * 企业登录
	 * @param userName
	 * @param password
	 * @throws IOException
	 */
	public static String login(String userName,String password,String loginUrl,String captcha) throws IOException {
		String jsessionId = HttpServletUtils.getClientCookies(new URL(loginUrl));
		logger.info("用户名为{}的企业开始进行用户登录",userName);
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("username", userName));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("captcha", captcha));

		logger.info("使用post请求方式请求开始。。。");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post = new HttpPost(loginUrl);
		post.setEntity(new UrlEncodedFormEntity(params, charset));
		post.setHeader("Cookie", "JSESSIONID=" + jsessionId);
		CloseableHttpResponse response=httpclient.execute(post);
		// 这里有一个非常有意思的逻辑，那就是在用户登录之后，如果登录成功，返回的状态是302，即重定向，如果是登录失败，返回200，并返回登录页面信息。
		logger.info("用户登录后返回的状态是:{}",response.getStatusLine().getStatusCode());
		logger.info("用户登录后返回的jsessionId是:{}",jsessionId);
		if(302==response.getStatusLine().getStatusCode()){
			return jsessionId;
		}
		return null;
	}

    /**
     * 处理响应结果
     * @param url
     * @param response
     * @return
     * @throws IOException
     */
    public static String dealResponse(String url, CloseableHttpResponse response) throws IOException {
        String result;// 取出请求返回状态
        StatusLine statusLine = response.getStatusLine();
        if (statusLine.getStatusCode() == 200) {
            // 获取http返回的报文实体
            HttpEntity httpEntity = response.getEntity();
            result = EntityUtils.toString(httpEntity);
            logger.debug("使用post请求方式请求返回的报文实体数据为:{}", result);

        } else {
            throw new IOException(stringConcatenation("请求失败！请求链接为：", url, "，返回的请求代码为：", statusLine.getStatusCode() + "",
                    "，错误原因为:", statusLine.getReasonPhrase()));
        }
        return result;
    }

    /**
     *
     * <p>
     * 将map集合中的数据转为http请求需要的请求键值对
     * </p>
     *
     * @return List<NameValuePair>
     * @author: 赵睿
     * @date: Created on 2016年5月30日 上午10:05:01
     */
    public static List<NameValuePair> toNameValuePairs(Map<String, String> map) {
        List<NameValuePair> params = new ArrayList<>();

        for (Entry<String, String> entry : map.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return params;
    }
}
