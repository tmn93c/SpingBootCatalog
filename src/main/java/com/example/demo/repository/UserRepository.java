package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;
import javax.transaction.Transactional;

import com.example.demo.model.UserModel;
import com.example.demo.model.UserModelFake;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByUsername(String username);

    @Query(value = "SELECT u.id,u.name,u.username,u.email,u.password FROM user_model u", nativeQuery = true)
    public List<Tuple> findAllNativeQuery();

    @Query(name = "find_all_native_query2", nativeQuery = true)
    public List<UserModelFake> findAllNativeQuery2();

    public List<UserModel> findAll();

    public List<UserModel> findAllByEnabled(short enabled);

    Optional<UserModel> findById(Long id);

    Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findByUsernameOrEmail(String username, String email);

    List<UserModel> findByIdIn(List<Long> userIds);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    public void deleteById(Long id);

}
