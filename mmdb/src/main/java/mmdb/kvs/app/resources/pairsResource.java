package mmdb.kvs.app.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponses;
import com.wordnik.swagger.annotations.ApiResponse;

import mmdb.kvs.app.output.pair;
import mmdb.kvs.app.output.pairs;

import java.io.BufferedReader;
//import java.io.Console;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Path("pairs")
@Api(value = "pairs", description = "Pairs API")
@Produces(MediaType.APPLICATION_JSON)
public class pairsResource {
	public static ConcurrentNavigableMap<String,Map<String, String>> map;
	private static List<String> fields;
	private static final String tableName="User";

    public pairsResource() {
    	//String csvFile = "/Users/sindhuja/eclipse-workspace/mmdb/src/main/java/mmdb/kvs/app/resources/dataset.csv";
        //String line = "";
       // String seperator = " ";
        
//    	try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
        this.map = new ConcurrentSkipListMap<String, Map<String, String>>();
        String[] array = new String[] { "field0", "field1", "field2", "field3", "field4", "field5", "field6", "field7", "field8", "field9"};
        this.fields= Arrays.asList(array);
//        while((line = br.readLine()) != null){
//        //	String[] id = line.split(seperator);
//    	//	String attributeID = id[0].substring(1);
//    	//	String attributeValue =         
//    		map.put(map.entrySet().size(), line);
//    		
//    		
//        }
      //String lineNumber = line.toString();
		//String[] id = lineNumber.split(seperator,2);
		//System.out.println(id);
		//for(String l:id){
		//String[] attributeID = l.split(seperator,2);
		
		
		//map.put(attributeID.toString(), "h");
        		//System.out.println(id[0]+id[1]);
        		//map.put("Team TAM:", "Number 1");
                //map.put("Team someone else:", "Number 2");
        		
        		
        		
        
        		
        	
//        }
//    	
//    	
//        catch(IOException e){
//        	e.printStackTrace();
//        }
    	
    	
    	
    }
    
  //read the csv file
    //access first column of the csv file
    //put that in 1st string
    //access all the other columns
    //put them in json object
    //convert the json into string
    //put them in 2nd string of concurrent skiplist map
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all pairs",
			notes = "Description here.",
			response = pairs.class)
    @Timed
    public Response getAllPairs() {
    		List<pair> pairList = new ArrayList<pair>();
    		for (Map.Entry<String, Map<String, String>> entry : map.entrySet())
    		{
    			pair tuple = new pair();
    			tuple.setKey(entry.getKey());
    			tuple.setValue(entry.getValue());
    			pairList.add(tuple);
    		 }
    		/*map.keySet().forEach(it->{
    			pair tuple = new pair();
    			tuple.setKey(it);
    			tuple.setValue(map.get(it));
    			pairList.add(tuple);
    		});*/
    		pairs result = new pairs(pairList);
    		return Response.ok(result).build();         
    	}
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed
	@ApiOperation(value = "Create a new entry",
			notes = "Description here.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 400, message = "Bad Request - invalid data has been "
					+ "passed in the request body"),
			@ApiResponse(code = 500, message = "Internal server error")
		})
	public Response insert(pair pair, @QueryParam(value="table")
	String table) {
		if (table==null||!table.equals(tableName)){
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (map.containsKey(pair.getKey())){
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (pair.getValue().entrySet().size()!=10){
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!pair.getValue().keySet().containsAll(fields)){
			return Response.status(Status.BAD_REQUEST).build();
		}
		try{		
			map.put(pair.getKey(), pair.getValue());
		}
		catch(Exception e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.CREATED).build();         
	}
}
	


