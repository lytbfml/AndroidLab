package com.hw3.geoquiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yangxiao on 10/16/2017.
 */

public class Questions {
	
	private static final String questList[] = {
			"Capital of UK is ",
			"Capital of USA is ",
			"Capital of France is ",
			"Capital of Italy is ",
			"Capital of Japan is ",
			"Capital of Germany is ",
			"Capital of China is "};
	
	private static final String choiceList[][] = {
			{"London", "Cambridge", "Manchester", "Oxford"},
			{"New York", "Chicago", "Washington DC", "San francisco"},
			{"Lyon", "Paris", "Saint-Paul", "Reims"},
			{"Milan", "Florence", "Venice", "Naples", "Rome"},
			{"Nagoya", "Osaka", "Saitama", "Tokyo", "Kyoto"},
			{"Munich", "Berlin", "Cologne", "Frankfurt am Main", "Stuttgart"},
			{"Beijing", "Chongqing", "Shanghai", "Hongkong", "Guangzhou"}
	};
	
	private static final int ansList[] = {0, 2, 1, 4, 3, 1, 0};
	
	/**
	 * An array of sample items.
	 */
	public static final List<Question> ITEMS = new ArrayList<Question>();
	
	/**
	 * A map of sample items, by ID.
	 */
	public static final Map<String, Question> ITEM_MAP = new HashMap<String, Question>();
	
	private static final int COUNT = 7;
	
	static {
		// Add some sample items.
		for (int i = 0; i < COUNT; i++) {
			addItem(createQuestions(i));
		}
	}
	
	private static void addItem(Question item) {
		ITEMS.add(item);
		ITEM_MAP.put(String.valueOf(item.id), item);
	}
	
	private static Question createQuestions(int position) {
		return new Question(position, questList[position], choiceList[position], ansList[position]);
	}
	
	public static class Question {
		public final int id;
		public final String content;
		public final String[] choices;
		public final int ans;
		
		public Question(int id, String content, String[] details, int ans) {
			this.id = id;
			this.content = content;
			this.choices = details;
			this.ans = ans;
		}
		
		@Override
		public String toString() {
			return content;
		}
	}
}
