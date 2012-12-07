package com.majomi.zeninstants.messagesentities;

import com.majomi.zeninstants.R;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;


public class MessageTextEntity {
	protected long id;
	private String text;
	private String summary; // Text shown in historical
	
	
	public MessageTextEntity() {
		this.text = new String();
		this.summary = new String();
	}
	
	public MessageTextEntity(String text) {
		super();
		this.text = text;
		if(text.length()> 40)
			this.summary = text.substring(0, 40);
		else
			this.summary = text;
	}
	
	public MessageTextEntity(String text, String summary) {
		super();
		this.text = text;
		this.summary = summary;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public int getLayout(){return -1;}
	
/*	
	public void setHistorialContent(Activity act, View vi)
	{
		TextView summarytext = (TextView) vi.findViewById(R.id.hmtext);
		summarytext.setText(getSummary());
	}
*/	
}