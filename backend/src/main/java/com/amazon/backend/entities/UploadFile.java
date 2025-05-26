package com.amazon.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table (name = "uploadfile")
public class UploadFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uploadedId;
	private String fileName;
	private String filePath;
	private String fileType;

}
