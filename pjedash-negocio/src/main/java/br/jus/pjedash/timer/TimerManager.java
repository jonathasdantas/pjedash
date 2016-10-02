package br.jus.pjedash.timer;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

@Singleton
@Startup
public class TimerManager {
	@Resource
	TimerService timerService;

	@PostConstruct
	private void init() {
		System.out.println("TimerManager -> init()");
		
		// TODO pegar todas as configurações de alertas e criar timers por orgao julgador 
		//Date date = new Date();
		//Calendar calender = Calendar.getInstance();
		//calender.setTimeInMillis(date.getTime());
		//calender.add(Calendar.SECOND, 10);
		//Date changeDate = calender.getTime();
	    
		//createTimer("timer1", changeDate);
	}

	public void createTimer(String timerInfo, Date expiration){
        timerService.createTimer(expiration, timerInfo);
     }
	
	public void changeTimerExpiration(String timerInfo, Date newExpiration) {
		cancelTimer(timerInfo);
		createTimer(timerInfo, newExpiration);
	}
	
	public void cancelTimer(String timerInfo) {
		if (timerService.getTimers() != null) {
			for (Timer timer : timerService.getTimers()) {
				if (timer.getInfo().equals(timerInfo)) {
					timer.cancel();
				}
			}
		}
	}

	@Timeout
	public void execute(Timer timer) {
		System.out.println("Executing ...");
		System.out.println("Execution Time : " + new Date());
		System.out.println("____________________________________________"); 
	}
}
