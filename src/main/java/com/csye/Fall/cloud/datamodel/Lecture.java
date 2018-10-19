package com.csye.Fall.cloud.datamodel;

import java.io.File;
import java.util.List;

public class Lecture {

	private List<File> notes;
	private List<File> material;
	private long lectureId;
	
	public List<File> getNotes() {
		return notes;
	}
	public void setNotes(List<File> notes) {
		this.notes = notes;
	}
	public List<File> getMaterial() {
		return material;
	}
	public void setMaterial(List<File> material) {
		this.material = material;
	}
	public long getLectureId() {
		return lectureId;
	}
	public void setLectureId(long lectureId) {
		this.lectureId = lectureId;
	}
	public Lecture() {
		
	}
	public Lecture(List<File> notes, List<File> material, long lectureId) {
		super();
		this.notes = notes;
		this.material = material;
		this.lectureId = lectureId;
	}
}
