package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314TestBoolExpressionsSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314TestBoolExpressionsSyntaxTest.class);

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
    // Serie TestBoolExpressions OK
    //
    @Test
    public void testTestBoolExpressions_constant_values_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ok/constant_values.b314", testFolder.newFile(), true, "TestBoolExpressions: constant_values");
    }

    @Test
    public void testTestBoolExpressions_environment_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ok/environment_var.b314", testFolder.newFile(), true, "TestBoolExpressions: environment_var");
    }

    @Test
    public void testTestBoolExpressions_left_expression_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ok/left_expression.b314", testFolder.newFile(), true, "TestBoolExpressions: left_expression");
    }

    @Test
    public void testTestBoolExpressions_operations_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ok/operations.b314", testFolder.newFile(), true, "TestBoolExpressions: operations");
    }

    @Test
    public void testTestBoolExpressions_operations_only_env_vars_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ok/operations_only_env_vars.b314", testFolder.newFile(), true, "TestBoolExpressions: operations_only_env_vars");
    }

    @Test
    public void testTestBoolExpressions_operations_with_arrays_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ok/operations_with_arrays.b314", testFolder.newFile(), true, "TestBoolExpressions: operations_with_arrays");
    }

    //
    // Serie TestBoolExpressions KO
    //
    @Test
    public void testTestBoolExpressions_and_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/and_env_var_wrong_type.b314", testFolder.newFile(), false, "TestBoolExpressions: and_env_var_wrong_type");
    }

    @Test
    public void testTestBoolExpressions_and_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/and_var_wrong_type.b314", testFolder.newFile(), false, "TestBoolExpressions: and_var_wrong_type");
    }

    @Test
    public void testTestBoolExpressions_and_var_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/and_var_wrong_type_2.b314", testFolder.newFile(), false, "TestBoolExpressions: and_var_wrong_type_2");
    }

    @Test
    public void testTestBoolExpressions_equals_mismatching_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/equals_mismatching_type.b314", testFolder.newFile(), false, "TestBoolExpressions: equals_mismatching_type");
    }

    @Test
    public void testTestBoolExpressions_equals_mismatching_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/equals_mismatching_type_2.b314", testFolder.newFile(), false, "TestBoolExpressions: equals_mismatching_type_2");
    }

    @Test
    public void testTestBoolExpressions_equals_mismatching_type_3_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/equals_mismatching_type_3.b314", testFolder.newFile(), false, "TestBoolExpressions: equals_mismatching_type_3");
    }

    @Test
    public void testTestBoolExpressions_greatherthan_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/greatherthan_env_var_wrong_type.b314", testFolder.newFile(), false, "TestBoolExpressions: greatherthan_env_var_wrong_type");
    }

    @Test
    public void testTestBoolExpressions_greatherthan_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/greatherthan_var_wrong_type.b314", testFolder.newFile(), false, "TestBoolExpressions: greatherthan_var_wrong_type");
    }

    @Test
    public void testTestBoolExpressions_greatherthan_var_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/greatherthan_var_wrong_type_2.b314", testFolder.newFile(), false, "TestBoolExpressions: greatherthan_var_wrong_type_2");
    }

    @Test
    public void testTestBoolExpressions_lessthan_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/lessthan_env_var_wrong_type.b314", testFolder.newFile(), false, "TestBoolExpressions: lessthan_env_var_wrong_type");
    }

    @Test
    public void testTestBoolExpressions_lessthan_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/lessthan_var_wrong_type.b314", testFolder.newFile(), false, "TestBoolExpressions: lessthan_var_wrong_type");
    }

    @Test
    public void testTestBoolExpressions_lessthan_var_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/lessthan_var_wrong_type_2.b314", testFolder.newFile(), false, "TestBoolExpressions: lessthan_var_wrong_type_2");
    }

    @Test
    public void testTestBoolExpressions_operations_with_arrays_missing_index_1_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/operations_with_arrays_missing_index_1.b314", testFolder.newFile(), false, "TestBoolExpressions: operations_with_arrays_missing_index_1");
    }

    @Test
    public void testTestBoolExpressions_operations_with_arrays_missing_index_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/operations_with_arrays_missing_index_2.b314", testFolder.newFile(), false, "TestBoolExpressions: operations_with_arrays_missing_index_2");
    }

    @Test
    public void testTestBoolExpressions_operations_with_arrays_wrong_type_in_expression_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/operations_with_arrays_wrong_type_in_expression.b314", testFolder.newFile(), false, "TestBoolExpressions: operations_with_arrays_wrong_type_in_expression");
    }

    @Test
    public void testTestBoolExpressions_operations_with_arrays_wrong_type_in_index_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/operations_with_arrays_wrong_type_in_index.b314", testFolder.newFile(), false, "TestBoolExpressions: operations_with_arrays_wrong_type_in_index");
    }

    @Test
    public void testTestBoolExpressions_or_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/or_env_var_wrong_type.b314", testFolder.newFile(), false, "TestBoolExpressions: or_env_var_wrong_type");
    }

    @Test
    public void testTestBoolExpressions_or_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/or_var_wrong_type.b314", testFolder.newFile(), false, "TestBoolExpressions: or_var_wrong_type");
    }

    @Test
    public void testTestBoolExpressions_or_var_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/or_var_wrong_type_2.b314", testFolder.newFile(), false, "TestBoolExpressions: or_var_wrong_type_2");
    }

    @Test
    public void testTestBoolExpressions_undeclared_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/undeclared_var.b314", testFolder.newFile(), false, "TestBoolExpressions: undeclared_var");
    }

    @Test
    public void testTestBoolExpressions_wrong_type_in_sub_expression_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/wrong_type_in_sub_expression.b314", testFolder.newFile(), false, "TestBoolExpressions: wrong_type_in_sub_expression");
    }

    @Test
    public void testTestBoolExpressions_wrong_type_in_sub_expression_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestBoolExpressions/ko/wrong_type_in_sub_expression_2.b314", testFolder.newFile(), false, "TestBoolExpressions: wrong_type_in_sub_expression_2");
    }

}