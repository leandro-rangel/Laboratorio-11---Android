package br.java.android.laboratorio11;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PrincipalActivity extends Activity {
	
	private EditText minimoEditText;
	private EditText maximoEditText;
	private TextView numeroGeradoTextView;
	private NumeroAleatorioSevice servico;

	private ServiceConnection conexao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		
		minimoEditText = (EditText) findViewById(R.id.numMinimoEditText);
		maximoEditText = (EditText) findViewById(R.id.numMaximoEditText);
		numeroGeradoTextView = (TextView) findViewById(R.id.numeroGeradoTextView);
		
		
		findViewById(R.id.gerarButton)
								.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View vParam) {
				obtemNumeroAleatorio();
				
			}
		});
		

		conexao = new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName nameParam) {
				servico = null;
				Log.d("Laboratorio11", "Desconectado!!");
			}
			
			@Override
			public void onServiceConnected(ComponentName nameParam,
					IBinder serviceParam) {
				Log.d("Laboratorio11", "Servi�o Conectado!!");

				servico = ((NumeroAleatorioSevice.NumeroAleatorioBind) 
						serviceParam).obtemServico();
			}
		};
		
		Intent servicoIntent = new Intent(this, NumeroAleatorioSevice.class);
		bindService(servicoIntent, conexao, Service.BIND_AUTO_CREATE); 

	}

	protected void obtemNumeroAleatorio() {
		
		int minimo = Integer.parseInt(minimoEditText.getText().toString());
		int maximo = Integer.parseInt(maximoEditText.getText().toString());
		int aleatorio = servico.getNumeroAleatorio(minimo, maximo);
		
		numeroGeradoTextView.setText("Gerado: " + aleatorio);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
