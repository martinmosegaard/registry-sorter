package dk.martinmosegaard.registrysorter.controller

import org.junit.Before
import org.junit.Test
import static org.junit.Assert.*

import dk.martinmosegaard.registrysorter.model.RegistryEntry

class SorterTest {

  Sorter sorter

  @Before void setup() {
    sorter = new Sorter()
  }

  @Test void testSort() {
    RegistryEntry entry = new RegistryEntry(parent: null, indent: -1, line: '')

    RegistryEntry ape = new RegistryEntry(parent: entry, indent: 2, line: 'ape')
    RegistryEntry bee = new RegistryEntry(parent: entry, indent: 2, line: 'bee')
    RegistryEntry cat = new RegistryEntry(parent: entry, indent: 2, line: 'cat')

    // children of 'bee'
    RegistryEntry buzzer = new RegistryEntry(parent: bee, indent: 4, line: 'buzzer')
    RegistryEntry stingy = new RegistryEntry(parent: bee, indent: 4, line: 'stingy')
    bee.children.add(stingy)
    bee.children.add(buzzer)

    entry.children.add(bee)
    entry.children.add(cat)
    entry.children.add(ape)

    assertEquals("Root child count before sorting", 3, entry.children.size())
    assertEquals("First root child before sorting",  bee, entry.children.get(0))
    assertEquals("Second root child before sorting", cat, entry.children.get(1))
    assertEquals("Third root child before sorting",  ape, entry.children.get(2))
    assertEquals("Bee child count before sorting", 2, bee.children.size())
    assertEquals("First bee child before sorting",  stingy, bee.children.get(0))
    assertEquals("Second bee child before sorting", buzzer, bee.children.get(1))

    sorter.sort(entry)

    assertEquals("Root child count after sorting", 3, entry.children.size())
    assertEquals("First root child after sorting",  ape, entry.children.get(0))
    assertEquals("Second root child after sorting", bee, entry.children.get(1))
    assertEquals("Third root child after sorting",  cat, entry.children.get(2))
    assertEquals("Bee child count after sorting", 2, bee.children.size())
    assertEquals("First bee child after sorting",  buzzer, bee.children.get(0))
    assertEquals("Second bee child after sorting", stingy, bee.children.get(1))
  }

}
