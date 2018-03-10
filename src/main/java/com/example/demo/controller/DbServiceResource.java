package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.QuotesRepository;
import com.example.demo.dto.QuoteDto;
import com.example.demo.dto.Quotes;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

	@Autowired
	private QuotesRepository quotesRepository;
	
	@GetMapping("{username}")
	public List<String> getQuotes(@PathVariable("username") final String userName){
		
		return getQuotesByUserName(userName);
	}

	@PostMapping("/add")
	public List<String> add(@RequestBody final Quotes quotes) {
 
		quotes.getQuotes().stream().map(quote -> new QuoteDto(quotes.getUserName(), quote))
				.forEach(quote -> quotesRepository.save(quote));
		return getQuotesByUserName(quotes.getUserName());
	}

	@PostMapping("/delete/{userName}")
	public List<String> add(@PathVariable("userName") final String userName) {

		List<QuoteDto> quoteId = quotesRepository.findByUserName(userName);
		quotesRepository.delete(quoteId);
		return getQuotesByUserName(userName);
	}

	private List<String> getQuotesByUserName(final String userName) {
		List<QuoteDto> quotes = quotesRepository.findByUserName(userName);
		List<String> collectQuoteInfo = quotes.stream().map(quote -> quote.getQuotes()).collect(Collectors.toList());
		return collectQuoteInfo;
	}
}
