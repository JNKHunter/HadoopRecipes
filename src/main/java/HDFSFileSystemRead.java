import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * Created by jhunter on 12/26/16.
 */
public class HDFSFileSystemRead {


    InputStream getInputStream(String hdfsUrl) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(hdfsUrl), conf);
        InputStream in = null;

        return fs.open(new Path(hdfsUrl));

    }




}
