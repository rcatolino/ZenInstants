package com.majomi.zeninstants;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MessagesService extends Service {
		
	@Override
	public void onCreate() {
		super.onCreate();
		
		//Create some stuff here
	}

	@Override
	public void onDestroy() {
		
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		AppLog.logString("Service: Created");
		Thread t = new Thread(){
			public void run(){
				startSendingMessages();
			}
		};
		t.start();
		return START_STICKY;
	}
	
	public void startSendingMessages(){
		for(int i = 0; i < 3; i++){
			try {
				synchronized (this){
					wait(10000);
				}
				Intent dialogIntent = new Intent(getBaseContext(), MessageActivity.class);
				dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				AppLog.logString("Service: Start activity");
				getApplication().startActivity(dialogIntent);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
		
		
}