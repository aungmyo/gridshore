package nl.gridshore.samples.bundles.trainingservice.api;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 9, 2008
 * Time: 10:14:51 PM
 * Service definition to be exposed
 */
public interface TrainingService {
    List<String> obtainTrainings();
}
