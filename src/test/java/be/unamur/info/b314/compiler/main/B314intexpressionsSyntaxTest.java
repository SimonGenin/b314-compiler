package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314intexpressionsSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314intexpressionsSyntaxTest.class);

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
    // Serie intexpressions OK
    //
    @Test
    public void testintexpressions_environment_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ok/environment_var.b314", testFolder.newFile(), true, "intexpressions: environment_var");
    }

    @Test
    public void testintexpressions_left_expression_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ok/left_expression.b314", testFolder.newFile(), true, "intexpressions: left_expression");
    }

    @Test
    public void testintexpressions_operations_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ok/operations.b314", testFolder.newFile(), true, "intexpressions: operations");
    }

    @Test
    public void testintexpressions_operations_with_arrays_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ok/operations_with_arrays.b314", testFolder.newFile(), true, "intexpressions: operations_with_arrays");
    }

    @Test
    public void testintexpressions_operations_with_minus_negative_integer_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ok/operations_with_minus_negative_integer.b314", testFolder.newFile(), true, "intexpressions: operations_with_minus_negative_integer");
    }

    @Test
    public void testintexpressions_postitive_and_negative_values_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ok/postitive_and_negative_values.b314", testFolder.newFile(), true, "intexpressions: postitive_and_negative_values");
    }

    //
    // Serie intexpressions KO
    //
    @Test
    public void testintexpressions_add_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/add_env_var_wrong_type.b314", testFolder.newFile(), false, "intexpressions: add_env_var_wrong_type");
    }

    @Test
    public void testintexpressions_add_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/add_var_wrong_type.b314", testFolder.newFile(), false, "intexpressions: add_var_wrong_type");
    }

    @Test
    public void testintexpressions_add_var_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/add_var_wrong_type_2.b314", testFolder.newFile(), false, "intexpressions: add_var_wrong_type_2");
    }

    @Test
    public void testintexpressions_div_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/div_env_var_wrong_type.b314", testFolder.newFile(), false, "intexpressions: div_env_var_wrong_type");
    }

    @Test
    public void testintexpressions_div_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/div_var_wrong_type.b314", testFolder.newFile(), false, "intexpressions: div_var_wrong_type");
    }

    @Test
    public void testintexpressions_div_var_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/div_var_wrong_type_2.b314", testFolder.newFile(), false, "intexpressions: div_var_wrong_type_2");
    }

    @Test
    public void testintexpressions_minus_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/minus_env_var_wrong_type.b314", testFolder.newFile(), false, "intexpressions: minus_env_var_wrong_type");
    }

    @Test
    public void testintexpressions_minus_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/minus_var_wrong_type.b314", testFolder.newFile(), false, "intexpressions: minus_var_wrong_type");
    }

    @Test
    public void testintexpressions_minus_var_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/minus_var_wrong_type_2.b314", testFolder.newFile(), false, "intexpressions: minus_var_wrong_type_2");
    }

    @Test
    public void testintexpressions_mod_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/mod_env_var_wrong_type.b314", testFolder.newFile(), false, "intexpressions: mod_env_var_wrong_type");
    }

    @Test
    public void testintexpressions_mod_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/mod_var_wrong_type.b314", testFolder.newFile(), false, "intexpressions: mod_var_wrong_type");
    }

    @Test
    public void testintexpressions_mod_var_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/mod_var_wrong_type_2.b314", testFolder.newFile(), false, "intexpressions: mod_var_wrong_type_2");
    }

    @Test
    public void testintexpressions_mul_env_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/mul_env_var_wrong_type.b314", testFolder.newFile(), false, "intexpressions: mul_env_var_wrong_type");
    }

    @Test
    public void testintexpressions_mul_var_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/mul_var_wrong_type.b314", testFolder.newFile(), false, "intexpressions: mul_var_wrong_type");
    }

    @Test
    public void testintexpressions_mul_var_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/mul_var_wrong_type_2.b314", testFolder.newFile(), false, "intexpressions: mul_var_wrong_type_2");
    }

    @Test
    public void testintexpressions_operations_with_arrays_missing_index_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/operations_with_arrays_missing_index.b314", testFolder.newFile(), false, "intexpressions: operations_with_arrays_missing_index");
    }

    @Test
    public void testintexpressions_operations_with_arrays_too_many_indexes_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/operations_with_arrays_too_many_indexes.b314", testFolder.newFile(), false, "intexpressions: operations_with_arrays_too_many_indexes");
    }

    @Test
    public void testintexpressions_operations_with_arrays_wrong_array_type_in_expression_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/operations_with_arrays_wrong_array_type_in_expression.b314", testFolder.newFile(), false, "intexpressions: operations_with_arrays_wrong_array_type_in_expression");
    }

    @Test
    public void testintexpressions_operations_with_arrays_wrong_type_in_index_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/operations_with_arrays_wrong_type_in_index.b314", testFolder.newFile(), false, "intexpressions: operations_with_arrays_wrong_type_in_index");
    }

    @Test
    public void testintexpressions_undeclared_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/undeclared_var.b314", testFolder.newFile(), false, "intexpressions: undeclared_var");
    }

    @Test
    public void testintexpressions_wrong_type_in_sub_expression_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/wrong_type_in_sub_expression.b314", testFolder.newFile(), false, "intexpressions: wrong_type_in_sub_expression");
    }

    @Test
    public void testintexpressions_wrong_type_in_sub_expression_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/intexpressions/ko/wrong_type_in_sub_expression_2.b314", testFolder.newFile(), false, "intexpressions: wrong_type_in_sub_expression_2");
    }

}