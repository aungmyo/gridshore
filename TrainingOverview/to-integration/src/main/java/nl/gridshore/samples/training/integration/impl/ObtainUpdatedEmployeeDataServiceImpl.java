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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 18, 2008
 * Time: 8:49:42 PM
 * Default implementation for the ObtainUpdatedEmployeeDataService interface
 */
public class ObtainUpdatedEmployeeDataServiceImpl implements ObtainUpdatedEmployeeDataService {
    final Logger logger = LoggerFactory.getLogger(ObtainUpdatedEmployeeDataServiceImpl.class);

    public Set<UpdatedEmployeeData> obtainEmployeeData(String filenameIncludingPath) {
        File file = new File(filenameIncludingPath);
        if (!file.exists()) {
            logger.info("The file {} could not be found for loading employee data",file);
            throw new IntegrationInputDataException(IntegrationExceptionCodes.ERROR_FILE_PATH_NOT_EXISTS);
        }
        return obtainEmployeeData(file);
    }

    public Set<UpdatedEmployeeData> obtainEmployeeData(File file) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            logger.info("The file could not be found for loading employee data",e);
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
            logger.info("There were problems reading the input stream for importing employee data",e);
            throw new IntegrationException(IntegrationExceptionCodes.ERROR_OBTAIN_RECORDS_FROM_INPUTSTREAM,e);
        }

        return helper.getAllEmployees();
    }

}
