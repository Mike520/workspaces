package com.bin.clipdrawable;


import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView imageView=(ImageView) findViewById(R.id.image);
		//��ȡͼƬ��ʾ��ClipDrawable����
		final ClipDrawable drawable=(ClipDrawable) imageView.getDrawable();
		final Handler handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				
				if(0==msg.what){
					//�޸�ClipDrawable��levelֵ
					drawable.setLevel(drawable.getLevel()+200);
				}
			}
		};
		
		final Timer timer=new Timer();
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
					Message msg=new Message();
					msg.what=0;
					handler.sendMessage(msg);//������Ϣ
					//ȡ����ʱ��
					if(drawable.getLevel()>=10000){
						timer.cancel();
					}
			}
			
		},0,300);
	}

}