package yuri.exceptions;

public class ReadInterruptedException extends Exception
   {

   /**
    * wird aufgerufen, wenn der Lese- oder Schreibvorgang
    * unterbrochen werden soll
    */
   private static final long serialVersionUID = 1L;

   public ReadInterruptedException()
      {
      // TODO Auto-generated constructor stub
      }

   public ReadInterruptedException(String arg0)
      {
      super(arg0);
      // TODO Auto-generated constructor stub
      }

   public ReadInterruptedException(Throwable arg0)
      {
      super(arg0);
      // TODO Auto-generated constructor stub
      }

   public ReadInterruptedException(String arg0, Throwable arg1)
      {
      super(arg0, arg1);
      // TODO Auto-generated constructor stub
      }

   }
