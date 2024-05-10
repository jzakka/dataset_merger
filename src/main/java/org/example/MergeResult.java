package org.example;

import java.util.Map;

public record MergeResult(String text, Map<Tags, Integer> tags) {
}
