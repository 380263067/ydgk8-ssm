package com.ydgk.crowd.controller;

import com.ydgk.crowd.entity.Role;
import com.ydgk.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-07 9:52
 */
/*
分配功能的控制器
 */
@RequestMapping("/assign")
@Controller
public class AssignController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("do/assign/role.html")
    public String doAssign(Integer pageNum, String keyWord, Integer adminId,
                           Integer[] assignedRoleIds){
        // 可以执行分配
        /*
        原始角色
        id      a_id  r_id
        1       1       2
        2       1       3
        3       1       4
        =========================
        需要分配的角色
        id      a_id  r_id
        1       1       5
        2       1       6
        3       1       7
        ======================
        最终角色
        id      a_id  r_id
        1       1       5
        2       1       6
        3       1       7
        2       1       3
        3       1       4
        解决办法： 在执行角色分配之前将原始的角色删除，然后将所有的角色再次执行插入
         */
        if(assignedRoleIds != null && assignedRoleIds.length > 0){
            roleService.saveAssignRole(adminId,assignedRoleIds);
        }
        return "redirect:/user/to/user.html?pageNum="+pageNum+"&keyWord="+keyWord;
    }

    @RequestMapping("/to/assign/{adminId}/{pageNum}/{keyWord}.html")
    public String toAssign(
            @PathVariable("adminId")Integer adminId,
            @PathVariable("pageNum")Integer pageNum,
            @PathVariable("keyWord")String keyWord,
            Map map
    ){
        // 根据用户id查询角色分配的信息，并将其放入模型中
        // 需要查询用户已分配角色列表
        List<Role> assignedList = roleService.getAssignedList(adminId);
        // 查询用户未分配角色列表
        List<Role> unassignedList = roleService.getUnassignedList(adminId);
        // 将其放入模型中
        map.put("assignedList",assignedList);
        map.put("unassignedList",unassignedList);

        map.put("pageNum",pageNum);
        map.put("keyWord",keyWord);
        map.put("adminId",adminId);

        return "pages/role-assign";
    }

}
