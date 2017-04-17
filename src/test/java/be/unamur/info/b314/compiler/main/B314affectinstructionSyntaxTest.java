package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314affectinstructionSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314affectinstructionSyntaxTest.class);

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
    // Serie affectinstruction OK
    //
    @Test
    public void testaffectinstruction_affect_bool_array_constant_values_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ok/affect_bool_array_constant_values.b314", testFolder.newFile(), true, "affectinstruction: affect_bool_array_constant_values");
    }

    @Test
    public void testaffectinstruction_affect_bool_array_right_expressions_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ok/affect_bool_array_right_expressions.b314", testFolder.newFile(), true, "affectinstruction: affect_bool_array_right_expressions");
    }

    @Test
    public void testaffectinstruction_affect_bool_constant_values_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ok/affect_bool_constant_values.b314", testFolder.newFile(), true, "affectinstruction: affect_bool_constant_values");
    }

    @Test
    public void testaffectinstruction_affect_bool_right_expressions_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ok/affect_bool_right_expressions.b314", testFolder.newFile(), true, "affectinstruction: affect_bool_right_expressions");
    }

    @Test
    public void testaffectinstruction_affect_int_array_constant_values_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ok/affect_int_array_constant_values.b314", testFolder.newFile(), true, "affectinstruction: affect_int_array_constant_values");
    }

    @Test
    public void testaffectinstruction_affect_int_array_right_expressions_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ok/affect_int_array_right_expressions.b314", testFolder.newFile(), true, "affectinstruction: affect_int_array_right_expressions");
    }

    @Test
    public void testaffectinstruction_affect_int_constant_values_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ok/affect_int_constant_values.b314", testFolder.newFile(), true, "affectinstruction: affect_int_constant_values");
    }

    @Test
    public void testaffectinstruction_affect_int_right_expressions_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ok/affect_int_right_expressions.b314", testFolder.newFile(), true, "affectinstruction: affect_int_right_expressions");
    }

    @Test
    public void testaffectinstruction_affect_square_array_constant_values_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ok/affect_square_array_constant_values.b314", testFolder.newFile(), true, "affectinstruction: affect_square_array_constant_values");
    }

    @Test
    public void testaffectinstruction_affect_square_array_right_expressions_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ok/affect_square_array_right_expressions.b314", testFolder.newFile(), true, "affectinstruction: affect_square_array_right_expressions");
    }

    @Test
    public void testaffectinstruction_affect_square_constant_values_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ok/affect_square_constant_values.b314", testFolder.newFile(), true, "affectinstruction: affect_square_constant_values");
    }

    @Test
    public void testaffectinstruction_affect_square_right_expressions_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ok/affect_square_right_expressions.b314", testFolder.newFile(), true, "affectinstruction: affect_square_right_expressions");
    }

    //
    // Serie affectinstruction KO
    //
    @Test
    public void testaffectinstruction_affect_bool_array_right_expressions_no_index_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_bool_array_right_expressions_no_index.b314", testFolder.newFile(), false, "affectinstruction: affect_bool_array_right_expressions_no_index");
    }

    @Test
    public void testaffectinstruction_affect_bool_array_to_bool_array_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_bool_array_to_bool_array.b314", testFolder.newFile(), false, "affectinstruction: affect_bool_array_to_bool_array");
    }

    @Test
    public void testaffectinstruction_affect_bool_constant_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_bool_constant.b314", testFolder.newFile(), false, "affectinstruction: affect_bool_constant");
    }

    @Test
    public void testaffectinstruction_affect_bool_constant_wrong_var_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_bool_constant_wrong_var_type.b314", testFolder.newFile(), false, "affectinstruction: affect_bool_constant_wrong_var_type");
    }

    @Test
    public void testaffectinstruction_affect_bool_constant_wrong_var_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_bool_constant_wrong_var_type_2.b314", testFolder.newFile(), false, "affectinstruction: affect_bool_constant_wrong_var_type_2");
    }

    @Test
    public void testaffectinstruction_affect_bool_env_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_bool_env_var.b314", testFolder.newFile(), false, "affectinstruction: affect_bool_env_var");
    }

    @Test
    public void testaffectinstruction_affect_bool_right_expressions_wrong_var_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_bool_right_expressions_wrong_var_type.b314", testFolder.newFile(), false, "affectinstruction: affect_bool_right_expressions_wrong_var_type");
    }

    @Test
    public void testaffectinstruction_affect_bool_right_expressions_wrong_var_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_bool_right_expressions_wrong_var_type_2.b314", testFolder.newFile(), false, "affectinstruction: affect_bool_right_expressions_wrong_var_type_2");
    }

    @Test
    public void testaffectinstruction_affect_int_array_right_expressions_no_index_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_int_array_right_expressions_no_index.b314", testFolder.newFile(), false, "affectinstruction: affect_int_array_right_expressions_no_index");
    }

    @Test
    public void testaffectinstruction_affect_int_array_to_int_array_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_int_array_to_int_array.b314", testFolder.newFile(), false, "affectinstruction: affect_int_array_to_int_array");
    }

    @Test
    public void testaffectinstruction_affect_int_constant_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_int_constant.b314", testFolder.newFile(), false, "affectinstruction: affect_int_constant");
    }

    @Test
    public void testaffectinstruction_affect_int_constant_wrong_var_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_int_constant_wrong_var_type.b314", testFolder.newFile(), false, "affectinstruction: affect_int_constant_wrong_var_type");
    }

    @Test
    public void testaffectinstruction_affect_int_constant_wrong_var_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_int_constant_wrong_var_type_2.b314", testFolder.newFile(), false, "affectinstruction: affect_int_constant_wrong_var_type_2");
    }

    @Test
    public void testaffectinstruction_affect_int_env_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_int_env_var.b314", testFolder.newFile(), false, "affectinstruction: affect_int_env_var");
    }

    @Test
    public void testaffectinstruction_affect_int_right_expressions_wrong_var_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_int_right_expressions_wrong_var_type.b314", testFolder.newFile(), false, "affectinstruction: affect_int_right_expressions_wrong_var_type");
    }

    @Test
    public void testaffectinstruction_affect_int_right_expressions_wrong_var_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_int_right_expressions_wrong_var_type_2.b314", testFolder.newFile(), false, "affectinstruction: affect_int_right_expressions_wrong_var_type_2");
    }

    @Test
    public void testaffectinstruction_affect_square_array_right_expressions_no_index_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_square_array_right_expressions_no_index.b314", testFolder.newFile(), false, "affectinstruction: affect_square_array_right_expressions_no_index");
    }

    @Test
    public void testaffectinstruction_affect_square_array_to_square_array_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_square_array_to_square_array.b314", testFolder.newFile(), false, "affectinstruction: affect_square_array_to_square_array");
    }

    @Test
    public void testaffectinstruction_affect_square_constant_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_square_constant.b314", testFolder.newFile(), false, "affectinstruction: affect_square_constant");
    }

    @Test
    public void testaffectinstruction_affect_square_constant_wrong_var_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_square_constant_wrong_var_type.b314", testFolder.newFile(), false, "affectinstruction: affect_square_constant_wrong_var_type");
    }

    @Test
    public void testaffectinstruction_affect_square_constant_wrong_var_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_square_constant_wrong_var_type_2.b314", testFolder.newFile(), false, "affectinstruction: affect_square_constant_wrong_var_type_2");
    }

    @Test
    public void testaffectinstruction_affect_square_env_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_square_env_var.b314", testFolder.newFile(), false, "affectinstruction: affect_square_env_var");
    }

    @Test
    public void testaffectinstruction_affect_square_right_expressions_wrong_var_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_square_right_expressions_wrong_var_type.b314", testFolder.newFile(), false, "affectinstruction: affect_square_right_expressions_wrong_var_type");
    }

    @Test
    public void testaffectinstruction_affect_square_right_expressions_wrong_var_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/affectinstruction/ko/affect_square_right_expressions_wrong_var_type_2.b314", testFolder.newFile(), false, "affectinstruction: affect_square_right_expressions_wrong_var_type_2");
    }

}