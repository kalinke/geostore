package br.com.geostore.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

import android.util.Log;

class GetHttp {
    
    public String page = "";
    
    public GetHttp(String URL) throws Exception{
        BufferedReader bufferedReader = null;
        try{
            HttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "android");
            HttpGet request = new HttpGet();
            request.setHeader("Content-Type", "text/plain; charset=utf-8");
            request.setURI(new URI(URL));
            HttpResponse response = client.execute(request);
            bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer stringBuffer = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            
            Log.d("GetHttp","Iniciando leitura de buffer.");
            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line + NL);
                Log.d("GetHttp", stringBuffer.toString());
            }
            bufferedReader.close();
            Log.d("GetHttp", "Leitura de buffer finalizada");
            
            page = stringBuffer.toString();
        }catch (Exception e) {
            Log.e("GetHttp", e.toString());
        }finally{
            if (bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch (IOException e){
                    Log.e("GetHttp", e.toString());
                }
            }
        }
    }
}
