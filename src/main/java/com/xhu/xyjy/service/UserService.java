package com.xhu.xyjy.service;

import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dto.ResultData;
import com.xhu.xyjy.pojo.Admin;
import com.xhu.xyjy.pojo.User;
import com.xhu.xyjy.dto.ResultData;
import com.xhu.xyjy.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    ResultData saveUser(User user);
    ResultData findUserById(User user);
    List<User> listAll();


    User findUserInfo(int user_id);

    int findUnread(int userId);

    List<User> findNearby(int userid);

    ResultData update(User user);

    ResultData updatepic(int user_id, MultipartFile file[]);
}
