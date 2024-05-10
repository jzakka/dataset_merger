package org.example.converter;

import org.example.MergeResult;
import org.example.Tags;
import org.example.dataline.DataLine;

import java.util.Map;
import java.util.stream.Collectors;

public class KmhasDataConverter implements Converter{
    Map<String, Tags> tagConvertingMap = Map.of(
            "0",Tags.LOCALE,
            "1", Tags.ETC,
            "2", Tags.ETC,
            "3", Tags.ABUSE,
            "4", Tags.YEAR,
            "5", Tags.GENDER,
            "6", Tags.RACE_COUNTRY,
            "7", Tags.RELIGION,
            "8", Tags.CLEAN
    );

    @Override
    public MergeResult convert(DataLine dataLine) {
        Map<Tags, Integer> mergedTags = dataLine.tags().entrySet().stream()
                .collect(Collectors.toMap(e -> tagConvertingMap.get(e.getKey()), e -> e.getValue(), (a, b) -> a | b));

        return new MergeResult(dataLine.content(), mergedTags);
    }
}
