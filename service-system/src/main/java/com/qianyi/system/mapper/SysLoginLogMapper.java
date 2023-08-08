package com.qianyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qianyi.model.system.SysLoginLog;
import com.qianyi.model.vo.SysLoginLogQueryVo;
import com.qianyi.model.vo.SysRoleQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {
	//条件分页查询
	IPage<SysLoginLog> selectPage(Page<Object> pageParam , @Param("vo") SysLoginLogQueryVo sysLoginLogQueryVo);

}
