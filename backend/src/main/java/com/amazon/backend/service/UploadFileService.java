package com.amazon.backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazon.backend.GlobalException.UploadProperExtFile;
import com.amazon.backend.GlobalException.UploadValidFileSize;
import com.amazon.backend.constant.UploadFileConstants;
import com.amazon.backend.entities.UploadFile;
import com.amazon.backend.repository.UploadFileRepository;

import org.springframework.util.StringUtils;

@Service
public class UploadFileService {

	@Value("${file.upload.greetings}")
	private String uploadFilePath;

	private int uploadFileSize = 1024 * 200;

	@Autowired
	private UploadFileRepository uploadFileRepository;

	public String uploadGreetingFile(MultipartFile inpFile) throws IOException {

		Path uploadpath = Paths.get(uploadFilePath);

		if (!Files.exists(uploadpath)) {
			Files.createDirectories(uploadpath);
		}

		String filenameextension = StringUtils.getFilenameExtension(inpFile.getOriginalFilename());
		String[] allowedfilext = { "wav", "mp3", "WAV" };

		Boolean verifyfileext = Arrays.stream(allowedfilext).anyMatch(t -> t.equals(filenameextension));
		if (verifyfileext == false) {
			throw new UploadProperExtFile(UploadFileConstants.UPLOAD_FILE_EXCEPTION);
		}

		if (inpFile.getSize() < uploadFileSize) {
			throw new UploadValidFileSize(UploadFileConstants.UPLOAD_FILE_SIZE_EXCEPTION);
		}

		String fileName = UUID.randomUUID().toString() + "PBX.Ext ---" + inpFile.getOriginalFilename();

		Path uploadpath1 = Paths.get(uploadFilePath + fileName);
		Files.copy(inpFile.getInputStream(), uploadpath1);

		UploadFile uploadFile = UploadFile.builder().fileName(fileName).fileType(filenameextension)
				.filePath(uploadpath1.toString()).build();

		uploadFileRepository.save(uploadFile);

		return "File has been uploaded successfully";

	}
	
	

}
