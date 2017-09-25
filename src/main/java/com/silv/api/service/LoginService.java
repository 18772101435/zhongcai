package com.silv.api.service;

import com.silv.api.dao.LoginDao;
import com.silv.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yao on 2017/9/25.
 */
@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;


    public void login() {
        List<User> list = this.loginDao.login();
        for(User user:list){
            System.out.println(user.getId()+"  "+user.getName()+"  "+user.getPassword());
        }
    }

    public void add() {
//        User user = new User("zhangsan","111111");       // 添加
//        this.loginDao.save(user);

//        User user = new User("zhangsan","111111");       // 修改
//        user.setId(3L);
//        user.setName("lisi");
//        this.loginDao.save(user);

//        this.loginDao.delete(3L);                        // 根据ID删除

//        User user = new User();                          // 根据对象删除
//        user.setId(4L);
//        this.loginDao.delete(user);

//        User user = this.loginDao.findOne(1L);            // 根据ID查询
//        System.out.println(user.getId()+"  "+user.getName()+"  "+user.getPassword());

//        List<User> list = (List<User>) this.loginDao.findAll();  // 查询所有
//        for(User user:list){
//            System.out.println(user.getId()+"  "+user.getName()+"  "+user.getPassword());
//        }

    }
}
