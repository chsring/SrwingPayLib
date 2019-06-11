package io.aifo.paylib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

public class MainActivity extends AppCompatActivity implements PayOnRespListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册微信回调
        PayManager.getInstance().reResp(this);

    }


    /**
    //请求服务器返回的微信 或者 支付宝的参数
    @Override
    public void onLinePaymentSuccess(BaseDataEntity<PayEntity> task) {
        if (task != null && task.get0() != null) {
            PayEntity payEntity = task.get0();
            switch (payType) {
                case 26: //微信
                    PayManager.getInstance().payWeChat(payEntity);
                    break;
                case 25://支付宝
                    PayManager.getInstance().payAliPay(payEntity, (RxAppCompatActivity) _mActivity);
                    break;
                default:
            }
        }
    }

    **/
    //支付成功的回调
    @Override
    public void onSucce(int type, String errStr) {
        // TODO: 2019-06-11 自己的操作
        if (type == PayOnRespListener.TYPE_WECHAT) {
//            start(PayResultFragment.newPayInstance("微信支付", "￥" + payMoney, PayResultFragment.RESULT_TYPE_CHARGE));
        } else if (type == PayOnRespListener.TYPE_ALIPAY) {
//            start(PayResultFragment.newPayInstance("支付宝支付", "￥" + payMoney, PayResultFragment.RESULT_TYPE_CHARGE));
        }
    }

    //支付失败的回调
    @Override
    public void onFail(String errStr) {
        // TODO: 2019-06-11
//        if (!TextUtils.isEmpty(errStr))
//            ToastUtils.showShort(errStr);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //销毁payManager 否则会内存泄露
        PayManager.getInstance().unResResp();
    }
}
