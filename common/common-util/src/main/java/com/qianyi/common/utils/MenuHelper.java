package com.qianyi.common.utils;

import com.qianyi.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jaycewang
 */
public class MenuHelper {

//构建树形结构
    //使用递归方法建菜单
    public static List<SysMenu> buildTree(List<SysMenu> menuList) {
        //创建集合封装最终数据
        ArrayList<SysMenu> trees = new ArrayList<>();
        //遍历所有菜单的集合
        for (SysMenu sysMenu: menuList) {
            //找到递归入口 parentid = 0
            if(sysMenu.getParentId().longValue() == 0){
                trees.add(findChildren(sysMenu, menuList));
            }
        }
        return trees;
    }

    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> treeNodes) {
        //初始化菜单数据
        sysMenu.setChildren(new ArrayList<SysMenu>());
        for (SysMenu it: treeNodes) {
            //获取当前菜单id
            Long id = sysMenu.getId().longValue();
            //获取所有菜单parentid
            Long parentId = it.getParentId().longValue();
            //对比
            if(id.equals(parentId)){
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return sysMenu;

    }
}
