package guopuran.bwie.com.space07.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;
import guopuran.bwie.com.space07.R;
import guopuran.bwie.com.space07.activity.HomeActivity;
import guopuran.bwie.com.space07.activity.QRCActivity;

public class FragmentMe extends Fragment {

    private Button me_button;
    private ImageView me_image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        //获取资源ID
        me_button = view.findViewById(R.id.me_button);
        me_image = view.findViewById(R.id.me_image);
        //生成二维码
        createQRCode();
        //点击跳转
        me_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),QRCActivity.class));
                getActivity().finish();
            }
        });
    }

    private void createQRCode() {
        String name = ((HomeActivity) getActivity()).QRCname();
        TaskQRCode taskQRCode = new TaskQRCode(getActivity(), me_image, name);
        taskQRCode.execute(name);
    }
    static class TaskQRCode extends AsyncTask<String,Void,Bitmap>{
        private WeakReference<Context> mcontext;
        private WeakReference<ImageView> mimageview;

        public TaskQRCode(Context context, ImageView imageView,String name) {

            mcontext=new WeakReference<>(context);
            mimageview=new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            if (TextUtils.isEmpty(strings[0])){
                return null;
            }
            int size = mcontext.get().getResources().getDimensionPixelOffset(R.dimen.code_size);
            return QRCodeEncoder.syncEncodeQRCode(strings[0],size);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap!=null){
                mimageview.get().setImageBitmap(bitmap);
            }else{
                Toast.makeText(mcontext.get(), "生成失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
