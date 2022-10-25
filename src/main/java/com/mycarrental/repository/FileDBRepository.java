package com.mycarrental.repository;

import com.mycarrental.domain.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface FileDBRepository extends JpaRepository<FileDB,String> {
}
