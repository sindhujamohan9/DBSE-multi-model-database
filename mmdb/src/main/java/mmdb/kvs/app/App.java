package mmdb.kvs.app;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import mmdb.kvs.app.resources.pairsResource;
import mmdb.kvs.app.AppConfiguration;

/**
 * See pairs!
 *
 */
public class App extends Application<AppConfiguration> {
    
	public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public String getName() {
        return "mmdb";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(AppConfiguration configuration,
                    Environment environment) {
    	final pairsResource resource = new pairsResource();
    	environment.jersey().register(resource);
    	/*
        // nothing to do yet
       	BufferedReader br = new BufferedReader(new FileReader("dataset.tbl"));
        String line =  null;
        	ConcurrentNavigableMap<String,String> map = new ConcurrentSkipListMap<String, String>();
        	
        	
        	while((line=br.readLine())!=null){
                String str[] = line.split("|");
                System.out.println(str);
                
                
                /*for(int i=0;i<str.length;i++){
                    String arr[] = str[i].split("|");
                    map.put(arr[0], arr[1]);
                }*/
  /*          }
            map.keySet().forEach(
            		it->{
            			System.out.println("Key:"+it+", Value:"+map.get(it));
            		}
            	);
        	

            /*map.put("1", "One");
            //map.put("2", "Two");
            map.put("3", "Three");
            map.put("5", "Five");
            map.put("6", "Six");

            System.out.println("Initial ConcurrentHashMap: "+map);
            System.out.println("HeadMap(\"2\") of ConcurrentHashMap: "+map.headMap("2"));
            System.out.println("TailMap(\"2\") of ConcurrentHashMap: "+map.tailMap("2"));
            System.out.println("SubMap(\"2\", \"4\") of ConcurrentHashMap: "+map.subMap("2","4"));*/
        	
        	
      //  }
    }
}
 


