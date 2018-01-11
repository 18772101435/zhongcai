package com.silv.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.silv.api.model.BaseDocTemplate;

@Repository
public interface DocTemplateDao extends JpaRepository<BaseDocTemplate, Long> {
}
