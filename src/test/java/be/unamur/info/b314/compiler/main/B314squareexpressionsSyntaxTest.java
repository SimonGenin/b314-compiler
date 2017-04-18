package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314squareexpressionsSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314squareexpressionsSyntaxTest.class);

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
    // Serie squareexpressions OK
    //
    @Test
    public void testsquareexpressions_constant_values_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/squareexpressions/ok/constant_values.b314", testFolder.newFile(), true, "squareexpressions: constant_values");
    }

    @Test
    public void testsquareexpressions_environment_var_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/squareexpressions/ok/environment_var.b314", testFolder.newFile(), true, "squareexpressions: environment_var");
    }

    @Test
    public void testsquareexpressions_environment_var_with_rightexpr_indexes_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/squareexpressions/ok/environment_var_with_rightexpr_indexes.b314", testFolder.newFile(), true, "squareexpressions: environment_var_with_rightexpr_indexes");
    }

    @Test
    public void testsquareexpressions_left_expression_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/squareexpressions/ok/left_expression.b314", testFolder.newFile(), true, "squareexpressions: left_expression");
    }

    @Test
    public void testsquareexpressions_operations_with_arrays_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/squareexpressions/ok/operations_with_arrays.b314", testFolder.newFile(), true, "squareexpressions: operations_with_arrays");
    }

    //
    // Serie squareexpressions KO
    //
    @Test
    public void testsquareexpressions_environment_var_with_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/squareexpressions/ko/environment_var_with_wrong_type.b314", testFolder.newFile(), false, "squareexpressions: environment_var_with_wrong_type");
    }

    @Test
    public void testsquareexpressions_environment_var_with_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/squareexpressions/ko/environment_var_with_wrong_type_2.b314", testFolder.newFile(), false, "squareexpressions: environment_var_with_wrong_type_2");
    }

    @Test
    public void testsquareexpressions_environment_var_with_wrong_type_3_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/squareexpressions/ko/environment_var_with_wrong_type_3.b314", testFolder.newFile(), false, "squareexpressions: environment_var_with_wrong_type_3");
    }

    @Test
    public void testsquareexpressions_environment_var_with_wrong_type_4_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/squareexpressions/ko/environment_var_with_wrong_type_4.b314", testFolder.newFile(), false, "squareexpressions: environment_var_with_wrong_type_4");
    }

    @Test
    public void testsquareexpressions_environment_var_with_wrong_type_5_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/squareexpressions/ko/environment_var_with_wrong_type_5.b314", testFolder.newFile(), false, "squareexpressions: environment_var_with_wrong_type_5");
    }

    @Test
    public void testsquareexpressions_environment_var_with_wrong_type_6_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/squareexpressions/ko/environment_var_with_wrong_type_6.b314", testFolder.newFile(), false, "squareexpressions: environment_var_with_wrong_type_6");
    }

    @Test
    public void testsquareexpressions_environment_var_with_wrong_type_7_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/squareexpressions/ko/environment_var_with_wrong_type_7.b314", testFolder.newFile(), false, "squareexpressions: environment_var_with_wrong_type_7");
    }

    @Test
    public void testsquareexpressions_environment_var_with_wrong_type_8_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/squareexpressions/ko/environment_var_with_wrong_type_8.b314", testFolder.newFile(), false, "squareexpressions: environment_var_with_wrong_type_8");
    }

    @Test
    public void testsquareexpressions_operations_with_arrays_too_many_indexes_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/squareexpressions/ko/operations_with_arrays_too_many_indexes.b314", testFolder.newFile(), false, "squareexpressions: operations_with_arrays_too_many_indexes");
    }

    @Test
    public void testsquareexpressions_operations_with_arrays_wrong_type_in_index_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/squareexpressions/ko/operations_with_arrays_wrong_type_in_index.b314", testFolder.newFile(), false, "squareexpressions: operations_with_arrays_wrong_type_in_index");
    }

}