package dk.martinmosegaard.registrysorter.controller

import spock.lang.Specification

import dk.martinmosegaard.registrysorter.model.RegistryEntry

/**
 * Unit test the Reader.
 */
class ReaderTest extends Specification {

  private final reader = new Reader()

  def 'count leading spaces when there are none'() {
    when:
    def line = 'no leading'
    then:
    assert reader.countLeadingSpaces(line) == 0 : 'Line with no spaces'
  }

  def 'count leading spaces when there are some'() {
    when:
    def line = '   three leading'
    then:
    assert reader.countLeadingSpaces(line) == 3 : 'Line with 3 spaces'
  }

  def 'count leading spaces with a tab'() {
    when:
    def line = '\tone tab'
    then:
    assert reader.countLeadingSpaces(line) == 1 : 'Line with 1 tab'
  }

  def 'count leading spaces with mixed tab and spaces'() {
    when:
    def line = ' \t  mixed'
    then:
    assert reader.countLeadingSpaces(line) == 4 : 'Line with mixed spaces'
  }

  def 'count leading spaces for an empty line'() {
    when:
    def line = ''
    then:
    assert reader.countLeadingSpaces(line) == 0 : 'Empty line'
  }

  def 'count leading spaces for a whitespace line'() {
    when:
    def line = '   '
    then:
    assert reader.countLeadingSpaces(line) == 0 : 'Whitespace line'
  }

  def 'count leading spaces for a whitespace tab line'() {
    when:
    def line = '\t\t\t'
    then:
    assert reader.countLeadingSpaces(line) == 0 : 'Line with tabs'
  }

  def 'count leading spaces for a mixed whitespace line'() {
    when:
    def line = '\t  \t'
    then:
    assert reader.countLeadingSpaces(line) == 0 : 'Line with mixed whitespace'
  }

  def 'can read text'() {
    setup:
    def text =
'''b
  3
  4

c
a'''
    when:
    RegistryEntry entry = reader.read(text)

    then:
    assert entry.parent == null : 'Root entry should not have a parent'
    assert entry.children.size() == 3 : 'Root entry child count'
    assert entry.indent == -1 : 'Root entry indent'
    assert entry.line == '' : 'Root entry line'

    assert entry.children.get(0).line == 'b' : 'First line'
    assert entry.children.get(0).children.size() == 2 : 'First line child count'

    assert entry.children.get(1).line == 'c' : 'Second line'
    assert entry.children.get(1).children.size() == 0 : 'Second line child count'

    assert entry.children.get(2).line == 'a'
    assert entry.children.get(2).children.size() == 0 : 'Third line child count'
  }

  def 'can read empty text'() {
    setup:
    def text = ''
    when:
    RegistryEntry entry = reader.read(text)
    then:
    assert entry.parent == null : 'Root entry should not have a parent'
    assert entry.children.size() == 0 : 'Root entry child count'
    assert entry.indent == -1 : 'Root entry indent'
    assert entry.line == '' : 'Root entry line'
  }

  def 'can read null text'() {
    setup:
    def text = null
    when:
    RegistryEntry entry = reader.read((String) text)
    then:
    assert entry.parent == null : 'Root entry should not have a parent'
    assert entry.children.size() == 0 : 'Root entry child count'
    assert entry.indent == -1 : 'Root entry indent'
    assert entry.line == '' : 'Root entry line'
  }

}
