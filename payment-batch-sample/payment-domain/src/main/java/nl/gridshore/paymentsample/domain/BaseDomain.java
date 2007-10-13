package nl.gridshore.paymentsample.domain;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 29-aug-2007
 * Time: 21:25:16
 * To change this template use File | Settings | File Templates.
 */
public class BaseDomain implements Serializable {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
