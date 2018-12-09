package guopuran.bwie.com.space07.bean;

import java.util.List;

public class ListBean {
    public List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public class Data{
        public String title;
        public String thumbnail_pic_s;

        public String getTitle() {
            return title;
        }

        public String getThumbnail_pic_s() {
            return thumbnail_pic_s;
        }
    }
}
