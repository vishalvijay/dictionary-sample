package com.example.dictionarysample;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

public class MainActivity extends ListActivity implements TextWatcher {
	private EditText searchEditText;
	private ArrayAdapter<String> mAdapter;
	private String currentWord = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		searchEditText = (EditText) findViewById(R.id.etSearch);
		searchEditText.addTextChangedListener(this);
		mAdapter = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_list_item_1);
		setListAdapter(mAdapter);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

	@Override
	public void afterTextChanged(Editable s) {
		currentWord = s.toString();
		searchWord(currentWord);
	}

	private void searchWord(String word) {
		Api.findMeaning(word, new JsonHttpResponseHandler() {
			@Override
			public void onStart() {
				super.onStart();
				mAdapter.clear();
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONArray response) {
				super.onSuccess(statusCode, headers, response);
				ArrayList<String> meanings = new ArrayList<String>();
				for (int i = 0; i < response.length(); i++) {
					try {
						if (response.getJSONObject(i).getString("word")
								.equalsIgnoreCase(currentWord)) {
							meanings.add(response.getJSONObject(i).getString(
									"text"));
						} else {
							return;
						}
					} catch (JSONException e) {
					}
				}
				mAdapter.addAll(meanings);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
				Toast.makeText(getApplicationContext(), "Connection Failed",
						Toast.LENGTH_SHORT).show();
			}

		});
	}

}
