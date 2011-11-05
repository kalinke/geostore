package br.com.geostore.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class TelaInicio extends Activity implements Runnable{
	
	// 3 segundos
		private final int DELAY = 2000;

		@Override
		public void onCreate(Bundle icicle) {
			super.onCreate(icicle);
			// Exibe o layout com a imagem...
			setContentView(R.layout.telainicio_bd);

			Toast.makeText(this, "Iniciando", Toast.LENGTH_SHORT).show();

			// Solicita para o Handler executar o Runnable (this), fechando a SplashScreen depois de alguns segundos
			Handler h = new Handler();
			h.postDelayed(this, DELAY);
		}

		public void run() {
			// Abre o menu principal
			startActivity(new Intent(TelaInicio.this, GeoStoreActivity.class));

			// Finaliza esta activity
			finish();
		}
}
