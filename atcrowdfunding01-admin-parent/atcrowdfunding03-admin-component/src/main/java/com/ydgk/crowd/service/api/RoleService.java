package com.ydgk.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.ydgk.crowd.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-03 9:43
 */
public interface RoleService {


    PageInfo<Role> getRoleByKeyWord(Integer pageNum, Integer pageSize, String keyWord);
}
