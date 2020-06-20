package com.xhu.xyjy.service;

import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dto.InformUser;
import com.xhu.xyjy.dto.MomentUser;
import com.xhu.xyjy.dto.ResultData;
import com.xhu.xyjy.pojo.Moment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public interface MomentService {
    ResultData publish(Moment moment,MultipartFile[] file,MultipartFile[] file2);
    PageInfo<MomentUser> selectAll(Integer page, Integer pageSize);
    PageInfo<MomentUser> selectById(Integer user_id,Integer page,Integer pageSize);
    PageInfo<MomentUser> selectFriendById(Integer user_id,Integer page,Integer pageSize);
    ResultData addLikeCount(Integer id,Integer user1_id,Integer user2_id);
    ResultData addViewCount(Integer id);
    ResultData selectMomentById(Integer id);

    PageInfo<MomentUser> findMomentByTag(String action,Integer page,Integer pageSize);

    ResultData deletemoment(int id);

    PageInfo<InformUser> selectFocus(int user_id, Integer page, Integer pageSize);

    ResultData addrecord(int id, int userid);
}
