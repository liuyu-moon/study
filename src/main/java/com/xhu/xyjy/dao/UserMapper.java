package com.xhu.xyjy.dao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xhu.xyjy.dto.RecordUser;
import com.xhu.xyjy.pojo.Admin;
import com.xhu.xyjy.pojo.Record;
import com.xhu.xyjy.pojo.Student;
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
            "                      ,user_birthday=#{user_birthday}\n" +
            "                      ,user_school=#{user_school}\n" +
            "        where user_id=#{user_id} ")
    Boolean updateInfo(User user);

    @Update("update user set user_picture=#{user_picture} where user_id =#{user_id}")
    Boolean updatePic(User user);

    /**
     * 通过id删除（软删除）
     * @param id
     * @return
     */
    @Delete(" update user set user_status=#{status} where user_id=#{id}")
    Boolean delete(@Param("id") Integer id, @Param("status") Integer status);




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

    @Select("select * from student where user_id=#{user_id}")
    Student findStudent(int user_id);

    @Update("update student set school=#{school},major=#{major},pic=#{pic},status=#{status} where user_id=user_id")
    Boolean updateStudent(Student student);


    @Select("SELECT * from `user` WHERE user_id in\n" +
            "(SELECT friend_id FROM `friendship` where user_id= #{user_id} ) and  Month(NOW())=Month(user_birthday) AND DAY(user_birthday)-DAY(NOW())<=3")
    List<User> findBirthday(int user_id);

    @Select("select ifNULL(sum(like_count),0) FROM `comment` where user_id=#{user_id}")
    Integer findCommentLikeCount(int user_id);

    @Select("select ifNULL(sum(like_count),0) FROM `moment` where user_id=#{user_id}")
    Integer findMomentLikeCount(int user_id);

    @Select("select ifNULL(sum(view_count),0) FROM `moment` where user_id=#{user_id}")
    Integer findViewCount(int user_id);


    //查询我最近访问的人
    @Select("SELECT record.*,user.user_name,user.user_picture  FROM  record INNER JOIN user  on  record.user2_id =user.user_id and  record.user1_id=#{id}  ORDER BY time DESc limit 5")
    List<RecordUser> findRecentA(int user_id);

    //查询最近访问我人

    @Select("SELECT record.*,user.user_name,user.user_picture  FROM  record INNER JOIN user  on  record.user1_id =user.user_id and  record.user2_id=#{id}  ORDER BY time DESc limit 5")
    List<RecordUser> findRecentB(int user_id);

    //插入记录
    @Insert("INSERT into  record (user1_id,user2_id,moment_id,time,type)VALUES (#{user1_id},#{user2_id},#{moment_id},#{time},#{type})")
    boolean insertRecent(Record record);

    //查询是否已经有访问记录
    @Select("select * from record where user1_id=#{user1_id} and user2_id=#{user2_id}")
    Record findRecordOne(int user1_id,int user2_id);

    //更新访问时间和类型
    @Update("update record set time=now(),type=#{type} where user1_id=#{user1_id} and user2_id=#{user2_id}")
    boolean updateRecordTimeandType(int user1_id,int user2_id,int type);

    //更新访问时间和类型和动态ID
    @Update("update record set moment_id=#{moment_id}, time=now(),type=#{type} where user1_id=#{user1_id} and user2_id=#{user2_id}")
    boolean updateRecordTimeandTypeandMoment(int user1_id,int user2_id,int moment_id,int type);

    @Select("SELECT * FROM user where user_id in\n" +
            "(SELECT user1_id FROM record WHERE moment_id in\n" +
            "  ( SELECT moment_id FROM record WHERE user1_id=#{user_id}) and moment_id!=0 and user1_id!= #{user_id})")
    List<User> interestUser(int user_id);



}
