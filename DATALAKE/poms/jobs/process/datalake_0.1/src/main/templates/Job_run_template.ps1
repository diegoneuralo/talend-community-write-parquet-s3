$fileDir = Split-Path -Parent $MyInvocation.MyCommand.Path
cd $fileDir
java '-Dtalend.component.manager.m2.repository=%cd%/../lib' '-Xms256M' '-Xmx1024M' '-Dfile.encoding=UTF-8' -cp '.;../lib/routines.jar;../lib/avro-1.8.2.jar;../lib/aws-java-sdk-1.11.406.jar;../lib/aws-java-sdk-core-1.11.466.jar;../lib/aws-java-sdk-glue-1.11.466.jar;../lib/commons-beanutils-1.9.3.jar;../lib/commons-codec-1.10.jar;../lib/commons-collections-3.2.2.jar;../lib/commons-compress-1.8.1.jar;../lib/commons-configuration2-2.1.1.jar;../lib/commons-io-2.5.jar;../lib/commons-lang-2.6.jar;../lib/commons-lang3-3.8.1.jar;../lib/commons-logging-1.1.3.jar;../lib/commons-pool-1.6.jar;../lib/dom4j-1.6.1.jar;../lib/external_sort.jar;../lib/fastutil-7.0.13.jar;../lib/guava-11.0.2.jar;../lib/hadoop-annotations-3.1.1.jar;../lib/hadoop-auth-3.1.1.jar;../lib/hadoop-aws-3.1.1.jar;../lib/hadoop-client-3.1.1.jar;../lib/hadoop-common-3.1.1.jar;../lib/htrace-core4-4.1.0-incubating.jar;../lib/httpclient-4.5.2.jar;../lib/httpclient-4.5.5.jar;../lib/httpcore-4.4.4.jar;../lib/httpcore-4.4.9.jar;../lib/ion-java-1.0.2.jar;../lib/jackson-annotations-2.6.0.jar;../lib/jackson-annotations-2.9.0.jar;../lib/jackson-core-2.6.7.jar;../lib/jackson-core-2.9.5.jar;../lib/jackson-core-asl-1.9.13.jar;../lib/jackson-databind-2.6.7.2.jar;../lib/jackson-databind-2.7.8.jar;../lib/jackson-dataformat-cbor-2.6.7.jar;../lib/jackson-mapper-asl-1.9.13.jar;../lib/jmespath-java-1.11.466.jar;../lib/joda-time-2.10.1.jar;../lib/joda-time-2.8.1.jar;../lib/log4j-1.2.17.jar;../lib/paranamer-2.7.jar;../lib/parquet-avro-1.10.0.jar;../lib/parquet-column-1.10.0.jar;../lib/parquet-common-1.10.0.jar;../lib/parquet-encoding-1.10.0.jar;../lib/parquet-format-2.4.0.jar;../lib/parquet-hadoop-1.10.0.jar;../lib/parquet-jackson-1.10.0.jar;../lib/slf4j-api-1.7.2.jar;../lib/snappy-java-1.1.2.6.jar;../lib/stax2-api-3.1.4.jar;../lib/talend_file_enhanced_20070724.jar;../lib/talendcsv.jar;../lib/woodstox-core-5.0.3.jar;../lib/xz-1.5.jar;datalake_0_1.jar;' datalake.datalake_0_1.DATALAKE  %*