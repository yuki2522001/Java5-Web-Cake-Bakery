package com.example.techpolyshop.Impl;

import java.util.List;
import java.util.Optional;

import com.example.techpolyshop.domain.Account;
import com.example.techpolyshop.repository.AccountRepository;
import com.example.techpolyshop.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;
     
    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
    @Override
    public List<Account> findByUsernameContaining(String username) {
        return accountRepository.findByUsernameContaining(username);
    }
    @Override
    public Page<Account> findByUsernameContaining(String username, Pageable pageable) {
        return accountRepository.findByUsernameContaining(username, pageable);
    }

    // @Autowired
    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    // public Account login(String username, String password){
    //     Optional<Account> optional = findById(username);

    //     if(optional.isPresent() && bCryptPasswordEncoder.matches(password, optional.get().getPassword())){
    //         optional.get().setPassword("");
    //         return optional.get();
    //     }
    //     return null;
    // }

    @Override
    public <S extends Account> S save(S entity) {
        // Optional<Account> optional = findById(entity.getUsername());

        // if(optional.isPresent()){
        //     if(StringUtils.isEmpty(entity.getPassword())){
        //         entity.setPassword((optional.get().getPassword()));
        //     }else{
        //         entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        //     }
        // }else{
        //     entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        // }
        return accountRepository.save(entity);
    }
    
    @Override
    public <S extends Account> Optional<S> findOne(Example<S> example) {
        return accountRepository.findOne(example);
    }
    @Override
    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }
    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
    @Override
    public List<Account> findAll(Sort sort) {
        return accountRepository.findAll(sort);
    }
    @Override
    public List<Account> findAllById(Iterable<String> ids) {
        return accountRepository.findAllById(ids);
    }
    @Override
    public Optional<Account> findById(String id) {
        return accountRepository.findById(id);
    }
    @Override
    public <S extends Account> List<S> saveAll(Iterable<S> entities) {
        return accountRepository.saveAll(entities);
    }
    @Override
    public void flush() {
        accountRepository.flush();
    }
    @Override
    public <S extends Account> S saveAndFlush(S entity) {
        return accountRepository.saveAndFlush(entity);
    }
    @Override
    public boolean existsById(String id) {
        return accountRepository.existsById(id);
    }
    @Override
    public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
        return accountRepository.saveAllAndFlush(entities);
    }
    @Override
    public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
        return accountRepository.findAll(example, pageable);
    }
    // @Override
    // public void deleteInBatch(Iterable<Account> entities) {
    //     accountRepository.deleteInBatch(entities);
    // }
    @Override
    public <S extends Account> long count(Example<S> example) {
        return accountRepository.count(example);
    }
    @Override
    public <S extends Account> boolean exists(Example<S> example) {
        return accountRepository.exists(example);
    }
    @Override
    public void deleteAllInBatch(Iterable<Account> entities) {
        accountRepository.deleteAllInBatch(entities);
    }
    @Override
    public long count() {
        return accountRepository.count();
    }
    @Override
    public void deleteById(String id) {
        accountRepository.deleteById(id);
    }
    @Override
    public void deleteAllByIdInBatch(Iterable<String> ids) {
        accountRepository.deleteAllByIdInBatch(ids);
    }
    @Override
    public void delete(Account entity) {
        accountRepository.delete(entity);
    }
    @Override
    public void deleteAllById(Iterable<? extends String> ids) {
        accountRepository.deleteAllById(ids);
    }
    @Override
    public void deleteAllInBatch() {
        accountRepository.deleteAllInBatch();
    }
    // @Override
    // public Account getOne(String id) {
    //     return accountRepository.getOne(id);
    // }
    @Override
    public void deleteAll(Iterable<? extends Account> entities) {
        accountRepository.deleteAll(entities);
    }
    @Override
    public void deleteAll() {
        accountRepository.deleteAll();
    }
    @Override
    public Account getById(String id) {
        return accountRepository.getById(id);
    }
    @Override
    public <S extends Account> List<S> findAll(Example<S> example) {
        return accountRepository.findAll(example);
    }
    @Override
    public <S extends Account> List<S> findAll(Example<S> example, Sort sort) {
        return accountRepository.findAll(example, sort);
    }

    
}
    
