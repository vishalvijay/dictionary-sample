package com.example.dictionarysample;

import com.loopj.android.http.AsyncHttpResponseHandler;

public class Api {
	public static void findMeaning(String word, AsyncHttpResponseHandler handler) {
		String url = "word.json/"
				+ word
				+ "/definitions?limit=200&includeRelated=true&useCanonical=false&includeTags=false";
		DictionaryRestClient.get(url, handler, null);
	}
}
