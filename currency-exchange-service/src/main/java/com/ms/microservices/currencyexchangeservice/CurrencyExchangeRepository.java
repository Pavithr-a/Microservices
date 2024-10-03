package com.ms.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.microservices.currencyexchangeservice.bean.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

	CurrencyExchange findByFromAndTo(String from,String to);
}
