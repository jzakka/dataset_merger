package org.example

import org.example.parser.TextLineParser
import spock.lang.Specification

class KmhasLineParse extends Specification {
    def parser = new TextLineParser()

    def "파싱테스트"() {
        given:
        def line = "this is content\t2,3,7   "
        when:
        def res = parser.parse(line)
        then:
        res.content() == "this is content"
        res.tags()["0"] == 0
        res.tags()["1"] == 0
        res.tags()["2"] == 1
        res.tags()["3"] == 1
        res.tags()["4"] == 0
        res.tags()["5"] == 0
        res.tags()["6"] == 0
        res.tags()["7"] == 1
    }
}
