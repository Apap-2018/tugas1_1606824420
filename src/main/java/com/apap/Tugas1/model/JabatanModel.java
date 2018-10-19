package com.apap.Tugas1.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "jabatan")
public class JabatanModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 225)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	@NotNull
	@Size(max = 225)
	@Column(name = "deskripsi", nullable = false)
	private String deskripsi;
	
	@NotNull
	@Column(name = "gaji_pokok", nullable=false)
	private double gaji_Pokok;
	
	@ManyToMany(fetch = FetchType.LAZY, 
	cascade = {CascadeType.PERSIST,CascadeType.MERGE}, 
	mappedBy = "jabatanPegawaiList")
	private List<PegawaiModel> pegawaiJabatanList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public double getGajiPokok() {
		return gaji_Pokok;
	}

	public void setGajiPokok(double gajiPokok) {
		this.gaji_Pokok = gajiPokok;
	}

	public List<PegawaiModel> getPegawaiJabatanList() {
		return pegawaiJabatanList;
	}

	public void setPegawaiJabatanList(List<PegawaiModel> pegawaiJabatanList) {
		this.pegawaiJabatanList = pegawaiJabatanList;
	}
	
	
}
