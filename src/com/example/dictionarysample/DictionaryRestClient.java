package com.example.dictionarysample;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class DictionaryRestClient {
	private static final String BASE_URL = "http://api.wordnik.com/v4/";

	private static AsyncHttpClient client = null;

	public static AsyncHttpClient getInstance() {
		if (client == null) {
			client = new AsyncHttpClient();
			client.addHeader("Content-Type", "application/json");
			client.addHeader("Accept", "application/json");
		}
		return client;
	}

	public static void get(String url,
			AsyncHttpResponseHandler responseHandler, RequestParams params) {
		if (params == null)
			params = new RequestParams();
		params.add("api_key",
				"a2a73e7b926c924fad7001ca3111acd55af2ffabf50eb4ae5");
		getInstance().get(getAbsoluteUrl(url), params, responseHandler);
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}
}
