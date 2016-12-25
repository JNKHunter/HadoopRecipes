import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by jhunter on 12/25/16.
 */
public class HDFSFileReadTest {
    HDFSFileRead fileRead;

    @Before
    public void setUp() throws Exception {
        fileRead = new HDFSFileRead();
    }

    @Test
    public void getInputStream() throws Exception {
        InputStream is = fileRead.getInputStream();
        assertNotNull(is);
        is.close();
    }
}