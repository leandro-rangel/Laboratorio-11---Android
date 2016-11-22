package br.java.android.laboratorio11;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NumeroAleatorioReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context contextParam, Intent intentParam) {
		
		Intent intent = new Intent(contextParam,NumeroAleatorioReceiver.class);
		contextParam.startService(intent);
	}
}
