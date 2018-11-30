package com.stand.alone.word;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class WordCount {
	public static void main(String[] args) throws IOException {
		List<String> content = readFileByLine("D:/source/test");
		Map<String, Long> wordFre = content.stream().flatMap(line -> {
			List<String> tmp = new ArrayList<>();
			StringTokenizer token = 
					new StringTokenizer(line, ". ,;\"\"=--()'*/<>:@“”‘’[]!#$?{}`&");
			while(token.hasMoreTokens()) {
				String condidate = token.nextToken();
				condidate = condidate.trim();
				if(condidate == null || condidate.equals(" ")) {
					continue;
				}
				tmp.add(condidate);
			}
			return tmp.stream();
		}).collect(Collectors.groupingBy(word -> word, Collectors.counting()));
		wordFre.entrySet().stream().forEach(System.out::println);
//		wordFre.entrySet().stream()
//		.filter(entry -> entry.getKey().contains("spring")).forEach(System.out::println);
	}
	
	public static List<String> readFileByLine(String path) throws IOException {
		File file = new File(path);
		File[] files = file.listFiles();
		List<String> result = new ArrayList<>();
		for(File f:files) {
			if(!f.exists()) {
				continue;
			}
			if(!f.isFile()) {
				continue;
			}
			InputStreamReader stream = new InputStreamReader(new FileInputStream(f));
			BufferedReader reader = new BufferedReader(stream);
			String line;
			while((line = reader.readLine()) != null) {
				result.add(line);
			}
			reader.close();
		    stream.close();
		}
		return result;
	}
}
