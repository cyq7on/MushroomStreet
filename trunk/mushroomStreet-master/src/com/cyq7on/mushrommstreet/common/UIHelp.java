package com.cyq7on.mushrommstreet.common;

import com.cyq7on.mushrommstreet.activity.MessageActivity;

import android.content.Context;
import android.content.Intent;

public class UIHelp {
	
	public static void startMessageActivity(Context context){
		Intent intent = new Intent(context,MessageActivity.class);
		context.startActivity(intent);
	}
	
}
