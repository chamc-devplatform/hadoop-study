package com.hadoop.maven.test;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;


public class CountMain {
	
	public static void main(String args[]) throws IOException {
		if(args.length != 2) {
			System.err.println("err");
			System.exit(-1);
		}
		JobConf conf = new JobConf();
		conf.setJarByClass(CountMain.class);
		conf.setJobName("WordCount");
		conf.setMapperClass(CountMapper.class);
		conf.setCombinerClass(CountReduce.class);
		conf.setReducerClass(CountReduce.class);
		conf.setNumMapTasks(3);
		conf.setNumReduceTasks(3);
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		JobClient.runJob(conf);
		System.exit(0);
	}
}
