package com.example.testcall;

import android.app.Activity;
import android.content.ClipData.Item;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText edittext1, edittext2;
	private Button mCallButton, playButton;
	
	private static boolean isExit = false;

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Log.d("lifecycle", "onCreate invoked");
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
        edittext1 = (EditText)findViewById(R.id.EditText1);
        edittext2 = (EditText)findViewById(R.id.EditText2);
		mCallButton = (Button)findViewById(R.id.btn_call);
		//Toast.makeText(getApplicationContext(), "谁是大英雄", Toast.LENGTH_LONG).show();
		mCallButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				int sum = 0;
//				if((TextUtils.isEmpty(edittext1.getText())||(TextUtils.isEmpty(edittext2.getText())))){
//					Toast.makeText(getApplicationContext(), "不完整输入", Toast.LENGTH_SHORT).show();
//				}else{
//					String value1 = edittext1.getText().toString();
//					String value2 = edittext2.getText().toString();
//					int a = Integer.parseInt(value1);
//					int b = Integer.parseInt(value2);
//					sum = a+b;
//					Toast.makeText(getApplicationContext(), String.valueOf(sum), Toast.LENGTH_SHORT).show();
//				}
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				startActivityForResult(intent, 2);
			}
		});
		playButton =(Button)findViewById(R.id.playMuic);
		playButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, PlayService.class);
				startService(intent);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==2){
			String msg = data.getStringExtra("MESSAGE");
			edittext1.setText(msg);
			
		}
	}

	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
