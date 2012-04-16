package yuri.arrays;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import yuri.checksum.MD5;

public class StringHandler
   {
   MD5 md5 = new MD5();

   // lists all entries in a string array
   public void listAll(String[] files)
      {
      for (int i = 0; i < files.length; i++)
         {
         System.out.println(files[i]);
         }
      }

   // adds a single entry to a string array
   public String[] add(String[] array, String file)
      {
      String[] result = new String[array.length + 1];

      System.arraycopy(array, 0, result, 0, array.length);
      result[array.length] = file;

      return result;
      }

   // removes a single entry from a string array
   public String[] del(String[] array, int pos) throws NoSuchAlgorithmException, IOException
      {
      int j = 0;
      String[] result = new String[array.length - 1];

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

   // removes empty entries in a string array and returns a new one
   public String[] removeEmpty(String[] array)
      {
      int j = 0;

      for (int i = 0; i < array.length; i++)
         {
         if (array[i] != null)
            {
            j++;
            }
         }

      String[] result = new String[j];
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

   // takes a list of files and returns their absolute paths as a string array
   public String[] toStringArray(File[] array)
      {
      String[] result = new String[array.length];

      for (int i = 0; i < result.length; i++)
         {
         result[i] = array[i].getAbsolutePath();
         }

      return result;
      }
   }
