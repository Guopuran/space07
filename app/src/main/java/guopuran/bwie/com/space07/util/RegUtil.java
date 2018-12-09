package guopuran.bwie.com.space07.util;

import java.util.regex.Pattern;

public class RegUtil {
    private static final String PHONE="^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";
    public static boolean isPhone(String phone){
        return Pattern.matches(PHONE,phone);
    }
}
