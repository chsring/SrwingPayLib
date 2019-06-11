package io.aifo.paylib.payhelper.templates;

import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;

import io.aifo.paylib.PayManager;
import io.aifo.paylib.PayOnRespListener;
import io.aifo.paylib.payhelper.base.BaseWXPayEntryActivity;


public class WXPayEntryTemplate extends BaseWXPayEntryActivity {

    @Override
    protected void onPaySuccess() {
        if (PayManager.getInstance().getOnRespListener() != null) {
            //  0	成功	展示成功页面
            //  -1	错误	可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
            //  -2	用户取消	无需处理。发生场景：用户不支付了，点击取消，返回APP。
            PayManager.getInstance().getOnRespListener().onSucce(PayOnRespListener.TYPE_WECHAT, "支付成功");
        }
        Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onPayFail(String errStr) {

        if (PayManager.getInstance().getOnRespListener() != null) {
            PayManager.getInstance().getOnRespListener().onFail(errStr);
        }
        Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onPayCancel() {
        Toast.makeText(this, "支付取消", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }
}
