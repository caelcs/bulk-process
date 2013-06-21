/*
 * Copyright 2011 Motorola Mobility, All rights reserved.
 */
package com.motorola.medios.cis.content.bulk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.google.common.io.Files;

public class FileUtils {

	/**
	 * Unzips a file in baseDirPath directory
	 * 
	 * @param baseDirPath
	 *            the path of the directory where the files will be uzipped
	 * @param filePath
	 *            the file to unzip
	 * @return the folder were files were unziped
	 * @throws IOException
	 */
	public List<File> unzipFile(final String baseDirPath, final String filePath)
			throws IOException {
		final List<File> unzippedFiles = new ArrayList<File>();
		final File baseDir = new File(baseDirPath);
		final ZipInputStream zis = new ZipInputStream(new FileInputStream(
				filePath));
		ZipEntry entry;
		while ((entry = zis.getNextEntry()) != null) {
			if (!entry.isDirectory()) {
				final File file = new File(baseDir, entry.getName());
				unzippedFiles.add(file);
			}
		}

		zis.close();

		return unzippedFiles;
	}

	public void renameTempFiles(final List<File> unzippedFiles)
			throws IOException {
		for (File file : unzippedFiles) {
			if (!file.renameTo(new File(file.getAbsolutePath().substring(0,
					file.getAbsolutePath().length() - 5)))) {
				throw new IOException("Couldn't rename file: "
						+ file.getAbsolutePath());
			}
		}
	}

	/**
	 * Zips the folder inFolder into outFile.zip
	 * 
	 * @param inFolder
	 *            The folder to zip
	 * @param outFile
	 *            The result zip file
	 * @throws IOException
	 */
	public void zipFolder(final String srcFolder, final String destZipFile) throws IOException {
		if (!folderIsEmpty(srcFolder)) {
			Files.createParentDirs(new File(destZipFile));
			ZipOutputStream zip = null;
			FileOutputStream fileWriter = null;

			fileWriter = new FileOutputStream(destZipFile);
			zip = new ZipOutputStream(fileWriter);

			addFolderToZip("", srcFolder, zip);
			zip.flush();
			zip.close();
			fileWriter.flush();
			fileWriter.close();
		}
	}

	private void addFolderToZip(final String path, final String srcFolder,
			final ZipOutputStream zip) throws IOException {
		final File folder = new File(srcFolder);

		if (folder.list() != null) {
			for (String fileName : folder.list()) {
				if ("".equals(path)) {
					addFileToZip(folder.getName(), srcFolder + "/" + fileName,
							zip);
				} else {
					addFileToZip(path + "/" + folder.getName(), srcFolder + "/"
							+ fileName, zip);
				}
			}
		}
	}

	private void addFileToZip(final String path, final String srcFile,
			final ZipOutputStream zip) throws IOException {

		final File folder = new File(srcFile);
		if (folder.isDirectory()) {
			addFolderToZip(path, srcFile, zip);
		} else {
			final byte[] buf = new byte[1024];
			int len;
			FileInputStream in = null;
			try {
				in = new FileInputStream(srcFile);
				zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
				while ((len = in.read(buf)) > 0) {
					zip.write(buf, 0, len);
				}
			} finally {
				in.close();
			}
		}

	}

	public boolean folderIsEmpty(final String folder) {
		final File file = new File(folder);
		final String[] list = file.list();
		return (list == null || list.length == 0);
	}

	/**
	 * Deletes a directory and its files recursively
	 * 
	 * @param dir
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean deleteDir(final File path) throws FileNotFoundException {
		if (!path.exists()) {
			throw new FileNotFoundException(path.getAbsolutePath());
		}
		boolean ret = true;
		if (path.isDirectory()) {
			for (File f : path.listFiles()) {
				ret = ret && deleteDir(f);
			}
		}
		return ret && path.delete();
	}

	/**
	 * Loads a the file in path into a String.
	 * 
	 * @param path
	 *            of the file to load
	 * @return the content of the file in a String.
	 * @throws IOException
	 */
	public String loadResource(final String path) throws IOException {
		return Files.toString(new File(path), Charset.forName("UTF-8"));
	}

	/**
	 * Moves the source file to dest. It also creates any necesary dir in dest
	 * before moving it.
	 * 
	 * @param source
	 *            the File to move
	 * 
	 * @param dest
	 *            the File destination
	 * @throws IOException
	 */
	public void move(final File source, final File dest) throws IOException {
		Files.createParentDirs(dest);
		Files.move(source, dest);
	}

	/**
	 * Checks if a directory exists.
	 * 
	 * @param dirPath
	 * @return true if the folder exists, otherwise false.
	 */
	public boolean folderExists(final String dirPath) {
		final File dir = new File(dirPath);
		return dir.exists();
	}

}
