package org.example

import org.example.converter.KmhasDataConverter
import org.example.dataline.DataLine
import spock.lang.Specification

class KmhasConverterTest extends Specification {
    def converter = new KmhasDataConverter()

    def "데이터 변환 테스트 - 외모&정치는 기타 태그로 분류"() {
        given:
        def dataline = new DataLine("This is content", [
                "0": 0, // 출신
                "1": 1, // 외모
                "2": 1, // 정치
                "3": 0, // 혐오욕설
                "4": 0, // 연령
                "5": 0, // 성차별
                "6": 0, // 인종차별
                "7": 0, // 종교
                "8": 0  // clean
        ])

        when:
        def res = converter.convert(dataline)

        then:
        res.tags()[Tags.ETC] == 1
        res.tags()[Tags.RELIGION] == 0
        res.tags()[Tags.ABUSE] == 0
        res.tags()[Tags.CLEAN] == 0
        res.tags()[Tags.YEAR] == 0
        res.tags()[Tags.RACE_COUNTRY] == 0
        res.tags()[Tags.LOCALE] == 0
        res.tags()[Tags.GENDER] == 0
    }

    def "데이터 변환 테스트 - 출신, 혐오욕설, 연령, 성차별, 인종,종교 -> LOCALE, ABUSE, YEAR, GENDER, RACE, RELIGION"() {
        given:
        def dataline = new DataLine("This is content", [
                "0": 1, // 출신
                "1": 0, // 외모
                "2": 0, // 정치
                "3": 1, // 혐오욕설
                "4": 1, // 연령
                "5": 1, // 성차별
                "6": 1, // 인종차별
                "7": 1, // 종교
                "8": 0  // clean
        ])

        when:
        def res = converter.convert(dataline)

        then:
        res.tags()[Tags.ETC] == 0
        res.tags()[Tags.RELIGION] == 1
        res.tags()[Tags.ABUSE] == 1
        res.tags()[Tags.CLEAN] == 0
        res.tags()[Tags.YEAR] == 1
        res.tags()[Tags.RACE_COUNTRY] == 1
        res.tags()[Tags.LOCALE] == 1
        res.tags()[Tags.GENDER] == 1
    }

    def "데이터 변환 테스트 - clean -> CLEAN"() {
        given:
        def dataline = new DataLine("This is content", [
                "0": 0, // 출신
                "1": 0, // 외모
                "2": 0, // 정치
                "3": 0, // 혐오욕설
                "4": 0, // 연령
                "5": 0, // 성차별
                "6": 0, // 인종차별
                "7": 0, // 종교
                "8": 1  // clean
        ])

        when:
        def res = converter.convert(dataline)

        then:
        res.tags()[Tags.ETC] == 0
        res.tags()[Tags.RELIGION] == 0
        res.tags()[Tags.ABUSE] == 0
        res.tags()[Tags.CLEAN] == 1
        res.tags()[Tags.YEAR] == 0
        res.tags()[Tags.RACE_COUNTRY] == 0
        res.tags()[Tags.LOCALE] == 0
        res.tags()[Tags.GENDER] == 0
    }
}
