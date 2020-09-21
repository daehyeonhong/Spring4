package org.zerock.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

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

	@PostMapping(value = "/uploadAjaxAction", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxAction(MultipartFile[] uploadFile) {
		log.info("uploadAjaxPost");
		List<AttachFileDTO> list = new ArrayList<AttachFileDTO>();
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

			AttachFileDTO attachFileDTO = new AttachFileDTO();
			attachFileDTO.setFileName(multipartFile.getOriginalFilename());
			log.info(saveFile.getAbsolutePath());
			try {
				/* 파일 저장 경로 없을 시 경로 생성 후 저장 */
				if (!uploadPath.exists()) {
					uploadPath.mkdirs();
				}
				multipartFile.transferTo(saveFile);
				attachFileDTO.setUuid(uuid.toString());
				attachFileDTO.setUploadPath(uploadPath.toString());
				/* ThumbNail */
				if (checkImageType(saveFile)) {
					attachFileDTO.setImage(true);
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
				}
				list.add(attachFileDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return new ResponseEntity<List<AttachFileDTO>>(list, HttpStatus.OK);
	}

	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName) throws Exception {
		log.info("fileName:" + fileName);

		log.info(URLDecoder.decode(fileName, "UTF-8"));
		File file = new File("c:\\upload\\" + fileName);
		log.info("file:" + file);

		ResponseEntity<byte[]> result = null;

		HttpHeaders header = new HttpHeaders();
		try {
			header.add("content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
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