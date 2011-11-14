package br.com.geostore.adapters;

import java.util.List;

import br.com.geostore.activities.R;
import br.com.geostore.entity.Promocao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListaPromocoesAdapter extends BaseAdapter {  
	  
	   private static final String TAG = "ListaPromocoesAdapter";
	   private Context ctx;  
	   private List<Promocao> promocoes;  
	  
	   public ListaPromocoesAdapter(Context ctx, List <Promocao> promocoes) {  
	      this.ctx = ctx;  
	      this.promocoes = promocoes;  
	   }

	@Override
	public int getCount() {
		return promocoes.size();
	}

	@Override
	public Object getItem(int position) {
		return promocoes.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Promocao p = promocoes.get(position);
		
		View v = LayoutInflater.from(ctx).inflate(R.layout.listapromocoes, null);		
   
		TextView txtDesc  = (TextView) v.findViewById(R.id.txtDesc);
		TextView txtQtde  = (TextView) v.findViewById(R.id.txtQtde);
				  
		txtDesc.setText(p.getDescricao());
		txtQtde.setText("Disponível: ".concat(Double.toString(p.getQdeVoucher()-p.getQdeSolicitada())));		

		return v;
		      	
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}  
	  

	}    