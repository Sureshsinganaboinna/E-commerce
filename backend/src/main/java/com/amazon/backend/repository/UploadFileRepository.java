package com.amazon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazon.backend.entities.UploadFile;

@Repository
public interface UploadFileRepository extends JpaRepository<UploadFile, Integer> {

}
