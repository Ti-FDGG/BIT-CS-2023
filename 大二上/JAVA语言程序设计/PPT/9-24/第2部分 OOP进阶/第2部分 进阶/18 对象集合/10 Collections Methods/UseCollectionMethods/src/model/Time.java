package model;

public class Time
{
   private int hour;   // 0 - 23
   private int minute; // 0 - 59
   private int second; // 0 - 59


   public Time()
   {
      this( 0, 0, 0 );
   }


   public Time(int h )
   { 
      this( h, 0, 0 );
   }


   public Time(int h, int m )
   { 
      this( h, m, 0 );
   }


   public Time(int h, int m, int s )
   { 
      setTime( h, m, s );
   }


   public Time(Time time )
   {
      // invoke Time2 three-argument constructor
      this( time.getHour(), time.getMinute(), time.getSecond() );
   } // end Time2 constructor with a Time2 object argument

   //region Getter/Set Methods
   // set a new time value using universal time; ensure that 
   // the data remains consistent by setting invalid values to zero
   public void setTime( int h, int m, int s )
   {
      setHour( h );   // set the hour
      setMinute( m ); // set the minute
      setSecond( s ); // set the second
   } // end method setTime

   // validate and set hour 
   public void setHour( int h ) 
   { 
      hour = ( ( h >= 0 && h < 24 ) ? h : 0 ); 
   } // end method setHour

   // validate and set minute 
   public void setMinute( int m ) 
   { 
      minute = ( ( m >= 0 && m < 60 ) ? m : 0 ); 
   } // end method setMinute

   // validate and set second 
   public void setSecond( int s ) 
   { 
      second = ( ( s >= 0 && s < 60 ) ? s : 0 ); 
   } // end method setSecond

   // Get Methods
   // get hour value
   public int getHour() 
   { 
      return hour; 
   } // end method getHour

   // get minute value
   public int getMinute() 
   { 
      return minute; 
   } // end method getMinute

   // get second value
   public int getSecond() 
   { 
      return second; 
   } // end method getSecond
//endregion

   // convert to String in universal-time format (HH:MM:SS)
   public String toUniversalString()
   {
      return String.format( 
         "%02d:%02d:%02d", getHour(), getMinute(), getSecond() );
   }

   // convert to String in standard-time format (H:MM:SS AM or PM)
   public String toString()
   {
      return String.format( "%d:%02d:%02d %s", 
         ( (getHour() == 0 || getHour() == 12) ? 12 : getHour() % 12 ),
         getMinute(), getSecond(), ( getHour() < 12 ? "AM" : "PM" ) );
   }
}

