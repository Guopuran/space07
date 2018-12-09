package guopuran.bwie.com.space07.presenter;

import guopuran.bwie.com.space07.callback.MyCallBack;
import guopuran.bwie.com.space07.model.Imodel;
import guopuran.bwie.com.space07.model.ImodelImpl;
import guopuran.bwie.com.space07.view.IView;

public class IpresenterImpl implements Ipresenter {

    private ImodelImpl mImodelImpl;
    private IView mIView;

    public IpresenterImpl(IView mIView) {
        this.mIView = mIView;
        //实例化
        mImodelImpl=new ImodelImpl();
    }

    @Override
    public void requestpre(String url, String params, Class clazz) {
        mImodelImpl.requestmodel(url, params, clazz, new MyCallBack() {
            @Override
            public void getdata(Object object) {
                mIView.getdata(object);
            }
        });
    }
    //解绑
    public void deatch(){
        if (mImodelImpl!=null){
            mImodelImpl=null;
        }
        if (mIView!=null){
            mIView=null;
        }
    }
}
