package info.logconsole.admin.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiahongjian 
 * @time   2018-04-10 16:56:16
 *
 */
@RestController
public class IndexController {
	private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);
	
	@GetMapping("/")
	public String index() {
		LOG.info("info");
		LOG.warn("warn");
		LOG.error("error");
		return "done";
	}

	@GetMapping("/localDate")
	public LocalDate localDate() {
		return LocalDate.now();
	}
	
	@GetMapping("/localTime")
	public LocalTime localTime() {
		return LocalTime.now();
	}
	
	@GetMapping("/localDateTime")
	public LocalDateTime localDateTime() {
		return LocalDateTime.now();
	}
}
