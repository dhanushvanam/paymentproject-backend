package com.dbs.web.repository;

import com.dbs.web.beans.Currency;

public interface CurrencyRepository {

	Iterable<Currency> findAll();

}
