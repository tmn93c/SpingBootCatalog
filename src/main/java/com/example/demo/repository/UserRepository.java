package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;

import com.example.demo.model.UserModel;
import com.example.demo.model.UserModelFake;
import com.example.demo.model.UserModelInteface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;



@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserModel, Long> {

	UserModel findByUsername(String username);
    @Query(
    value = "SELECT u.id,u.name,u.username,u.email,u.password,u.create_at FROM user_model u", 
    nativeQuery = true)
    List<Tuple> findAllNativeQuery();
    List<UserModel> findAll();

	List<UserModel> findAllByEnabled(boolean enabled);

	Optional<UserModel> findById(Long id);

	Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findByUsernameOrEmail(String username, String email);

    List<UserModel> findByIdIn(List<Long> userIds);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    void deleteById(Long id);


}


