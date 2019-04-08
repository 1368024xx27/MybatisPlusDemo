package com.vrain.httpclient;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 响应的服务接口
 * 
 * @author zhu
 *
 */
public class HttpClientService {

	/**
	 * 不带参数的get请求
	 * 
	 * @param url
	 *            请求的url
	 * @return
	 * @throws Exception
	 */
	public HttpResult doGet(String url) throws Exception {
		return doGet(url, null);
	}

	/**
	 * 参数的get请求
	 * 
	 * @param url
	 *            请求的url
	 * @param map
	 *            请求传递的参数
	 * @return
	 * @throws Exception
	 */
	public HttpResult doGet(String url, Map<String, Object> map) throws Exception {
		// 1.创建HttpClient的对象
		CloseableHttpClient client = HttpClients.createDefault();
		// 2.创建HttpGet get请求对象
		URIBuilder builder = new URIBuilder(url);
		// 遍历参数，设置参数
		if (map != null) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				builder.setParameter(entry.getKey(), entry.getValue().toString());
			}
		}
		URI uri = builder.build();
		HttpGet httpGet = new HttpGet(uri);
		// 3.执行请求
		CloseableHttpResponse response = client.execute(httpGet);
		// 4.获取响应的结果，封装到HttpResult中，并返回
		int code = response.getStatusLine().getStatusCode();// 获取状态码
		String body = null;
		if (response.getEntity() != null) {
			body = EntityUtils.toString(response.getEntity(), "utf-8");
		}
		return new HttpResult(code, body);
	}

	/**
	 * 不带参数的post请求
	 * 
	 * @param url
	 *            请求的url
	 * @return
	 * @throws Exception
	 */
	public HttpResult doPost(String url) throws Exception {
		return doPost(url, null);
	}

	/**
	 * 带参数的post请求
	 * 
	 * @param url
	 *            请求的url
	 * @param map
	 *            请求传递的参数
	 * @return
	 * @throws Exception
	 */
	public HttpResult doPost(String url, Map<String, Object> map) throws Exception {
		// 1.创建HttpClient对象
		CloseableHttpClient client = HttpClients.createDefault();
		// 2.创建HttpPost post请求对象
		HttpPost httpPost = new HttpPost();
		// 遍历参数集合map集合，设置参数列表
		if (map != null) {
			List<NameValuePair> parameters = new ArrayList<>();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
			}
			// 创建表单实体对象，将参数设置到表单实体中
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
			// 将表单实体对象设置到HttpPost请求对象中
			httpPost.setEntity(formEntity);
		}
		// 3.执行请求
		CloseableHttpResponse response = client.execute(httpPost);
		// 4.获取请求结果，封装到HttpResult中，并返回
		int code = response.getStatusLine().getStatusCode();
		String body = null;
		if (response.getEntity() != null) {
			body = EntityUtils.toString(response.getEntity(), "utf-8");
		}
		return new HttpResult(code, body);
	}
}