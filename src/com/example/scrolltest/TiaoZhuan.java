package com.example.scrolltest;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SeekBar;

public class TiaoZhuan extends Dialog {

	ScrollView scroll;
	RadioGroup radioGroup;
	RadioButton bu1;
	RadioButton bu2;
	RadioButton bu3;
	Context context;
	Button button;
	public Button button2;
	SeekBar seek;
	int index = 0;

	public TiaoZhuan(Context context, ScrollView scroll) {
		super(context);
		// TODO Auto-generated constructor stub
		this.scroll = scroll;
		this.context = context;
	}

	public TiaoZhuan(Context context, int theme) {
		super(context, theme);
		this.context = context;

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.crip);
		this.setTitle("Ìø×ªÉèÖÃ");
		Button button1 = (Button) this.findViewById(R.id.back);
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TiaoZhuan.this.dismiss();
			}

		});
		seek = (SeekBar) this.findViewById(R.id.progress_horizontal);
		button2 = (Button) this.findViewById(R.id.trun_s);
		button2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (index == 1) {
					scroll.scrollTo(0, 0);
				} else if (index == 2) {
					scroll.scrollTo(0, scroll.getHeight() / 2);
				} else if (index == 3) {
					scroll.scrollTo(0, scroll.getHeight());
				} else if (index == 0) {
					scroll.scrollTo(0, seek.getProgress() * scroll.getHeight()
							/ seek.getMax());
				}
				TiaoZhuan.this.dismiss();
			}

		});

		bu1 = (RadioButton) this.findViewById(R.id.button1);
		bu2 = (RadioButton) this.findViewById(R.id.button2);
		bu3 = (RadioButton) this.findViewById(R.id.button3);
		radioGroup = (RadioGroup) this.findViewById(R.id.group);
		radioGroup
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub
						if (checkedId == bu1.getId()) {
							index = 1;

						} else if (checkedId == bu2.getId()) {
							index = 2;

						} else if (checkedId == bu3.getId()) {
							index = 3;

						}
					}

				});
	}

}