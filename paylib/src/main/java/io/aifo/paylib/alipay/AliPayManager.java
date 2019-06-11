package io.aifo.paylib.alipay;

import com.alipay.sdk.app.PayTask;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.aifo.paylib.PayOnRespListener;
import io.aifo.paylib.PayReturnStatus;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Created by small small su
 * Date: 2019-05-23
 * Email: surao@foryou56.com
 */
public class AliPayManager {

    private PayOnRespListener onRespListener;


    private PayTask alipay;

    public AliPayManager() {
    }

    public void registerOnRespListener(PayOnRespListener onRespListener) {
        this.onRespListener = onRespListener;
    }


    public void pay(String info, final RxAppCompatActivity activity) {
        Observable.just(info).flatMap(new Function<String, Observable<PayReturnStatus>>() {
            @Override
            public Observable<PayReturnStatus> apply(String info) throws Exception {
                alipay = new PayTask(activity);
                AliPayResultCode payResult = new AliPayResultCode(alipay.payV2(info, false));
                PayReturnStatus status = PayReturnStatus.getStatusCode(Integer.valueOf(payResult.getResultStatus()), payResult.getMemo());
                return Observable.just(status);
            }
        })
                .compose(activity.<PayReturnStatus>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AliPayObserver<PayReturnStatus>() {
                    @Override
                    public void onSuccess(PayReturnStatus aliPayResultCode) {
                        onRespListener.onSucce(PayOnRespListener.TYPE_ALIPAY, aliPayResultCode.msg);
                    }

                    @Override
                    public void onFailure(String desc) {
                        onRespListener.onFail(desc);
                    }

                    @Override
                    public void onReleaseAliPay() {
                        alipay = null;
                    }
                });
    }

    public void unRegisterListener() {
        onRespListener = null;
    }
}
