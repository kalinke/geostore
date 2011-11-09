package br.com.geostore.activities;

import java.util.ArrayList;
import java.util.List;

import br.com.geostore.adapters.ListaProdutosAdapter;
import br.com.geostore.entity.Produto;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.AdapterView;

public class ListaProdutosActivity extends ListActivity {

	protected static final int CONTEXTMENU_ROTA    = 1; 
	protected static final int CONTEXTMENU_DISCAR  = 2; 
	protected static final int CONTEXTMENU_VOUCHER = 3; 
	private List<Produto> prods = null;
	
	@SuppressWarnings("unchecked")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.prods = (ArrayList<Produto>) getIntent().getSerializableExtra("produtos");		
		ListaProdutosAdapter adapter = new ListaProdutosAdapter(this, prods);  
		setListAdapter(adapter);
		registerForContextMenu(getListView());
	}
	
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("Escolha uma opção...");
		menu.add(0,CONTEXTMENU_ROTA,   0,"Traçar Rota");
		menu.add(0,CONTEXTMENU_DISCAR, 0,"Discar Loja");
		menu.add(0,CONTEXTMENU_VOUCHER,0,"Promoções");
	}
	
    public boolean onContextItemSelected(MenuItem item) {
	    
    	AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();	    
	    int i   = item.getItemId();
	    Produto p = prods.get(info.position);
	    switch (i) {
	    	case CONTEXTMENU_ROTA:    		
	    		Intent in = new Intent(ListaProdutosActivity.this, Rota.class);
				in.putExtra("produto", p);										
				startActivity(in);	    		
	    		return true;
	    	case CONTEXTMENU_DISCAR:	    		
	    		Uri fone = Uri.parse("tel:" + p.getLoja().getTelefone());
	    		Intent discar = new Intent(Intent.ACTION_CALL,fone);
	    		startActivity(discar);
	    		return true;	    	
	    	case CONTEXTMENU_VOUCHER:	    		    			    			    		    	
	    		return true;	    	
	    	default:
	    		return super.onContextItemSelected(item);
	    }
    }
}

