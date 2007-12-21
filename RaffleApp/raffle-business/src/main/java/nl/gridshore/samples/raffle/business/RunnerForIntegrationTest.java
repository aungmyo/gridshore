package nl.gridshore.samples.raffle.business;

import nl.gridshore.samples.raffle.domain.Participant;
import nl.gridshore.samples.raffle.domain.Raffle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 15, 2007
 * Time: 3:14:35 PM
 * File used to test the complete integration of spring, jpa and database
 */
public class RunnerForIntegrationTest {
    private ApplicationContext appContext;

    public static void main(String[] args) {
        RunnerForIntegrationTest runner = new RunnerForIntegrationTest();
        System.out.println("count : " + runner.countRaffles());
        runner.addRaffle();
        runner.addParticipant();
    }

    public RunnerForIntegrationTest() {
        appContext = new ClassPathXmlApplicationContext(new String[]{"classpath:business-config.xml", "dao-config.xml"});
    }

    public int countRaffles() {
        RaffleService raffleService = getRaffleService();
        return raffleService.giveAllRaffles().size();
    }

    public void addParticipant() {
        Raffle raffle = getRaffleService().giveAllRaffles().get(0);
        Participant participant = new Participant();
        participant.setName("Jettro");
        raffle.addParticipant(participant);
        getRaffleService().storeRaffle(raffle);
    }

    public void addRaffle() {
        Raffle raffle = new Raffle();
        raffle.setTitle("Runtime raffle");
        raffle.setDescription("This raffle is for nerds");
        getRaffleService().storeRaffle(raffle);
    }

    private RaffleService getRaffleService() {
        return (RaffleService) appContext.getBean("raffleService");
    }

}
