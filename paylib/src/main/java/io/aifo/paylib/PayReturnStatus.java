package io.aifo.paylib;

import android.text.TextUtils;

import io.aifo.paylib.alipay.AliPayResultCode;
import io.aifo.paylib.wechat.WeChatResultCode;


/**
 * Description:
 * Created by small small su
 * Date: 2019-05-25
 * Email: surao@foryou56.com
 */
public class PayReturnStatus {

    public static final int SUCC = 10000;
    public static final int FAIL = 10001;

    public int code;
    public String msg;

    public PayReturnStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static PayReturnStatus getStatusCode(int type, String desc) {
        if (type == WeChatResultCode.PAY_SUCCESS ||
                type == AliPayResultCode.PAY_SUCCESS
        )
            return new PayReturnStatus(SUCC, "充值成功");
        else {
            //微信 desc 返回有可能为空
            //  -1	错误	可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
            //  -2	用户取消	无需处理。发生场景：用户不支付了，点击取消，返回APP
            if (TextUtils.isEmpty(desc)) {
                if (type == -1)
                    return new PayReturnStatus(FAIL, "签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配");
                else
                    return new PayReturnStatus(FAIL, "用户取消");
            } else
                return new PayReturnStatus(FAIL, desc);
        }
    }

    public static PayReturnStatus getStatus() {
        return new PayReturnStatus(FAIL, "异常");
    }
}
