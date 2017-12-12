package com.silv.api.dao;

import com.silv.api.model.Sms;
import com.silv.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yao on 2017/9/25.
 */
@Repository
@Transactional
public interface SmsDao extends JpaRepository<Sms,Long> {

    @Query(value="from User")
    List<User> login();

    @Query(value="select * from sms s where s.phone=:phone order by expire_time desc limit 1 ", nativeQuery = true)
    Sms getCodeByPhone(@Param("phone") String phone);
}
