package com.vrain.utils;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class FileUtil {
	private static String seperator = System.getProperty("file.separator");

	/**
	 * 返回保存系统文件的根目录
	 * 
	 * @return
	 */
	public static String getBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "D:/nickname/";
		} else {
			basePath = "/home/nickname/";
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}

	/**
	 * 生产新闻的文件保存路径:/news/(newCtgId)/(newsId)/
	 * 
	 * @param request
	 * @return
	 */
	public static String getNewsPath(HttpServletRequest request, String newId) {
		StringBuilder newsPathBuilder = new StringBuilder();
		PageData data = new PageData(request);
		// 获取上传新闻的类别id
		long newCtgId = (long) data.get("newCtgId");
		// 拼接路径
		newsPathBuilder.append("/news" + "/");
		newsPathBuilder.append(newCtgId + "/");
		newsPathBuilder.append(newId + "/");
		// 根据不同的系统格式化路径
		String newsPath = newsPathBuilder.toString().replace("/", seperator);
		// 返回
		return newsPath;
	}

	/**
	 * 将上传的文件更改名字
	 * 
	 * @param fileName
	 *            原文件名
	 * @return 新的文件名
	 */
	private static String getFileName(String fileName) {
		// 获取文件后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		// 获取当前时间戳
		String date = DateTool.getData("yyyyMMddHHmmss");
		// 随机生成四位随机数
		int random = (int) ((Math.random() * 9 + 1) * 1000);
		// 返回：时间戳+四位随机数+文件后缀名
		return "" + date + random + suffixName;
	}

	/**
	 * 保存上传的文件
	 * 
	 * @param multipart
	 *            要保存的文件对象
	 * @param filePath
	 * @throws Exception
	 */
	public static String saveFile(CommonsMultipartFile multipart, String filePath) throws Exception {
		// 判断保存文件的路径是否存在，不存在就创建
		File parent = new File(getBasePath() + filePath);
		if (!parent.exists()) {
			parent.mkdirs();
		}
		// 保存文件
		File file = new File(parent, getFileName(multipart.getOriginalFilename()));
		multipart.transferTo(file);
		// 返回文件保存的路径
		return file.getPath();
	}

	/**
	 * 递归删除文件（如果输入的是文件夹，将会删除该文件夹下的所有文件）
	 * 
	 * @param file
	 *            需要删除的文件或文件夹
	 * @return
	 */
	public static boolean deleteFile(File file) {
		if (file.exists()) {
			// 判断是否是文件还是文件夹
			if (file.isDirectory()) {
				// 是文件夹
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {

					if (files[i].isFile()) {
						files[i].delete();
					} else {
						deleteFile(files[i]);
					}
				}
			} else {
				// 是文件
				file.delete();
			}
		}
		return true;
	}

}
