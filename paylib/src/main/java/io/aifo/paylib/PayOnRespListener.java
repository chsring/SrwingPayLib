package io.aifo.paylib;

/**
 * Description:
 * Created by small small su
 * Date: 2019-05-23
 * Email: surao@foryou56.com
 */
public interface PayOnRespListener {

    public static int TYPE_ALIPAY = 1;
    public static int TYPE_WECHAT = 2;

    //wechat
    //  0	成功	展示成功页面
    //  -1	错误	可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
    //  -2	用户取消	无需处理。发生场景：用户不支付了，点击取消，返回APP。

    //支付宝
    //9000	订单支付成功
    //8000	正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
    //4000	订单支付失败
    //5000	重复请求
    //6001	用户中途取消
    //6002	网络连接出错
    //6004	支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
    //其它	其它支付错误

    void onSucce(int type, String errStr);

    void onFail(String errStr);

}
