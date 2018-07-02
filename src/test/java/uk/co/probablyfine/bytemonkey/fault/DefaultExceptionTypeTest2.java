package uk.co.probablyfine.bytemonkey.fault;

import java.io.IOException;

import com.ea.agentloader.AgentLoader;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.co.probablyfine.bytemonkey.ByteMonkeyAgent;
import uk.co.probablyfine.bytemonkey.testfiles.FaultTestObject;

public class DefaultExceptionTypeTest2 {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldThrowSomethingIDK() throws IOException {
        AgentLoader.loadAgentClass(
            ByteMonkeyAgent.class.getName(),
            "mode:fault,filter:uk/co/probablyfine/bytemonkey/testfiles/FaultTestObject/printAndThrowNonPublicException;uk/co/probablyfine/bytemonkey/testfiles/FaultTestObject/printSomething"
        );
        expectedException.expect(IOException.class);
        new FaultTestObject().printAndThrowNonPublicException();
        new FaultTestObject().printSomething();
    }
}
