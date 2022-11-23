package com.qianyi.common.util;

import com.qianyi.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 * 根据菜单数据构建菜单树的工具类
 * </p>
 *
 */
public class MenuHelper {
    /**
     * 使用递归方法建菜单
     * @param sysMenuList
     * @return
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        ArrayList<SysMenu> trees = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            //获取递归方法的入口parentid
            Long parentId = sysMenu.getParentId();
            if(parentId == 0){
                trees.add(findChildren(sysMenu, sysMenuList));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param sysMenu
     * @param treeNodes
     * @return
     */
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> treeNodes) {
        //初始化数据
        sysMenu.setChildren(new ArrayList<SysMenu>());
        for (SysMenu treeNode : treeNodes) {
            //获取菜单的id
            Long id = sysMenu.getId();
            //获取菜单的parentid
            Long parentId = treeNode.getParentId();
            //转化
            if(id == parentId){
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(treeNode, treeNodes));
            }
        }
        return sysMenu;
    }
}
