package io.aifo.paylib.alipay;

import android.text.TextUtils;

import java.util.Map;

/**
 * Description:
 * Created by small small su
 * Date: 2019-05-23
 * Email: surao@foryou56.com
 */
public class AliPayResultCode {
    //9000	订单支付成功
    //8000	正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
    //4000	订单支付失败
    //5000	重复请求
    //6001	用户中途取消
    //6002	网络连接出错
    //6004	支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
    //其它	其它支付错误

    public static final int PAY_SUCCESS = 9000;
    public static final int PAY_FAIL_8000 = 8000;
    public static final int PAY_FAIL_4000 = 4000;
    public static final int PAY_FAIL_5000 = 5000;
    public static final int PAY_FAIL_6001 = 6001;
    public static final int PAY_FAIL_6002 = 6002;
    public static final int PAY_FAIL_6004 = 6004;

    private String resultStatus;
    private String result;
    private String memo;

    public AliPayResultCode(Map<String, String> rawResult) {
        if (rawResult == null) {
            return;
        }

        for (String key : rawResult.keySet()) {
            if (TextUtils.equals(key, "resultStatus")) {
                resultStatus = rawResult.get(key);
            } else if (TextUtils.equals(key, "result")) {
                result = rawResult.get(key);
            } else if (TextUtils.equals(key, "memo")) {
                memo = rawResult.get(key);
            }
        }
    }

    @Override
    public String toString() {
        return "resultStatus={" + resultStatus + "};memo={" + memo
                + "};result={" + result + "}";
    }

    /**
     * @return the resultStatus
     */
    public String getResultStatus() {
        return resultStatus;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }
}
