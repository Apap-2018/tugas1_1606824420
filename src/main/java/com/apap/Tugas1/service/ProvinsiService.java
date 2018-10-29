package com.apap.Tugas1.service;

import java.util.List;
import java.util.Optional;

import com.apap.Tugas1.model.InstansiModel;
import com.apap.Tugas1.model.PegawaiModel;
import com.apap.Tugas1.model.ProvinsiModel;

public interface ProvinsiService {
	Optional<ProvinsiModel> getProvinsiById(long id);
	List<ProvinsiModel> findAllProvinsi();
}
