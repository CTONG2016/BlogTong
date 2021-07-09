package com.chentong.myblog.dao;

import com.chentong.myblog.model.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 根据博客的id拿到所有的对应的评论信息，且只取顶级的评论信息用于整合子级的评论信息 ParentCommentIsNull(
     * @param blogId
     * @param sort
     * @return
     */
    List<Comment> findCommentsByBlogIdAndParentCommentIsNull(Long blogId, Sort sort);

    /**
     * 数据库表中一个字段的更新Modifying，适合自定义的SQL语句
     * ==> 必须加入事务里面更新 Transactional
     */
    @Transactional
    @Modifying
    @Query("update Blog b set b.good = b.good + 1 where b.id = ?1")
    int updateGoodBlog(Long id);

}
