package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.demo.model.UserModel;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface UserSpeRepository extends CrudRepository<UserModel, Long>,
JpaSpecificationExecutor<UserModel> {

	UserModel findByUsername(String username);

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


