package com.apap.Tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.Tugas1.model.JabatanModel;
import com.apap.Tugas1.model.PegawaiModel;
import com.apap.Tugas1.model.ProvinsiModel;
import com.apap.Tugas1.service.JabatanService;
import com.apap.Tugas1.service.PegawaiService;
import com.apap.Tugas1.service.ProvinsiService;

@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	//@Autowired
	//private ProvinsiService provinsiService;
	
	@Autowired 
	private JabatanService jabatanService;
	@RequestMapping("/")
	private String home(Model model) {
		List<JabatanModel> listJabatan = jabatanService.findAllJabatan();
		model.addAttribute("listJabatan", listJabatan);

		
		return"home";
	}
	
	@RequestMapping(value = "/pegawai")
	private String viewPilot(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiByNip(nip);
		List<JabatanModel> jabatanPegawai = pegawai.getJabatanPegawaiList();
		double gajiPokokTerbesar = 0;
		for(JabatanModel jabatan : jabatanPegawai) {
			if(jabatan.getGajiPokok() > gajiPokokTerbesar) {
				gajiPokokTerbesar = jabatan.getGajiPokok();
			}
		}
		// gaji pokok + (%tunjangan x gaji pokok)
		int gajiPegawai = (int) ((int)gajiPokokTerbesar + (pegawai.getInstansi().getProvinsi().getPresentaseTunjangan() * gajiPokokTerbesar/100)); ;
	
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("jabatanPegawai", jabatanPegawai);
		model.addAttribute("gajiPegawai", gajiPegawai);
		return "lihat-pegawai";
	}
	@RequestMapping(value = "/pegawai/tambah")
	private String add(Model model) {
		PegawaiModel pegawai = new PegawaiModel();
		List<ProvinsiModel> provin = ProvinsiService.findAllProvinsi();
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("listOfProvinsi", provin);
		return "TambahPegawai";
	}
 
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	private String addPegawai(@ModelAttribute PegawaiModel pegawai) {
	PegawaiService.addPegawai(pegawai);
		return"tambah";
}
}
