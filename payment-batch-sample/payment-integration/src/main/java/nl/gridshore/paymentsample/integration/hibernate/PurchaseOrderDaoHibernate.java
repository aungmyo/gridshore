package nl.gridshore.paymentsample.integration.hibernate;

import nl.gridshore.paymentsample.domain.PurchaseOrder;
import nl.gridshore.paymentsample.integration.PurchaseOrderDao;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 29-aug-2007
 * Time: 22:07:31
 * To change this template use File | Settings | File Templates.
 */
public class PurchaseOrderDaoHibernate extends BaseDaoHibernate<PurchaseOrder> implements PurchaseOrderDao {
    public PurchaseOrderDaoHibernate() {
        super(PurchaseOrder.class);
    }
}
