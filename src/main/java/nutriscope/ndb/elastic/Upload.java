package nutriscope.ndb.elastic;

import com.opencsv.CSVReader;
import io.searchbox.action.BulkableAction;
import io.searchbox.annotations.JestId;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import nutriscope.ndb.utility.Setup;

import java.io.FileReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 4/11/17.
 */
public class Upload {


    public static void main(String[] args) throws Exception {


        CSVReader reader = new CSVReader(new FileReader(Setup.outputPath + "ndbid-name.tsv"), '\t');

        String[] line;

        List<SimpleFood> simpleFoods = new ArrayList<>();

        List<BulkableAction> actions = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            line = reader.readNext();
            simpleFoods.add(new SimpleFood(line[0], line[1]));


        }

        for (SimpleFood food: simpleFoods) {
            actions.add(new Index.Builder(food).index("ndb").type("food").build());

        }


        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(ElasticInfo.getUrl())
                .multiThreaded(true)
                //Per default this implementation will create no more than 2 concurrent connections per given route
                .defaultMaxTotalConnectionPerRoute(1)
                // and no more 20 connections in total
                .maxTotalConnection(1)
                        .build());
        JestClient client = factory.getObject();

//        Settings settings = Settings.builder().put("cluster.name", "841821909397:ndb").build();

        Bulk bulk = new Bulk.Builder()
                .defaultIndex("ndb")
                .defaultType("food")
                .addAction(actions)
                .build();

        client.execute(bulk);

        client.shutdownClient();

   //     Get get = new Get.Builder("ndb","01002").type("food").build();

  //      JestResult result = client.execute(get);

    //    System.out.println(result.getJsonString());


    }




}

class SimpleFood{
    public SimpleFood(String ndbId, String name) {
        this.ndbId = ndbId;
        this.name = name;
    }
    @JestId
    String ndbId;
    String name;
}
