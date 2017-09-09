package com.example.ahmet.kutuphanelerim.WebSocket;

import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by Ahmet on 08.09.2017.
 */

public class EchoWebSocketListener {
    private static final int Normal_Clouruse_Status = 1000;
    private OkHttpClient okHttpClient;
    private WebSocket webSocket;
    public static  int Status ;
    private String wsUri,MessageRead;

    private  class EchWebSocketListener extends WebSocketListener{
        @Override
        public void onOpen(okhttp3.WebSocket webSocket, Response response) {
            Log.e("WebSocket","onopen");
            Status = 1;
        }
        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
        }
        @Override
        public void onMessage(WebSocket webSocket, String text) {
            Status = 2;
            MessageRead = text;
            // Listen || Static Func Add
        }
        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(Normal_Clouruse_Status,null);
            Status = 3;
            Log.e("WebSocket","OnClose");
            Dinle();
        }
        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            Log.e("OnError",t.toString());
            Status=0;
            Dinle();
        }
    }


    private void Dinle(){
        Request request = new Request.Builder().url(wsUri).build();
        EchWebSocketListener listener = new EchWebSocketListener();
        webSocket = okHttpClient.newWebSocket(request,listener);

    }
    public void ShutdownWebSocket(){
        okHttpClient.dispatcher().executorService().shutdown();
    }
    public void SendWebSocket(String textSend){
        webSocket.send(textSend);
    }
    public EchoWebSocketListener(String wsUri){
        okHttpClient = new OkHttpClient();
        this.wsUri = wsUri;
    }
}
