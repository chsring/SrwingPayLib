package io.aifo.paylib;

/**
 * Description:
 * Created by small small su
 * Date: 2019-05-23
 * Email: surao@foryou56.com
 */
public class PayEntity {

    /**
     * package : Sign=WXPay
     * appid : 1491229252
     * sign : 22482CBD3AA05D039E73ADD49F834F8E
     * partnerid : 1491229252
     * noncestr : 151132214777031
     * prepayid : wx2017112211422720c4adb62a0647512314
     * timestamp : 1511323026
     *
     * "appData": "alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017102409498628&biz_content=%7B%22out_trade_no%22%3A%2220000129309_35%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%8D%95%E5%8F%B7%EF%BC%9A20000129309%E7%AD%89%E8%BF%90%E8%B4%B9%22%2C%22timeout_express%22%3A%22119m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2Fd2pay.fuyoukache.com%2Fapi%2Fpayment%2Fcallback%2FalipayCallBack.do&sign=ctfi0tMld4fJmuYwIDyF57hPllSD2TAUhSR6zqFJUj5uC3WCfFLM8bwX3t0fc1CwpHxxCk0ZyCI0hRSnBSyiZ6fWfGwSZQyCILSpjBPDNoSXIPZ61GFJ1%2FLJO%2BMA5pgbnY1f1FEn8%2FeVgatDQDICtCEdSxVXg5q71pmPovYPR34s1nzKZLdnRIi3%2B0RepZGUKEfgVto%2FnMkm%2FycDUDx%2FhL46PJKTBySPPXb28AqQvfDyfTgtAMwZ%2BrZOi5uUS%2FuWLwTIs5AldUF5j79rGe7KI4EV1oNRXgoaChi7PAilupssBYi9XAWm0ne3qVrO5oMMbDtk4IF2Hs1VKa1sJfRaSw%3D%3D&sign_type=RSA2×tamp=2017-11-23+11%3A51%3A51&version=1.0"
     */

    //微信
    public String packageValue;
    public String appid;
    public String sign;
    public String partnerid;
    public String noncestr;
    public String prepayid;
    public String timestamp;

    //支付宝
    public String appData;

}
