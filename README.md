Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.chsring:SrwingPayLib:1.0.0'
	}
  
 代码中配置：
 app的gradle中添加依赖
  buildTypes {
        debug {
        	...
        }
    repositories {
        flatDir {
            dirs 'libs', '../paylibs/libs'
        }
    }
}
1. 在application中注册
//注册支付
PayManager.getInstance().initWeChat(new WeakReference<>(mContext), BuildConfig.WECHAT_ID).initAliPay().build();
2.在调用支付的页面注册支付的回调
PayManager.getInstance().reResp(this);

 //支付成功的回调
    @Override
    public void onSucce(int type, String errStr) {
        if (type == PayOnRespListener.TYPE_WECHAT) {
            
        } else if (type == PayOnRespListener.TYPE_ALIPAY) {
           
        }
    }

    //支付失败的回调
    @Override
    public void onFail(String errStr) {
    
    }
3. 支付
调用微信：PayManager.getInstance().payWeChat(payEntity);
支付宝支付： PayManager.getInstance().payAliPay(payEntity, (RxAppCompatActivity) _mActivity);
4.在onDestroy中释放（如果不释放，会造成内存泄露）
 PayManager.getInstance().unResResp();
