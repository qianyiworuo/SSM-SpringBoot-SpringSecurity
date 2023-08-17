package com.qianyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qianyi.model.system.SysOperLog;
import com.qianyi.model.vo.SysOperLogQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {
    IPage<SysOperLog> selectPage(Page<Object> pageParam, @Param("vo")SysOperLogQueryVo sysOperLogQueryVo);
}
