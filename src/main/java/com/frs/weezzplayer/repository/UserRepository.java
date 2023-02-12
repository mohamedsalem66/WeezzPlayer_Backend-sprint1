package com.frs.weezzplayer.repository;

import com.frs.weezzplayer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findByEmail(String email);

    Boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);




}
