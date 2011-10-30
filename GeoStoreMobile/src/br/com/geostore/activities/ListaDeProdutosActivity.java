package br.com.geostore.activities;

import java.util.ArrayList;
import java.util.List;

import br.com.geostore.adapters.ListaDeProdutosAdapter;
import br.com.geostore.entity.Produto;

import android.app.ListActivity;
import android.os.Bundle;

public class ListaDeProdutosActivity extends ListActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//recuperando os parametros
		@SuppressWarnings("unchecked")
		List<Produto> prods = (ArrayList<Produto>) getIntent().getSerializableExtra("produtos");
		
		ListaDeProdutosAdapter adapter = new ListaDeProdutosAdapter(this, prods);  
		setListAdapter(adapter);	
	}	
}

