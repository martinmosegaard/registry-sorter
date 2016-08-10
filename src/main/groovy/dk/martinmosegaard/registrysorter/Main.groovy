package dk.martinmosegaard.registrysorter

import dk.martinmosegaard.registrysorter.view.AlphabetizeView

/**
 * Main application class.
 */
class Main {

  static void main(String[] args) {
    def view = new AlphabetizeView('Registersortering')
    view.visible = true
  }

}
