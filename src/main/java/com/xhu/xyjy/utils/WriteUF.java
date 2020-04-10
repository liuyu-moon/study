package com.xhu.xyjy.utils;

import com.xhu.xyjy.dao.FriendMapper;
import com.xhu.xyjy.pojo.Friend;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class WriteUF {



    static FriendMapper friendMapper;

    @Test
    public void writeUF() throws Exception  {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("F:\\xyjy\\src\\main\\resources\\resource\\ufid.txt", false)));
        List<Friend> list=new ArrayList<>();
        list=friendMapper.selectUF();
        int n=list.size();
//        外层循环是User_id
        for(int i=1;i<=n;i++){
            //内层是循环list
            for (int j=0;j<n;j++){
                if (list.get(j).getUser_id()==i){
                        bw.write(list.get(j).getFriend_id()+"\n");
                }
            }

        }
    }
}
