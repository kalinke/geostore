package br.com.geostore.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import br.com.geostore.adapters.ListaProdutosAdapter;
import br.com.geostore.entity.Produto;
import br.com.geostore.entity.Promocao;

public class ListaProdutosActivity extends ListActivity {

	private static final String TAG = "ListaProdutosActivity";
	private static final int CONTEXTMENU_ROTA    = 1; 
	private static final int CONTEXTMENU_DISCAR  = 2; 
	private static final int CONTEXTMENU_PROMO = 3; 
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
		menu.add(0,CONTEXTMENU_PROMO,  0,"Promoções");
	}
	
	public void tracarRota(Produto p){
		Intent in = new Intent(ListaProdutosActivity.this, RotaActivity.class);
		in.putExtra("produto", p);										
		startActivity(in);	  
	}
	
	public void discarLoja(Produto p){
		Uri fone = Uri.parse("tel:" + p.getLoja().getTelefone());
		Intent in = new Intent(Intent.ACTION_CALL,fone);
		startActivity(in);
	}
	
    public boolean onContextItemSelected(MenuItem item) {
	    
    	AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();	    
	    int i   = item.getItemId();
	    Produto p = prods.get(info.position);
	    Intent in;
	    switch (i) {
	    	case CONTEXTMENU_ROTA:    		
	    		tracarRota(p);
	    		return true;
	    	case CONTEXTMENU_DISCAR:	    		
	    		discarLoja(p);
	    		return true;	    	
	    	case CONTEXTMENU_PROMO:	    		    			    			    		    	
	    		if (p.getPromocoes().size()>0){		    		
	    			in = new Intent(ListaProdutosActivity.this, ListaPromocoesActivity.class);					
	    			in.putExtra("promocoes", (ArrayList<Promocao>) p.getPromocoes());
	    			finish();
					startActivity(in);
	    		}else{
	    			Toast.makeText(ListaProdutosActivity.this, "Não existe nenhuma promoção cadastrada para este produto...", Toast.LENGTH_SHORT).show();
	    		}
	    		return true;	    	
	    	default:
	    		return super.onContextItemSelected(item);
	    }
    }
}

