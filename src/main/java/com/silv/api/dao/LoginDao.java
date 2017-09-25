package com.silv.api.dao;

import com.silv.api.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yao on 2017/9/25.
 */
@Repository
@Transactional
public interface LoginDao extends CrudRepository<User,Long> {

    @Query(value="from User")
    List<User> login();

}
