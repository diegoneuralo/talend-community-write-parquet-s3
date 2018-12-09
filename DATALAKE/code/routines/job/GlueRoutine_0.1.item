package routines;

import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.glue.AWSGlue;
import com.amazonaws.services.glue.AWSGlueClientBuilder;
import com.amazonaws.services.glue.model.CreatePartitionRequest;
import com.amazonaws.services.glue.model.GetPartitionsRequest;
import com.amazonaws.services.glue.model.GetPartitionsResult;
import com.amazonaws.services.glue.model.GetTableRequest;
import com.amazonaws.services.glue.model.GetTableResult;
import com.amazonaws.services.glue.model.PartitionInput;
import com.amazonaws.services.glue.model.StorageDescriptor;

public class GlueRoutine {
	
	private static AWSGlue client = AWSGlueClientBuilder
    		.standard()
    		.withCredentials(new DefaultAWSCredentialsProviderChain())
    		.build(); 

    public static String addPartition(String databaseName, String tableName, LinkedHashMap<String, String> partitionMap) {
    	System.out.println(String.format("Get Partition info from table %s", tableName));
    	
    	String expression = partitionMap.entrySet().stream()
    			.map(entry -> String.format("%s = '%s'", entry.getKey(), entry.getValue()))
    			.collect(Collectors.joining(" AND "));
    	
    	GetPartitionsResult result = client.getPartitions(new GetPartitionsRequest()
    			.withDatabaseName(databaseName)
    			.withTableName(tableName)
    			.withExpression(expression));
    	
    	if (result.getPartitions().isEmpty()) {
    		System.out.println(String.format("Create partition %s to table %s", expression, tableName));
    		
    		GetTableResult tableResult = client.getTable(new GetTableRequest()
    				.withDatabaseName(databaseName)
    				.withName(tableName));
    		
    		StorageDescriptor tableStorage = tableResult.getTable().getStorageDescriptor();
    		
    		String location = String.format("%s%s/", 
    				tableStorage.getLocation(), 
    				partitionMap.entrySet().stream()
    					.map(entry -> String.format("%s=%s", entry.getKey(), entry.getValue()))
    					.collect(Collectors.joining("/")));
    		
    		System.out.println(String.format("Defining Partition %s location %s to table %s", expression, location, tableName));
    		
    		client.createPartition(new CreatePartitionRequest()
    				.withDatabaseName(databaseName)
    				.withTableName(tableName)
    				.withPartitionInput(new PartitionInput()
    						.withValues(partitionMap.values())
    						.withStorageDescriptor(new StorageDescriptor()
    								.withLocation(location)
    								.withInputFormat(tableStorage.getInputFormat())
    								.withOutputFormat(tableStorage.getOutputFormat())
    								.withSerdeInfo(tableStorage.getSerdeInfo()))));
    		
    		System.out.println(String.format("Partition %s is created %s", expression, tableName));
    		
    		return location;
    	} else {
    		System.out.println(String.format("Partition %s already exists in table %s", expression, tableName));
    		return result.getPartitions().get(0).getStorageDescriptor().getLocation();
    	}
    }
}
