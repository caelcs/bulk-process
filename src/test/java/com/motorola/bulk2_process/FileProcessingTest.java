/**
 * 
 */
package com.motorola.bulk2_process;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.motorola.medios.cis.content.bulk.ImportZipGatway;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/bulk2-process-context-test.xml"})
public class FileProcessingTest {

	private static final String TEST_ZIP_5 = "src/test/resources/cis/zip/examplesADI-1.zip";
    private static final String TEST_ZIP_MORE = "src/test/resources/cis/zip/examplesADI.zip";
	
	@Autowired
	@Qualifier("importZipGateway")
	private ImportZipGatway importGateway;
	
	@Test
	public void testImportGatewayWithFiveFiles() throws Exception {
		importGateway.process(TEST_ZIP_5);
	}

    @Test
    public void testImportGatewayWithALotOfFiles() throws Exception {
        importGateway.process(TEST_ZIP_MORE);
    }
}
