package com.qianyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qianyi.model.system.SysOperLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {
}
