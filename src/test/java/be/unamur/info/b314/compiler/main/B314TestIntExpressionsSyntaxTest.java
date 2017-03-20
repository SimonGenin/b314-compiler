package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314TestIntExpressionsSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314TestIntExpressionsSyntaxTest.class);

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
    // Serie TestIntExpressions OK
    //
    @Test
    public void testTestIntExpressions_environment_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ok/environment_var.b314", testFolder.newFile(), true, "TestIntExpressions: environment_var");
    }

    @Test
    public void testTestIntExpressions_left_expression_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ok/left_expression.b314", testFolder.newFile(), true, "TestIntExpressions: left_expression");
    }

    @Test
    public void testTestIntExpressions_operations_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ok/operations.b314", testFolder.newFile(), true, "TestIntExpressions: operations");
    }

    @Test
    public void testTestIntExpressions_operations_with_arrays_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ok/operations_with_arrays.b314", testFolder.newFile(), true, "TestIntExpressions: operations_with_arrays");
    }

    @Test
    public void testTestIntExpressions_operations_with_minus_negative_integer_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ok/operations_with_minus_negative_integer.b314", testFolder.newFile(), true, "TestIntExpressions: operations_with_minus_negative_integer");
    }

    @Test
    public void testTestIntExpressions_postitive_and_negative_values_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ok/postitive_and_negative_values.b314", testFolder.newFile(), true, "TestIntExpressions: postitive_and_negative_values");
    }

    //
    // Serie TestIntExpressions KO
    //
    @Test
    public void testTestIntExpressions_add_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/add_env_var_wrong_type.b314", testFolder.newFile(), false, "TestIntExpressions: add_env_var_wrong_type");
    }

    @Test
    public void testTestIntExpressions_add_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/add_var_wrong_type.b314", testFolder.newFile(), false, "TestIntExpressions: add_var_wrong_type");
    }

    @Test
    public void testTestIntExpressions_add_var_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/add_var_wrong_type_2.b314", testFolder.newFile(), false, "TestIntExpressions: add_var_wrong_type_2");
    }

    @Test
    public void testTestIntExpressions_div_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/div_env_var_wrong_type.b314", testFolder.newFile(), false, "TestIntExpressions: div_env_var_wrong_type");
    }

    @Test
    public void testTestIntExpressions_div_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/div_var_wrong_type.b314", testFolder.newFile(), false, "TestIntExpressions: div_var_wrong_type");
    }

    @Test
    public void testTestIntExpressions_div_var_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/div_var_wrong_type_2.b314", testFolder.newFile(), false, "TestIntExpressions: div_var_wrong_type_2");
    }

    @Test
    public void testTestIntExpressions_minus_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/minus_env_var_wrong_type.b314", testFolder.newFile(), false, "TestIntExpressions: minus_env_var_wrong_type");
    }

    @Test
    public void testTestIntExpressions_minus_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/minus_var_wrong_type.b314", testFolder.newFile(), false, "TestIntExpressions: minus_var_wrong_type");
    }

    @Test
    public void testTestIntExpressions_minus_var_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/minus_var_wrong_type_2.b314", testFolder.newFile(), false, "TestIntExpressions: minus_var_wrong_type_2");
    }

    @Test
    public void testTestIntExpressions_mod_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/mod_env_var_wrong_type.b314", testFolder.newFile(), false, "TestIntExpressions: mod_env_var_wrong_type");
    }

    @Test
    public void testTestIntExpressions_mod_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/mod_var_wrong_type.b314", testFolder.newFile(), false, "TestIntExpressions: mod_var_wrong_type");
    }

    @Test
    public void testTestIntExpressions_mod_var_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/mod_var_wrong_type_2.b314", testFolder.newFile(), false, "TestIntExpressions: mod_var_wrong_type_2");
    }

    @Test
    public void testTestIntExpressions_mul_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/mul_env_var_wrong_type.b314", testFolder.newFile(), false, "TestIntExpressions: mul_env_var_wrong_type");
    }

    @Test
    public void testTestIntExpressions_mul_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/mul_var_wrong_type.b314", testFolder.newFile(), false, "TestIntExpressions: mul_var_wrong_type");
    }

    @Test
    public void testTestIntExpressions_mul_var_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/mul_var_wrong_type_2.b314", testFolder.newFile(), false, "TestIntExpressions: mul_var_wrong_type_2");
    }

    @Test
    public void testTestIntExpressions_operations_with_arrays_missing_index_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/operations_with_arrays_missing_index.b314", testFolder.newFile(), false, "TestIntExpressions: operations_with_arrays_missing_index");
    }

    @Test
    public void testTestIntExpressions_operations_with_arrays_too_many_indexes_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/operations_with_arrays_too_many_indexes.b314", testFolder.newFile(), false, "TestIntExpressions: operations_with_arrays_too_many_indexes");
    }

    @Test
    public void testTestIntExpressions_operations_with_arrays_wrong_array_type_in_expression_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/operations_with_arrays_wrong_array_type_in_expression.b314", testFolder.newFile(), false, "TestIntExpressions: operations_with_arrays_wrong_array_type_in_expression");
    }

    @Test
    public void testTestIntExpressions_operations_with_arrays_wrong_type_in_index_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/operations_with_arrays_wrong_type_in_index.b314", testFolder.newFile(), false, "TestIntExpressions: operations_with_arrays_wrong_type_in_index");
    }

    @Test
    public void testTestIntExpressions_undeclared_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/undeclared_var.b314", testFolder.newFile(), false, "TestIntExpressions: undeclared_var");
    }

    @Test
    public void testTestIntExpressions_wrong_type_in_sub_expression_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/wrong_type_in_sub_expression.b314", testFolder.newFile(), false, "TestIntExpressions: wrong_type_in_sub_expression");
    }

    @Test
    public void testTestIntExpressions_wrong_type_in_sub_expression_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestIntExpressions/ko/wrong_type_in_sub_expression_2.b314", testFolder.newFile(), false, "TestIntExpressions: wrong_type_in_sub_expression_2");
    }

}