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

public class ListaDeProdutosAdapter extends BaseAdapter {  
	  
	   private Context ctx;  
	   private List<Produto> produtos;  
	  
	   public ListaDeProdutosAdapter(Context ctx, List <Produto> produtos) {  
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
		
		View v = LayoutInflater.from(ctx).inflate(R.layout.lista, null);		

		TextView txtProduto = (TextView) v.findViewById(R.id.txtProduto);   
		  
		txtProduto.setText(p.getDescricao());  
		  
		return v;
		      	
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}  
	  

	}    