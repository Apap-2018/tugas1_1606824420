package com.apap.Tugas1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.Tugas1.model.PegawaiModel;
import com.apap.Tugas1.model.InstansiModel;
import com.apap.Tugas1.model.JabatanModel;

@Repository
public interface PegawaiDb extends JpaRepository<PegawaiModel, Long>{
	PegawaiModel findByNip(String nip);

	List<PegawaiModel> findByInstansi(InstansiModel instansi);

	//List<PegawaiModel> findByProvinsi();

	
	
}