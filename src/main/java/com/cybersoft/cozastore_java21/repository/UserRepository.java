package com.cybersoft.cozastore_java21.repository;

import com.cybersoft.cozastore_java21.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    // Luu y : Cau query la cua thu vien ORM, chu kho ng phai cau query thuan cua
    // co so du lieu dang su dung
//    @Query("from users u where u.email = ?1 and u.password = ?2 ")
//    List<UserEntity> getUserEmailPassword(String email, String password);

    UserEntity findByEmail(String email);

}
