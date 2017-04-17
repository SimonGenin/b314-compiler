package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314boolexpressionsSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314boolexpressionsSyntaxTest.class);

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
    // Serie boolexpressions OK
    //
    @Test
    public void testboolexpressions_constant_values_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ok/constant_values.b314", testFolder.newFile(), true, "boolexpressions: constant_values");
    }

    @Test
    public void testboolexpressions_environment_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ok/environment_var.b314", testFolder.newFile(), true, "boolexpressions: environment_var");
    }

    @Test
    public void testboolexpressions_left_expression_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ok/left_expression.b314", testFolder.newFile(), true, "boolexpressions: left_expression");
    }

    @Test
    public void testboolexpressions_operations_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ok/operations.b314", testFolder.newFile(), true, "boolexpressions: operations");
    }

    @Test
    public void testboolexpressions_operations_only_env_vars_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ok/operations_only_env_vars.b314", testFolder.newFile(), true, "boolexpressions: operations_only_env_vars");
    }

    @Test
    public void testboolexpressions_operations_with_arrays_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ok/operations_with_arrays.b314", testFolder.newFile(), true, "boolexpressions: operations_with_arrays");
    }

    //
    // Serie boolexpressions KO
    //
    @Test
    public void testboolexpressions_and_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/and_env_var_wrong_type.b314", testFolder.newFile(), false, "boolexpressions: and_env_var_wrong_type");
    }

    @Test
    public void testboolexpressions_and_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/and_var_wrong_type.b314", testFolder.newFile(), false, "boolexpressions: and_var_wrong_type");
    }

    @Test
    public void testboolexpressions_and_var_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/and_var_wrong_type_2.b314", testFolder.newFile(), false, "boolexpressions: and_var_wrong_type_2");
    }

    @Test
    public void testboolexpressions_equals_mismatching_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/equals_mismatching_type.b314", testFolder.newFile(), false, "boolexpressions: equals_mismatching_type");
    }

    @Test
    public void testboolexpressions_equals_mismatching_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/equals_mismatching_type_2.b314", testFolder.newFile(), false, "boolexpressions: equals_mismatching_type_2");
    }

    @Test
    public void testboolexpressions_equals_mismatching_type_3_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/equals_mismatching_type_3.b314", testFolder.newFile(), false, "boolexpressions: equals_mismatching_type_3");
    }

    @Test
    public void testboolexpressions_greatherthan_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/greatherthan_env_var_wrong_type.b314", testFolder.newFile(), false, "boolexpressions: greatherthan_env_var_wrong_type");
    }

    @Test
    public void testboolexpressions_greatherthan_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/greatherthan_var_wrong_type.b314", testFolder.newFile(), false, "boolexpressions: greatherthan_var_wrong_type");
    }

    @Test
    public void testboolexpressions_greatherthan_var_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/greatherthan_var_wrong_type_2.b314", testFolder.newFile(), false, "boolexpressions: greatherthan_var_wrong_type_2");
    }

    @Test
    public void testboolexpressions_lessthan_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/lessthan_env_var_wrong_type.b314", testFolder.newFile(), false, "boolexpressions: lessthan_env_var_wrong_type");
    }

    @Test
    public void testboolexpressions_lessthan_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/lessthan_var_wrong_type.b314", testFolder.newFile(), false, "boolexpressions: lessthan_var_wrong_type");
    }

    @Test
    public void testboolexpressions_lessthan_var_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/lessthan_var_wrong_type_2.b314", testFolder.newFile(), false, "boolexpressions: lessthan_var_wrong_type_2");
    }

    @Test
    public void testboolexpressions_operations_with_arrays_missing_index_1_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/operations_with_arrays_missing_index_1.b314", testFolder.newFile(), false, "boolexpressions: operations_with_arrays_missing_index_1");
    }

    @Test
    public void testboolexpressions_operations_with_arrays_missing_index_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/operations_with_arrays_missing_index_2.b314", testFolder.newFile(), false, "boolexpressions: operations_with_arrays_missing_index_2");
    }

    @Test
    public void testboolexpressions_operations_with_arrays_wrong_type_in_expression_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/operations_with_arrays_wrong_type_in_expression.b314", testFolder.newFile(), false, "boolexpressions: operations_with_arrays_wrong_type_in_expression");
    }

    @Test
    public void testboolexpressions_operations_with_arrays_wrong_type_in_index_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/operations_with_arrays_wrong_type_in_index.b314", testFolder.newFile(), false, "boolexpressions: operations_with_arrays_wrong_type_in_index");
    }

    @Test
    public void testboolexpressions_or_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/or_env_var_wrong_type.b314", testFolder.newFile(), false, "boolexpressions: or_env_var_wrong_type");
    }

    @Test
    public void testboolexpressions_or_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/or_var_wrong_type.b314", testFolder.newFile(), false, "boolexpressions: or_var_wrong_type");
    }

    @Test
    public void testboolexpressions_or_var_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/or_var_wrong_type_2.b314", testFolder.newFile(), false, "boolexpressions: or_var_wrong_type_2");
    }

    @Test
    public void testboolexpressions_undeclared_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/undeclared_var.b314", testFolder.newFile(), false, "boolexpressions: undeclared_var");
    }

    @Test
    public void testboolexpressions_wrong_type_in_sub_expression_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/wrong_type_in_sub_expression.b314", testFolder.newFile(), false, "boolexpressions: wrong_type_in_sub_expression");
    }

    @Test
    public void testboolexpressions_wrong_type_in_sub_expression_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/boolexpressions/ko/wrong_type_in_sub_expression_2.b314", testFolder.newFile(), false, "boolexpressions: wrong_type_in_sub_expression_2");
    }

}