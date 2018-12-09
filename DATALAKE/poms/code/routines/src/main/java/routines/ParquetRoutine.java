package routines;

/*
 * user specification: the function's comment should contain keys as follows: 1. write about the function's comment.but
 * it must be before the "{talendTypes}" key.
 * 
 * 2. {talendTypes} 's value must be talend Type, it is required . its value should be one of: String, char | Character,
 * long | Long, int | Integer, boolean | Boolean, byte | Byte, Date, double | Double, float | Float, Object, short |
 * Short
 * 
 * 3. {Category} define a category for the Function. it is required. its value is user-defined .
 * 
 * 4. {param} 's format is: {param} <type>[(<default value or closed list values>)] <name>[ : <comment>]
 * 
 * <type> 's value should be one of: string, int, list, double, object, boolean, long, char, date. <name>'s value is the
 * Function's parameter name. the {param} is optional. so if you the Function without the parameters. the {param} don't
 * added. you can have many parameters for the Function.
 * 
 * 5. {example} gives a example for the Function. it is optional.
 */

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;


public class ParquetRoutine {
	
	public static class SchemaField {
		
		private String name;
		private String type;
		private boolean nullable;
		
		public SchemaField(String name, String type, boolean nullable) {
			this.name = name;
			this.type = type;
			this.nullable = nullable;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public boolean isNullable() {
			return nullable;
		}
		public void setNullable(boolean nullable) {
			this.nullable = nullable;
		}
	}
	
	public static Schema getSchema(ParquetRoutine.SchemaField... fields) {
		System.out.println("Creating Parquet Schema Schema");
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"type\" : \"record\",");
		sb.append("\"name\" : \"parquet_schema\",");
		sb.append("\"namespace\" : \"ERM_INTERNAL_EKP\",");
		sb.append("\"fields\" : [");
		
		sb.append(Arrays.asList(fields).stream().map(field -> {
			StringBuilder fieldSb = new StringBuilder();
			return fieldSb.append("{ \"name\" : \"")
			.append(field.getName())
			.append("\", \"type\": \"")
			.append(field.isNullable() ? "[\"null\", \"" : "")
			.append(field.getType())
			.append("\"")
			.append(field.isNullable() ? "], \"default\": null }" : "}").toString();
		}).collect(Collectors.joining(", ")));
		
		sb.append("]");
		sb.append("}");	
		
		Schema schema = new Schema.Parser().parse(sb.toString());
		
		System.out.println("Schema has been created");
		return schema;
	}
	
	public static void closeWriter(ParquetWriter<GenericData.Record> writer) throws IOException {
		System.out.println("Closing Writer");
		writer.close();
		System.out.println("Writer has been closed");
	}
	
	public static ParquetWriter<GenericData.Record> getWriter(String path, Schema schema) throws IOException {
		System.out.println(String.format("Creating Parquet Writer to Path %s", path));
		Configuration conf = new Configuration();
		conf.set("fs.s3a.aws.credentials.provider", "com.amazonaws.auth.DefaultAWSCredentialsProviderChain");
		
		ParquetWriter<GenericData.Record> writer = AvroParquetWriter.<GenericData.Record>builder(new Path(path))
    			.withSchema(schema)
    			.withConf(conf)
    			.withCompressionCodec(CompressionCodecName.SNAPPY)
    			.build();
    	
    	System.out.println(String.format("Writer has been created to path %s", path));
    	
    	return writer;
    }
}
