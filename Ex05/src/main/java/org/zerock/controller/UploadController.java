package org.zerock.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UploadController {

	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("uploadForm");
	}

	@PostMapping("/uploadFormAction")
	public String uploadFormAction(MultipartFile[] uploadFile) {
		log.info("uploadFormAction");
		String uploadFolder = "c:\\upload";
		for (MultipartFile multipartFile : uploadFile) {
			log.info(":::::::::::::::::");
			log.info("UploadFileName==> " + multipartFile.getOriginalFilename());
			log.info("UploadFileSize==> " + multipartFile.getSize());

			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());

			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "/uploadForm";
	}

	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("uploadAjax");
	}

	@PostMapping("/uploadAjaxAction")
	public void uploadAjaxAction(MultipartFile[] uploadFile) {
		log.info("uploadAjaxPost");
		String uploadFolder = "c:\\upload";
		File uploadPath = new File(uploadFolder, getFolder());

		for (MultipartFile multipartFile : uploadFile) {
			log.info(":::::::::::::::::");
			log.info("UploadFileName==> " + multipartFile.getOriginalFilename());
			log.info("UploadFileSize==> " + multipartFile.getSize());

			UUID uuid = UUID.randomUUID();
			log.info(uuid);

			String uploadFileName = uuid.toString() + "_" + multipartFile.getOriginalFilename();

			File saveFile = new File(uploadPath, uploadFileName);

			log.info(saveFile.getAbsolutePath());
			try {
				/* 파일 저장 경로 없을 시 경로 생성 후 저장 */
				if (!uploadPath.exists()) {
					uploadPath.mkdirs();
				}
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private String getFolder() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = simpleDateFormat.format(date);
		return str.replace("-", File.separator);
	}

	/* 전송될 파일의 Type_Method (Image?) */
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}