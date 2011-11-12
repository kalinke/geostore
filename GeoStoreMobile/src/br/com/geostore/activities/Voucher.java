package br.com.geostore.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Voucher extends Activity{
	
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.voucher_tela);
		
		Button btTeste = (Button)findViewById(R.id.btVoltarVoucher);
								
		btTeste.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				final Intent i = new Intent(Voucher.this, GeoStoreActivity.class);
                startActivity(i);
			}
		});
		
	}

}
