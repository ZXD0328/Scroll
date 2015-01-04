package com.example.scrolltest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ScrollView;

public class ZiDong extends Activity {

	private boolean isturn = false;
	ScrollView scroll;
	Dialog dialog;
	int cr = 0;
	Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		scroll = (ScrollView) this.findViewById(R.id.scroll);
		cr = scroll.getScrollY();
		init();
	}

	private void init() {
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				scroll.scrollTo(0, msg.arg1);
				cr = scroll.getScrollY();
				// 将要执行的线程放入到队列当中
				super.handleMessage(msg);
			}

		};
		// handler.post(updateThread);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.crip:
			dialog = new TiaoZhuan(ZiDong.this, scroll);
			dialog.show();

			break;
		case R.id.turn:

			if (!isturn) {
				item.setTitle("停止滚屏");
				cr = scroll.getScrollY();
				handler.post(updateThread);
				isturn = true;

			} else {
				item.setTitle("自动滚屏");
				isturn = false;
				handler.removeCallbacks(updateThread);
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	Runnable updateThread = new Runnable() {
		int i = cr;

		public void run() {
			// TODO Auto-generated method stub
			i += 10;
			// 得到一个消息对象，Message类是android系统提供的
			Message msg = handler.obtainMessage();
			// 将Message对象的arg1参数的值设置为i
			msg.arg1 = i; // 用arg1、arg2这两个成员变量传递消息，优点是系统性能消耗较少
			try {
				Thread.sleep(100); // 让当前线程休眠100毫秒
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			// 将Message对象加入到消息队列当中
			handler.sendMessage(msg);
			cr = scroll.getScrollY();
			int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
			if (cr == (scroll.getHeight()-screenHeight)) {
				handler.removeCallbacks(updateThread);
			} else {
				handler.post(updateThread);
			}
		}
	};
}
