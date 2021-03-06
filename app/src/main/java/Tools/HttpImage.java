package Tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.renderscript.ScriptGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ZFW on 2018/3/2.
 */

public class HttpImage extends Thread {
    private ImageView imageView;
    private String url;
    private Handler handler;
    public HttpImage(String url,Handler handler,ImageView imageView){
        this.handler=handler;
        this.url=url;
        this.imageView=imageView;
    }
    @Override
    public void run(){
        try {
            URL httpUrl=new URL(url);
            HttpURLConnection conn=(HttpURLConnection)httpUrl.openConnection();
            conn.setReadTimeout(4000);
            conn.setRequestMethod("GET");
            InputStream in=conn.getInputStream();
            final Bitmap bitmap= BitmapFactory.decodeStream(in);
            handler.post(new Runnable() {
                @Override
                public void run() {
                   imageView.setImageBitmap(bitmap);
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
