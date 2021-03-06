package com.apap.Tugas1.service;

import java.util.List;
import java.util.Optional;

import com.apap.Tugas1.model.InstansiModel;
import com.apap.Tugas1.model.PegawaiModel;

public interface PegawaiService {
	PegawaiModel getPegawaiByNip(String nip);

	List<PegawaiModel> findAllPegawai();
	List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi);

	String generateNip(PegawaiModel pegawai);

	Optional<PegawaiModel> getPegawaiById(long id);

	void addPegawai(PegawaiModel pegawai);

}