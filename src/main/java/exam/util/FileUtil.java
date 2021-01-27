package exam.util;

public class FileUtil {
	
	public static String getFileName(String contentDisposition) {
		String[] attrs = contentDisposition.split("; ");
		for(int i = 0; i < attrs.length; i++) {
			if(attrs[i].startsWith("filename")) {
				return attrs[i].substring(10, attrs[i].length()-1);
			}
		}
		return "";
	}
	
	public static String getFileExtension(String filename) {
		if(filename.indexOf(".") == -1 ) {
			return "";
		}else {
			return filename.substring(filename.lastIndexOf("."), filename.length());
		}
	}
}
