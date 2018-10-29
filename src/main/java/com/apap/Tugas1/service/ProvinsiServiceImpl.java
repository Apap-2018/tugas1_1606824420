package com.apap.Tugas1.service;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.Tugas1.model.ProvinsiModel;
import com.apap.Tugas1.repository.ProvinsiDb;
@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService{
	@Autowired
	private ProvinsiDb provinsiDb;

	@Override
	public Optional<ProvinsiModel> getProvinsiById(long id) {
		// TODO Auto-generated method stub
		return provinsiDb.findById(id);
	}

	@Override
	public List<ProvinsiModel> findAllProvinsi() {
		return provinsiDb.findAll();
	}

	//@Override
	//public List<ProvinsiModel> getProvinsiById(long id){
		//return ProvinsiModel.findById(id);
	//}
	
}
