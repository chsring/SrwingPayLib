package io.aifo.paylib;

import android.content.Context;
import android.text.TextUtils;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.ref.WeakReference;

import io.aifo.paylib.alipay.AliPayManager;
import io.aifo.paylib.wechat.WeChatManager;

/**
 * Description:
 * Created by small small su
 * Date: 2019-05-22
 * Email: surao@foryou56.com
 */
public class PayManager {

    private static PayManager instance;

    private AliPayManager aliPayManager;
    private WeChatManager weChatManager;
    private PayOnRespListener onRespListener;
    private WeakReference<Context> mContext;
    private String weChatId;
    private boolean hasAliPay = false;

    public static PayManager getInstance() {
        if (instance == null)
            instance = new PayManager();
        return instance;
    }


    //初始化微信，在application中调用
    public PayManager initWeChat(WeakReference<Context> context, String id) {
        mContext = context;
        weChatId = id;
        return instance;
    }

    //初始化支付宝，在application中调用
    public PayManager initAliPay() {
        hasAliPay = true;
        return instance;
    }

    public void build() {
        weChatManager = new WeChatManager(mContext.get(), weChatId);
        weChatManager.registerWeChat();
        if (hasAliPay)
            aliPayManager = new AliPayManager();
    }

    //微信支付
    public void payWeChat(PayEntity payEntity) {
        if (weChatManager == null) {
            weChatManager = new WeChatManager(mContext.get(), weChatId);
            weChatManager.registerWeChat();
        }
        weChatManager.pay(payEntity);
    }

    //支付宝支付
    public void payAliPay(PayEntity payEntity, RxAppCompatActivity activity) {
        if (!TextUtils.isEmpty(payEntity.appData)) {
            if (aliPayManager == null) {
                aliPayManager = new AliPayManager();
            }
            aliPayManager.pay(payEntity.appData, activity);
        }
    }

    //注册结果的回调，在调起微信时候调用
    public void reResp(PayOnRespListener listener) {
        this.onRespListener = listener;
        if (weChatManager != null) {
            weChatManager.registerOnRespListener(onRespListener);
        }

        if (aliPayManager != null) {
            aliPayManager.registerOnRespListener(onRespListener);
        }
    }

    public void unResResp() {
        if (aliPayManager != null)
            aliPayManager.unRegisterListener();
        if (weChatManager != null)
            weChatManager.unRegisterListener();
        onRespListener = null;
    }


    public IWXAPI getApi() {
        return weChatManager.getApi();
    }

    public PayOnRespListener getOnRespListener() {
        return onRespListener;
    }
}
