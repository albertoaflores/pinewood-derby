package io.cybertech.pd.service.usb;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import io.cybertech.pd.service.HeatResultServiceBean;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Profile({"integration", "production"})
public class SerialPortScanner {
	private Map<String, SerialPort> serialPorts = new HashMap<String, SerialPort>();
	@Autowired private HeatResultServiceBean resultsHandler;
	
	@PostConstruct
	public void configure() {
		String[] portNames = SerialPortList.getPortNames();
	    for(int i = 0; i < portNames.length; i++){
	    	String serialPortName = portNames[i];
	        register(serialPortName);
	    }
	}
	
	@PreDestroy
	public void destroy() {
		for (SerialPort serialPort : serialPorts.values()) {
			try {
				log.info("Closing serial port '{}'", serialPort.getPortName());
				serialPort.closePort();
			} catch (SerialPortException e) {
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
	        serialPort.setEventsMask(mask);//Set mask
	        
	        // setup listener 
	        serialPort.addEventListener(new TimerSensorEventListener(serialPort, resultsHandler));
	        log.info("Registered listener for serial port '{}'", serialPortAddress);
	    }
	    catch (SerialPortException ex) {
	    	log.error("Unable to register serial port!", ex);
	    }
	}

}
