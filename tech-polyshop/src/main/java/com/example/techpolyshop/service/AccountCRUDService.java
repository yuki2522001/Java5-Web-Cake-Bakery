// package com.example.techpolyshop.service;

// import java.util.List;
// import java.util.Optional;

// import com.example.techpolyshop.domain.Account;

// import org.springframework.data.domain.Example;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;

// public interface AccountCRUDService {
//     Account getById(String id);

// 	void deleteAll();

// 	void deleteAll(Iterable<? extends Account> entities);

// 	void deleteAllInBatch();

// 	void deleteAllById(Iterable<? extends String> ids);

// 	void delete(Account entity);

// 	void deleteAllByIdInBatch(Iterable<String> ids);

// 	void deleteById(String id);

// 	// Long count();

// 	void deleteAllInBatch(Iterable<Account> entities);

// 	<S extends Account> boolean exists(Example<S> example);

// 	<S extends Account> List<S> saveAllAndFlush(Iterable<S> entities);

// 	boolean existsById(String id);

// 	<S extends Account> S saveAndFlush(S entity);

// 	void flush();

// 	<S extends Account> List<S> saveAll(Iterable<S> entities);

// 	Optional<Account> findById(String id);

// 	List<Account> findAllById(Iterable<String> ids);

// 	List<Account> findAll(Sort sort);

// 	List<Account> findAll();

// 	Page<Account> findAll(Pageable pageable);

// 	<S extends Account> S save(S entity);

// 	List<Account> findByNameContaining(String name);
	
// 	Page<Account> findByNameContaining(String name, Pageable pageable);
	
// }
