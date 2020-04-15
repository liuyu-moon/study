package com.xhu.xyjy.dao;
import com.xhu.xyjy.pojo.Admin;
import com.xhu.xyjy.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

//    查询所有用户
    @Select("select * from user")
    List<User> selectAll();


    /**
     * 添加用户
     */
    @Insert("INSERT INTO user (user_name,user_pwd,user_picture,user_type,user_status,user_addtime,user_phone,user_sex) VALUES (#{user_name},#{user_pwd},#{user_picture},#{user_type},#{user_status},#{user_addtime},#{user_phone},#{user_sex})")
    Boolean saveUser(User user);

//    登陆
    @Select("select * from user where user_name = #{user_name}")
    User selectUserByName(String user_name);


    //查询用户朋友列表
    @Select("select * from user where user_id=#{friend_id}")
    User selectUserWithFriendByid(int friend_id);

    @Update(" update user set user_logintime = #{user_logintime},lng=#{lng},lat=#{lat} where user_id=#{user_id};")
    void updateLogintimeAndPosition(User user);


//    @Select("select * from user where user_id=#{user_id}")
//    @Results({
//            @Result(property = "friendList",column ="user_id", many = @Many(select = "com.xhu.xyjy.dao.FriendMapper.selectFriend "))
//    })
//    User selectFriendList (int user_id);


    /**
     * 查询所有管理员
     * @return
     */
    @Select("select * from user where user_type=1 and user_status!=2 and user_name like #{username} order by user_id desc")
    List<User> selectAllAdmin(@Param("username") String username);

    /**
     * 查询用户
     * @return
     */
    @Select("select * from user where (user_type=0 or user_type=2 or user_type=3) and user_status!=2 and user_name like #{username} order by user_id desc")
    List<User> selectAllUser(@Param("username") String username);

    /**
     * 更新用户状态信息
     * @param id:当前用户id
     * @param status:当前用户状态
     * @return
     */
    @Update(" update user set user_status=#{status} where user_id=#{id}")
    Boolean updateStatus(@Param("id") Integer id, @Param("status") Integer status);

    /**
     * 通过id查询数据
     * @return
     */

    @Select(" select * from user where user_id=#{user_id}")
    User findById(@Param("user_id") Integer user_id);

    /**
     * 修改管理员信息
     * @param user
     * @return
     */
    @Update("update user set user_name=#{user_name}\n" +
            "                      ,user_sex=#{user_sex}\n" +
            "                      ,user_phone=#{user_phone}\n" +
            "                      ,user_touxiang=#{user_touxiang}\n" +
            "                      ,user_school=#{user_school}\n" +
            "        where user_id=#{user_id}")
    Boolean updateInfo(User user);


    /**
     * 通过id删除（软删除）
     * @param id
     * @return
     */
    @Delete(" update user set user_status=#{status} where user_id=#{id}")
    Boolean delete(@Param("id") Integer id, @Param("status") Integer status);


    /**
     * 批量删除（软删除）
     * @param ids
     * @return
     */
    @Delete("update user set user_status=2 where user_id in\n" +
            "        <foreach collection=\"array\" item=\"id\" open=\"(\" separator=\",\" close=\")\">\n" +
            "            #{id}\n" +
            "        </foreach>")
    Boolean batchDelete(String[] ids);

    /**
     * 修改密码
     * @param newpwd
     * @return
     */
    @Update("update user set user_pwd=#{newpwd} where user_id=#{id}")
    Boolean updatePwd(@Param("id") Integer id, @Param("newpwd") String newpwd);


    @Select("select ifNULL(sum(unread),0) FROM chatlist WHERE user_id =#{user_id}")
    int findUnread(int user_id);

    @Select("select * from user\n" +
            "where lat <> 0   and lng >#{minLng} and lng < #{maxLng}  and lat > #{minLat} and lat < #{maxLat} and user_id <>#{userid} limit 5   ")
    List<User> selectNearby(Double maxLat, Double minLat, Double maxLng, Double minLng,int userid);

    @Select("select * from user where user_id in (${ids})" )
    List<User> findUsers( @Param("ids") String ids);
}
