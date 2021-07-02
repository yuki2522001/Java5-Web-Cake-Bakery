package com.example.techpolyshop.repository;


import java.util.List;

import com.example.techpolyshop.domain.Account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{
    Account findByUsername(String username);
    List<Account> findByUsernameContaining(String username);

    Page<Account> findByUsernameContaining(String username, Pageable pageable);
   
}
