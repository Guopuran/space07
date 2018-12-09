package guopuran.bwie.com.space07.model;

import guopuran.bwie.com.space07.callback.MyCallBack;

public interface Imodel {
    void requestmodel(String url, String params, Class clazz, MyCallBack callBack);
}
