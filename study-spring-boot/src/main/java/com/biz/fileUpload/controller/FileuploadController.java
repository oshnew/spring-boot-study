package com.biz.fileUpload.controller;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.biz.fileUpload.vo.FileInfo;

/**
 * 파일업로드 컨트롤러
 * 
 * @author 엄승하
 */
@RestController
@RequestMapping("/file")
public class FileuploadController {

	@Autowired
	private ServletContext context;

	/**
	 * 파일 멀티파트 업로드 Rest full
	 * 
	 * @param inputFile
	 * @return
	 */
	@RequestMapping(value = "/upload", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<FileInfo> upload(@RequestParam("file") MultipartFile inputFile) {

		FileInfo fileInfo = new FileInfo();
		HttpHeaders headers = new HttpHeaders();
		if (!inputFile.isEmpty()) {

			try {

				String oriFileNm = inputFile.getOriginalFilename();
				File destinationFile = new File(context.getRealPath("/WEB-INF/uploaded") + File.separator + oriFileNm);
				inputFile.transferTo(destinationFile);
				headers.add("File Uploaded Successfully - ", oriFileNm);

				fileInfo.setFileName(destinationFile.getPath());
				fileInfo.setFileSize(inputFile.getSize());
				return new ResponseEntity<FileInfo>(fileInfo, headers, HttpStatus.OK);

			} catch (Exception e) {
				return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
		}
	}

}
