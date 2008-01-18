package nl.gridshore.samples.training.domain;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 18, 2008
 * Time: 11:37:18 PM
 * Domain class
 */
public class Training extends BaseDomain {
    private String name;
    private String code;
    private String remark;
    
    List<TrainingSession> sessions = new ArrayList<TrainingSession>();
}
