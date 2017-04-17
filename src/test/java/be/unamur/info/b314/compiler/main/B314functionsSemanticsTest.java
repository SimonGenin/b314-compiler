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

public class B314functionsSemanticsTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314functionsSemanticsTest.class);

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
    public void testfunctions_factorial_recursive_call() throws Exception{
        File pcodeFile = testFolder.newFile();
        CompilerTestHelper.launchCompilation("/semantics/functions/factorial_recursive_call.b314", pcodeFile, true, "functions: factorial_recursive_call");
        LOG.debug("PCode is: {{}", FileUtils.readFileToString(pcodeFile));
        InterpreterResult result;
        // Turns: 1
        LOG.debug("Starting interpretation with 1 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 1);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(1));
        assertThat(result.getOutLines(), contains("0"));
        // Turns: 3
        LOG.debug("Starting interpretation with 3 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 3);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(3));
        assertThat(result.getOutLines(), contains("0", "0", "0"));
        // Turns 5
        LOG.debug("Starting interpretation with 5 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 5);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(5));
        assertThat(result.getOutLines(), contains("0", "0", "0", "0", "0"));
    }

    @Test
    public void testfunctions_multiple_functions_no_local_var() throws Exception{
        File pcodeFile = testFolder.newFile();
        CompilerTestHelper.launchCompilation("/semantics/functions/multiple_functions_no_local_var.b314", pcodeFile, true, "functions: multiple_functions_no_local_var");
        LOG.debug("PCode is: {{}", FileUtils.readFileToString(pcodeFile));
        InterpreterResult result;
        // Turns: 1
        LOG.debug("Starting interpretation with 1 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 1);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(1));
        assertThat(result.getOutLines(), contains("0"));
        // Turns: 3
        LOG.debug("Starting interpretation with 3 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 3);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(3));
        assertThat(result.getOutLines(), contains("0", "0", "0"));
        // Turns 5
        LOG.debug("Starting interpretation with 5 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 5);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(5));
        assertThat(result.getOutLines(), contains("0", "0", "0", "0", "0"));
    }

    @Test
    public void testfunctions_multiple_global_vars_and_functions_no_local_var() throws Exception{
        File pcodeFile = testFolder.newFile();
        CompilerTestHelper.launchCompilation("/semantics/functions/multiple_global_vars_and_functions_no_local_var.b314", pcodeFile, true, "functions: multiple_global_vars_and_functions_no_local_var");
        LOG.debug("PCode is: {{}", FileUtils.readFileToString(pcodeFile));
        InterpreterResult result;
        // Turns: 1
        LOG.debug("Starting interpretation with 1 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 1);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(1));
        assertThat(result.getOutLines(), contains("0"));
        // Turns: 3
        LOG.debug("Starting interpretation with 3 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 3);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(3));
        assertThat(result.getOutLines(), contains("0", "0", "0"));
        // Turns 5
        LOG.debug("Starting interpretation with 5 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 5);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(5));
        assertThat(result.getOutLines(), contains("0", "0", "0", "0", "0"));
    }

    @Test
    public void testfunctions_one_boolean_function_no_local_var() throws Exception{
        File pcodeFile = testFolder.newFile();
        CompilerTestHelper.launchCompilation("/semantics/functions/one_boolean_function_no_local_var.b314", pcodeFile, true, "functions: one_boolean_function_no_local_var");
        LOG.debug("PCode is: {{}", FileUtils.readFileToString(pcodeFile));
        InterpreterResult result;
        // Turns: 1
        LOG.debug("Starting interpretation with 1 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 1);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(1));
        assertThat(result.getOutLines(), contains("0"));
        // Turns: 3
        LOG.debug("Starting interpretation with 3 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 3);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(3));
        assertThat(result.getOutLines(), contains("0", "0", "0"));
        // Turns 5
        LOG.debug("Starting interpretation with 5 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 5);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(5));
        assertThat(result.getOutLines(), contains("0", "0", "0", "0", "0"));
    }

    @Test
    public void testfunctions_one_int_function_no_local_var() throws Exception{
        File pcodeFile = testFolder.newFile();
        CompilerTestHelper.launchCompilation("/semantics/functions/one_int_function_no_local_var.b314", pcodeFile, true, "functions: one_int_function_no_local_var");
        LOG.debug("PCode is: {{}", FileUtils.readFileToString(pcodeFile));
        InterpreterResult result;
        // Turns: 1
        LOG.debug("Starting interpretation with 1 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 1);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(1));
        assertThat(result.getOutLines(), contains("0"));
        // Turns: 3
        LOG.debug("Starting interpretation with 3 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 3);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(3));
        assertThat(result.getOutLines(), contains("0", "0", "0"));
        // Turns 5
        LOG.debug("Starting interpretation with 5 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 5);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(5));
        assertThat(result.getOutLines(), contains("0", "0", "0", "0", "0"));
    }

    @Test
    public void testfunctions_one_square_function_no_local_var() throws Exception{
        File pcodeFile = testFolder.newFile();
        CompilerTestHelper.launchCompilation("/semantics/functions/one_square_function_no_local_var.b314", pcodeFile, true, "functions: one_square_function_no_local_var");
        LOG.debug("PCode is: {{}", FileUtils.readFileToString(pcodeFile));
        InterpreterResult result;
        // Turns: 1
        LOG.debug("Starting interpretation with 1 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 1);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(1));
        assertThat(result.getOutLines(), contains("0"));
        // Turns: 3
        LOG.debug("Starting interpretation with 3 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 3);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(3));
        assertThat(result.getOutLines(), contains("0", "0", "0"));
        // Turns 5
        LOG.debug("Starting interpretation with 5 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 5);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(5));
        assertThat(result.getOutLines(), contains("0", "0", "0", "0", "0"));
    }

    @Test
    public void testfunctions_one_void_function_no_local_var() throws Exception{
        File pcodeFile = testFolder.newFile();
        CompilerTestHelper.launchCompilation("/semantics/functions/one_void_function_no_local_var.b314", pcodeFile, true, "functions: one_void_function_no_local_var");
        LOG.debug("PCode is: {{}", FileUtils.readFileToString(pcodeFile));
        InterpreterResult result;
        // Turns: 1
        LOG.debug("Starting interpretation with 1 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 1);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(1));
        assertThat(result.getOutLines(), contains("0"));
        // Turns: 3
        LOG.debug("Starting interpretation with 3 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 3);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(3));
        assertThat(result.getOutLines(), contains("0", "0", "0"));
        // Turns 5
        LOG.debug("Starting interpretation with 5 turn");
        result = PCodeInterpreter.getInterpreter().execute(pcodeFile, 5);
        assertThat("Interpreter exist status was not 0", result.getExitStatus(), equalTo(0));
        assertThat("Wrong number of outputs, there was 1 turn", result.getOutLines(), hasSize(5));
        assertThat(result.getOutLines(), contains("0", "0", "0", "0", "0"));
    }

}