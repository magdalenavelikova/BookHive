package com.bookhive.repository;

import com.bookhive.model.entity.ReaderOpinionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderOpinionRepository extends JpaRepository<ReaderOpinionsEntity, Long> {

}
