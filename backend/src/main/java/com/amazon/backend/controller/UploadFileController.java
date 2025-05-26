package com.amazon.backend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazon.backend.constant.AddressConstants;
import com.amazon.backend.constant.UploadFileConstants;
import com.amazon.backend.entities.Address;
import com.amazon.backend.response.ApiResponse;
import com.amazon.backend.service.UploadFileService;

@RestController
@RequestMapping("/files")
public class UploadFileController {

	
	@Autowired
	private UploadFileService uploadFileService;
	
	
	@PostMapping("/greeting")
	public ResponseEntity<ApiResponse<String>> uploadGreetings(@RequestParam("file") MultipartFile inputFile) throws IOException
	{
		
		 String uploadedfileresponse = uploadFileService.uploadGreetingFile(inputFile);
			ApiResponse<String> uploadedFile = new ApiResponse<String>(true, UploadFileConstants.FILE_UPLOADED_SUCCESSFULLY,
					uploadedfileresponse);

			return ResponseEntity.status(HttpStatus.OK).body(uploadedFile);
		
	}
	
	
	
}
