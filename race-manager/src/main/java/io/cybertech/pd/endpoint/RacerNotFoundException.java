package io.cybertech.pd.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Racer not found!")
public class RacerNotFoundException extends RuntimeException {
}
