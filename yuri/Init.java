package yuri;

import yuri.gui.GUI;

public class Init
   {
   public void init()
      {
      GUI gui = new GUI();
      gui.setVisible(true);
      gui.pack();
      gui.setSize(gui.getSize().width * 2, gui.getSize().height);
      }
   }
