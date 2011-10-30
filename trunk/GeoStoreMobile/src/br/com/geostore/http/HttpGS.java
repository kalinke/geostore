package br.com.geostore.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;


public class HttpGS {

	private static final String URL_SERVER = "http://10.0.2.2:8080/GeoStore/seam/resource/";
	private String servlet = null;
	
	public HttpGS(String servlet){
		this.servlet = URL_SERVER.concat(servlet);
	}
	
	@SuppressWarnings("rawtypes")
	public String getQueryString(Map params){
		if (params == null || params.size()==0){
			return "";
		}
		String queryString = null;
		Iterator e = (Iterator) params.keySet().iterator();
		while (e.hasNext()){
			String chave = (String) e.next();
			Object objValor = params.get(chave);
			String valor = objValor.toString();
			queryString = queryString == null ? "" : queryString + "&";
			queryString += chave + "=" + valor;
		}
		return queryString;
	}
	
	@SuppressWarnings("rawtypes")
	public HttpResponse doPost(Map params){
		
		HttpClient client = new DefaultHttpClient();
		String urlParams = getQueryString(params);		
		HttpGet get = new HttpGet(this.servlet + "?" + urlParams);
		try {
			HttpResponse resp = client.execute(get);
			return resp;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public HttpResponse getResponse(List<NameValuePair> params){
		
		DefaultHttpClient client = new DefaultHttpClient();
		
		try {
			
			HttpPost post = new HttpPost(this.servlet);
			post.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = client.execute(post);
			return response;
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public HttpResponse sendJObj(JSONObject j){
		
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(this.servlet);

		
		try {
			StringEntity str = new StringEntity(j.toString());
			
			str.setContentType("application/json; charset=utf-8");
			str.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json; charset=utf-8"));
			
			post.setEntity(str);		
			
			HttpResponse response = client.execute(post);
			
			return response;
						
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	
}
