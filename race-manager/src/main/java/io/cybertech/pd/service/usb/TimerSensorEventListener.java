package io.cybertech.pd.service.usb;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import io.cybertech.pd.model.HeatResults;
import io.cybertech.pd.service.HeatResultServiceBean;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import lombok.extern.slf4j.Slf4j;


/**
 * 
 * https://code.google.com/archive/p/java-simple-serial-connector/wikis/jSSC_examples.wiki
 */
@Slf4j
public class TimerSensorEventListener implements SerialPortEventListener {
	private final SerialPort serialPort;
	private final HeatResultServiceBean heatResultServiceBean;
	
	public TimerSensorEventListener(SerialPort serialPort, HeatResultServiceBean heatResultServiceBean) {
		this.serialPort = serialPort;
		this.heatResultServiceBean = heatResultServiceBean;
	}
	
	private StringBuilder heatResultsStream = new StringBuilder();
	
	@Override
	public void serialEvent(SerialPortEvent event) {
		if(event.isRXCHAR()){//If data is available
			
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
				log.debug("");
    			log.info("<<<<<<<<<<<  Result >>>>>>>>>>");
    			log.info("Raw: {}", results);
    			
    			// process heat results
    			HeatResults heatResults = SerialPortParser.buildResult(results.trim());
    			heatResultServiceBean.processHeatResults(heatResults);
    			
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
	 * @see http://www.asciitable.com/
	 */
	private String cleanupBuffer(String buffer) {
		String results = StringUtils.chomp(buffer);
		results = StringUtils.chomp(buffer); // do it twice since we may have a 13 and 10
		return results;
	}
}
