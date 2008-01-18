package nl.gridshore.samples.training.integration.impl;

import nl.gridshore.samples.training.integration.ObtainUpdatedEmployeeDataService;
import nl.gridshore.samples.training.integration.exceptions.IntegrationException;
import nl.gridshore.samples.training.integration.exceptions.IntegrationExceptionCodes;
import nl.gridshore.samples.training.integration.exceptions.IntegrationInputDataException;
import nl.gridshore.samples.training.integration.vo.UpdatedEmployeeData;

import java.util.Set;
import java.io.*;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 18, 2008
 * Time: 8:49:42 PM
 * Default implementation for the ObtainUpdatedEmployeeDataService interface
 */
public class ObtainUpdatedEmployeeDataServiceImpl implements ObtainUpdatedEmployeeDataService {

    public Set<UpdatedEmployeeData> obtainEmployeeData(String filenameIncludingPath) {
        File file = new File(filenameIncludingPath);
        if (!file.exists()) {
            throw new IntegrationInputDataException(IntegrationExceptionCodes.ERROR_FILE_PATH_NOT_EXISTS);
        }
        return obtainEmployeeData(file);
    }

    public Set<UpdatedEmployeeData> obtainEmployeeData(File file) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new IntegrationInputDataException(IntegrationExceptionCodes.ERROR_FILE_PATH_NOT_EXISTS);
        }
        return obtainEmployeeData(inputStream);
    }

    public Set<UpdatedEmployeeData> obtainEmployeeData(InputStream inputStream) {

        ObtainUpdatedEmployeeDataServiceHelper helper = new ObtainUpdatedEmployeeDataServiceHelper();

        try {
            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            InputStream excelIn = fs.createDocumentInputStream("Workbook");
            HSSFRequest req = new HSSFRequest();
            req.addListenerForAllRecords(new RecordListener(helper));
            HSSFEventFactory factory = new HSSFEventFactory();
            factory.processEvents(req, excelIn);
            excelIn.close();
        } catch (IOException e) {
            throw new IntegrationException(IntegrationExceptionCodes.ERROR_OBTAIN_RECORDS_FROM_INPUTSTREAM,e);
        }

        return helper.getAllEmployees();
    }

}
