import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses({
        CircleBufferTest.class,
        GeneratingThreadTest.class
})
@RunWith(Suite.class)
public class ComplexTestSuite {
}
