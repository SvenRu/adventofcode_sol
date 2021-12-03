package com.sven.adventofcode.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChallengeInputReader {
    
    public static List<String> readFileContentasList(String fileName) throws IOException{
       
        Path path = Paths.get("src/main/resources/files/" + fileName);
        List<String> result;
	    try (Stream<String> lines = Files.lines(path)) {
		    result = lines.collect(Collectors.toList());
	    }

        return result;
    }
}
