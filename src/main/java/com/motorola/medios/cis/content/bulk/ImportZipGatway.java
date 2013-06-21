/*
 * Copyright 2011 Motorola Mobility, All rights reserved.
 */
package com.motorola.medios.cis.content.bulk;

/**
 * This service provides a way for loading multiple ADI files from a zip file
 * into CIS, saving them in DB and indexing in Solr.
 * 
 */
public interface ImportZipGatway {
	/**
	 * Loads the contents from zipFilePath into DB and Solr. The progress and
	 * result of process will be shown in log. The failed an success files will
	 * be zipped into archivePath @see BulkLoadConfig whit the name
	 * "timestamp-ERROR/SUCCESS-zipFileName.zip".
	 * 
	 * @param zipFilePath
	 *            The local path to zip file to be loaded
	 * @throws IOException
	 *             if any IO error occurs during the process
	 * 
	 */
	public void process(final String zipFilePath);
}