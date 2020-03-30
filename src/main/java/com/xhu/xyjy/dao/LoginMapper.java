package com.xhu.xyjy.dao;
import com.xhu.xyjy.pojo.User;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginMapper {
    /**
     * 通过用户名查询数据
     */
    User findByName(String username);

    /**
     * 登录时更新登录时间
     */
    void updateLogintime(User user);
}
