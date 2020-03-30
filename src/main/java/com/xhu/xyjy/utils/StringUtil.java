package com.xhu.xyjy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class StringUtil {

    //得到文件的后缀，确定文件格式
    public  static  String getExt(String  filename)
    {
        if(filename==null||filename.isEmpty())
        {
            return null;
        }
        return filename.substring(filename.lastIndexOf("."));
    }


    public  static  String GetUniueFileName(){
        UUID uuid =UUID.randomUUID();
        return  uuid.toString().replace("-","");

    }

    public  static  String GetNowData() {
        //设置位标准的时间格式
        SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
        return  format.format(new Date());


    }



}
