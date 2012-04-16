package yuri.arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArrayTest
   {
   ArrayHandler ah = new ArrayHandler();
   
   String[] noDups = {"eins", "zwei", "drei", "vier"};
   String[] dups1 = {"eins", "eins", "zwei"};
   String[] dups2 = {"eins", "zwei", "eins", "zwei"};
   String[] dups3 = {"eins", "zwei", "eins", "drei", "vier", "eins", "fuenf"};

   @Test
   public void containsTest()
      {
      assertTrue(ah.contains(noDups,"zwei")==1);
      assertTrue(ah.contains(dups1,"eins")==2);
      assertTrue(ah.contains(dups1,"zwei")==1);
      assertTrue(ah.contains(dups2,"zwei")==2);
      assertTrue(ah.contains(dups3,"eins")==3);
      assertTrue(ah.contains(dups3,"zwei")==1);
      assertTrue(ah.contains(noDups,"xyz")==0);
      }
   
   @Test
   public void cleanTest()
      {
      String[] test = ah.clean(dups3);
      
      for (int i = 0; i<test.length; i++)
         {
         System.out.println(test[i]);
         }
      
      assertTrue(ah.clean(noDups).length==noDups.length);
      assertTrue(ah.clean(dups1).length==dups1.length-1);
      assertTrue(ah.clean(dups2).length==dups2.length-2);
      assertTrue(ah.clean(dups3).length==dups3.length-2);
      }
   }
