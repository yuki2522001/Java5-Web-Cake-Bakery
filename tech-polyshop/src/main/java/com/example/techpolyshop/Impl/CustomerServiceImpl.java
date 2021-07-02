package com.example.techpolyshop.Impl;

import java.util.List;
import java.util.Optional;

import com.example.techpolyshop.domain.Customer;
import com.example.techpolyshop.repository.CustomerRepository;
import com.example.techpolyshop.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    CustomerRepository customerRepository;

	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
	public Page<Customer> findByNameContaining(String name, Pageable pageable) {
		return customerRepository.findByNameContaining(name, pageable);
	}

	@Override
    public List<Customer> findByNameContaining(String name) {
		return customerRepository.findByNameContaining(name);
	}

	@Override
	public <S extends Customer> S save(S entity) {
		// Optional<Customer> optExist = findByEmail(entity.getPassword());
        // if (optExist.isPresent()) {
        //     if (StringUtils.isEmpty(entity.getPassword())) {
        //         entity.setPassword(optExist.get().getPassword());
        //     } else {
        //         entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        //     }
        // } else {
        //     entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        // }

        return customerRepository.save(entity);
	}

	public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);

    }
	@Override
	public Page<Customer> findAll(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public List<Customer> findAll(Sort sort) {
		return customerRepository.findAll(sort);
	}

	@Override
	public List<Customer> findAllById(Iterable<Long> ids) {
		return customerRepository.findAllById(ids);
	}

	@Override
	public Optional<Customer> findById(Long id) {
		return customerRepository.findById(id);
	}

	@Override
	public <S extends Customer> List<S> saveAll(Iterable<S> entities) {
		return customerRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		customerRepository.flush();
	}

	@Override
	public <S extends Customer> S saveAndFlush(S entity) {
		return customerRepository.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Long id) {
		return customerRepository.existsById(id);
	}

	@Override
	public <S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities) {
		return customerRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Customer> boolean exists(Example<S> example) {
		return customerRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Customer> entities) {
		customerRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return customerRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		customerRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		customerRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Customer entity) {
		customerRepository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		customerRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		customerRepository.deleteAllInBatch();
	}

	@Override
	public void deleteAll(Iterable<? extends Customer> entities) {
		customerRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		customerRepository.deleteAll();
	}

	@Override
	public Customer getById(Long id) {
		return customerRepository.getById(id);
	}
	public void updateResetToken(String token, String email) throws ClassNotFoundException {
        Customer customer = customerRepository.findbyemail(email);
        if (customer != null) {
            customer.setResetToken(token);
            customerRepository.save(customer);
        } else {
            throw new ClassNotFoundException("Could not find any customer with the email " + email);
        }
    }

    public Customer getByResetToken(String token) {
        return customerRepository.findByResetToken(token);
    }

    public void updatePassword(Customer customer, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        customer.setPassword(encodedPassword);

        customer.setResetToken(null);
        customerRepository.save(customer);

    }

    public Customer findByResetToken(String resetToken) {
        return customerRepository.findByResetToken(resetToken);
    }
	// @Override
	// public Customer login(String email, String password) {
	// 	// TODO Auto-generated method stub
	// 	return null;
	// }
    
}
