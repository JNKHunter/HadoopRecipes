import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.junit.Before;

/**
 * Created by jhunter on 1/8/17.
 */
public class ListStatusTest {

    private Path[] paths;
    private ListStatus listStatus;
    private FileStatus fileStatus;
    private String[] pathStrings;

    @Before
    public void setup() throws Exception {
        pathStrings = new String[]{"hdfs://localhost/user/jhunter", "hdfs://localhost/user/roger"};
        listStatus = new ListStatus("hdfs://localhost", pathStrings);
        paths = listStatus.getPaths();

    }

}