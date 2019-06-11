package io.aifo.paylib;

import android.app.Application;

import java.lang.ref.WeakReference;

/**
 * Description:
 * Created by small small su
 * Date: 2019-06-11
 * Email: surao@foryou56.com
 */
public class App extends Application {
    //自己的微信id
    private static final String WECHAT_ID = "ae1x23sdf2345sdf";

    @Override
    public void onCreate() {
        super.onCreate();

        //注册支付
        PayManager.getInstance().initWeChat(new WeakReference<>(getApplicationContext()), WECHAT_ID).initAliPay().build();
    }
}
