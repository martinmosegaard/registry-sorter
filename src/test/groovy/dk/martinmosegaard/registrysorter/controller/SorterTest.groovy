package dk.martinmosegaard.registrysorter.controller

import spock.lang.Specification

import dk.martinmosegaard.registrysorter.model.RegistryEntry

/**
 * Unit test the Sorter.
 */
class SorterTest extends Specification {

  private final sorter = new Sorter()

  def 'sorting a list with children'() {
    setup:
    RegistryEntry entry = new RegistryEntry(parent:null, indent:-1, line:'')

    RegistryEntry ape = new RegistryEntry(parent:entry, indent:2, line:'ape')
    RegistryEntry bee = new RegistryEntry(parent:entry, indent:2, line:'bee')
    RegistryEntry cat = new RegistryEntry(parent:entry, indent:2, line:'cat')

    // children of 'bee'
    RegistryEntry buzzer = new RegistryEntry(parent:bee, indent:4, line:'buzzer')
    RegistryEntry stingy = new RegistryEntry(parent:bee, indent:4, line:'stingy')
    bee.children.add(stingy)
    bee.children.add(buzzer)

    entry.children.add(bee)
    entry.children.add(cat)
    entry.children.add(ape)

    assert entry.children.size() == 3 : 'Root child count before sorting'
    assert entry.children.get(0) == bee : 'First root child before sorting'
    assert entry.children.get(1) == cat : 'Second root child before sorting'
    assert entry.children.get(2) == ape : 'Third root child before sorting'
    assert bee.children.size() == 2 : 'Bee child count before sorting'
    assert bee.children.get(0) == stingy : 'First bee child before sorting'
    assert bee.children.get(1) == buzzer : 'Second bee child before sorting'
    Sorter sorter = new Sorter()

    when:
    sorter.sort(entry)

    then:
    assert entry.children.size() == 3 : 'Root child count after sorting'
    assert entry.children.get(0) == ape : 'First root child after sorting'
    assert entry.children.get(1) == bee : 'Second root child after sorting'
    assert entry.children.get(2) == cat : 'Third root child after sorting'
    assert bee.children.size() == 2 : 'Bee child count before sorting'
    assert bee.children.get(0) == buzzer : 'First bee child before sorting'
    assert bee.children.get(1) == stingy : 'Second bee child before sorting'
  }

  def 'sorting with quotes'() {
    setup:
    RegistryEntry entry = new RegistryEntry(parent:null, indent:-1, line:'')
    RegistryEntry ape = new RegistryEntry(parent:entry, indent:2, line:'ape')
    RegistryEntry bee = new RegistryEntry(parent:entry, indent:2, line:"'bee'")
    RegistryEntry cat = new RegistryEntry(parent:entry, indent:2, line:'"cat"')
    entry.children.add(bee)
    entry.children.add(cat)
    entry.children.add(ape)

    when:
    sorter.sort(entry)

    then:
    assert entry.children.size() == 3 : 'Root child count after sorting'
    assert entry.children.get(0) == ape : 'First root child after sorting'
    assert entry.children.get(1) == bee : 'Second root child after sorting'
    assert entry.children.get(2) == cat : 'Third root child after sorting'
  }

}
