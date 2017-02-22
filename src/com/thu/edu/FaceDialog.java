package com.thu.edu;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class FaceDialog extends Dialog{
	
	Context context;

	public FaceDialog(Context context) {
		super(context);
		this.context = context;
	}
	
	public FaceDialog(Context context, int theme) {  
        super(context, theme);  
    } 
	
	public FaceDialog create(){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final FaceDialog dialog = new FaceDialog(context, R.style.selectorDialog);
		View layout = inflater.inflate(R.layout.bigface, null);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		dialog.addContentView(layout, layoutParams); 
		
		return dialog;
	}

	
}
