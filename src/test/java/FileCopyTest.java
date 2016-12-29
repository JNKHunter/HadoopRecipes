import org.junit.Before;
import org.junit.Test;

/**
 * Created by jhunter on 12/28/16.
 */
public class FileCopyTest {

    FileCopy fileCopy;

    @Before
    public void setUp() throws Exception {
        fileCopy = new FileCopy("../resources/longtext.txt",
                "hdfs://localhost:9000/user/jhunter/filecopy/longtext.txt");
    }

    @Test
    public void copyFile() throws Exception {
        fileCopy.copyFile();
    }

}