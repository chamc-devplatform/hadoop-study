package com.hadoop.maven.test;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class CountMapper extends MapReduceBase implements Mapper<Object, Text, Text, IntWritable> {
	private Text word = new Text();
	private static final IntWritable one = new IntWritable(1);
	
	public void map(Object key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		StringTokenizer itr = new StringTokenizer(value.toString(), ". ,;\"\"=--()'*/<>:@“”‘’[]!#$?{}`&");
		while(itr.hasMoreTokens()) {
			String tmp = itr.nextToken();
			tmp = tmp.trim();
			if(StringUtils.isEmpty(tmp)) {
				continue;
			}
			word.set(tmp);
			output.collect(word, one);
		}
	}
}
