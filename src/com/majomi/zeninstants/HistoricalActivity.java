/**
 * 
 */
package com.majomi.zeninstants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.majomi.zeninstants.messagescontroller.Message_Manager;
import com.majomi.zeninstants.messagesviews.MessageImageView;
import com.majomi.zeninstants.messagesviews.MessageSoundView;
import com.majomi.zeninstants.messagesviews.MessageTextView;
import com.majomi.zeninstants.messagesviews.MessageVideoView;
import com.majomi.zeninstants.messagesviews.MessageView;


/**
 * 
 * Displays the historial.
 * For now, it's the initial activity
 *
 */
public class HistoricalActivity extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.historial_layout);
		ListView lv = (ListView) findViewById(R.id.historial_list);
		lv.setOnItemClickListener(mMessageClickedHandler);
	 	Historial_Adapter adapter = new Historial_Adapter(this);
	 	lv.setAdapter(adapter);
	}
	
	// Create a message handling witch manage the selection an item.
    private OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
		@SuppressWarnings("rawtypes")
		public void onItemClick(AdapterView parent, View v, int position, long id) {
			Intent myIntent;
			Message_Manager msgMgr = Message_Manager.getMessageManager();
			MessageView msg = msgMgr.getMessageView(position);
			
			if (msg instanceof MessageTextView ){
	        	myIntent = new Intent( getApplicationContext(), MessageTextActivity.class); 	
			}else if (msg instanceof MessageImageView ){
				myIntent = new Intent( getApplicationContext(), MessageImageActivity.class);
			}else if (msg instanceof MessageVideoView ){
				myIntent = new Intent( getApplicationContext(), MessageVideoActivity.class);
			}else if (msg instanceof MessageSoundView ){
				myIntent = new Intent( getApplicationContext(), MessageSoundActivity.class);
			}else{
				myIntent = new Intent( getApplicationContext(), MessageTextActivity.class);//Not working
			}
			
			myIntent.putExtra("MESSAGE_ID", (int)position);
            startActivityForResult(myIntent, 0);
        }
    };
}

//// Saved version of the MainActivity (useful for the services stuff). Will be deleted soon..
//
//package com.majomi.zeninstants;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.Menu;
//import android.widget.TextView;
//
//public class MainActivity extends Activity {
//
//	//public static final String KEY_121 = "http://xx.xx.xxx.xxx/hellomysql/mysqlcon.php"; //i use my real ip here
//	public static final String KEY_121 = "http://192.168.1.138/zenManagement/index.php"; //i use my real ip here
//	TextView txt;
//	
//  @Override
//  public void onCreate(Bundle savedInstanceState) {
//  	super.onCreate(savedInstanceState);
//  	
//      setContentView(R.layout.activity_main);
//      //Hi!! 
//      AppLog.logString("Main:Starting Service");
//      startService(new Intent(this,MessagesService.class));
//      AppLog.logString("Main:Service Started");
//  }
//
//  @Override
//  public boolean onCreateOptionsMenu(Menu menu) {
//      getMenuInflater().inflate(R.menu.activity_main, menu);
//      return true;
//  }
//  
//  public void startMsgService(){
//  	Thread t = new Thread(){
//  		public void run(){
//  		getApplicationContext().startService(new Intent(getApplicationContext(),MessagesService.class));
//
//  		}
//  		};
//  		t.start();
//  }
//  
//}



