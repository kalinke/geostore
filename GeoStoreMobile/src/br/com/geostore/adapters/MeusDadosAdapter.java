package br.com.geostore.adapters;

import java.util.List;

import br.com.geostore.activities.R;
import br.com.geostore.entity.Voucher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MeusDadosAdapter extends BaseAdapter{
	
	private Context ctx;  
	private List<Voucher> vouchers;  
	
	public MeusDadosAdapter(Context ctx, List <Voucher> vouchers){
		this.ctx = ctx;
		this.vouchers = vouchers;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Voucher voucher = vouchers.get(position);
		
		View v = LayoutInflater.from(ctx).inflate(R.layout.meusdados, null);		
   
		
		TextView txtDesc  = (TextView) v.findViewById(R.id.txtDesc);
		TextView txtPrc   = (TextView) v.findViewById(R.id.txtPrc);
		TextView txtLoja  = (TextView) v.findViewById(R.id.txtLoja);
		TextView txtPromo = (TextView) v.findViewById(R.id.txtPromo);
		  
		

		return v;
	}
	
}
