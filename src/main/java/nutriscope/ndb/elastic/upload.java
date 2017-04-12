package nutriscope.ndb.elasticbulk;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Get;
import java.net.InetAddress;

/**
 * Created by john on 4/11/17.
 */
public class upload {


    public static void main(String[] args) throws Exception {





        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("https://search-ndb-wgyzllq2pfef5gh7jmuusfgfvm.us-east-1.es.amazonaws.com")
                .multiThreaded(true)
                //Per default this implementation will create no more than 2 concurrent connections per given route
                .defaultMaxTotalConnectionPerRoute(1)
                // and no more 20 connections in total
                .maxTotalConnection(1)
                        .build());
        JestClient client = factory.getObject();

//        Settings settings = Settings.builder().put("cluster.name", "841821909397:ndb").build();

        Get get = new Get.Builder("ndb","01002").type("food").build();

        JestResult result = client.execute(get);

        System.out.println(result.getJsonString());


    }

}
