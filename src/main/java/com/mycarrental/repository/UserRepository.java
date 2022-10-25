package com.mycarrental.repository;


import com.mycarrental.domain.User;
import com.mycarrental.exception.BadRequestException;
import com.mycarrental.exception.ResourceNotFoundException;
import com.mycarrental.projection.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
//user--> bizim olusturdugmuz user sinifi
//long --> user da kullandigimiz ID olarak dusunebiliriz
public interface UserRepository extends JpaRepository<User,Long> {
    //bu dosyaya sorgularimizi yazacagiz


    //@Query("select u from User u where u.email =?1")
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email) throws ResourceNotFoundException;

    List<ProjectUser> findAllBy();

    @Modifying
    @Query("UPDATE User u " +
            "SET u.firstName = ?2, u.lastName = ?3, u.phoneNumber = ?4, " +
            "u.email = ?5, u.address = ?6, u.zipCode = ?7 " +
            "WHERE u.id = ?1")
    void update(Long id, String firstName, String lastName, String phoneNumber, String email, String address,
                String zipCode) throws BadRequestException;
}
