package br.com.geostore.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.com.geostore.activities.R;
import br.com.geostore.entity.Voucher;

public class MeusDadosAdapter extends ArrayAdapter<Voucher>{
	
	private Context ctx;
	private int listViewResourceId;
	private ArrayList<Voucher> vouchers;  
	
	public MeusDadosAdapter(Context ctx, int listViewResourceId, ArrayList <Voucher> vouchers){
		super(ctx, listViewResourceId, vouchers);
		this.ctx = ctx;
		this.listViewResourceId = listViewResourceId;
		this.vouchers = vouchers;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Voucher voucher = vouchers.get(position);
		
		View v = LayoutInflater.from(ctx).inflate(R.layout.meusdados, null);		
   
		TextView txtNomeVoucher =  (TextView) v.findViewById(R.id.txtNomeVoucher);
		TextView txtDescVoucher =  (TextView) v.findViewById(R.id.txtDescVoucher);
		TextView txtLojaVoucher =  (TextView) v.findViewById(R.id.txtLojaVoucher);
		TextView txtEndVoucher  =  (TextView) v.findViewById(R.id.txtEndLojaVoucher);
		TextView txtDescPromo   =  (TextView) v.findViewById(R.id.txtPromoDescVoucher);
		TextView txtNumVoucher  =  (TextView) v.findViewById(R.id.txtVoucherVoucher);
		TextView txtPrcVoucher  =  (TextView) v.findViewById(R.id.txtPrcVoucher);
		
		txtNomeVoucher.setText(voucher.getPromocao().getProduto().getNome());
		txtDescVoucher.setText(voucher.getPromocao().getProduto().getDescricao());
		txtLojaVoucher.setText(voucher.getPromocao().getProduto().getLoja().getNomeFantasia());
		txtEndVoucher.setText(voucher.getPromocao().getProduto().getLoja().getEndereco().getLogradouro());
		txtDescPromo.setText(voucher.getPromocao().getDescricao());
		txtNumVoucher.setText(voucher.getCodigoVoucher());
		txtPrcVoucher.setText(voucher.getPromocao().getProduto().getValor().toString());
		
		return v;
	}
	
}
