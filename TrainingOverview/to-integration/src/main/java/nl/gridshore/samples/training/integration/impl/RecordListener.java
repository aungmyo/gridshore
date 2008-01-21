package nl.gridshore.samples.training.integration.impl;

import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.record.*;
import nl.gridshore.samples.training.integration.vo.UpdatedEmployeeData;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 18, 2008
 * Time: 9:50:43 PM
 * Class used to process read records, based on state it is not possible to make this a singleton
 */
class RecordListener implements HSSFListener {
    private ObtainUpdatedEmployeeDataServiceHelper serviceHelper;
    private SSTRecord sstrec;
    public static final int HEADER_ROW_NUMBER = 518 - 1; // rownumber in excel minus 1
    public static final int COL_EMPLOYEE_NUMBER = 0;
    public static final int COL_CUSTER = 13;
    public static final int COL_CELL = 15;
    public static final int COL_COMPLETE_NAME = 12;
    public static final int COL_CLIENT = 17;
    public static final int COL_LEVEL = 14;


    public RecordListener(ObtainUpdatedEmployeeDataServiceHelper serviceHelper) {
        this.serviceHelper = serviceHelper;
    }


    public void processRecord(Record record) {
        switch (record.getSid()) {
            case RowRecord.sid:
                RowRecord rowrec = (RowRecord) record;
                if (rowrec.getRowNumber() > HEADER_ROW_NUMBER) {
                    String key = "row" + rowrec.getRowNumber();
                    serviceHelper.addUpdatedEmployee(key);
                }
                break;
            case NumberRecord.sid:
                doHandleNumberColumn((NumberRecord) record);
                break;
            case SSTRecord.sid:
                sstrec = (SSTRecord) record;
                break;
            case LabelSSTRecord.sid:
                doHandleStringColumn((LabelSSTRecord)record);
                break;
        }
    }

    private void doHandleStringColumn(LabelSSTRecord lrec) {
        if (lrec.getRow() > HEADER_ROW_NUMBER) {
            switch (lrec.getColumn()) {
                case COL_CUSTER:
                    serviceHelper.getUpdatedEmployee("row"+lrec.getRow()).setCluster(sstrec.getString(lrec.getSSTIndex()).toString());
                    break;
                case COL_CELL:
                    serviceHelper.getUpdatedEmployee("row"+lrec.getRow()).setCell(sstrec.getString(lrec.getSSTIndex()).toString());
                    break;
                case COL_COMPLETE_NAME:
                    serviceHelper.getUpdatedEmployee("row"+lrec.getRow()).setLongName(sstrec.getString(lrec.getSSTIndex()).toString());
                    break;
                case COL_CLIENT:
                    String clientProject = sstrec.getString(lrec.getSSTIndex()).toString();
                    UpdatedEmployeeData empData = serviceHelper.getUpdatedEmployee("row"+lrec.getRow());
                    String[] data = clientProject.split("-");
                    if (data.length > 1) {
                        empData.setProject(data[1].trim());
                    } else {
                        empData.setProject(null);
                    }
                    empData.setClient(data[0].trim());
                    break;
                case COL_LEVEL:
                    serviceHelper.getUpdatedEmployee("row"+lrec.getRow()).setLevel(sstrec.getString(lrec.getSSTIndex()).toString());
                    break;
            }
        }
    }

    private void doHandleNumberColumn(NumberRecord numrec) {
        if (numrec.getRow() > HEADER_ROW_NUMBER) {
            String key = "row" + numrec.getRow();
            String formattedNumber = String.format("%10.0f", numrec.getValue());
            switch (numrec.getColumn()) {
                case COL_EMPLOYEE_NUMBER:
                    serviceHelper.getUpdatedEmployee(key).setIdNumber(formattedNumber.trim());
                    break;
            }
        }
    }

}
