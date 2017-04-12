package nutriscope.ndb.utility;

import com.google.common.collect.Lists;
import com.google.common.io.ByteStreams;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.List;

/**
 *  Downloads required data files for bulk processing to Elasticsearch.
 */
public class Setup {

    public static String outputPath = "data/";

    public static void main(String[] args) throws Exception {

        // Path for data storage


        // List of data files
        List<String> files = Lists.newArrayList(
                "https://s3.amazonaws.com/nutriscopedata/ndbid-mfgorfoodgroup.tsv",
                "https://s3.amazonaws.com/nutriscopedata/ndbid-name.tsv");

        OkHttpClient client = new OkHttpClient();

        Request request;
        Response response;
        for (String file: files) {
                request = new Request.Builder()
                        .url(file)
                        .build();

                response = client.newCall(request).execute();

                OutputStream out = new FileOutputStream(outputPath + getFilename(file));
                InputStream in = response.body().byteStream();

            try {
                ByteStreams.copy(in, out);

            }
                finally {
                response.close();
                out.flush();

            }
        }
    }


    /**
     *
     * @param path the full path
     * @return the filename at the path
     */
    private static String getFilename(String path) {
        return Paths.get(path).getFileName().toString();

    }


}
