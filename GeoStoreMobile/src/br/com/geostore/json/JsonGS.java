package br.com.geostore.json;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.geostore.entity.Usuario;

public class JsonGS {

	public Usuario JSonObjectToUsuario(String response){
		
		try {
			
			JSONObject jObj = new JSONObject(response);
			Usuario u = new Usuario();			
			u.setId(jObj.getLong("id"));
			u.setNome(jObj.getString("nome"));
			u.setCpf(jObj.getString("cpf"));
			u.setEmail(jObj.getString("email"));						
			u.setSenha(jObj.getString("senha"));
			
			return u;
			
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		
		return null;		
	}
	
}
