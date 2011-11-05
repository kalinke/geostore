package br.com.geostore.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.util.Log;


public class HttpGS {

	private static final String URL_SERVER = "http://10.0.2.2:8080/GeoStore/seam/resource/";
	//private static final String URL_SERVER = "http://172.16.1.104:8080/GeoStore/seam/resource/";
	private DefaultHttpClient httpClient = null;
	
	public HttpGS(){
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 15000);
		HttpConnectionParams.setSoTimeout(params, 15000);
		httpClient = new DefaultHttpClient(params);	
	}
	
	public String buscarProdutos(String texto, String log, String lat, String raio){		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("texto", texto));
		params.add(new BasicNameValuePair("log",   log));
		params.add(new BasicNameValuePair("lat",   lat));
		params.add(new BasicNameValuePair("raio",  raio));
		return doPost("produtoServlet",params);
	}
	
	public String doPost(String servlet, List<NameValuePair> params){
		
		HttpResponse res = null;		
		HttpPost post = new HttpPost(URL_SERVER.concat(servlet));
		
		try {
			post.setEntity(new UrlEncodedFormEntity(params));
			res = httpClient.execute(post);
			return EntityUtils.toString(res.getEntity());			
		} catch (UnsupportedEncodingException e) {
			Log.e("HttpGS","UnsupportedEncodingException: " + e.getMessage());
		} catch (ClientProtocolException e) {
			Log.e("HttpGS","ClientProtocolException: " + e.getMessage());
		} catch (IOException e) {
			Log.e("HttpGS","IOException: " + e.getMessage());
		}
		
		return null;
	}		
}
