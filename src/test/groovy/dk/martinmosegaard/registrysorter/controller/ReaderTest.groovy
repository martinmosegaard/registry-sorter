package dk.martinmosegaard.registrysorter.controller

import org.junit.Before
import org.junit.Test

import dk.martinmosegaard.registrysorter.model.RegistryEntry

/**
 * Unit test the Reader.
 */
class ReaderTest {

  private Reader reader

  @Before void setup() {
    reader = new Reader()
  }

  @Test void testCountLeadingSpaces() {
    assert reader.countLeadingSpaces('no leading') == 0 : 'Line with no spaces'
    assert reader.countLeadingSpaces('   three leading') == 3 : 'Line with 3 spaces'
    assert reader.countLeadingSpaces('\tone tab') == 1 : 'Line with 1 tab'
    assert reader.countLeadingSpaces(' \t  mixed') == 4 : 'Line with mixed spaces'
  }

  @Test void testCountLeadingSpacesEmpty() {
    assert reader.countLeadingSpaces('') == 0 : 'Empty line'
  }

  @Test void testCountLeadingSpacesWhitespace() {
    assert reader.countLeadingSpaces('   ') == 0 : 'Whitespace line'
  }

  @Test void testCountLeadingSpacesWhitespaceTab() {
    assert reader.countLeadingSpaces('\t\t\t') == 0 : 'Line with tabs'
  }

  @Test void testCountLeadingSpacesWhitespaceMixed() {
    assert reader.countLeadingSpaces('\t  \t') == 0 : 'Line with mixed whitespace'
  }

  @Test void testRead() {
    def text =
'''b
  3
  4

c
a'''
    RegistryEntry entry = reader.read(text)
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

  @Test void testReadEmpty() {
    def text = ''
    RegistryEntry entry = reader.read(text)
    assert entry.parent == null : 'Root entry should not have a parent'
    assert entry.children.size() == 0 : 'Root entry child count'
    assert entry.indent == -1 : 'Root entry indent'
    assert entry.line == '' : 'Root entry line'
  }

  @Test void testReadNull() {
    def text = null
    RegistryEntry entry = reader.read(text)
    assert entry.parent == null : 'Root entry should not have a parent'
    assert entry.children.size() == 0 : 'Root entry child count'
    assert entry.indent == -1 : 'Root entry indent'
    assert entry.line == '' : 'Root entry line'
  }

}
