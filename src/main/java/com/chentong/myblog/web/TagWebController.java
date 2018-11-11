package com.chentong.myblog.web;

import com.chentong.myblog.model.BlogQuery;
import com.chentong.myblog.model.Tag;
import com.chentong.myblog.service.AdminService;
import com.chentong.myblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagWebController {

    @Autowired
    private TagService tagService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/tags/{id}")
    public String types(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, @PathVariable long id, Model model){
        List<Tag> tags = tagService.listTagTop(20); // 查询top的类型，全表的类型
        if(id == -1){ // 从导航点入的默认的id值, 取最大的那个值
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        model.addAttribute("page", adminService.listBlog(pageable, id));
        model.addAttribute("activeId", id);
        return "tags";
    }

}
