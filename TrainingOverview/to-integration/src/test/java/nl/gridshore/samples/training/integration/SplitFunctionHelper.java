package nl.gridshore.samples.training.integration;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 21, 2008
 * Time: 9:22:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class SplitFunctionHelper {
    public static void main(String[] args) {
        runSplit("Client - Project");
        runSplit("Client");
    }

    private static void runSplit(String toTest) {
        String[] result = toTest.split("-");
        System.out.println("client : " + result[0].trim());
        System.out.println("project : " + result[1].trim());
    }
}
