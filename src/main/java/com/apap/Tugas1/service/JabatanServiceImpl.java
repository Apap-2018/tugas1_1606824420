package com.apap.Tugas1.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.Tugas1.model.JabatanModel;
import com.apap.Tugas1.repository.JabatanDb;


@Service
@Transactional
public class JabatanServiceImpl implements JabatanService{
	
	@Autowired
	private JabatanDb jabatanDb;
	


	@Override
	public List<JabatanModel> findAllJabatan() {
		return jabatanDb.findAll();
	}

	@Override
	public Optional<JabatanModel> getJabatanById(Long id) {
		// TODO Auto-generated method stub
		return jabatanDb.findById(id);
	}

	@Override
	public void addJabatan(JabatanModel jabatan) {
		// TODO Auto-generated method stub
		jabatanDb.save(jabatan);
	}
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		jabatanDb.deleteById(id);
	}

	@Override
	public void deleteById(JabatanModel jabatan) {
		// TODO Auto-generated method stub
		jabatanDb.delete(jabatan);
	}

	@Override
	public void deleteJabatan(JabatanModel jabatan) {
		// TODO Auto-generated method stub
		jabatanDb.delete(jabatan);
	}

	@Override
	public List<JabatanModel> getAllJabatan() {
		// TODO Auto-generated method stub
		return jabatanDb.findAll();
	}
	
}