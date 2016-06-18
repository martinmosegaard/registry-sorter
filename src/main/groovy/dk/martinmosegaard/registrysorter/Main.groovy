package dk.martinmosegaard.registrysorter

import dk.martinmosegaard.registrysorter.controller.AlphabetizeController
import dk.martinmosegaard.registrysorter.view.AlphabetizeView

class Main {

  static void main(String[] args) {
    def view = new AlphabetizeView('Registersortering')
    new AlphabetizeController(view)
    view.visible = true
  }

}
