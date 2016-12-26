import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by jhunter on 12/25/16.
 */
public class HDFSFileReadTest {
    HDFSFileRead fileRead;
    String hdfsUrl = "hdfs://localhost:9000/quangle.txt";

    @BeforeClass
    public static void beforeClass() throws Exception{
        //This method can be called at most once per JVM
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    @Before
    public void setUp() throws Exception {
        fileRead = new HDFSFileRead();
    }

    @Test
    public void successfulInputStreamReturnsNotNull() throws Exception {
        InputStream is = fileRead.getInputStream(hdfsUrl);
        assertNotNull(is);
        is.close();
    }

    @Test
    public void readInputStream() throws Exception{
        InputStream is = fileRead.getInputStream(hdfsUrl);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line = br.readLine();
        assertEquals("quangle",line);
    }
}