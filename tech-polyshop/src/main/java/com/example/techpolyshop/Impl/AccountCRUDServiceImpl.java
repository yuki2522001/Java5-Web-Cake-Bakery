// package com.example.techpolyshop.Impl;

// import java.util.List;
// import java.util.Optional;

// import com.example.techpolyshop.domain.Account;
// import com.example.techpolyshop.repository.AccountCRUDReposotory;
// import com.example.techpolyshop.service.AccountCRUDService;

// import org.springframework.data.domain.Example;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
// import org.springframework.stereotype.Service;

// @Service
// public class AccountCRUDServiceImpl implements AccountCRUDService{
//     AccountCRUDReposotory accountCRUDReposotory;

//     public AccountCRUDServiceImpl(AccountCRUDReposotory accountCRUDReposotory) {
//         this.accountCRUDReposotory = accountCRUDReposotory;
//     }

//     @Override
// 	public Page<Account> findByNameContaining(String name, Pageable pageable) {
// 		return accountCRUDReposotory.findByNameContaining(name, pageable);
// 	}

// 	@Override
//     public List<Account> findByNameContaining(String name) {
// 		return accountCRUDReposotory.findByNameContaining(name);
// 	}

// 	@Override
// 	public <S extends Account> S save(S entity) {
// 		return accountCRUDReposotory.save(entity);
// 	}

// 	@Override
// 	public Page<Account> findAll(Pageable pageable) {
// 		return accountCRUDReposotory.findAll(pageable);
// 	}

// 	@Override
// 	public List<Account> findAll() {
// 		return accountCRUDReposotory.findAll();
// 	}

// 	@Override
// 	public List<Account> findAll(Sort sort) {
// 		return accountCRUDReposotory.findAll(sort);
// 	}

// 	@Override
// 	public List<Account> findAllById(Iterable<String> ids) {
// 		return accountCRUDReposotory.findAllById(ids);
// 	}

// 	@Override
// 	public Optional<Account> findById(String id) {
// 		return accountCRUDReposotory.findById(id);
// 	}

// 	@Override
// 	public <S extends Account> List<S> saveAll(Iterable<S> entities) {
// 		return accountCRUDReposotory.saveAll(entities);
// 	}

// 	@Override
// 	public void flush() {
// 		accountCRUDReposotory.flush();
// 	}

// 	@Override
// 	public <S extends Account> S saveAndFlush(S entity) {
// 		return accountCRUDReposotory.saveAndFlush(entity);
// 	}

// 	@Override
// 	public boolean existsById(String id) {
// 		return accountCRUDReposotory.existsById(id);
// 	}

// 	@Override
// 	public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
// 		return accountCRUDReposotory.saveAllAndFlush(entities);
// 	}

// 	@Override
// 	public <S extends Account> boolean exists(Example<S> example) {
// 		return accountCRUDReposotory.exists(example);
// 	}

// 	@Override
// 	public void deleteAllInBatch(Iterable<Account> entities) {
// 		accountCRUDReposotory.deleteAllInBatch(entities);
// 	}

// 	@Override
// 	public void deleteById(String id) {
// 		accountCRUDReposotory.deleteById(id);
// 	}

// 	@Override
// 	public void deleteAllByIdInBatch(Iterable<String> ids) {
// 		accountCRUDReposotory.deleteAllByIdInBatch(ids);
// 	}

// 	@Override
// 	public void delete(Account entity) {
// 		accountCRUDReposotory.delete(entity);
// 	}

// 	@Override
// 	public void deleteAllById(Iterable<? extends String> ids) {
// 		accountCRUDReposotory.deleteAllById(ids);
// 	}

// 	@Override
// 	public void deleteAllInBatch() {
// 		accountCRUDReposotory.deleteAllInBatch();
// 	}

// 	@Override
// 	public void deleteAll(Iterable<? extends Account> entities) {
// 		accountCRUDReposotory.deleteAll(entities);
// 	}

// 	@Override
// 	public void deleteAll() {
// 		accountCRUDReposotory.deleteAll();
// 	}

// 	@Override
// 	public Account getById(String id) {
// 		return accountCRUDReposotory.getById(id);
// 	}

// }
