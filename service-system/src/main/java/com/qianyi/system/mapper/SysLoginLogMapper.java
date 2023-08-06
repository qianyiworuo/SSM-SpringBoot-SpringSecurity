package com.qianyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qianyi.model.system.SysLoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {

}
