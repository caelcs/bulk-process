package com.motorola.medios.cis.content.bulk;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.integration.Message;

public class ContentWsExecutor {

	private static final Logger LOGGER = Logger
			.getLogger("ContentWsExecutor.class");
	
	public void process(Message<InputStream> message) {
		LOGGER.log(Level.INFO, "ContentWsExecutor Finished." + message.getPayload());
		LOGGER.log(Level.INFO, "ContentWsExecutor HEADER: " + message.getHeaders());
	}
}
