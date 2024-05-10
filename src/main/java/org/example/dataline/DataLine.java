package org.example.dataline;

import java.util.Map;

public record DataLine(String content, Map<String, Integer> tags) {
}
