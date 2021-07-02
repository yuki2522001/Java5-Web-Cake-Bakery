package com.example.techpolyshop.service;

import java.util.List;
import java.util.Optional;

import com.example.techpolyshop.domain.Account;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface AccountService {
	// Account login(String username, String password);

    <S extends Account> List<S> findAll(Example<S> example, Sort sort);

	<S extends Account> List<S> findAll(Example<S> example);

	<S extends Account> S save(S entity);

	Account getById(String id);

	void deleteAll();

	void deleteAll(Iterable<? extends Account> entities);

	// Account getOne(String id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends String> ids);

	void delete(Account entity);

	void deleteAllByIdInBatch(Iterable<String> ids);

	void deleteById(String id);

	long count();

	void deleteAllInBatch(Iterable<Account> entities);

	<S extends Account> boolean exists(Example<S> example);

	<S extends Account> long count(Example<S> example);

	// void deleteInBatch(Iterable<Account> entities);

	<S extends Account> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Account> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(String id);

	<S extends Account> S saveAndFlush(S entity);

	void flush();

	<S extends Account> List<S> saveAll(Iterable<S> entities);

	Optional<Account> findById(String id);

	List<Account> findAllById(Iterable<String> ids);

	List<Account> findAll(Sort sort);

	List<Account> findAll();

	Page<Account> findAll(Pageable pageable);

	<S extends Account> Optional<S> findOne(Example<S> example);



	Page<Account> findByUsernameContaining(String username, Pageable pageable);

	List<Account> findByUsernameContaining(String username);

	Account findByUsername(String username);

}
