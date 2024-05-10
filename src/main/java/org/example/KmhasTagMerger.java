package org.example;

public class KmhasTagMerger implements TagMerger{
    private final Parser parser;

    public KmhasTagMerger(Parser parser) {
        this.parser = parser;
    }

    public MergeResult merge(String line) {
        return parser.parse(line);
    }
}
