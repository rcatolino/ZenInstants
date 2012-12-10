package com.majomi.zeninstants;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.PorterDuff;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MessageImageActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_image); 
        MessageButtonHandler btnHandler = new MessageButtonHandler(this);
    }    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_message_image, menu);
        return true;
    }
    
    
}
