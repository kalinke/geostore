package br.com.geostore.activities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import br.com.geostore.adapters.MeusDadosAdapter;
import br.com.geostore.entity.Endereco;
import br.com.geostore.entity.Loja;
import br.com.geostore.entity.Produto;
import br.com.geostore.entity.Promocao;
import br.com.geostore.entity.Voucher;
import br.com.geostore.http.HttpGS;


public class MeusDadosActivity extends Activity{
	
	private static final int NENHUMVOUCHER  = 1;
	private static final int ERRO           = 9;
	private static final int OK             = 99;
	
	private int retorno;
	private MeusDadosAdapter adapter;
	private List<Voucher> vouchers;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meusdados);
        
        TextView txtNome = (TextView) findViewById(R.id.tvNomeUsuarioMeusDados);
    	txtNome.setText(BuscarActivity.getUsuario().getNome());
    	
    	TextView txtEmail = (TextView) findViewById(R.id.tvEmailUsuarioMeusDados);
    	txtEmail.setText(BuscarActivity.getUsuario().getEmail());
    	
    	TextView txtCpf = (TextView) findViewById(R.id.tvCpfUsuarioMeusDados);
    	txtCpf.setText(BuscarActivity.getUsuario().getCpf());

    	getVouchers();
    	
    	switch (this.retorno){
    		case OK:
    			adapter = new MeusDadosAdapter(this, R.id.l, this.vouchers);
    			
    			break;
    		case NENHUMVOUCHER:
    			TextView txtNomeVoucher = (TextView) findViewById(R.id.txtNomeVoucher);
    			txtNomeVoucher.setText("Você não possui nenhum voucher...");
    			break;
			case ERRO:
				Toast.makeText(MeusDadosActivity.this, "O servidor retornou dados inesperados!", Toast.LENGTH_SHORT).show();
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
					
					Loja loja = new Loja();
					loja.setNomeFantasia(jVoucher.getString("nomeLoja"));
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
					this.retorno = MeusDadosActivity.NENHUMVOUCHER;
				}
				
			} catch (JSONException e1) {
				
				this.retorno = MeusDadosActivity.ERRO;
				e1.printStackTrace();
				
			}
						
		}
		
	}
	
}
