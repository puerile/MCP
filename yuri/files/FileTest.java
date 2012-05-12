package yuri.files;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class FileTest extends TestCase
   {
   String path = System.getProperty("user.dir");
   FileHandler fh = new FileHandler();
   File file = new File(path);
   File[] before = new File[3];

   public File[] fill(File[] array, File file)
      {
      for (int i = 0; i < array.length; i++)
         {
         array[i] = file;
         }

      return array;
      }

   public void print(File[] array)
      {
      for (int i = 0; i < array.length; i++)
         {
         System.out.println(array[i]);
         }
      }

   @Before
   public void setUp()
      {
      fill(before, file);
      }

   @Test
   public void testAdd()
      {
      File[] after = new File[4];
      fill(after, file);

      System.out.println("before:");
      print(before);

      System.out.println("after:");
      print(after);

      before = fh.add(before, file);

      System.out.println("before:");
      print(before);

      System.out.println("after:");
      print(after);

      assertTrue(before.length == after.length);
      }

   @Test
   public void testDel() throws NoSuchAlgorithmException, IOException
      {
      File[] after = new File[2];

      before[0] = new File(System.getProperty("user.dir") + "\\1.txt");
      before[1] = new File(System.getProperty("user.dir") + +\\2.txt");
      before[2] = new File(System.getProperty("user.dir") + "\\3.txt");

      after[0] = new File(System.getProperty("user.dir") + "\\2.txt");
      after[1] = new File(System.getProperty("user.dir") + "\\3.txt");

      System.out.println("before:");
      print(before);

      System.out.println("after:");
      print(after);

      before = fh.del(before, 0);

      System.out.println("before:");
      print(before);

      System.out.println("after:");
      print(after);

      assertTrue(before.length == after.length);

      for (int i = 0; i < after.length; i++)
         {
         assertTrue(before[i].equals(after[i]));
         }
      }
   }
