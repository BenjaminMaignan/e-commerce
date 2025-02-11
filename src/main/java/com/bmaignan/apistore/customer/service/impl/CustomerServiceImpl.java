package com.bmaignan.apistore.customer.service.impl;

import com.bmaignan.apistore.customer.dto.CustomerRequestDto;
import com.bmaignan.apistore.customer.dto.CustomerResponseDto;
import com.bmaignan.apistore.customer.mapper.CustomerMapper;
import com.bmaignan.apistore.customer.repository.CustomerDao;
import com.bmaignan.apistore.customer.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.bmaignan.apistore.core.exception.ExceptionFactory.notFound;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public List<CustomerResponseDto> findAllCustomers() {
        return customerDao.findAll().stream()
                .map(CustomerMapper::toResponseDto)
                .toList();
    }


    @Override
    public CustomerResponseDto getCustomer(UUID id) {
        return customerDao.findById(id)
                .map(CustomerMapper::toResponseDto)
                .orElseThrow(() -> notFound("Customer not found"));
    }

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto customerDTO) {
        return CustomerMapper.toResponseDto(customerDao.save(CustomerMapper.toEntity(customerDTO)));
    }

    @Override
    public CustomerResponseDto updateCustomer(UUID id, CustomerRequestDto customerDTO) {
        if (!customerDTO.id().equals(id)) {
            throw notFound("Customer not found"); // FIXME : change the error
        }

        return CustomerMapper.toResponseDto(customerDao.save(CustomerMapper.toEntity(customerDTO)));
    }

    @Override
    public void deleteCustomer(UUID id) {
        customerDao.deleteById(id);
    }
}
