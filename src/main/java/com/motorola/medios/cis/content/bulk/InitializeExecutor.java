package com.motorola.medios.cis.content.bulk;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MessageBuilder;

public class InitializeExecutor {
	
	@Autowired
	@Qualifier("fileToProcess")
	private QueueChannel channel;
	
	@Autowired
	@Qualifier("jobInfoExecutor")
	JobInfoExecutor jobInfoExecutor;
	
	private static final Logger LOGGER = Logger
			.getLogger("InitializeExecutor.class");
	
	public void process(Message<String> zipFilePath) throws IOException {
		ZipFile zf = new ZipFile(zipFilePath.getPayload());
		ZipEntry entry;
		Enumeration<? extends ZipEntry> listFiles = zf.entries();
		while (listFiles.hasMoreElements()) {
            entry = listFiles.nextElement();
			if (!entry.isDirectory()) {
				Message<InputStream> message = MessageBuilder.withPayload(zf.getInputStream(entry)).build();
				channel.send(message);
			}
		}

		zf.close();
		LOGGER.log(Level.INFO, "InitializeExecutor Finished.");
	}
}
