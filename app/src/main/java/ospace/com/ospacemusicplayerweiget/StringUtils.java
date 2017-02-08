package ospace.com.ospacemusicplayerweiget;

/**
 * Created by jiang on 2017/2/8.
 */

public class StringUtils {
    public static String duration2Str(int duration){
        String result = "";
        int i = duration/1000;
        int min= i/60;
        int sec=i%60;
        if(min>9){
            if(sec>9){
                result = min+":"+sec;
            }
            else{
                result = min+":"+0+sec;
            }
        }else {
            if(sec>9){
                result = 0+min+":"+sec;
            }
            else {
                result = 0+min+":"+0+sec;
            }
        }
        return  result;
    }
    public static int Str2Duration(String str){
        String[] i = str.split(":");

        int k=1;
        int result=0;
        for(String j:i) {

            if (k < 2) {
                int min = Integer.valueOf(j);
                result = result + min * 60;
                k++;

            } else if (k < 3) {
                int sec = Integer.valueOf(j);
                result = result + sec;
                k++;
            }
        }
        return result*1000;
}}
