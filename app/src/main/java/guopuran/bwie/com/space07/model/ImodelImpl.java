package guopuran.bwie.com.space07.model;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.google.gson.Gson;

import guopuran.bwie.com.space07.callback.MyCallBack;
import guopuran.bwie.com.space07.presenter.Ipresenter;
import guopuran.bwie.com.space07.util.NetUtil;

public class ImodelImpl implements Imodel {


    @SuppressLint("StaticFieldLeak")
    @Override
    public void requestmodel(String url, String params, final Class clazz, final MyCallBack callBack) {
        new AsyncTask<String, Void, Object>() {
            @Override
            protected Object doInBackground(String... strings) {
                return new Gson().fromJson(NetUtil.geturl(strings[0]),clazz);
            }

            @Override
            protected void onPostExecute(Object object) {
                callBack.getdata(object);
            }
        }.execute(url);
    }
}
