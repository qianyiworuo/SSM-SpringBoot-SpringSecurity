/*
角色管理相关的API请求函数
*/
import request from '@/utils/request'

const api_name = '/admin/system/sysRole'

export default {
    //查询所有
    getPageList(page, limit, searchObj) {
        return request({
            url: `${api_name}/${page}/${limit}`,
            method: 'get',
            //其它参数
            params: searchObj
        })
    },
    //根据id删除
    removeId(id){
        return request({
            url: `${api_name}/remove/${id}`,
            method: "delete"
        })
    },
    //添加
    saveRole(role){
        return request({
            url: `${api_name}/save`,
            method: "post",
            //后端如果是@ResquestBody，则表示要传递json格式数据
            data: role
        })

    },
    //修改-根据id查询
    findRoleById(id) {
        return request({
            url: `${api_name}/findRoleById/${id}`,
            method: 'get'
        })
    },
    //修改-执行修改动作
    updateById(role) {
        return request({
            url: `${api_name}/update`,
            method: "put",
            data: role
        })
    },
    //批量刪除
    batchRemove(idList){
        return request({
            url: `${api_name}/batchRemove`,
            method: 'delete',
            data: idList
        })
    },
    //根据用户id查询用户已分配的角色
    getRolesByUserId(userId) {
        return request({
            url: `${api_name}/toAssign/${userId}`,
            method: 'get'
        })
    },
  
  //分配角色
    assignRoles(assginRoleVo) {
        return request({
            url: `${api_name}/doAssign`,
            method: 'post',
            data: assginRoleVo
        })
    }
}