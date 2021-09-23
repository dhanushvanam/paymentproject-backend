package com.dbs.web.repository;

import com.dbs.web.beans.Currency;
import com.dbs.web.beans.Transfertypes;

public interface TransferTypesRepository {

	Iterable<Transfertypes> findAll();

}
