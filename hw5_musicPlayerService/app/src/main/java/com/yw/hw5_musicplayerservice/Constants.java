package com.yw.hw5_musicplayerservice;

/**
 * @author lytbf on 11/27/2017.
 */

public class Constants {
	
	public interface ACTION {
		public static String MAIN_ACTION = "com.yw.hw5_musicplayerservice.action.main";
		public static String PREV_ACTION = "com.yw.hw5_musicplayerservice.action.prev";
		public static String PLAY_ACTION = "com.yw.hw5_musicplayerservice.action.play";
		public static String NEXT_ACTION = "com.yw.hw5_musicplayerservice.action.next";
		public static String STARTFOREGROUND_ACTION = "com.yw.hw5_musicplayerservice.action.startforeground";
		public static String STOPFOREGROUND_ACTION = "com.yw.hw5_musicplayerservice.action.stopforeground";
		public static String PAUSE_ACTION = "com.yw.hw5_musicplayerservice.action.pause";
		public static final String CONTINUE = "com.yw.hw5_musicplayerservice.action.cont";
	}
	
	public interface NOTIFICATION_ID {
		public static int FOREGROUND_SERVICE = 101;
	}
}
