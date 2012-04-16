package yuri.checksum;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5
   {
   public String checkSum(File file) throws NoSuchAlgorithmException, IOException
      {
      FileInputStream stream = new FileInputStream(file);
      byte[] bytes = new byte[(int) file.length()];
      MessageDigest digest = MessageDigest.getInstance("MD5");

      stream.read(bytes);

      digest.update(bytes);

      byte[] hash = digest.digest();

      return MD5.convertToHex(hash);
      }

   public static String convertToHex(byte[] data)
      {
      StringBuffer buf = new StringBuffer();
      for (int i = 0; i < data.length; i++)
         {
         int halfbyte = (data[i] >>> 4) & 0x0F;
         int two_halfs = 0;
         do
            {
            if ((0 <= halfbyte) && (halfbyte <= 9))
               buf.append((char) ('0' + halfbyte));
            else
               buf.append((char) ('a' + (halfbyte - 10)));
            halfbyte = data[i] & 0x0F;
            }
         while (two_halfs++ < 1);
         }
      return buf.toString();
      }
   }
