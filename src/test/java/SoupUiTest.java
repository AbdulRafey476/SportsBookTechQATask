import com.eviware.soapui.SoapUI;
import com.eviware.soapui.StandaloneSoapUICore;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCaseRunner;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.support.SoapUIException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SoupUiTest {

    @Test
    public static void getTestSuite() throws IOException, SoapUIException {

        String suiteName = "";
        String reportString = "";

        // variables for getting duration
        long startTime = 0;
        long duration = 0;

        WsdlTestCaseRunner runner = null;

        List<TestSuite> suiteList = new ArrayList<>();
        List<TestCase> caseList = new ArrayList<>();

        SoapUI.setSoapUICore(new StandaloneSoapUICore(true));

        // specified soapUI project
        WsdlProject project = new WsdlProject("SportBookTechQATask-soapui-project.xml");

        // get a list of all test suites on the project
        suiteList = project.getTestSuiteList();

        // you can use for each loop
        for(int i = 0; i < suiteList.size(); i++){

            // get name of the "i" element in the list of a test suites
            suiteName = suiteList.get(i).getName();
            reportString = reportString + "\nTest Suite: " + suiteName;

            // get a list of all test cases on the "i"-test suite
            caseList = suiteList.get(i).getTestCaseList();


            for(int k = 0; k < caseList.size(); k++){

                startTime = System.currentTimeMillis();

                // run "k"-test case in the "i"-test suite
                runner = project.getTestSuiteByName(suiteName).getTestCaseByName(caseList.get(k).getName()).run(new PropertiesMap(), false);

                duration = System.currentTimeMillis() - startTime;

                reportString = reportString + "\n\tTestCase: " + caseList.get(k).getName() + "\tStatus: " + runner.getStatus() + "\tReason: " + runner.getReason() + "\tDuration: " + duration;
            }

        }

        System.out.println(reportString);
    }
}
