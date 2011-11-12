package br.com.geostore.activities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import br.com.geostore.adapters.ListaPromocoesAdapter;
import br.com.geostore.entity.Promocao;
import br.com.geostore.http.HttpGS;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class ListaPromocoes extends ListActivity {

	protected static final int CONTEXTMENU_VOUCHER    = 1;
	protected static final String TAG = "ListaPromocoes";
	
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
		menu.setHeaderTitle("Escolha uma op��o...");
		menu.add(0,CONTEXTMENU_VOUCHER,   0,"Gerar voucher");		
	}
	
    public boolean onContextItemSelected(MenuItem item) {
	    
    	AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();	    
	    int i   = item.getItemId();
	    Promocao p = promocoes.get(info.position);
	    switch (i) {
	    	case CONTEXTMENU_VOUCHER:    		
	    		Long idUsuario = GeoStoreActivity.getIdUsuario();
	    		if (idUsuario!=null){
	    			String voucher = GerarVoucher(p.getId(), p.getProduto().getId(), idUsuario);
	    			if (voucher!=null){
	    				Toast.makeText(ListaPromocoes.this, "N�mero do voucher gerado: " + voucher, Toast.LENGTH_SHORT).show();
	    			}else{
	    				Toast.makeText(ListaPromocoes.this, "N�o foi poss�vel gerar o voucher.", Toast.LENGTH_SHORT).show();
	    			}
	    			
	    		}else{
	    			Toast.makeText(ListaPromocoes.this, "Para gerar o voucher � necess�rio efetuar o login!", Toast.LENGTH_SHORT).show();
	    		}
	    		return true;    	
	    	default:
	    		return super.onContextItemSelected(item);
	    }
    }
    
    public String GerarVoucher(Long idPromocao, Long idProduto, Long idUsuario){
    	
    	HttpGS h = new HttpGS(this);
    	String s = h.gerarVoucher(Long.toString(idPromocao), Long.toString(idProduto), Long.toString(idUsuario));
    	
    	try{    		
    		JSONObject jVoucher = new JSONObject(s);
    		return jVoucher.getString("voucher");
    		
    	}catch (Exception e) {
    		Log.e(TAG, e.getMessage());
		}
    	
    	return null;
    }
}

