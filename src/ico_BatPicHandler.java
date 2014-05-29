import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileFilter;

import javax.imageio.ImageIO;

public class ico_BatPicHandler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String dirName = "C:\\";
		File[] listFileName = GetImageFileList(dirName);
		for (int i = 0; i < listFileName.length; i++) {
			String handleFile = listFileName[i].getAbsolutePath();
			try {
				ImgConvert(512, 512, handleFile);
				ImgConvert(72, 72, handleFile);
			} catch (Exception e) {
				System.out.println("系统异常");
				e.printStackTrace();
			}
		}
		System.out.println("处理完毕");
	}
	
	public static boolean FormatPic(String inputPath,String outputFileName,int weight,int height){
		File[] listFileName = GetImageFileList(inputPath);
		for (int i = 0; i < listFileName.length; i++) {
			String handleFile = listFileName[i].getAbsolutePath();
			try {
				ImgConvertPng(weight, height, handleFile,outputFileName);
			} catch (Exception e) {
				System.out.println("系统异常");
				e.printStackTrace();
				return false;
			}
		}
		System.out.println("处理完毕");
		return true;
	}
	
	public static boolean FormatIcoPic(String inputPath,String outputFileName,int weight,int height){
		File[] listFileName = GetImageFileList(inputPath);
		for (int i = 0; i < listFileName.length; i++) {
			String handleFile = listFileName[i].getAbsolutePath();
			try {
				ImgIcoConvert(weight, height, handleFile,outputFileName);
			} catch (Exception e) {
				System.out.println("系统异常");
				e.printStackTrace();
				return false;
			}
		}
		System.out.println("处理完毕");
		return true;
	}

	public static File[] GetImageFileList(String path) {
		File file = new File(path);
		if (file.exists() && file.isDirectory()) {
			return file.listFiles(new FileFilter() {

				@Override
				public boolean accept(File pathname) {
					String fileName = pathname.getName();
					if (fileName.contains("png") || fileName.contains("bmp") || fileName.contains("jpg")) {
						return true;
					}
					return false;
				}
			});
		} else {
			System.out.println("源目录异常!程序退出");
			System.exit(0);
		}
		return null;
	}

	public static void ImgConvert(int width, int height, String source) throws Exception {
		File inputFile = new File(source);
		String inputFileName = inputFile.getName();
		String format = inputFileName.substring(inputFileName.lastIndexOf('.') + 1);
		File outtemp = new File(inputFile.getParent() + "\\" + width +'.'+ format);
		RenderedImage im = convertIco(width, height, ImageIO.read(inputFile));
		ImageIO.write(im, format, outtemp);
	}
	
	public static void ImgConvertPng(int width, int height, String source,String outputFilename) throws Exception {
		File inputFile = new File(source);
		String inputFileName = inputFile.getName().substring(0, inputFile.getName().indexOf("."));
		String format = "jpg";
		File outtemp = new File(inputFile.getParent() + "\\" + outputFilename + "\\" + inputFileName+".jpg");
		if(!outtemp.exists()){outtemp.mkdirs();}
		RenderedImage im = convertPic(width, height, ImageIO.read(inputFile));
		ImageIO.write(im, format, outtemp);
	}
	
	public static void ImgConvert(int width, int height, String source,String outputFilename) throws Exception {
		File inputFile = new File(source);
		String inputFileName = inputFile.getName();
		String format = inputFileName.substring(inputFileName.lastIndexOf('.') + 1);
		File outtemp = new File(inputFile.getParent() + "\\" + outputFilename + "\\" + inputFileName);
		if(!outtemp.exists()){outtemp.mkdirs();}
		RenderedImage im = convertPic(width, height, ImageIO.read(inputFile));
		ImageIO.write(im, format, outtemp);
	}
	
//	410522198412133212
	public static void ImgIcoConvert(int width, int height, String source,String outputFilename) throws Exception {
		File inputFile = new File(source);
		String inputFileName = inputFile.getName();
		String format = inputFileName.substring(inputFileName.lastIndexOf('.') + 1);
		File outtemp = new File(inputFile.getParent() + "\\" + width+"."+format);
		if(!outtemp.exists()){outtemp.mkdirs();}
		RenderedImage im = convertIco(width, height, ImageIO.read(inputFile));
		ImageIO.write(im, format, outtemp);
	}
	public static BufferedImage convertIco(int width, int height, BufferedImage input) throws Exception {
		BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Image image = input.getScaledInstance(output.getWidth(), output.getHeight(), output.getType());
		output.createGraphics().drawImage(image, null, null);
		return output;
	}
	
	public static BufferedImage convertPic(int width, int height, BufferedImage input) throws Exception {
		BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Image image = input.getScaledInstance(output.getWidth(), output.getHeight(), output.getType());
		output.createGraphics().drawImage(image, null, null);
		return output;
	}

}
