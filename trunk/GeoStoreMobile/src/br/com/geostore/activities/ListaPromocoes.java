package br.com.geostore.activities;

import java.util.ArrayList;
import java.util.List;

import br.com.geostore.adapters.ListaPromocoesAdapter;
import br.com.geostore.entity.Promocao;
import br.com.geostore.http.HttpGS;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.AdapterView;

public class ListaPromocoes extends ListActivity {

	protected static final int CONTEXTMENU_VOUCHER    = 1; 
	
	private List<Promocao> promocoes = null;
	
	@SuppressWarnings("unchecked")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.promocoes = (ArrayList<Promocao>) getIntent().getSerializableExtra("promocoes");		
		ListaPromocoesAdapter adapter = new ListaPromocoesAdapter(this, promocoes);  
		setListAdapter(adapter);
		registerForContextMenu(getListView());
	}
	
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("Escolha uma opção...");
		menu.add(0,CONTEXTMENU_VOUCHER,   0,"Gerar voucher");		
	}
	
    public boolean onContextItemSelected(MenuItem item) {
	    
    	AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();	    
	    int i   = item.getItemId();
	    Promocao p = promocoes.get(info.position);
	    switch (i) {
	    	case CONTEXTMENU_VOUCHER:    		
	    		GerarVoucher(p.getId(), p.getProduto().getId(), 0);
	    		return true;    	
	    	default:
	    		return super.onContextItemSelected(item);
	    }
    }
    
    public void GerarVoucher(Long idPromocao, Long idProduto, int idUsuario){
    	HttpGS h = new HttpGS(this);
    	String s = h.gerarVoucher(Long.toString(idPromocao), Long.toString(idProduto), Long.toString(idUsuario));    	
    }
}

