package be.unamur.info.b314.compiler.main;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314localvariabledeclSemanticsTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314localvariabledeclSemanticsTest.class);

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder(); // Create a temporary folder for outputs deleted after tests

    @Rule
    public TestRule watcher = new TestWatcher() { // Prints message on logger before each test
        @Override
        protected void starting(Description description) {
            LOG.info(String.format("Starting test: %s()...",
                    description.getMethodName()));
        }
    ;
    };

    @Test
    public void testlocalvariabledecl_fct_local_variable_diff_names() throws Exception{
        File pcodeFile = testFolder.newFile();
        CompilerTestHelper.launchCompilation("/semantics/localvariabledecl/fct_local_variable_diff_names.b314", pcodeFile, true, "localvariabledecl: fct_local_variable_diff_names");
        LOG.debug("PCode is: {{}", FileUtils.readFileToString(pcodeFile));
        InterpreterResult result;
        // Turns: 1
        LOG.debug("Starting interpretation with 1 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 1);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(1));
        assertThat(result.getOutLines(), contains("1"));
        // Turns: 3
        LOG.debug("Starting interpretation with 3 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 3);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(3));
        assertThat(result.getOutLines(), contains("1", "1", "1"));
        // Turns 5
        LOG.debug("Starting interpretation with 5 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 5);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(5));
        assertThat(result.getOutLines(), contains("1", "1", "1", "1", "1"));
    }

    @Test
    public void testlocalvariabledecl_fct_local_variable_same_names_as_global() throws Exception{
        File pcodeFile = testFolder.newFile();
        CompilerTestHelper.launchCompilation("/semantics/localvariabledecl/fct_local_variable_same_names_as_global.b314", pcodeFile, true, "localvariabledecl: fct_local_variable_same_names_as_global");
        LOG.debug("PCode is: {{}", FileUtils.readFileToString(pcodeFile));
        InterpreterResult result;
        // Turns: 1
        LOG.debug("Starting interpretation with 1 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 1);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(1));
        assertThat(result.getOutLines(), contains("1"));
        // Turns: 3
        LOG.debug("Starting interpretation with 3 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 3);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(3));
        assertThat(result.getOutLines(), contains("1", "1", "1"));
        // Turns 5
        LOG.debug("Starting interpretation with 5 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 5);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(5));
        assertThat(result.getOutLines(), contains("1", "1", "1", "1", "1"));
    }

}