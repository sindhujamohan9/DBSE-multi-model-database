package mmdb.kvs.app.resources;

import mmdb.kvs.app.output.pair;
import mmdb.kvs.app.output.pairs;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/pairs")
@Produces(MediaType.APPLICATION_JSON)
public class pairsResource {
	private static ConcurrentNavigableMap<String,String> map; 

    public pairsResource() {
        this.map = new ConcurrentSkipListMap<String, String>();
        map.put("Team TAM:", "Number 1");
    }

    @GET
    @Timed
    public pairs getAllPairs() {
    		List<pair> pairList = new ArrayList<>();
    		map.keySet().forEach(it->{
    			pair tuple = new pair();
    			tuple.setKey(it);
    			tuple.setValue(map.get(it));
    			pairList.add(tuple);
    		});
    		pairs result = new pairs(pairList);
    		return result;        
    }
}
