package com.chentong.myblog.dao;

import com.chentong.myblog.model.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 根据博客的id拿到所有的对应的评论信息，且只取顶级的评论信息用于整合子级的评论信息 ParentCommentIsNull(
     * @param blogId
     * @param sort
     * @return
     */
    List<Comment> findCommentsByBlogIdAndParentCommentIsNull(Long blogId, Sort sort);

}
