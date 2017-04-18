package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314functionsSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314functionsSyntaxTest.class);

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
    // Serie functions OK
    //
    @Test
    public void testfunctions_factorial_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/functions/ok/factorial.b314", testFolder.newFile(), true, "functions: factorial");
    }

    @Test
    public void testfunctions_multiple_functions_no_local_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/functions/ok/multiple_functions_no_local_var.b314", testFolder.newFile(), true, "functions: multiple_functions_no_local_var");
    }

    @Test
    public void testfunctions_multiple_global_vars_and_functions_no_local_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/functions/ok/multiple_global_vars_and_functions_no_local_var.b314", testFolder.newFile(), true, "functions: multiple_global_vars_and_functions_no_local_var");
    }

    @Test
    public void testfunctions_multiple_global_vars_and_functions_with_parameters_and_instructions_no_local_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/functions/ok/multiple_global_vars_and_functions_with_parameters_and_instructions_no_local_var.b314", testFolder.newFile(), true, "functions: multiple_global_vars_and_functions_with_parameters_and_instructions_no_local_var");
    }

    @Test
    public void testfunctions_multiple_global_vars_and_functions_with_parameters_and_instructions_using_global_vars_no_local_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/functions/ok/multiple_global_vars_and_functions_with_parameters_and_instructions_using_global_vars_no_local_var.b314", testFolder.newFile(), true, "functions: multiple_global_vars_and_functions_with_parameters_and_instructions_using_global_vars_no_local_var");
    }

    @Test
    public void testfunctions_multiple_global_vars_and_functions_with_parameters_call_from_when_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/functions/ok/multiple_global_vars_and_functions_with_parameters_call_from_when.b314", testFolder.newFile(), true, "functions: multiple_global_vars_and_functions_with_parameters_call_from_when");
    }

    @Test
    public void testfunctions_multiple_global_vars_and_functions_with_parameters_calling_other_functions_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/functions/ok/multiple_global_vars_and_functions_with_parameters_calling_other_functions.b314", testFolder.newFile(), true, "functions: multiple_global_vars_and_functions_with_parameters_calling_other_functions");
    }

    @Test
    public void testfunctions_multiple_global_vars_and_functions_with_parameters_no_local_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/functions/ok/multiple_global_vars_and_functions_with_parameters_no_local_var.b314", testFolder.newFile(), true, "functions: multiple_global_vars_and_functions_with_parameters_no_local_var");
    }

    @Test
    public void testfunctions_one_boolean_function_no_local_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/functions/ok/one_boolean_function_no_local_var.b314", testFolder.newFile(), true, "functions: one_boolean_function_no_local_var");
    }

    @Test
    public void testfunctions_one_int_function_no_local_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/functions/ok/one_int_function_no_local_var.b314", testFolder.newFile(), true, "functions: one_int_function_no_local_var");
    }

    @Test
    public void testfunctions_one_square_function_no_local_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/functions/ok/one_square_function_no_local_var.b314", testFolder.newFile(), true, "functions: one_square_function_no_local_var");
    }

    @Test
    public void testfunctions_one_void_function_no_local_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/functions/ok/one_void_function_no_local_var.b314", testFolder.newFile(), true, "functions: one_void_function_no_local_var");
    }

    //
    // Serie functions KO
    //
    @Test
    public void testfunctions_calling_undeclared_function_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/calling_undeclared_function.b314", testFolder.newFile(), false, "functions: calling_undeclared_function");
    }

    @Test
    public void testfunctions_multiple_functions_with_same_name_no_local_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/multiple_functions_with_same_name_no_local_var.b314", testFolder.newFile(), false, "functions: multiple_functions_with_same_name_no_local_var");
    }

    @Test
    public void testfunctions_multiple_global_vars_and_functions_with_parameters_and_instructions_using_global_vars_wrong_type_expression_no_local_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/multiple_global_vars_and_functions_with_parameters_and_instructions_using_global_vars_wrong_type_expression_no_local_var.b314", testFolder.newFile(), false, "functions: multiple_global_vars_and_functions_with_parameters_and_instructions_using_global_vars_wrong_type_expression_no_local_var");
    }

    @Test
    public void testfunctions_multiple_global_vars_and_functions_with_parameters_and_wrongly_typed_instructions_no_local_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/multiple_global_vars_and_functions_with_parameters_and_wrongly_typed_instructions_no_local_var.b314", testFolder.newFile(), false, "functions: multiple_global_vars_and_functions_with_parameters_and_wrongly_typed_instructions_no_local_var");
    }

    @Test
    public void testfunctions_multiple_global_vars_and_functions_with_parameters_array_in_parameters_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/multiple_global_vars_and_functions_with_parameters_array_in_parameters.b314", testFolder.newFile(), false, "functions: multiple_global_vars_and_functions_with_parameters_array_in_parameters");
    }

    @Test
    public void testfunctions_multiple_global_vars_and_functions_with_parameters_same_name_different_signatures_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/multiple_global_vars_and_functions_with_parameters_same_name_different_signatures.b314", testFolder.newFile(), false, "functions: multiple_global_vars_and_functions_with_parameters_same_name_different_signatures");
    }

    @Test
    public void testfunctions_multiple_global_vars_and_functions_with_parameters_with_parameter_list_ending_by_comma_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/multiple_global_vars_and_functions_with_parameters_with_parameter_list_ending_by_comma.b314", testFolder.newFile(), false, "functions: multiple_global_vars_and_functions_with_parameters_with_parameter_list_ending_by_comma");
    }

    @Test
    public void testfunctions_multiple_global_vars_and_functions_with_parameters_with_same_name_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/multiple_global_vars_and_functions_with_parameters_with_same_name.b314", testFolder.newFile(), false, "functions: multiple_global_vars_and_functions_with_parameters_with_same_name");
    }

    @Test
    public void testfunctions_multiple_global_vars_and_functions_with_parameters_with_same_name_as_function_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/multiple_global_vars_and_functions_with_parameters_with_same_name_as_function.b314", testFolder.newFile(), false, "functions: multiple_global_vars_and_functions_with_parameters_with_same_name_as_function");
    }

    @Test
    public void testfunctions_multiple_global_vars_and_functions_with_same_name_no_local_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/multiple_global_vars_and_functions_with_same_name_no_local_var.b314", testFolder.newFile(), false, "functions: multiple_global_vars_and_functions_with_same_name_no_local_var");
    }

    @Test
    public void testfunctions_one_array_function_no_local_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/one_array_function_no_local_var.b314", testFolder.newFile(), false, "functions: one_array_function_no_local_var");
    }

    @Test
    public void testfunctions_one_void_function_at_least_one_instruction_no_local_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/one_void_function_at_least_one_instruction_no_local_var.b314", testFolder.newFile(), false, "functions: one_void_function_at_least_one_instruction_no_local_var");
    }

    @Test
    public void testfunctions_one_void_function_no_body_no_local_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/one_void_function_no_body_no_local_var.b314", testFolder.newFile(), false, "functions: one_void_function_no_body_no_local_var");
    }

    @Test
    public void testfunctions_wrong_number_of_parameters_when_calling_function_1_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/wrong_number_of_parameters_when_calling_function_1.b314", testFolder.newFile(), false, "functions: wrong_number_of_parameters_when_calling_function_1");
    }

    @Test
    public void testfunctions_wrong_number_of_parameters_when_calling_function_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/wrong_number_of_parameters_when_calling_function_2.b314", testFolder.newFile(), false, "functions: wrong_number_of_parameters_when_calling_function_2");
    }

    @Test
    public void testfunctions_wrong_type_in_expression_with_function_call_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/wrong_type_in_expression_with_function_call.b314", testFolder.newFile(), false, "functions: wrong_type_in_expression_with_function_call");
    }

    @Test
    public void testfunctions_wrong_type_in_parameters_when_calling_function_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/wrong_type_in_parameters_when_calling_function.b314", testFolder.newFile(), false, "functions: wrong_type_in_parameters_when_calling_function");
    }

    @Test
    public void testfunctions_wrong_type_in_return_1_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/wrong_type_in_return_1.b314", testFolder.newFile(), false, "functions: wrong_type_in_return_1");
    }

    @Test
    public void testfunctions_wrong_type_in_return_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/wrong_type_in_return_2.b314", testFolder.newFile(), false, "functions: wrong_type_in_return_2");
    }

    @Test
    public void testfunctions_wrong_type_is_return_3_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/functions/ko/wrong_type_is_return_3.b314", testFolder.newFile(), false, "functions: wrong_type_is_return_3");
    }

}