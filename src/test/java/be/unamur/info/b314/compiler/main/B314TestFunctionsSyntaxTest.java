package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314TestFunctionsSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314TestFunctionsSyntaxTest.class);

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

    //
    // Serie TestFunctions OK
    //
    //
    // Serie TestFunctions KO
    //
    @Test
    public void testTestFunctions_multiple_global_vars_and_functions_with_parameters_and_instructions_using_global_vars_wrong_type_expression_no_local_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestFunctions/ko/multiple_global_vars_and_functions_with_parameters_and_instructions_using_global_vars_wrong_type_expression_no_local_var.b314", testFolder.newFile(), false, "TestFunctions: multiple_global_vars_and_functions_with_parameters_and_instructions_using_global_vars_wrong_type_expression_no_local_var");
    }

    @Test
    public void testTestFunctions_multiple_global_vars_and_functions_with_parameters_array_in_parameters_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestFunctions/ko/multiple_global_vars_and_functions_with_parameters_array_in_parameters.b314", testFolder.newFile(), false, "TestFunctions: multiple_global_vars_and_functions_with_parameters_array_in_parameters");
    }

    @Test
    public void testTestFunctions_wrong_number_of_parameters_when_calling_function_1_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestFunctions/ko/wrong_number_of_parameters_when_calling_function_1.b314", testFolder.newFile(), false, "TestFunctions: wrong_number_of_parameters_when_calling_function_1");
    }

    @Test
    public void testTestFunctions_wrong_number_of_parameters_when_calling_function_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestFunctions/ko/wrong_number_of_parameters_when_calling_function_2.b314", testFolder.newFile(), false, "TestFunctions: wrong_number_of_parameters_when_calling_function_2");
    }

    @Test
    public void testTestFunctions_wrong_type_in_parameters_when_calling_function_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestFunctions/ko/wrong_type_in_parameters_when_calling_function.b314", testFolder.newFile(), false, "TestFunctions: wrong_type_in_parameters_when_calling_function");
    }

    @Test
    public void testTestFunctions_wrong_type_in_return_1_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestFunctions/ko/wrong_type_in_return_1.b314", testFolder.newFile(), false, "TestFunctions: wrong_type_in_return_1");
    }

    @Test
    public void testTestFunctions_wrong_type_in_return_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestFunctions/ko/wrong_type_in_return_2.b314", testFolder.newFile(), false, "TestFunctions: wrong_type_in_return_2");
    }

    @Test
    public void testTestFunctions_wrong_type_is_return_3_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestFunctions/ko/wrong_type_is_return_3.b314", testFolder.newFile(), false, "TestFunctions: wrong_type_is_return_3");
    }

    @Test
    public void testTestFunctions_multiple_global_vars_and_functions_with_parameters_calling_other_functions_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestFunctions/ko/ok/multiple_global_vars_and_functions_with_parameters_calling_other_functions.b314", testFolder.newFile(), true, "functions: multiple_global_vars_and_functions_with_parameters_calling_other_functions");
    }
}