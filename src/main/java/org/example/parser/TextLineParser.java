package org.example.parser;

import org.example.dataline.DataLine;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TextLineParser {
    public DataLine parse(String line) {
        String tagsStr = getTags(line);
        String content = line.replaceAll("\\s*" + tagsStr + "\\s*$", "");
        Set<String> tags = Arrays.stream(tagsStr.split(",")).collect(Collectors.toSet());

        Map<String, Integer> resultTags = IntStream.rangeClosed(0, 8)
                .mapToObj(String::valueOf)
                .collect(Collectors.toMap(Function.identity(), k -> tags.contains(k) ? 1 : 0));

        return new DataLine(content, resultTags);
    }

    private String getTags(String line) {
        String[] tokens = line.replaceAll("\\s*$", "").split("\\s+");
        return tokens[tokens.length - 1];
    }
}
