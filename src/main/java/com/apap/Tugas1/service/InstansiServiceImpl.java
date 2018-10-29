package com.apap.Tugas1.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.Tugas1.model.InstansiModel;
import com.apap.Tugas1.repository.InstansiDb;
@Service
@Transactional
public class InstansiServiceImpl implements InstansiService {
	@Autowired
	private InstansiDb InstansiDb;

	@Override
	public Optional<InstansiModel> getIntansiById(Long id) {
		// TODO Auto-generated method stub
		return InstansiDb.findById(id);
	}

	@Override
	public List<InstansiModel> getAllInstansi() {
		// TODO Auto-generated method stub
		return InstansiDb.findAll();
	}

	@Override
	public List<InstansiModel> findAllInstansi() {
		// TODO Auto-generated method stub
		return InstansiDb.findAll();
	}

/**	@Override
	public Optional<InstansiModel> getInstansiDetailById(Long idInstansi) {
		// TODO Auto-generated method stub
		return InstansiDb.findById(idInstansi);
	}
**/
	@Override
	public Optional<InstansiModel> getInstansiById(Long id) {
		// TODO Auto-generated method stub
		return InstansiDb.findById(id);
	}


}
