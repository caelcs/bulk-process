package com.motorola.medios.cis.content.bulk;


public class BulkLoadConfig {

	private String zipFilePath;
	private String inProgressDir;
	private String incomingPath;
	private String archivePath;
	private String failedsPath;
	private String successPath;
	
	public String getInProgressDir() {
		return inProgressDir;
	}
	public void setInProgressDir(final String inProgressDir) {
		this.inProgressDir = inProgressDir;
	}
	public String getIncomingPath() {
		return incomingPath;
	}
	public void setIncomingPath(final String incomingPath) {
		this.incomingPath = incomingPath;
	}
	public String getArchivePath() {
		return archivePath;
	}
	public void setArchivePath(final String archivePath) {
		this.archivePath = archivePath;
	}
	public String getFailedsPath() {
		return failedsPath;
	}
	public void setFailedsPath(final String failedsPath) {
		this.failedsPath = failedsPath;
	}
	public String getSuccessPath() {
		return this.successPath;
	}
	public void setSuccessPath(final String successPath) {
		this.successPath = successPath;
	}
	public String getZipFilePath() {
		return zipFilePath;
	}
	public void setZipFilePath(String zipFilePath) {
		this.zipFilePath = zipFilePath;
	}

}
