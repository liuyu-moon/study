package com.xhu.xyjy.service;

        import com.github.pagehelper.PageInfo;
        import com.xhu.xyjy.dto.CommentUser;
        import com.xhu.xyjy.dto.ResultData;
        import com.xhu.xyjy.pojo.Comment;

        import java.util.List;

public interface CommentService {
    PageInfo<CommentUser> selectComment1(int moment_id, Integer page, Integer pageSize);
    PageInfo<CommentUser> selectComment2(int id, Integer page, Integer pageSize);


    ResultData addComment(int moment_id, String content,int user_id);
    ResultData addComment2(Comment comment);

    ResultData addLikeCount(int id);
}
