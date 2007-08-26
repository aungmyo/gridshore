package nl.gridshore.paymentsample.httpunit;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 17-aug-2007
 * Time: 0:12:01
 * Job used to close all open payments
 */
public class OpenPaymentsJob extends QuartzJobBean {
    private final static Logger logger = Logger.getLogger(OpenPaymentsJob.class);
    private int timeout;

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<String> results = UpdateStatusOfOpenOrders.doUpdate();
        for (String result : results) {
            logger.info(result);
        }
    }
}
