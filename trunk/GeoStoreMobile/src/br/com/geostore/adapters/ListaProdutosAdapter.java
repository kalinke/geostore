package br.com.geostore.adapters;

import java.util.List;

import br.com.geostore.activities.R;
import br.com.geostore.entity.Produto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListaProdutosAdapter extends BaseAdapter {  
	  
	   private Context ctx;  
	   private List<Produto> produtos;  
	  
	   public ListaProdutosAdapter(Context ctx, List <Produto> produtos) {  
	      this.ctx = ctx;  
	      this.produtos = produtos;  
	   }

	@Override
	public int getCount() {
		return produtos.size();
	}

	@Override
	public Object getItem(int position) {
		return produtos.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Produto p = produtos.get(position);
		
		View v = LayoutInflater.from(ctx).inflate(R.layout.lista_produtos, null);		
   
		TextView txtNome  = (TextView) v.findViewById(R.id.txtNome);
		TextView txtDesc  = (TextView) v.findViewById(R.id.txtDesc);
		TextView txtPrc   = (TextView) v.findViewById(R.id.txtPrc);
		TextView txtLoja  = (TextView) v.findViewById(R.id.txtLoja);
		TextView txtPromo = (TextView) v.findViewById(R.id.txtPromo);
		  
		txtNome.setText("Nome: ".concat(p.getNome().toUpperCase()));
		txtDesc.setText("Descrição: ".concat(p.getDescricao().toUpperCase()));
		txtPrc.setText("Preço Unt.: ".concat(Double.toString(p.getValor()).toUpperCase()));
		txtLoja.setText("Loja: ".concat(p.getLoja().getNomeFantasia().toUpperCase()));
		txtPromo.setText("Promoções: ".concat(String.valueOf(p.getPromocoes().size())));

		return v;
		      	
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}  
	  

	}    