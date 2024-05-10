package org.example;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Parser {
    public MergeResult parse(String line) {
        String tagsStr = getTags(line);
        String content = line.replaceAll("\\t*" + tagsStr + "\\s*$", "");
        String[] split = tagsStr.split(",");

        Set<Tags> tags = Arrays.stream(split)
                .mapToInt(Integer::parseInt)
                .mapToObj(Tags::convert)
                .collect(Collectors.toSet());

        Map<Tags, Integer> tagMaps = Arrays.stream(Tags.values())
                .collect(Collectors.toMap(Function.identity(), tag -> tags.contains(tag) ? 1 : 0));

        return new MergeResult(content, tagMaps);
    }

    private String getTags(String line) {
        String[] tokens = line.replaceAll("\\s*$", "").split("\\s+");
        return tokens[tokens.length - 1];
    }
}
