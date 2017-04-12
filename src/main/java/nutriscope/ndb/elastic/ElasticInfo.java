package nutriscope.ndb.elastic;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by john on 4/11/17.
 */
public class ElasticInfo {
    private static String creds = "elasticsearch-cred.json";


    public static String getUrl() throws FileNotFoundException {
            BufferedReader reader = new BufferedReader(new FileReader(creds));
            ElasticSearchCred cred = new Gson().fromJson(reader, ElasticSearchCred.class);
            return cred.url;
    }

    class ElasticSearchCred {
        String url;
    }

}
