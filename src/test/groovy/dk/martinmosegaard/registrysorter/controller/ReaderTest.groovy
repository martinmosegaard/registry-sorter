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
    assertNull("Root entry should not have a parent", entry.getParent())
    assertEquals("Root entry child count", 3, entry.getChildren().size())
    assertEquals("Root entry indent", -1, entry.getIndent())
    assertEquals("Root entry line", "", entry.getLine())

    assertEquals("First line", "b", entry.getChildren().get(0).getLine())
    assertEquals("First line child count", 2, entry.getChildren().get(0).getChildren().size())

    assertEquals("Second line", "c", entry.getChildren().get(1).getLine())
    assertEquals("Second line child count", 0, entry.getChildren().get(1).getChildren().size())

    assertEquals("Third line", "a", entry.getChildren().get(2).getLine())
    assertEquals("Third line child count", 0, entry.getChildren().get(2).getChildren().size())
  }

  @Test void testReadEmpty() throws Exception {
    def text = ''
    RegistryEntry entry = reader.read(text)
    assertNull("Root entry should not have a parent", entry.getParent())
    assertEquals("Root entry child count", 0, entry.getChildren().size())
    assertEquals("Root entry indent", -1, entry.getIndent())
    assertEquals("Root entry line", "", entry.getLine())
  }

  @Test void testReadNull() throws Exception {
    def text = null
    RegistryEntry entry = reader.read(text)
    assertNull("Root entry should not have a parent", entry.getParent())
    assertEquals("Root entry child count", 0, entry.getChildren().size())
    assertEquals("Root entry indent", -1, entry.getIndent())
    assertEquals("Root entry line", "", entry.getLine())
  }

}
