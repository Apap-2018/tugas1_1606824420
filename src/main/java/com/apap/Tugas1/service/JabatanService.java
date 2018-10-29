package com.apap.Tugas1.service;

import java.util.List;
import java.util.Optional;

import com.apap.Tugas1.model.JabatanModel;

public interface JabatanService {
	/**Optional<JabatanModel> getJabatanById(long id);
	List<JabatanModel> findAllJabatan();
**/
	Optional<JabatanModel> getJabatanById(Long id);
	
	void addJabatan(JabatanModel jabatan);
	
	void deleteById(JabatanModel jabatan);

	List<JabatanModel> findAllJabatan();

	void deleteById(Long id);

	void deleteJabatan(JabatanModel jabatan);

	List<JabatanModel> getAllJabatan();
}

