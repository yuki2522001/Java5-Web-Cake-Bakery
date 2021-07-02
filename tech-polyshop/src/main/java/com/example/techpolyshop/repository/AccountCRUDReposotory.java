// package com.example.techpolyshop.repository;

// import java.util.List;

// import com.example.techpolyshop.domain.Account;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface AccountCRUDReposotory extends JpaRepository<Account, String>{

//     List<Account> findByNameContaining(String username);

//     Page<Account> findByNameContaining(String username, Pageable pageable);
// }