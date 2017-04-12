package br.com.codepampa.alarmes.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import br.com.codepampa.alarmes.R;
import br.com.codepampa.alarmes.util.NotificationUtil;



public class AlarmeReceiver extends BroadcastReceiver {

    public static final String INTERCEPTOU_A_INTENT = "Interceptou a intent com a ação: ";
    private final String TAG = "Alarme demo";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, INTERCEPTOU_A_INTENT + intent.getAction());
        NotificationUtil.notify(
                context,
                1,
                intent,
                context.getResources().getString(R.string.title_alarme),
                context.getResources().getString(R.string.title_alarme),
                context.getResources().getString(R.string.title_alarme));
    }
}
