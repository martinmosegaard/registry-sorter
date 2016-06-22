package dk.martinmosegaard.registrysorter.controller

import spock.lang.Specification

import dk.martinmosegaard.registrysorter.model.RegistryEntry
import dk.martinmosegaard.registrysorter.view.AlphabetizeView

class AlphabetizeControllerTest extends Specification {

  def view = Mock(AlphabetizeView)
  def subject = new AlphabetizeController(view)

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
    def text = ['bee', 'cat', 'car', 'ape'].join('\n')
    when:
    def result = subject.alphabetize(text)
    then:
    result == ['ape', 'bee', 'car', 'cat'].join('\n') + '\n'
  }

  def 'alphabetize nested list returns sorted, nested list'() {
    setup:
    def inputFile = new File(AlphabetizeControllerTest.getResource('/input0.txt').file)
    def text = inputFile.text
    when:
    def result = subject.alphabetize(text)
    then:
    def sortedFile = new File(AlphabetizeControllerTest.getResource('/input0.txt.sorted').file)
    def sortedText = sortedFile.text
    result == sortedText 
  }

}
