package com.apap.Tugas1.service;

import java.util.List;
import java.util.Optional;

import com.apap.Tugas1.model.InstansiModel;

public interface InstansiService {
	Optional<InstansiModel> getIntansiById(Long idInstansi);
	
	List<InstansiModel> getAllInstansi();

	List<InstansiModel> findAllInstansi();

	Optional<InstansiModel> getInstansiById(Long id);

	//Optional<InstansiModel> getInstansiDetailById(Optional<Long> id_Instansi);

	//Optional<InstansiModel> getInstansiDetailById(Long id);

	
}
