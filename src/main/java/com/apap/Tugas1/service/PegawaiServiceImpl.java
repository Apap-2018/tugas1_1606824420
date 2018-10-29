package com.apap.Tugas1.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.Tugas1.model.InstansiModel;
import com.apap.Tugas1.model.PegawaiModel;
import com.apap.Tugas1.repository.PegawaiDb;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {

	@Autowired
	private PegawaiDb pegawaiDb;
	
	@Override
	public PegawaiModel getPegawaiByNip(String nip) {
		return pegawaiDb.findByNip(nip);
	}

	@Override
	public List<PegawaiModel> findAllPegawai() {
		// TODO Auto-generated method stub
		return pegawaiDb.findAll();
	}

	@Override
	public List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi) {
		// TODO Auto-generated method stub
		return pegawaiDb.findByInstansi(instansi);
	}

	@Override
	public String generateNip(PegawaiModel pegawai) {
		DateFormat df = new SimpleDateFormat("ddMMYY");
		Date tglLahir = pegawai.getTanggal_lahir();
		String formatted = df.format(tglLahir);
		System.out.println("date->"+formatted);
		
		Long kodeInstansi = pegawai.getInstansi().getId();
		System.out.println("kode instansi->"+kodeInstansi);
		
		int idAkhir = 0;
		for (PegawaiModel peg : findAllPegawai()) {
			if (peg.getTanggal_lahir().equals(pegawai.getTanggal_lahir()) && peg.getTahun_masuk().equals(pegawai.getTahun_masuk())) {
				idAkhir+=1;
			}
		}
		idAkhir+=1;
		
		String kodeMasuk = "";
		if (idAkhir<10) {
			kodeMasuk = "0"+idAkhir;
		}
		else {
			kodeMasuk = Integer.toString(idAkhir);
		}
		
		System.out.println(kodeInstansi+formatted+pegawai.getTahun_masuk()+kodeMasuk);
		return kodeInstansi+formatted+pegawai.getTahun_masuk()+kodeMasuk;
	}

	@Override
	public Optional<PegawaiModel> getPegawaiById(long id) {
		return pegawaiDb.findById(id);
	}

	@Override
	public void addPegawai(PegawaiModel pegawai) {
		pegawaiDb.save(pegawai);
	}


	

}
