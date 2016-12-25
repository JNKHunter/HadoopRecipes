import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jhunter on 12/25/16.
 */
public class HDFSFileRead {

    InputStream inputStream = null;

    public InputStream getInputStream(){
        try{
            URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
            inputStream = new URL("hdfs://localhost:9000/quangle.txt").openStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return inputStream;
    }
}
