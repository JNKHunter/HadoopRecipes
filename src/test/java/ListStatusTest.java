import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Created by jhunter on 1/8/17.
 */
public class ListStatusTest {

    private Path[] paths;
    private ListStatus listStatus;
    private String[] pathStrings;
    private FileStatus[] fileStatuses;

    @BeforeClass
    public static void beforeClass() throws Exception{
        //This method can be called at most once per JVM
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    @Before
    public void setup() throws Exception {
        pathStrings = new String[]{"hdfs://localhost:9000/user/jhunter"};
        listStatus = new ListStatus("hdfs://localhost:9000", pathStrings);
        paths = listStatus.getPaths();
    }

    @Test
    public void checkPathStringsLength() throws Exception{
        assertEquals(1, paths.length);
    }

    @Test
    public void checkPathStringsStatus() throws Exception{
        fileStatuses = listStatus.getFilesStatuses();
        assertEquals(2, fileStatuses.length);
    }


}