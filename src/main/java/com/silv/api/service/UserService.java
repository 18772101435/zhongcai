package com.silv.api.service;

import com.silv.api.dao.UserDao;
import com.silv.api.model.Result;
import com.silv.api.model.User;
import com.silv.api.util.MD5Utils;
import com.silv.api.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yao on 2017/12/20.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Result addUser(String name, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = new User();
        user.setName(name);
        user.setPassword(MD5Utils.EncoderByMd5(password));
        this.userDao.save(user);
        return ResultUtil.success(user);
    }

    public Result checkUser(String name, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = this.userDao.findByName(name);
        if (user == null)
            return ResultUtil.error(0, "该用户不存在");
        String md5Password = MD5Utils.EncoderByMd5(password);
        if (user.getPassword().equals(md5Password)) {
            return ResultUtil.success(user);
        }
        return ResultUtil.error(0, "密码不正确");
    }
}
