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
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.*;

/**
 * Created by jhunter on 1/3/17.
 */
public class FileStatusTest {

    private MiniDFSCluster cluster;
    private FileSystem fs;
    private String filePath = "/dir/file";
    private String filePath2 = "/dir/file2";
    private String dirPath = "/dir";
    private Path dir;
    private Path file;
    private Path file2;

    @Before
    public void setUp() throws IOException {
        Configuration conf = new Configuration();
        if (System.getProperty("test.build.data") == null ){
            System.setProperty("test.build.data", "/tmp");
        }

        cluster = new MiniDFSCluster.Builder(conf).build();
        fs = cluster.getFileSystem();
        dir = new Path(dirPath);
        file = new Path(filePath);
        file2 = new Path(filePath2);
        OutputStream out = fs.create(new Path(filePath));
        out.write("content".getBytes("UTF-8"));
        out.close();

        out = fs.create(new Path(filePath2));
        out.write("content2".getBytes("UTF-8"));
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
        FileStatus stat = fs.getFileStatus(file);
        FileStatus[] stats = fs.globStatus(new Path("/dir/file*"));
        stat.getPath().toUri().getPath();
        assertThat(stat.getPath().toUri().getPath(), is(filePath));
        assertThat(stat.isDirectory(), is(false));
        assertThat(stat.getLen(), is (7L));
        assertThat(stat.getModificationTime(), is(lessThanOrEqualTo(System.currentTimeMillis())));
        assertThat(stat.getReplication(), is((short) 1));
        assertThat(stat.getBlockSize(), is(128 * 1024* 1024L));
        assertThat(stat.getOwner(), is(System.getProperty("user.name")));
        assertThat(stat.getGroup(), is("supergroup"));
        assertThat(stat.getPermission().toString(), is("rw-r--r--"));
        assertEquals(2, stats.length);
    }

    @Test
    public void fileStatusForDirectory() throws IOException {
        FileStatus stat = fs.getFileStatus(dir);
        stat.getPath().toUri().getPath();
        assertThat(stat.getPath().toUri().getPath(), is(dirPath));
        assertThat(stat.isDirectory(), is(true));
        assertThat(stat.getLen(), is (0L));
        assertThat(stat.getModificationTime(), is(lessThanOrEqualTo(System.currentTimeMillis())));
        assertThat(stat.getReplication(), is((short) 0));
        assertThat(stat.getBlockSize(), is(0L));
        assertThat(stat.getOwner(), is(System.getProperty("user.name")));
        assertThat(stat.getGroup(), is("supergroup"));
        assertThat(stat.getPermission().toString(), is("rwxr-xr-x"));
    }

    @Test
    public void checkForExistence() throws IOException {
        assertThat(fs.exists(dir), is(true) );
        assertThat(fs.exists(file), is(true) );
    }

    @Test
    public void checkForDeletion() throws IOException {
        assertTrue(fs.delete(dir,true));
        assertFalse(fs.delete(dir,true));
    }

}
