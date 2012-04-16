package yuri.gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public class GBL
   {
   void addComponent(JPanel panel, GridBagLayout gbl, Component c, int x, int y, int width, int height, double weightx,
            double weighty, int anchor)
      {
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.gridx = x;
      gbc.gridy = y;
      gbc.gridwidth = width;
      gbc.gridheight = height;
      gbc.weightx = weightx;
      gbc.weighty = weighty;
      gbc.anchor = anchor;
      gbc.insets = new Insets(5, 5, 5, 5);

      gbl.setConstraints(c, gbc);
      panel.add(c);
      }
   }
