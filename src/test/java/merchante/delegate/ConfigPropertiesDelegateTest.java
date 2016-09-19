package merchante.delegate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import merchante.util.FileUtil;

public class ConfigPropertiesDelegateTest {
	private static final String OUTPUT_FILE = "outputFile";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        File dir = FileUtil.getOutputFilePath().toFile();
        
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    System.out.println(file);
                    file.delete();
                }
            }
            dir.delete();
        } else {
            dir.delete();
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void configPropertiesDelegateTest() throws IOException {
		ConfigPropertiesDelegate delegate = new ConfigPropertiesDelegate();
		delegate.processUrlFromConfigProps();
		assert(Files.exists(Paths.get(new StringBuilder().append(FileUtil.getOutputFilePath()).append(File.separator)
				.append(FileUtil.getOutputFileName(OUTPUT_FILE)).toString())));
    }
}