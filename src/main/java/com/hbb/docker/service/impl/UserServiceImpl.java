package com.hbb.docker.service.impl;

import com.hbb.docker.entity.User;
import com.hbb.docker.mapper.UserMapper;
import com.hbb.docker.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hbbcn
 * @since 2026-08-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
