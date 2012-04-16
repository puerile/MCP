package yuri.gui;

import yuri.exceptions.ReadInterruptedException;
import yuri.logic.Hashmap;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener
   {
   private static final long serialVersionUID = 1L;
   private static String NAME = "MCP";

   private JLabel inputTitle = new JLabel("<html><body><h3>" + NAME + "</h3></body></html>");

   private JLabel input = new JLabel("Directory:");
   private JTextField path = new JTextField();

   private JCheckBox subDirs = new JCheckBox("Include Subdirectories?", true); // include subdirs?

   private JFileChooser dir = new JFileChooser(new File(""));

   private JButton openDialog = new JButton("...");

   private JButton start = new JButton("start");
   private JButton cancel = new JButton("cancel");

   private JPanel c = new JPanel();
   private JScrollPane scroll = new JScrollPane(c);

   private JPanel buttons = new JPanel();
   private JPanel title = new JPanel();

   private JTextArea textArea = new JTextArea();
   private JScrollPane area = new JScrollPane(textArea);

   private JLabel filesRead = new JLabel(" ");
   private JLabel fileCount = new JLabel();

   // private ActionListener action = new MyActionListener();

   private Dimension size = new Dimension(200, 200);
   private Hashmap map;

   private int files = 0;

   public void setStart(boolean status)
      {
      this.start.setEnabled(status);
      }

   public void setCancel(boolean status)
      {
      this.cancel.setEnabled(status);
      }

   public GUI()
      {
      super(NAME);

      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.add(scroll);

      resetButtons();

      start.addActionListener(this);
      cancel.addActionListener(this);
      openDialog.addActionListener(this);

      this.initWindow();
      }

   protected void initWindow()
      {
      GridBagLayout gbl = new GridBagLayout();
      c.setLayout(gbl);

      title.add(inputTitle);

      area.setPreferredSize(size);

      buttons.add(start);
      buttons.add(cancel);

      GBL gbld = new GBL();

      gbld.addComponent(c, gbl, title, 0, 0, 4, 1, 1.0, 0, GridBagConstraints.CENTER);
      gbld.addComponent(c, gbl, input, 0, 1, 1, 1, 0, 0, GridBagConstraints.WEST);
      gbld.addComponent(c, gbl, path, 1, 1, 1, 1, 1.0, 0, GridBagConstraints.CENTER);
      gbld.addComponent(c, gbl, openDialog, 3, 1, 1, 1, 0, 0, GridBagConstraints.CENTER);
      gbld.addComponent(c, gbl, subDirs, 0, 7, 4, 1, 0, 0, GridBagConstraints.CENTER);
      gbld.addComponent(c, gbl, area, 0, 9, 4, 1, 0, 0, GridBagConstraints.CENTER);
      gbld.addComponent(c, gbl, filesRead, 0, 10, 4, 1, 0, 0, GridBagConstraints.CENTER);
      gbld.addComponent(c, gbl, fileCount, 1, 10, 4, 1, 0, 0, GridBagConstraints.CENTER);
      gbld.addComponent(c, gbl, buttons, 0, 11, 4, 1, 0, 1.0, GridBagConstraints.SOUTH);

      this.pack();
      }

   // private class MyActionListener implements ActionListener
   // {
   public void actionPerformedWithException(ActionEvent e) throws ReadInterruptedException, NoSuchAlgorithmException,
            IOException
      {
      if (e.getSource() == start)
         {
         map = new Hashmap(this);

         map.setPath(path.getText());
         map.useSubdirs(subDirs.isSelected());

         start.setEnabled(false);
         cancel.setEnabled(true);

         filesRead.setText("Files read:");

         map.start();
         }

      else
         if (e.getSource() == cancel)
            {
            start.setEnabled(true);
            cancel.setEnabled(false);
            throw new ReadInterruptedException();
            }

         else
            if (e.getSource() == openDialog)
               {
               dir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
               int state = dir.showOpenDialog(null);

               if (state == JFileChooser.APPROVE_OPTION)
                  {
                  File file = dir.getSelectedFile();
                  System.out.println(file.getPath());
                  path.setText(file.getPath());
                  }
               }
      }

   @Override
   public void actionPerformed(ActionEvent e)
      {
      try
         {
         actionPerformedWithException(e);
         }

      catch (ReadInterruptedException e1)
         {
         System.out.println("\n***\n");
         System.out.println("process interrupted by user");

         map.interrupt();
         }

      catch (NoSuchAlgorithmException e1)
         {
         // TODO Auto-generated catch block
         e1.printStackTrace();
         }

      catch (IOException e1)
         {
         // TODO Auto-generated catch block
         e1.printStackTrace();
         }
      };

   // }

   public void resetButtons()
      {
      start.setEnabled(true);
      cancel.setEnabled(false);
      }

   public JTextArea getText()
      {
      return this.textArea;
      }

   public void setText(String text)
      {
      this.textArea.setText(text);
      }

   public void addText(String text)
      {
      if (this.textArea.getText() == null || this.textArea.getText().equals(""))
         {
         this.textArea.setText(text);
         }

      else
         {
         this.textArea.setText(this.textArea.getText() + "\n" + text);
         }
      }

   public void addFileCount()
      {
      this.fileCount.setText("" + files++);
      }

   public void resetFileCount()
      {
      files = 0;
      }
   }
