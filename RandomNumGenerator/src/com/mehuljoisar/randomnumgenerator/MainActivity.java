package com.mehuljoisar.randomnumgenerator;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static final String TAG = "MainActivity";
	
	private Context mContext;
	
	private EditText etStart,etEnd;
	private Button btnGenerateNums;
	private TextView tvResultInTextView,tvGeneratedNums,tvResultInListView;
	private ListView lvGeneratedNums;
	private ArrayList<Integer> mList;
	private ArrayAdapter<Integer> mAdapter;
	private int start,end;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initialize();
	}

	private void initialize() {
		
		mContext = this;
		
		etStart = (EditText)findViewById(R.id.etStart);
		etEnd = (EditText)findViewById(R.id.etEnd);
		btnGenerateNums =(Button)findViewById(R.id.btnGenerateNums);
		tvResultInTextView = (TextView)findViewById(R.id.tvResultInTextView);
		tvGeneratedNums = (TextView)findViewById(R.id.tvGeneratedNums);
		tvResultInListView = (TextView)findViewById(R.id.tvResultInListView);
		lvGeneratedNums = (ListView)findViewById(R.id.lvGeneratedNums);
		
		start = end = -1;
		mList = new ArrayList<Integer>();
		
		btnGenerateNums.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				tvGeneratedNums.setText("");
				tvResultInTextView.setVisibility(View.GONE);
				tvResultInListView.setVisibility(View.GONE);
				mList.clear();
				if(isValid())
				{
					tvResultInTextView.setVisibility(View.VISIBLE);
					tvResultInListView.setVisibility(View.VISIBLE);
					RandomNumGenerator mRandomNumGen = new RandomNumGenerator(start,end);
					for(int i=0;i<=end-start;i++)
					{
						tvGeneratedNums.setText(tvGeneratedNums.getText().toString()+mRandomNumGen.generateNewRandom(i)+",");
						mList.add(mRandomNumGen.generateNewRandom(i));

					}
					if(tvGeneratedNums.getText().toString().endsWith(","))
					{
						tvGeneratedNums.setText(tvGeneratedNums.getText().toString().substring(0, tvGeneratedNums.getText().toString().length()-1));
					}
					
				}
				mAdapter = new ArrayAdapter<Integer>(mContext, android.R.layout.simple_list_item_1, mList);
				lvGeneratedNums.setAdapter(mAdapter);

					
				}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	private boolean isValid()
	{
		
		if(etStart.getText().toString().equals(""))
		{
			Toast.makeText(mContext,"Enter valid staring range", Toast.LENGTH_SHORT).show();
			return false;
		}
		else
		{
			start = Integer.valueOf(etStart.getText().toString());
				
		}
		if(etEnd.getText().toString().equals(""))
		{
			Toast.makeText(mContext,"Enter valid ending range", Toast.LENGTH_SHORT).show();
			return false;
		}
		else
		{
			end = Integer.valueOf(etEnd.getText().toString());
		}
		if(end<=start)
		{
			Toast.makeText(mContext,"Ending range should be greater than starting range", Toast.LENGTH_SHORT).show();
			return false;
			
		}
		
		return true;
	}

}
