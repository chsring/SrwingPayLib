package io.aifo.paylib.wechat;

import android.content.Context;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import io.aifo.paylib.PayEntity;
import io.aifo.paylib.PayOnRespListener;

/**
 * Created by fykc on 2017/6/15.
 */

public class WeChatManager {

    private PayOnRespListener onRespListener;
    private String weChatId;
    private Context context;

    public WeChatManager(Context context, String weChatId) {
        this.context = context;
        this.weChatId = weChatId;
    }

    private IWXAPI msgApi;


    public IWXAPI getApi() {
        if (msgApi == null) {
            msgApi = WXAPIFactory.createWXAPI(context, weChatId, false);
            msgApi.registerApp(weChatId);
        }
        return msgApi;
    }

    public void registerWeChat() {
        msgApi = WXAPIFactory.createWXAPI(context, weChatId, false);
        msgApi.registerApp(weChatId);
    }

    public void registerOnRespListener(PayOnRespListener listener) {
        onRespListener = listener;
    }


    public void pay(PayEntity payEntity) {
        if (msgApi == null)
            return;
        if (!msgApi.isWXAppInstalled()) {
            onRespListener.onFail("没有在手机中识别到微信，请先下载微信后再\n进行充值操作");
            return;
        }
        PayReq request = new PayReq();
        request.appId = payEntity.appid;
        request.partnerId = payEntity.partnerid;
        request.prepayId = payEntity.prepayid;
        request.packageValue = payEntity.packageValue;
        request.nonceStr = payEntity.noncestr;
        request.timeStamp = payEntity.timestamp;
        request.sign = payEntity.sign;
        boolean reqFlag = msgApi.sendReq(request);
        if (!reqFlag)
            onRespListener.onFail("微信调起失败，请稍后再试");
    }

    public void unRegisterListener(){
        onRespListener = null;
    }
}
