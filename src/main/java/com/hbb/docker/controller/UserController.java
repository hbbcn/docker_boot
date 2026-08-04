package com.hbb.docker.controller;


import cn.hutool.json.JSONUtil;
import com.hbb.docker.entity.User;
import com.hbb.docker.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hbbcn
 * @since 2026-08-03
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/add")
    public String addUsers(@RequestBody List<User> users) {
        userService.saveBatch(users);
        for (User user : users) {
            String key = "user:" + user.getId();
            stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(user), 30, TimeUnit.MINUTES);
        }
        return "添加成功";
    }

    @PostMapping("/addTwo")
    public String addTwoUsers() {
        User user1 = new User();
        user1.setName("张三");

        User user2 = new User();
        user2.setName("李四");

        userService.save(user1);
        userService.save(user2);

        stringRedisTemplate.opsForValue().set("user:" + user1.getId(), JSONUtil.toJsonStr(user1), 30, TimeUnit.MINUTES);
        stringRedisTemplate.opsForValue().set("user:" + user2.getId(), JSONUtil.toJsonStr(user2), 30, TimeUnit.MINUTES);

        return "添加成功";
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {
        String key = "user:" + id;
        String json = stringRedisTemplate.opsForValue().get(key);
        if (json != null) {
            return JSONUtil.toBean(json, User.class);
        }
        User user = userService.getById(id);
        if (user != null) {
            stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(user), 30, TimeUnit.MINUTES);
        }
        return user;
    }
}