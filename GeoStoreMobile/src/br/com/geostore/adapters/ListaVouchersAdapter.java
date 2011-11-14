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

public class ListaVouchersAdapter extends BaseAdapter {  
	  
	   private static final String TAG = "ListaVouchersAdapter";
	   private Context ctx;  
	   private List<Voucher> vouchers;  
	  
	   public ListaVouchersAdapter(Context ctx, List <Voucher> vouchers) {  
	      this.ctx = ctx;  
	      this.vouchers = vouchers;  
	   }

	
	   @Override
		public int getCount() {
			return vouchers.size();
		}

		@Override
		public Object getItem(int position) {
			return vouchers.get(position);
		}
	   
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Voucher voucher = this.vouchers.get(position);
		
		View v = LayoutInflater.from(ctx).inflate(R.layout.meusvouchers, null);		
   
		TextView tvNomeProd  = (TextView) v.findViewById(R.id.tvNomeProdMeusVouchers);
		TextView tvDescProd  = (TextView) v.findViewById(R.id.tvDescProdMeusVouchers);
		TextView tvNomeLoja  = (TextView) v.findViewById(R.id.tvNomeLojaMeusVouchers);
		TextView tvEndLoja   = (TextView) v.findViewById(R.id.tvEndLojaMeusVouchers);
		TextView tvPrcProd   = (TextView) v.findViewById(R.id.tvPrcProdMeusVouchers);
		TextView tvNumVoucher  = (TextView) v.findViewById(R.id.tvNumVoucherMeusVouchers);
		
		tvNomeProd.setText(voucher.getPromocao().getProduto().getNome());
		tvDescProd.setText(voucher.getPromocao().getProduto().getDescricao());
		tvNomeLoja.setText(voucher.getPromocao().getProduto().getLoja().getNomeFantasia());
		tvEndLoja.setText(voucher.getPromocao().getProduto().getLoja().getEndereco().getLogradouro());
		tvPrcProd.setText(voucher.getPromocao().getProduto().getValor().toString());
		tvNumVoucher.setText(voucher.getCodigoVoucher());
				  		
		return v;
		      	
	}


	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	  

	}    