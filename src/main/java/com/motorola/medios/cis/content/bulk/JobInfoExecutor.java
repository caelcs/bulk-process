package com.motorola.medios.cis.content.bulk;

import org.springframework.integration.Message;

public class JobInfoExecutor {

	public Long getJobId(Message<String> message) {
		//TODO: Invocar el DAO para obtener de la secuencia el JOBID
		return 1L;
	}
	
	public String getJobStatus() {
		//TODO: Obtener el status del DAO
		return "lala";
	}
}
