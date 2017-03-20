package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314TestAffectInstructionSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314TestAffectInstructionSyntaxTest.class);

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
    // Serie TestAffectInstruction OK
    //
    @Test
    public void testTestAffectInstruction_affect_bool_array_constant_values_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ok/affect_bool_array_constant_values.b314", testFolder.newFile(), true, "TestAffectInstruction: affect_bool_array_constant_values");
    }

    @Test
    public void testTestAffectInstruction_affect_bool_array_right_expressions_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ok/affect_bool_array_right_expressions.b314", testFolder.newFile(), true, "TestAffectInstruction: affect_bool_array_right_expressions");
    }

    @Test
    public void testTestAffectInstruction_affect_bool_constant_values_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ok/affect_bool_constant_values.b314", testFolder.newFile(), true, "TestAffectInstruction: affect_bool_constant_values");
    }

    @Test
    public void testTestAffectInstruction_affect_bool_right_expressions_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ok/affect_bool_right_expressions.b314", testFolder.newFile(), true, "TestAffectInstruction: affect_bool_right_expressions");
    }

    @Test
    public void testTestAffectInstruction_affect_int_array_constant_values_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ok/affect_int_array_constant_values.b314", testFolder.newFile(), true, "TestAffectInstruction: affect_int_array_constant_values");
    }

    @Test
    public void testTestAffectInstruction_affect_int_array_right_expressions_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ok/affect_int_array_right_expressions.b314", testFolder.newFile(), true, "TestAffectInstruction: affect_int_array_right_expressions");
    }

    @Test
    public void testTestAffectInstruction_affect_int_constant_values_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ok/affect_int_constant_values.b314", testFolder.newFile(), true, "TestAffectInstruction: affect_int_constant_values");
    }

    @Test
    public void testTestAffectInstruction_affect_int_right_expressions_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ok/affect_int_right_expressions.b314", testFolder.newFile(), true, "TestAffectInstruction: affect_int_right_expressions");
    }

    @Test
    public void testTestAffectInstruction_affect_square_array_constant_values_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ok/affect_square_array_constant_values.b314", testFolder.newFile(), true, "TestAffectInstruction: affect_square_array_constant_values");
    }

    @Test
    public void testTestAffectInstruction_affect_square_array_right_expressions_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ok/affect_square_array_right_expressions.b314", testFolder.newFile(), true, "TestAffectInstruction: affect_square_array_right_expressions");
    }

    @Test
    public void testTestAffectInstruction_affect_square_constant_values_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ok/affect_square_constant_values.b314", testFolder.newFile(), true, "TestAffectInstruction: affect_square_constant_values");
    }

    @Test
    public void testTestAffectInstruction_affect_square_right_expressions_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ok/affect_square_right_expressions.b314", testFolder.newFile(), true, "TestAffectInstruction: affect_square_right_expressions");
    }

    //
    // Serie TestAffectInstruction KO
    //
    @Test
    public void testTestAffectInstruction_affect_bool_array_right_expressions_no_index_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_bool_array_right_expressions_no_index.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_bool_array_right_expressions_no_index");
    }

    @Test
    public void testTestAffectInstruction_affect_bool_array_to_bool_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_bool_array_to_bool.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_bool_array_to_bool");
    }

    @Test
    public void testTestAffectInstruction_affect_bool_constant_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_bool_constant.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_bool_constant");
    }

    @Test
    public void testTestAffectInstruction_affect_bool_constant_wrong_var_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_bool_constant_wrong_var_type.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_bool_constant_wrong_var_type");
    }

    @Test
    public void testTestAffectInstruction_affect_bool_constant_wrong_var_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_bool_constant_wrong_var_type_2.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_bool_constant_wrong_var_type_2");
    }

    @Test
    public void testTestAffectInstruction_affect_bool_env_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_bool_env_var.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_bool_env_var");
    }

    @Test
    public void testTestAffectInstruction_affect_bool_right_expressions_wrong_var_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_bool_right_expressions_wrong_var_type.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_bool_right_expressions_wrong_var_type");
    }

    @Test
    public void testTestAffectInstruction_affect_bool_right_expressions_wrong_var_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_bool_right_expressions_wrong_var_type_2.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_bool_right_expressions_wrong_var_type_2");
    }

    @Test
    public void testTestAffectInstruction_affect_int_array_right_expressions_no_index_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_int_array_right_expressions_no_index.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_int_array_right_expressions_no_index");
    }

    @Test
    public void testTestAffectInstruction_affect_int_array_to_int_array_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_int_array_to_int_array.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_int_array_to_int_array");
    }

    @Test
    public void testTestAffectInstruction_affect_int_constant_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_int_constant.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_int_constant");
    }

    @Test
    public void testTestAffectInstruction_affect_int_constant_wrong_var_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_int_constant_wrong_var_type.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_int_constant_wrong_var_type");
    }

    @Test
    public void testTestAffectInstruction_affect_int_constant_wrong_var_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_int_constant_wrong_var_type_2.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_int_constant_wrong_var_type_2");
    }

    @Test
    public void testTestAffectInstruction_affect_int_env_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_int_env_var.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_int_env_var");
    }

    @Test
    public void testTestAffectInstruction_affect_int_right_expressions_wrong_var_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_int_right_expressions_wrong_var_type.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_int_right_expressions_wrong_var_type");
    }

    @Test
    public void testTestAffectInstruction_affect_int_right_expressions_wrong_var_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_int_right_expressions_wrong_var_type_2.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_int_right_expressions_wrong_var_type_2");
    }

    @Test
    public void testTestAffectInstruction_affect_square_array_right_expressions_no_index_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_square_array_right_expressions_no_index.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_square_array_right_expressions_no_index");
    }

    @Test
    public void testTestAffectInstruction_affect_square_array_to_square_array_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_square_array_to_square_array.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_square_array_to_square_array");
    }

    @Test
    public void testTestAffectInstruction_affect_square_constant_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_square_constant.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_square_constant");
    }

    @Test
    public void testTestAffectInstruction_affect_square_constant_wrong_var_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_square_constant_wrong_var_type.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_square_constant_wrong_var_type");
    }

    @Test
    public void testTestAffectInstruction_affect_square_constant_wrong_var_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_square_constant_wrong_var_type_2.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_square_constant_wrong_var_type_2");
    }

    @Test
    public void testTestAffectInstruction_affect_square_env_var_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_square_env_var.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_square_env_var");
    }

    @Test
    public void testTestAffectInstruction_affect_square_right_expressions_wrong_var_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_square_right_expressions_wrong_var_type.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_square_right_expressions_wrong_var_type");
    }

    @Test
    public void testTestAffectInstruction_affect_square_right_expressions_wrong_var_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestAffectInstruction/ko/affect_square_right_expressions_wrong_var_type_2.b314", testFolder.newFile(), false, "TestAffectInstruction: affect_square_right_expressions_wrong_var_type_2");
    }

}