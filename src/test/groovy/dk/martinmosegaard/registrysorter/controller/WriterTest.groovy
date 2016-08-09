package dk.martinmosegaard.registrysorter.controller

import spock.lang.Specification

import dk.martinmosegaard.registrysorter.model.RegistryEntry

/**
 * Unit test the Writer.
 */
class WriterTest extends Specification {

  private final writer = new Writer()

  def 'write empty entry returns empty string'() {
    setup:
    def entry = new RegistryEntry(line:'')
    when:
    def result = writer.write(entry)
    then:
    result == ''
  }

  def 'write entry with a line returns the line'() {
    setup:
    def line = 'some text'
    def entry = new RegistryEntry(line:line)
    when:
    def result = writer.write(entry)
    then:
    result == "${line}${System.lineSeparator()}"
  }

  def 'write entry with children returns nested lines'() {
    setup:
    def line1 = '1st level'
    def entry1 = new RegistryEntry(line:line1)

    def line2 = '  2nd level'
    def indent = 2
    def entry2 = new RegistryEntry(line:line2, indent:indent)
    entry1.children.add(entry2)

    when:
    def result = writer.write(entry1)

    then:
    result == "${line1}${System.lineSeparator()}${line2}${System.lineSeparator()}"
  }

}
