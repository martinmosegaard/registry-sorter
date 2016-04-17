package dk.martinmosegaard.registrysorter;

import dk.martinmosegaard.registrysorter.controller.AlphabetizeController;
import dk.martinmosegaard.registrysorter.view.AlphabetizeView;

public class Main {

  public static void main(String[] args) {
    AlphabetizeView view = new AlphabetizeView("Registersortering");
    new AlphabetizeController(view);
    view.setVisible(true);
  }

}
