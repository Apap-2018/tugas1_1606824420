package com.apap.Tugas1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.Tugas1.model.JabatanModel;
import com.apap.Tugas1.service.JabatanService;


@Controller
public class JabatanController {
	@Autowired
	JabatanService jabatanService;
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
	private String tambahJabatan(Model model) {
		model.addAttribute("jabatan", new JabatanModel());
		return "tambah-jabatan";
	}

	
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
	private String tambahPegawaiSubmit(@ModelAttribute JabatanModel jabatan) {
		jabatanService.addJabatan(jabatan);
		return "submit";
	} 
	@RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
	private String findPegawai(@RequestParam(value = "idJabatan") Long id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanById(id).get();
		if (jabatan!=null ) {
			model.addAttribute("jabatan", jabatan);
			model.addAttribute("title", "Detail Jabatan");
			return "lihat-jabatan";
		}
		return "error";
		
	}
	
	@RequestMapping(value="/jabatan/hapus", method=RequestMethod.POST)
	private String hapusJabatan(String jabatanId) {
		JabatanModel jabatan = jabatanService.getJabatanById(Long.parseLong(jabatanId)).get();
		System.out.println(jabatan);
		Optional<JabatanModel> listPegawai = jabatanService.getJabatanById(Long.parseLong(jabatanId));
		System.out.println(listPegawai);
		if(listPegawai.empty() != null) {
			jabatanService.deleteJabatan(jabatan);
			return "delete";
		}
		return "error";
	}
	@RequestMapping(value = "/jabatan/viewall", method = RequestMethod.GET)
	public String ubahJabatan (Model model) {
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", listJabatan);
		
		return "viewall";
	}
}

		
	

