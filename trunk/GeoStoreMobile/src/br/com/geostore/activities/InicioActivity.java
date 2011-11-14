package br.com.geostore.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class InicioActivity extends Activity implements Runnable{
	
		private static final String TAG = "InicioActivity";
		private final int DELAY = 2240;
		

		@Override
		public void onCreate(Bundle icicle) {
			super.onCreate(icicle);
			setContentView(R.layout.inicio);
			Toast.makeText(this, "Iniciando...", Toast.LENGTH_SHORT).show();
			Handler h = new Handler();
			h.postDelayed(this, DELAY);
		}

		public void run() {
			startActivity(new Intent(InicioActivity.this, BuscarActivity.class));
			finish();
		}
}
