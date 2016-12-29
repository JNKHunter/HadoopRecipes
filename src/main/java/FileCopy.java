import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.*;
import java.net.URI;

/**
 * Created by jhunter on 12/28/16.
 */
public class FileCopy {

    String localSrc;
    String destSrc;

    public FileCopy(String localSrc, String destSrc) {
        this.localSrc = localSrc;
        this.destSrc = destSrc;
    }

    public void copyFile() throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(destSrc), conf);
        OutputStream out = fs.create(new Path(destSrc), new Progressable() {
            public void progress() {
                System.out.print(".");
            }
        });

        IOUtils.copyBytes(in, out, 4096, true);
    }


}
