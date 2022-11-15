package com.qianyi.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qianyi.model.system.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qianyi.model.vo.SysUserQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author qianyi
 * @since 2022-11-10
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    IPage<SysUser> selectPage(Page<Object> params, @Param("vo")SysUserQueryVo sysUserQueryVo);

}
