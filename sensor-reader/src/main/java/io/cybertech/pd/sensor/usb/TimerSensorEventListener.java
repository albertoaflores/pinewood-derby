package io.cybertech.pd.sensor.usb;

import io.cybertech.pd.sensor.handler.HeatResultHandler;
import io.cybertech.pd.sensor.model.HeatResult;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import lombok.extern.slf4j.Slf4j;


/**
 * Implementation of a {@link SerialPortEventListener} that reacts to {@link SerialPortEvent} events.
 *
 * For more information, see:
 *
 * https://code.google.com/archive/p/java-simple-serial-connector/wikis/jSSC_examples.wiki
 */
@Slf4j
public class TimerSensorEventListener implements SerialPortEventListener {
    private final HeatResultHandler heatResultHandler;
	private final SerialPort serialPort;
	
	public TimerSensorEventListener(SerialPort serialPort, HeatResultHandler heatResultHandler) {
		this.serialPort = serialPort;
		this.heatResultHandler = heatResultHandler;
	}
	
	private StringBuilder heatResultsStream = new StringBuilder();
	
	@Override
	public void serialEvent(SerialPortEvent event) {
		if (event.isRXCHAR()){//If data is available
			
			// per the SerialPortEvent API, "eventValue" is the bytes count for RXCHAR events.
			int bytesCount = event.getEventValue();
            
    		log.debug("************* Event Info *************");
    		log.debug("Detected {} byte(s) from Serial Port '{}'", bytesCount, event.getPortName());
            
			String buffer = readBytes(bytesCount);
			log.debug("Buffer: '{}'", buffer);
			
			// always append to results buffer
			heatResultsStream.append(buffer);
			
			if (isLastBuffer(buffer)) {
    			String results = cleanupBuffer(heatResultsStream.toString());
				log.debug("<<<<<<<<<<<  Result >>>>>>>>>>");
    			log.debug("Raw: {}", results);
    			
    			// build and handle heat results
    			HeatResult heatResults = TimerSensorEventMessageParser.buildResultFromThreeLaneEvent(results.trim());
    			heatResultHandler.handleHeatResult(heatResults);

    			// reset buffer
    			heatResultsStream = new StringBuilder();
			}
            
        } else if(event.isCTS()){//If CTS line has changed state
        	
            if(event.getEventValue() == 1){//If line is ON
            	log.debug("CTS - ON");
            }
            else {
            	log.debug("CTS - OFF");
            }
            
        } else if(event.isDSR()){///If DSR line has changed state
        	
            if(event.getEventValue() == 1){//If line is ON
            	log.debug("DSR - ON");
            }
            else {
            	log.debug("DSR - OFF");
            }
        }
	}
	
	private String readBytes(int bytesCount) {
		try {
            byte buffer[] = serialPort.readBytes(bytesCount);
            log.debug("Bytes  : {}", buffer);
            StringBuilder sb = new StringBuilder();
            for (byte b : buffer) {
            	
            	char c = (char) b;
            	if (!CharUtils.isAscii(c)) {
            		log.error("Received not ASCII character!");
            	} 
            	
                sb.append(c);
            }
            return sb.toString();
        } catch (SerialPortException ex) {
        	log.error("Error reading bytes!", ex);
        }
		return null;
	}

	//
	private boolean isLastBuffer(String buffer) {
		// The last characters have to be 10 (line feed)
		if (buffer != null && CharUtils.LF == buffer.charAt(buffer.length() - 1) ) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @see 'http://www.asciitable.com/'
	 */
	private String cleanupBuffer(String buffer) {
		String results = StringUtils.chomp(buffer);
		return results;
	}
}
