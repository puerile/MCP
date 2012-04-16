package yuri.arrays;

public class ArrayHandler
   {
   // removes empty entries in a string list and returns a new array
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

   public boolean containsDuplicates(String[] array)
      {
      if (array.length > 1)
         {
         System.out.println(array.length);
         for (int i = 0; i < array.length; i++)
            {
            String a = array[i];

            for (int j = i + 1; j < array.length; j++)
               {
               System.out.println("array[i] = " + a);
               System.out.println("array[j] = " + array[j]);

               if (a.equals(array[j]))
                  {
                  return true;
                  }
               }
            }
         }

      return false;
      }

   // checks whether a given array includes a certain string
   public int contains(String[] array, String string)
      {
      int result = 0;
      
      for (int i = 0; i < array.length; i++)
         {
         if (array[i] != null && array[i].equals(string))
            {
            result++;
            }
         }

      return result;
      }

   // remove duplicate entries in array
   public String[] clean(String[] array)
      {
      String[] result = new String[array.length];

      for (int i = 0; i < array.length; i++)
         {
         if (contains(result, array[i])<1)
            {
            result[i] = array[i];
            }
         }

      return removeEmpty(result);
      }

   // removes empty entries in a string list and returns a new array
   public String[] del(String[] array, String entry)
      {
      int j = 0;

      for (int i = 0; i < array.length; i++)
         {
         if (array[i].equals(entry))
            {
            j++;
            }
         }

      String[] result = new String[array.length - j];
      j = 0;

      for (int i = 0; i < array.length; i++)
         {
         if (!(array[i].equals(entry)))
            {
            result[j] = array[i];
            j++;
            }
         }

      return result;
      }
   }
