package yuri.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import yuri.arrays.ArrayHandler;
import yuri.arrays.StringHandler;
import yuri.checksum.MD5;
import yuri.files.FileHandler;
import yuri.gui.GUI;

public class Hashmap extends Thread
   {
   private MD5 md5 = new MD5();
   private FileHandler fh = new FileHandler();
   private ArrayHandler ah = new ArrayHandler();
   private StringHandler sh = new StringHandler();
   private String[] hashes = new String[0];
   private String path;
   private boolean useSubdirs;
   private boolean isInterrupted;
   private GUI gui;

   public Hashmap(GUI gui)
      {
      this.gui = gui;
      }

   public void interrupt()
      {
      this.isInterrupted = true;
      }

   public void useSubdirs(boolean bool)
      {
      this.useSubdirs = bool;
      }

   public void setPath(String path)
      {
      this.path = path;
      }

   public String[] getHashes()
      {
      return this.hashes;
      }

   public void setHashes(String[] array)
      {
      this.hashes = ah.clean(array);
      }

   public void addHash(String hash)
      {
      String[] result = new String[hashes.length + 1];

      System.arraycopy(hashes, 0, result, 0, hashes.length);
      result[hashes.length] = hash;

      setHashes(result);
      }

   public void removeHash(String hash)
      {
      String[] result = new String[hashes.length];
      int j = 0;

      for (int i = 0; i < hashes.length; i++)
         {
         if (!(hashes[i].equals(hash)))
            {
            result[j] = hashes[i];
            j++;
            }
         }

      setHashes(ah.removeEmpty(hashes));
      }

   // NEEDS TO BE FIXED ASAP!
   public Map<String, String[]> createMapNoDirs() throws NoSuchAlgorithmException, IOException
      {
      Map<String, String[]> hashmap = new HashMap<String, String[]>();
      File[] files = fh.stripFiles((new File(path)).listFiles());

      for (int i = 0; i < files.length && !isInterrupted; i++)
         {
         try
            {
            String tmpChksm = md5.checkSum(files[i]);
            String[] temp;

            if (hashmap.containsKey(tmpChksm))
               {
               temp = sh.add(hashmap.get(tmpChksm), files[i].getAbsolutePath());
               }

            else
               {
               temp = new String[1];
               temp[0] = files[i].getAbsolutePath();
               }

            hashmap.put(tmpChksm, temp);
            addHash(tmpChksm);

            gui.addFileCount();
            }

         catch (FileNotFoundException e)
            {
            System.err.println("file not found? :/");
            }
         }

      return hashmap;
      }

   public Map<String, String[]> createMap() throws NoSuchAlgorithmException, IOException
      {
      Map<String, String[]> hashmap = new HashMap<String, String[]>();

      File[] content = (new File(path)).listFiles();
      File[] dirs = new File[0];

      do
         {
         try
            {
            for (int i = 0; i < content.length && !isInterrupted; i++)
               {
               try
                  {
                  if (content[i].isDirectory())
                     {
                     dirs = fh.add(dirs, content[i]);
                     }

                  else
                     if (content[i].isFile())
                        {
                        String tmpChksm = md5.checkSum(content[i]);
                        String[] temp;

                        if (hashmap.containsKey(tmpChksm))
                           {
                           temp = sh.add(hashmap.get(tmpChksm), content[i].getAbsolutePath());
                           }

                        else
                           {
                           temp = new String[1];
                           temp[0] = content[i].getAbsolutePath();
                           }

                        System.out.println("Eingelesene Datei: " + content[i].getAbsolutePath() + " ("
                                 + md5.checkSum(content[i]) + ")");

                        hashmap.put(tmpChksm, temp);
                        addHash(tmpChksm);

                        gui.addFileCount();
                        }
                  }

               catch (FileNotFoundException e)
                  {
                  System.err.println("File " + content[i] + " could not be read :/");
                  }

               catch (OutOfMemoryError e)
                  {
                  // System.err.println(e.getCause());
                  System.err.println("out of memory :(");
                  }
               }
            }

         catch (NullPointerException e)
            {
            System.err.println("keine Ahnung, was jetzt passiert ist °~°");
            }

         dirs = fh.removeEmpty(dirs);
         content = dirs[0].listFiles();

         if (dirs.length > 0)
            {
            dirs = fh.del(dirs, 0);
            }
         }
      while (dirs.length > 0);

      return hashmap;
      }

   // checks for map items with more than one file entry per hash and puts the URL into a list
   // list contains arrays of file addresses
   // <{file1,file2},{file3,file4},...>
   public List<String[]> compare(Map<String, String[]> map)
      {
      List<String[]> list = new LinkedList<String[]>();

      for (int i = 0; i < hashes.length && !isInterrupted; i++)
         {
         if (map.containsKey(hashes[i]) && map.get(hashes[i]).length > 1)
            {
            String[] tempList = new String[map.get(hashes[i]).length];

            for (int j = 0; j < tempList.length && !isInterrupted; j++)
               {
               tempList[j] = map.get(hashes[i])[j];
               System.out.println("Doppelte Datei: " + map.get(hashes[i])[j]);
               }

            list.add(tempList);
            }
         }

      return list;
      }

   public void print(List<String[]> list)
      {
      for (int i = 0; i < list.size() && !isInterrupted; i++)
         {
         gui.addText("Identical Files:");
         gui.getText().repaint();

         for (int j = 0; j < list.get(i).length && !isInterrupted; j++)
            {
            gui.addText((list.get(i)[j]));
            gui.getText().repaint();
            }

         gui.addText("---");
         gui.getText().repaint();
         }
      }

   public void go() throws NoSuchAlgorithmException, IOException
      {
      gui.setText("");

      if (useSubdirs)
         {
         print(compare(createMap()));
         }

      else
         {
         print(compare(createMapNoDirs()));
         }
      }

   @Override
   public void run()
      {
      try
         {
         go();
         }

      catch (NoSuchAlgorithmException e)
         {
         // TODO Auto-generated catch block
         e.printStackTrace();
         }
      catch (IOException e)
         {
         // TODO Auto-generated catch block
         e.printStackTrace();
         }

      gui.resetButtons();
      gui.resetFileCount();
      }
   }
