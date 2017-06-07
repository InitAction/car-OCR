package org.easypr;

/**
 * fujunhao
 */
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.java1234.pojo.Result;
import com.java1234.service.PlateRecognise;
import com.java1234.service.impl.PlateRecogniseImpl;

@SpringBootApplication
@RestController
public class MainApplication {

	private static final Logger logger = Logger.getLogger(MainApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@RequestMapping(value = "classify_upload")
	@ResponseBody
	public Result upload(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty() || file==null) {
			List<String> responses = new ArrayList<String>();
			responses.add("文件为空");
			logger.info("文件为空");
			return new Result(-1, responses, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}
		String fileName = file.getOriginalFilename();
		@SuppressWarnings("unused")
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		String filePath = "F:\\test\\image\\";
		// fileName = UUID.randomUUID() + suffixName;
		File dest = new File(filePath + fileName);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
			PlateRecognise plateRecognise = new PlateRecogniseImpl();
			String img = filePath + fileName;
			logger.info(img);
			List<String> res = plateRecognise.plateRecognise(filePath + fileName);
			if (res.size() < 1 || res.contains("")) {
				logger.info("识别失败！不如换张图片试试？");
				List<String> responses = new ArrayList<String>();
				responses.add("识别不了");
				return new Result(-1, responses, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			}
			Result result = new Result(201, plateRecognise.plateRecognise(filePath + fileName),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			logger.info(result.toString());
			return result;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> responses = new ArrayList<String>();
		responses.add("上传失败");
		return new Result(-1, responses, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}

}
