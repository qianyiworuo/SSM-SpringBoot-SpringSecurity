/*
用户管理相关的API请求函数
*/
import request from '@/utils/request'

const api_name = '/admin/system/sysUser'

export default{
    //条件分页查询
    wrapperQueryPage(page, limit, searchObj){
        return request({
            url: `${api_name}/${page}/${limit}`,
            method: 'get',
            params: searchObj// url查询字符串或表单键值对
        })
    },
    //新增角色接口
    saveUser(sysUser){
        return request({
            url: `${api_name}/save`,
            method: 'post',
            data: sysUser
        })
    },
    //按ID查询用户接口
    queryUserById(id){
        return request({
            url: `${api_name}/queryUserById/${id}`,
            method: 'get',
            params: id
        })
    },
    //修改用户接口
    updateUser(sysUser){
        return request({
            url: `${api_name}/update`,
            method: 'put',
            data: sysUser
        })     
    },
    //根据ID删除用户
    removeById(id){
        return request({
            url: `${api_name}/removeById/${id}`,
            method: 'delete',
            params: id
        })
    },
    //批量删除用户
    batchRemove(idList){
        return request({
            url: `${api_name}/batchRemove/${idList}`,
            method: 'delete',
            params: idList
        })
    },
    //修改用户状态
    updateStatus(id, status){
        return request({
            url: `${api_name}/updateStatus/${id}/${status}`,
            method: 'get'
        })
    }
    
}