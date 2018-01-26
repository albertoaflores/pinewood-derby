package io.cybertech.pd.sensor.usb;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import io.cybertech.pd.sensor.handler.HeatResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SerialPortScanner {
    @Autowired private HeatResultHandler heatResultHandler;
	private Map<String, SerialPort> serialPorts = new HashMap<>();

	@PostConstruct
	public void configure() {
		String[] portNames = SerialPortList.getPortNames();
		if (portNames.length > 0) {
			// TODO: what happens when we have multiple ports plugged in the computer?
			log.info("Detected {} serial ports!", portNames.length);
			for(int i = 0; i < portNames.length; i++){
				String serialPortName = portNames[i];
				register(serialPortName);
			}
		} else {
			log.warn("No serial port in use detected. Please verify cables are attached!");
		}
	}
	
	@PreDestroy
	public void destroy() {
		for (SerialPort serialPort : serialPorts.values()) {
			try {
				log.info("Closing serial port '{}'", serialPort.getPortName());
				serialPort.closePort();
			} catch (SerialPortException e) {
				// TODO: Should we do something if we can't close a port?
				log.error("Unable to close serial port!", e);
			}
		}
	}
	
	public void register(String serialPortAddress) {
		SerialPort serialPort = serialPorts.get(serialPortAddress);
				
		if (serialPort == null) {
			serialPort = new SerialPort(serialPortAddress);
			serialPorts.put(serialPortAddress, serialPort);
			log.info("Creating new serial port '{}'", serialPortAddress);
		} else {
			// TODO: Do we ever reuse serial ports? Perhaps when the cable is unplugged and plugged again?
			log.info("Reusing serial port '{}'", serialPortAddress);
		}
		
		try {
	    	// Prepare 
	    	serialPort.openPort();
	        serialPort.setParams(SerialPort.BAUDRATE_9600, 
			                     SerialPort.DATABITS_8,
			                     SerialPort.STOPBITS_1,
			                     SerialPort.PARITY_NONE);
	        
	        // Prepare mask
	        int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR; 
	        serialPort.setEventsMask(mask);
	        
	        // setup listener 
	        serialPort.addEventListener(new TimerSensorEventListener(serialPort, heatResultHandler));
	        log.info("Registered time sensor listener on serial port '{}'", serialPortAddress);
	    }
	    catch (SerialPortException ex) {
			// TODO: Need to flag this error state as we are not able to register a port
	    	log.error("Unable to register serial port!", ex);
	    }
	}

}
