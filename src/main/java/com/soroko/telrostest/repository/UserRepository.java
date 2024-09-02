package com.soroko.telrostest.repository;


import com.soroko.telrostest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * This class consists SQL queries to create, read, update or delete user
 * from database
 *
 * @author yuriy.soroko
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
