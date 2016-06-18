package dk.martinmosegaard.registrysorter.controller

import org.junit.Before
import org.junit.Test
import static org.junit.Assert.*

import dk.martinmosegaard.registrysorter.controller.Reader
import dk.martinmosegaard.registrysorter.model.RegistryEntry

class ReaderTest {

  Reader reader

  @Before void setup() {
    reader = new Reader()
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
