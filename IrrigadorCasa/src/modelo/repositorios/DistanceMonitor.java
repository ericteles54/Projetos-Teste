package modelo.repositorios;

/**
 * Class to monitor distance measured by an HC-SR04 distance sensor on a 
 * Raspberry Pi.
 * 
 * The main method assumes the trig pin is connected to the pin # 7 and the echo
 * pin is connected to pin # 11.  Output of the program are comma separated lines
 * where the first value is the number of milliseconds since unix epoch, and the
 * second value is the measured distance in centimeters.
 */



/**
 * DistanceMonitor class to monitor distance measured by sensor
 * 
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class DistanceMonitor {
    
    private final static float SOUND_SPEED = 340.29f;  // speed of sound in m/s
    
    private final static int TRIG_DURATION_IN_MICROS = 10; // trigger duration of 10 micro s
    private final static int WAIT_DURATION_IN_MILLIS = 60; // wait 60 milli s

    private final static int TIMEOUT = 2100;
    
    private Gpio gpio = Gpio.getInstance();
    
    public DistanceMonitor() {
        this.gpio.getTrigPinDistanceSensor().low();
    }
    
    /*
     * This method returns the distance measured by the sensor in cm
     * 
     * @throws TimeoutException if a timeout occurs
     */
    public float measureDistance() throws TimeoutException {
        this.triggerSensor();
        this.waitForSignal();
        long duration = this.measureSignal();
        
        return duration * SOUND_SPEED / ( 2 * 10000 );
    }

    /**
     * Put a high on the trig pin for TRIG_DURATION_IN_MICROS
     */
    private void triggerSensor() {
        try {
            this.gpio.getTrigPinDistanceSensor().high();
            Thread.sleep( 0, TRIG_DURATION_IN_MICROS * 1000 );
            this.gpio.getTrigPinDistanceSensor().low();
        } catch (InterruptedException ex) {
            System.err.println( "Interrupt during trigger" );
        }
    }
    
    /**
     * Wait for a high on the echo pin
     * 
     * @throws DistanceMonitor.TimeoutException if no high appears in time
     */
    private void waitForSignal() throws TimeoutException {
        int countdown = TIMEOUT;
        
        while( this.gpio.getEchoPinDistanceSensor().isLow() && countdown > 0 ) {
            countdown--;
        }
        
        if( countdown <= 0 ) {
            throw new TimeoutException( "Timeout waiting for signal start" );
        }
    }
    
    /**
     * @return the duration of the signal in micro seconds
     * @throws DistanceMonitor.TimeoutException if no low appears in time
     */
    private long measureSignal() throws TimeoutException {
        int countdown = TIMEOUT;
        long start = System.nanoTime();
        while( this.gpio.getEchoPinDistanceSensor().isHigh() && countdown > 0 ) {
            countdown--;
        }
        long end = System.nanoTime();
        
        if( countdown <= 0 ) {
            throw new TimeoutException( "Timeout waiting for signal end" );
        }
        
        return (long)Math.ceil( ( end - start ) / 1000.0 );  // Return micro seconds
    }    

    public static int getWaitDurationInMillis() {
		return WAIT_DURATION_IN_MILLIS;
	}

	/**
     * Exception thrown when timeout occurs
     */
    private static class TimeoutException extends Exception {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private final String reason;
        
        public TimeoutException( String reason ) {
            this.reason = reason;
        }
        
        @Override
        public String toString() {
            return this.reason;
        }
    }
    
}
