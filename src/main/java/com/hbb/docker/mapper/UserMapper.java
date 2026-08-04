package com.hbb.docker.mapper;

import com.hbb.docker.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hbbcn
 * @since 2026-08-03
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
