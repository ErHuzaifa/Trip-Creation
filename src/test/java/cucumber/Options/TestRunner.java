package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions

//(features="src/test/java/features/v2VendorOrderSingle.feature", glue= "stepDefinations")
//(features="src/test/java/features/Dotpe.feature", glue= "stepDefinations")
//(features="src/test/java/features/v1VendorOrder.feature", glue= "stepDefinations")
(features="src/test/java/features", glue= "stepDefinations")

public class TestRunner {

}




