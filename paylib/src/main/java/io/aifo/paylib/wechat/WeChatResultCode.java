package io.aifo.paylib.wechat;

/**
 * Created by dubin on 2017/6/29.
 */

public class WeChatResultCode {
    public static final int PAY_SUCCESS = 0;
    public static final int PAY_FAIL = -1;
    public static final int PAY_CANCEL = -2;
//0	成功	展示成功页面
//-1	错误	可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
//-2	用户取消	无需处理。发生场景：用户不支付了，点击取消，返回APP。
}
