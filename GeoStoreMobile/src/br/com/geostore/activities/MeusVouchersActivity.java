package br.com.geostore.activities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.Toast;
import br.com.geostore.adapters.ListaVouchersAdapter;
import br.com.geostore.entity.Endereco;
import br.com.geostore.entity.Loja;
import br.com.geostore.entity.Produto;
import br.com.geostore.entity.Promocao;
import br.com.geostore.entity.Voucher;
import br.com.geostore.http.HttpGS;


public class MeusVouchersActivity extends ListActivity{
	
	private static final int NENHUMVOUCHER  = 1;
	private static final int ERRO           = 9;
	private static final int OK             = 99;
	private static final int CONTEXTMENU_ROTA    = 1; 
	private static final int CONTEXTMENU_DISCAR  = 2; 
	
	private int retorno;
	private List<Voucher> vouchers;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.retorno = MeusVouchersActivity.OK;
    	getVouchers();
    	
    	switch (this.retorno){
    		case OK:
    			ListaVouchersAdapter adapter = new ListaVouchersAdapter(this, vouchers);  
    			setListAdapter(adapter);
    			registerForContextMenu(getListView());
    			break;
    		case NENHUMVOUCHER:
    			Toast.makeText(MeusVouchersActivity.this, "Você não possui nenhum voucher.", Toast.LENGTH_SHORT).show();
    			finish();
    			break;
			case ERRO:
				Toast.makeText(MeusVouchersActivity.this, "O servidor retornou dados inesperados!", Toast.LENGTH_SHORT).show();
				finish();
				break;
    	}        
	}
	
	public void getVouchers(){
		
		HttpGS http = new HttpGS(this);
		
		String response = http.getVouchers(String.valueOf(BuscarActivity.getUsuario().getId()));
		
		if (response!=null){
						
			try {
				
				JSONArray jVouchers = new JSONArray(response);
				this.vouchers = new ArrayList<Voucher>();
				
				for (int i=0; i<jVouchers.length(); i++) {				
					
					JSONObject jVoucher = jVouchers.getJSONObject(i).getJSONObject("voucher");			
					
					Endereco endereco = new Endereco();
					endereco.setLogradouro(jVoucher.getString("endLoja"));
					endereco.setNumeroLogradouro(jVoucher.getString("numLoja"));
					endereco.setBairro(jVoucher.getString("bairroLoja"));
					endereco.setLatitude(jVoucher.getDouble("latLoja"));
					endereco.setLongitude(jVoucher.getDouble("logLoja"));
					
					Loja loja = new Loja();
					loja.setNomeFantasia(jVoucher.getString("nomeLoja"));
					loja.setTelefone(jVoucher.getString("telLoja"));
					loja.setEndereco(endereco);
					
					Produto produto = new Produto();
					produto.setNome(jVoucher.getString("nomeProduto"));
					produto.setDescricao(jVoucher.getString("descProduto"));
					produto.setValor(jVoucher.getDouble("precoProduto"));					
					produto.setLoja(loja);					
					
					Promocao promocao = new Promocao();
					promocao.setDescricao(jVoucher.getString("descPromocao"));
					promocao.setProduto(produto);
					
					Voucher voucher = new Voucher();					
					voucher.setCodigoVoucher(jVoucher.getString("numVoucher"));
					voucher.setPromocao(promocao);
					
					this.vouchers.add(voucher);
					
				}
				
				if (this.vouchers.size()<=0){
					this.retorno = MeusVouchersActivity.NENHUMVOUCHER;
				}
				
			} catch (JSONException e1) {
				
				this.retorno = MeusVouchersActivity.ERRO;
				e1.printStackTrace();
				
			}
						
		}
		
	}
	
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("Escolha uma opção...");
		menu.add(0,CONTEXTMENU_ROTA,   0,"Traçar Rota");
		menu.add(0,CONTEXTMENU_DISCAR, 0,"Discar Loja");
	}
	
    public boolean onContextItemSelected(MenuItem item) {
	    
    	AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();	    
	    int i   = item.getItemId();
	    Voucher voucher = vouchers.get(info.position);
	    Intent in;
	    switch (i) {
	    	case CONTEXTMENU_ROTA:    		
	    		in = new Intent(MeusVouchersActivity.this, RotaActivity.class);
				in.putExtra("produto", voucher.getPromocao().getProduto());										
				startActivity(in);	    		
	    		return true;
	    	case CONTEXTMENU_DISCAR:	    		
	    		Uri fone = Uri.parse("tel:" + voucher.getPromocao().getProduto().getLoja().getTelefone());
	    		in = new Intent(Intent.ACTION_CALL,fone);
	    		startActivity(in);
	    		return true;	    	  	
	    	default:
	    		return super.onContextItemSelected(item);
	    }
    }
	
}
