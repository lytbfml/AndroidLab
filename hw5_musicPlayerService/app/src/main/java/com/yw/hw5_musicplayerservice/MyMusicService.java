package com.yw.hw5_musicplayerservice;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyMusicService extends IntentService {
    private static final String TAG = "MyMusicService";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.yw.hw5_musicplayerservice.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.yw.hw5_musicplayerservice.extra.PARAM2";

    //media player
    private MediaPlayer player;

    public MyMusicService() {
        super("MyMusicService");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
            Log.e(TAG, "Received Start Foreground");
            Intent notificationIntent = new Intent(this, MainActivity.class);
            notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
            notificationIntent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    notificationIntent, 0);

            Bitmap icon = BitmapFactory.decodeResource(getResources(),
                    R.mipmap.ic_launcher);

            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle("ringtone")
                    .setTicker("Music Player")
                    .setContentText("My Music")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(
                            Bitmap.createScaledBitmap(icon, 128, 128, false))
                    .setContentIntent(pendingIntent)
                    .setOngoing(true)
                    .build();

            startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE,
                    notification);

            player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
            player.setLooping(true);
            player.start();

        } else if (intent.getAction().equals(Constants.ACTION.STOPFOREGROUND_ACTION)) {
            Log.i(TAG, "Received Stop Foreground Intent");
            player.stop();
            stopForeground(true);
            stopSelf();
        } else if (intent.getAction().equals(Constants.ACTION.PAUSE_ACTION)){
            player.pause();
        }
        return START_STICKY;
    }


    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionStart(Context context, String[] param1, String param2) {
        Intent intent = new Intent(context, MyMusicService.class);
        intent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     *
     * @see IntentService
     */
    public static void startActionStop(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyMusicService.class);
        intent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public static void pauseSong(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyMusicService.class);
        intent.setAction(Constants.ACTION.PAUSE_ACTION);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (Constants.ACTION.STARTFOREGROUND_ACTION.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (Constants.ACTION.STOPFOREGROUND_ACTION.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
