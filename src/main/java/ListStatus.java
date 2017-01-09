import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

/**
 * Created by jhunter on 1/8/17.
 */
public class ListStatus {

    private String[] pathStrings;
    private Path[] paths;
    private FileSystem fs;
    private Configuration conf;

    public ListStatus(String fsPathString, String[] pathStrings) throws Exception {
        this.pathStrings = pathStrings;
        conf = new Configuration();
        fs = FileSystem.get(URI.create(fsPathString), conf);
        initPaths();
    }

    private void initPaths(){
        paths = new Path[pathStrings.length];
        for(int i = 0; i < pathStrings.length; i++){
            paths[i] = new Path(pathStrings[i]);
        }
    }

    public Path[] getPaths() {
        return paths;
    }

    public FileStatus[] getFilesStatuses() throws IOException {
        return fs.listStatus(paths);
    }
}
