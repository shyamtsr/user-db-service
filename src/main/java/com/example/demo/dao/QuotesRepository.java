package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.QuoteDto;

@Repository
public interface QuotesRepository extends JpaRepository<QuoteDto, Integer> {

	public List<QuoteDto> findByUserName(String userName);

}
