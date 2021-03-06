package dk.martinmosegaard.registrysorter.controller

import spock.lang.Specification

/**
 * Unit test the Alphabetizer.
 */
class AlphabetizerTest extends Specification {

  private final subject = new Alphabetizer()

  def 'alphabetize empty string returns empty string'() {
    setup:
    def text = ''
    when:
    def result = subject.alphabetize(text)
    then:
    result == ''
  }

  def 'alphabetize lines returns sorted list'() {
    setup:
    def text = ['bee', 'cat', 'car', 'ape'].join(System.lineSeparator())
    when:
    def result = subject.alphabetize(text)
    then:
    result == ['ape', 'bee', 'car', 'cat'].join(System.lineSeparator()) +
        System.lineSeparator()
  }

  def 'alphabetize nested list returns sorted, nested list'() {
    setup:
    def inputFile = new File(AlphabetizerTest.getResource('/input0.txt').file)
    def text = inputFile.text
    when:
    def result = subject.alphabetize(text)
    then:
    def sortedFile = new File(AlphabetizerTest.getResource('/input0.txt.sorted').file)
    def sortedText = sortedFile.text
    result == sortedText
  }

}
