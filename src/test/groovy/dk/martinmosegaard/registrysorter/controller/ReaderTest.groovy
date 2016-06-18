package dk.martinmosegaard.registrysorter.controller

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import static org.junit.Assert.*

import dk.martinmosegaard.registrysorter.model.RegistryEntry

class ReaderTest {

  Reader reader

  @Rule public ExpectedException thrown = ExpectedException.none()

  @Before void setup() {
    reader = new Reader()
  }

  @Test void testCountLeadingSpaces() {
    assertEquals("Line with no spaces",    0, reader.countLeadingSpaces("no leading"))
    assertEquals("Line with 3 spaces",     3, reader.countLeadingSpaces("   three leading"))
    assertEquals("Line with 1 tab",        1, reader.countLeadingSpaces("\tone tab"))
    assertEquals("Line with mixed spaces", 4, reader.countLeadingSpaces(" \t  mixed"))
  }

  @Test void testCountLeadingSpacesEmpty() {
    thrown.expect(RuntimeException.class)
    reader.countLeadingSpaces("")
  }

  @Test void testCountLeadingSpacesWhitespace() {
    thrown.expect(RuntimeException.class)
    reader.countLeadingSpaces("   ")
  }

  @Test void testCountLeadingSpacesWhitespaceTab() {
    thrown.expect(RuntimeException.class)
    reader.countLeadingSpaces("\t\t\t")
  }

  @Test void testCountLeadingSpacesWhitespaceMixed() {
    thrown.expect(RuntimeException.class)
    reader.countLeadingSpaces("\t  \t")
  }

  @Test void testRead() throws Exception {
    def text =
"""b
  3
  4

c
a"""
    RegistryEntry entry = reader.read(text)
    assertNull("Root entry should not have a parent", entry.parent)
    assertEquals("Root entry child count", 3, entry.children.size())
    assertEquals("Root entry indent", -1, entry.indent)
    assertEquals("Root entry line", "", entry.line)

    assertEquals("First line", "b", entry.children.get(0).line)
    assertEquals("First line child count", 2, entry.children.get(0).children.size())

    assertEquals("Second line", "c", entry.children.get(1).line)
    assertEquals("Second line child count", 0, entry.children.get(1).children.size())

    assertEquals("Third line", "a", entry.children.get(2).line)
    assertEquals("Third line child count", 0, entry.children.get(2).children.size())
  }

  @Test void testReadEmpty() throws Exception {
    def text = ''
    RegistryEntry entry = reader.read(text)
    assertNull("Root entry should not have a parent", entry.parent)
    assertEquals("Root entry child count", 0, entry.children.size())
    assertEquals("Root entry indent", -1, entry.indent)
    assertEquals("Root entry line", "", entry.line)
  }

  @Test void testReadNull() throws Exception {
    def text = null
    RegistryEntry entry = reader.read(text)
    assertNull("Root entry should not have a parent", entry.parent)
    assertEquals("Root entry child count", 0, entry.children.size())
    assertEquals("Root entry indent", -1, entry.indent)
    assertEquals("Root entry line", "", entry.line)
  }

}
