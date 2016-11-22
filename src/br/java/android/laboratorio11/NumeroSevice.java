package br.java.android.laboratorio11;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class NumeroAleatorioSevice extends Service {

	private NumeroAleatorioBind vinculo; //binder
	
	@Override
	public void onCreate() {
		super.onCreate();
		vinculo = new NumeroAleatorioBind();
	}
	
	@Override
	public IBinder onBind(Intent intentParam) {
		return vinculo;
	}
	
	@Override
	public int onStartCommand(Intent intentParam, int flagsParam,
			int startIdParam) {
		TimerTask tarefa = new TimerTask() {
			
			@Override
			public void run() {
				int aleatorio = getNumeroAleatorio(0,100);
				Log.d("Laboratorio11", "Numero Gerado:"+ aleatorio);
				
			}
		};
		Timer agendador = new Timer();
		// A cada 10 segundos, o proprio serviço irá agendar para executar
		agendador.schedule(tarefa, 10000);
		
		return 0;
		
	}

	protected int getNumeroAleatorio(int min, int max) {
		Random aleatorioGerado = new Random(System.currentTimeMillis());
		return aleatorioGerado.nextInt(max - min) + min;
	}
	
	class NumeroAleatorioBind extends Binder {
		
		public NumeroAleatorioSevice obtemServico() {
			return NumeroAleatorioSevice.this;
		}
	}

}
