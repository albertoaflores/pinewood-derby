package io.cybertech.pd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PageController {
	
	@RequestMapping("/page/heats")
    public String heat(@RequestParam(name="heatId", required=false) Long heatId) {
		if (heatId != null) {
			log.info("Racing heat {}", heatId);
			return "race";
		}
        return "heats";
    }
	
}
