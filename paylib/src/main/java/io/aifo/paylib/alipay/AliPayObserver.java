package io.aifo.paylib.alipay;

import io.aifo.paylib.PayReturnStatus;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Created by small small su
 * Date: 2019-05-28
 * Email: surao@foryou56.com
 */
public abstract class AliPayObserver<T extends PayReturnStatus> implements Observer<T> {
    /**
     * 根据具体的Api 业务逻辑去重写 onSuccess 方法！
     * Error 是选择重写，but 必须Super ！
     */

    public abstract void onReleaseAliPay();

    public abstract void onSuccess(PayReturnStatus aliPayResultCode);

    public abstract void onFailure(String desc);

    private Disposable disposable;

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
    }

    @Override
    public void onNext(T payReturnStatus) {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
        if (payReturnStatus.code == PayReturnStatus.SUCC)
            onSuccess(payReturnStatus);
        else
            onFailure(payReturnStatus.msg);
        onReleaseAliPay();
    }

    @Override
    public void onError(Throwable e) {
        PayReturnStatus es = PayReturnStatus.getStatus();
        onFailure(es.msg);
        onReleaseAliPay();
    }

    @Override
    public void onComplete() {
    }
}
