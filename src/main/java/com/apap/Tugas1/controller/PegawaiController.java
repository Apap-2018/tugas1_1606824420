package com.apap.Tugas1.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.Tugas1.model.InstansiModel;
import com.apap.Tugas1.model.JabatanModel;
import com.apap.Tugas1.model.PegawaiModel;
import com.apap.Tugas1.model.ProvinsiModel;
import com.apap.Tugas1.service.InstansiService;
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
	@Autowired 
	private InstansiService instansiService;
	@Autowired
	private ProvinsiService provinsiService;
	private Long id;
	@RequestMapping("/")
	private String home(Model model) {
		List<JabatanModel> listJabatan = jabatanService.findAllJabatan();
		List<InstansiModel> listInstansi = instansiService.getAllInstansi();
		model.addAttribute("listJabatan", listJabatan);
		model.addAttribute("listInstansi", listInstansi);
		
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
	@RequestMapping(value = "/pegawai/termuda-tertua", method = RequestMethod.GET)
	private String oldYoung(@RequestParam(value = "idInstansi") Long idInstansi, Model model) {
		List<PegawaiModel> all = pegawaiService.findAllPegawai();
		InstansiModel instansi = instansiService.getIntansiById(idInstansi).get();
		ArrayList<PegawaiModel> baru = new ArrayList<>();
		for (PegawaiModel pegawai : all) {
			if (pegawai.getInstansi().equals(instansi)) {
				baru.add(pegawai);
			}
		}
		PegawaiModel termuda = Collections.min(baru,comp);
		PegawaiModel tertua = Collections.max(baru,comp);
		model.addAttribute("termuda", termuda);
		model.addAttribute("tertua", tertua);
		//model.addAttribute("title", "Lihat Pegawai Tertua & Termuda");
		return "tua-muda";
	}
	
	@RequestMapping(value = "/pegawai/cari")
	private String cari(@RequestParam(value = "idProvinsi", required=false) Optional<Long> idProvinsi,
			@RequestParam(value="idInstansi",  required=false) Optional<Long> id_Instansi,
			@RequestParam(value="idJabatan", required=false) Optional<Long> idJabatan,
			Model model) {
		List<JabatanModel> allJabatan = jabatanService.findAllJabatan();
		List<InstansiModel> allInstansi = instansiService.findAllInstansi();
		List<ProvinsiModel> allProvinsi = provinsiService.findAllProvinsi();
		model.addAttribute("allJabatan",allJabatan);
		model.addAttribute("allInstansi",allInstansi);
		model.addAttribute("allProvinsi",allProvinsi);
		
		
		List<PegawaiModel> allPegawai = pegawaiService.findAllPegawai();
		List<PegawaiModel> hasil = new ArrayList<>();
		if (idProvinsi.isPresent()) {
			if (id_Instansi.isPresent() && idJabatan.isPresent()) {
				List<PegawaiModel> temp = new ArrayList<>();
				InstansiModel instansi = instansiService.getInstansiById(id).get();
				System.out.println(instansi.getNama());
				JabatanModel jabatan = jabatanService.getJabatanById(id).get();
				System.out.println(jabatan.getNama());
				temp = pegawaiService.getPegawaiByInstansi(instansi);
				System.out.println(temp.size());
				for (PegawaiModel pegawai : temp) {
					for (JabatanModel jab : pegawai.getJabatanPegawaiList()) {
						if (jab.equals(jabatan)) {
							hasil.add(pegawai);
						}
					}
				}
				System.out.println(hasil.size());
			}
			else if (!(id_Instansi.isPresent()) && idJabatan.isPresent()) {
				//provinsi
				//jabatan
				//provinsi & jabatan
				List<PegawaiModel> temp = new ArrayList<>();
				ProvinsiModel prov = provinsiService.getProvinsiById(id).get();
				for (PegawaiModel pegawai : allPegawai) {
					if (pegawai.getInstansi().getProvinsi().equals(prov)) {
						temp.add(pegawai);
					}
				}
				JabatanModel jabatan = jabatanService.getJabatanById(id).get();
				for (PegawaiModel pegawai : temp) {
					for (JabatanModel jab : pegawai.getJabatanPegawaiList()) {
						if (jab.equals(jabatan)) {
							hasil.add(pegawai);
						}
					}
				}
			}
			else if(id_Instansi.isPresent() && !(idJabatan.isPresent())) { 
				//provinsi dan instansi
				//System.out.println("provinsi dan instansi");
				//Long idInstansi = Long.parseLong(id_Instansi.get());
				InstansiModel instansi = instansiService.getInstansiById(id).get();
				hasil = pegawaiService.getPegawaiByInstansi(instansi);
				
			}
			else if(!(id_Instansi.isPresent()) && !(idJabatan.isPresent())) {
				//just provinsi
				ProvinsiModel prov = provinsiService.getProvinsiById(id).get();
				for (PegawaiModel pegawai : allPegawai) {
					if(pegawai.getInstansi().getProvinsi().equals(prov)) {
						hasil.add(pegawai);
					}
				}
			}
		}
		else {
			//jabatan
			//instansi
			//jabatan dan instansi-worked
			if (idJabatan.isPresent() && id_Instansi.isPresent()) {
				List<PegawaiModel> temp = new ArrayList<>();
				InstansiModel instansi = instansiService.getInstansiById(id).get();
				JabatanModel jabatan = jabatanService.getJabatanById(id).get();
				temp = pegawaiService.getPegawaiByInstansi(instansi);
				for (PegawaiModel pegawai : temp) {
					for (JabatanModel jab : pegawai.getJabatanPegawaiList()) {
						if (jab.equals(jabatan)) {
							hasil.add(pegawai);
						}
					}
				}
			}
			
			//jabatan doang
			else if(idJabatan.isPresent() && !(id_Instansi.isPresent())) {
			//	Long idJabatan = Long.parseLong(idJabatan.get());
				JabatanModel jabatan = jabatanService.getJabatanById(id).get();
				for (PegawaiModel pegawai : allPegawai) {
					for (JabatanModel jab : pegawai.getJabatanPegawaiList()) {
						if (jab.equals(jabatan)) {
							hasil.add(pegawai);
						}
					}
				}
			}
			//instansi doang
			else if(!(idJabatan.isPresent()) && id_Instansi.isPresent()) {
			//	Long idInstansi = Long.parseLong(id_Instansi.get());
				InstansiModel instansi = instansiService.getInstansiById(id).get();
				hasil = pegawaiService.getPegawaiByInstansi(instansi);
			}
			else if(!(idJabatan.isPresent()) && !(id_Instansi.isPresent())) {
				hasil = null;
			}
		}
		model.addAttribute("allData",hasil);
		return "cari-pegawai";
		
	}
	
	public static Comparator<PegawaiModel> comp = new Comparator<PegawaiModel>() {

		@Override
		public int compare(PegawaiModel o1, PegawaiModel o2) {
			if (o1.calculateUmur()<o2.calculateUmur()) {
				return -1;
			}
			else if (o1.calculateUmur()>o2.calculateUmur()) {
				return 1;
			}
			return 0;
		}
	};
	
	
/**	@RequestMapping(value = "/pegawai/tambah")
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
**/
}
