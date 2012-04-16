package yuri.files;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import yuri.checksum.MD5;

public class FileHandler
   {
   MD5 md5 = new MD5();

   // lists all entries [FILES/DIRS] in a file list
   public void listAll(File[] files)
      {
      for (int i = 0; i < files.length; i++)
         {
         System.out.println(files[i].getAbsolutePath());
         }
      }

   // adds a single entry [FILE/DIR] to a list of files
   public File[] add(File[] array, File file)
      {
      File[] result = new File[array.length + 1];

      System.arraycopy(array, 0, result, 0, array.length);
      result[array.length] = file;

      return result;
      }

   // removes a single entry [FILE/DIR] from a list
   public File[] del(File[] array, int pos) throws NoSuchAlgorithmException, IOException
      {
      int j = 0;
      File[] result = new File[array.length - 1];

      for (int i = 0; i < array.length; i++)
         {
         if (i != pos)
            {
            result[j] = array[i];
            j++;
            }
         }

      return result;
      }

   // reads all FILES from two file lists and returns them as an array
   public File[] combineFiles(File[] firstArray, File[] secondArray)
      {
      int i;
      File result[] = new File[firstArray.length + secondArray.length];

      for (i = 0; i < firstArray.length; i++)
         {
         if (firstArray[i].isFile())
            {
            result[i] = firstArray[i];
            }
         }

      for (i = i; i < result.length; i++)
         {
         if (secondArray[i - firstArray.length].isFile())
            {
            result[i] = secondArray[i - firstArray.length];
            }
         }

      return removeEmpty(result);
      }

   // reads all DIRS from two file lists and returns them as an array
   public File[] combineDirs(File[] firstArray, File[] secondArray)
      {
      int i;
      File result[] = new File[firstArray.length + secondArray.length];

      for (i = 0; i < firstArray.length; i++)
         {
         if (firstArray[i].isDirectory())
            {
            result[i] = firstArray[i];
            }
         }

      for (i = i; i < result.length; i++)
         {
         if (secondArray[i - firstArray.length].isDirectory())
            {
            result[i] = secondArray[i - firstArray.length];
            }
         }

      return removeEmpty(result);
      }

   // removes empty entries in a file list and returns a new array
   public File[] removeEmpty(File[] array)
      {
      int j = 0;

      for (int i = 0; i < array.length; i++)
         {
         if (array[i] != null)
            {
            j++;
            }
         }

      File[] result = new File[j];
      j = 0;

      for (int i = 0; i < array.length; i++)
         {
         if (array[i] != null)
            {
            result[j] = array[i];
            j++;
            }
         }

      return result;
      }

   // creates a file list from a directory
   public File[] createList(String path)
      {
      File directory = new File(path);
      File content[] = directory.listFiles();

      return content;
      }

   // checks whether a list of file types contains DIRS
   public boolean containsDirs(File[] array)
      {
      for (int i = 0; i < array.length; i++)
         {
         if (array[i].isDirectory())
            {
            return true;
            }
         }

      return false;
      }

   // checks whether a list of file types contains FILES
   public boolean containsFiles(File[] array)
      {
      for (int i = 0; i < array.length; i++)
         {
         if (array[i].isFile())
            {
            return true;
            }
         }

      return false;
      }

   // returns only the DIRS in a given array
   public File[] stripDirs(File[] array)
      {
      int amount = 0;
      File[] result = new File[array.length];

      if (containsDirs(array))
         {
         for (int i = 0; i < array.length; i++)
            {
            if (array[i].isDirectory())
               {
               result[amount] = array[i];
               amount++;
               }
            }
         }

      return removeEmpty(result);
      }

   // returns only the FILES in a given array
   public File[] stripFiles(File[] array)
      {
      int amount = 0;
      File[] result = new File[array.length];

      if (containsFiles(array))
         {
         for (int i = 0; i < array.length; i++)
            {
            if (array[i].isFile())
               {
               result[amount] = array[i];
               amount++;
               }
            }
         }

      return removeEmpty(result);
      }
   }
