package com.silv.api.dao;

import com.silv.api.model.FileAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yao on 2017/9/25.
 */
@Repository
@Transactional
public interface FileAttachmentDao extends JpaRepository<FileAttachment,Long> {

}
