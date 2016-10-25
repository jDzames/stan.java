package io.nats.stan;

import static io.nats.stan.UnitTestUtilities.runDefaultServer;
import static org.junit.Assert.fail;

import io.nats.client.NUID;
import io.nats.stan.examples.StanBench;

import ch.qos.logback.classic.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class StanBenchTest {
    static final Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    static final Logger logger = (Logger) LoggerFactory.getLogger(StanBenchTest.class);

    static final LogVerifier verifier = new LogVerifier();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TestCasePrinterRule pr = new TestCasePrinterRule(System.out);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    @Before
    public void setUp() throws Exception {}

    @After
    public void tearDown() throws Exception {}

    // @Test
    // public void testStanBenchStringArray() {
    // fail("Not yet implemented"); // TODO
    // }

    @Test
    public void testStanBenchProperties() {
        try (StanServer srv = runDefaultServer()) {
            Properties props = new Properties();
            String client = NUID.nextGlobal();
            props.setProperty("bench.stan.servers", "nats://localhost:4222");
            props.setProperty("bench.stan.cluster.id", "my_test_cluster");
            props.setProperty("bench.stan.client.id", client);
            props.setProperty("bench.stan.secure", "false");
            props.setProperty("bench.stan.msg.count", "10000");
            props.setProperty("bench.stan.msg.size", "0");
            props.setProperty("bench.stan.secure", "false");
            props.setProperty("bench.stan.pubs", "1");
            props.setProperty("bench.stan.subs", "0");
            props.setProperty("bench.stan.subject", "foo");
            props.setProperty("bench.stan.pub.maxpubacks", "1000");
            props.setProperty("bench.stan.sub.ignoreold", Boolean.toString(true));
            props.setProperty("bench.stan.async", Boolean.toString(true));

            final StanBench bench = new StanBench(props);
            try {
                bench.run();
            } catch (Exception e) {
                fail(e.getMessage());
            }
        }
    }

    // @Test
    // public void testRun() {
    // fail("Not yet implemented"); // TODO
    // }
    //
    // @Test
    // public void testInstallShutdownHook() {
    // fail("Not yet implemented"); // TODO
    // }
    //
    // @Test
    // public void testUsage() {
    // fail("Not yet implemented"); // TODO
    // }
    //
    // @Test
    // public void testMain() {
    // fail("Not yet implemented"); // TODO
    // }
}
