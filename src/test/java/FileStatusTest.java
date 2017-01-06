import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.MiniDFSCluster;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jhunter on 1/3/17.
 */
public class FileStatusTest {

    private MiniDFSCluster cluster;
    private FileSystem fs;
    private String filePath = "/dir/file";

    @Before
    public void setUp() throws IOException {
        Configuration conf = new Configuration();
        if (System.getProperty("test.build.data") == null ){
            System.setProperty("test.build.data", "/tmp");
        }

        cluster = new MiniDFSCluster.Builder(conf).build();
        fs = cluster.getFileSystem();
        OutputStream out = fs.create(new Path(filePath));
        out.write("content".getBytes("UTF-8"));
        out.close();
    }

    @After
    public void tearDown() throws IOException{
       if (fs != null) {
           fs.close();
       }

       if (cluster != null) {
           cluster.shutdown();
       }
    }

    @Test(expected = FileNotFoundException.class)
    public void throwsFileNotFoundForNonExistentFile() throws IOException{
        fs.getFileStatus(new Path("no-such-file"));
    }

    @Test
    public void fileStatusForFile() throws IOException {
        Path file = new Path(filePath);
        FileStatus stat = fs.getFileStatus(file);
        stat.getPath().toUri().getPath();
        assertThat(stat.getPath().toUri().getPath(), is(filePath));
        assertThat(stat.isDirectory(), is(false));
        assertThat(stat.getLen(), is (7L));
    }

}
