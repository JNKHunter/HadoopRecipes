import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

/**
 * Created by jhunter on 12/26/16.
 */
public class HDFSFileSystemReadTest {
    HDFSFileSystemRead fileSystemRead;
    private String hdfsUrl = "hdfs://localhost:9000/quangle.txt";

    @Before
    public void setUp() throws Exception {
        fileSystemRead = new HDFSFileSystemRead();
    }

    @Test
    public void getInputStream() throws Exception {
        InputStream in = fileSystemRead.getInputStream(hdfsUrl);
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);
        String line = br.readLine();

        assertEquals("quangle", line);
    }

}