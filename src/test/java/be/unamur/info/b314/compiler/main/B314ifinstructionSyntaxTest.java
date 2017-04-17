package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314ifinstructionSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314ifinstructionSyntaxTest.class);

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
    // Serie ifinstruction OK
    //
    @Test
    public void testifinstruction_check_global_var_value_is_preserved_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ok/check_global_var_value_is_preserved.b314", testFolder.newFile(), true, "ifinstruction: check_global_var_value_is_preserved");
    }

    @Test
    public void testifinstruction_if_constant_conditions_skip_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ok/if_constant_conditions_skip.b314", testFolder.newFile(), true, "ifinstruction: if_constant_conditions_skip");
    }

    @Test
    public void testifinstruction_if_else_constant_conditions_skip_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ok/if_else_constant_conditions_skip.b314", testFolder.newFile(), true, "ifinstruction: if_else_constant_conditions_skip");
    }

    @Test
    public void testifinstruction_if_else_multiple_instructions_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ok/if_else_multiple_instructions.b314", testFolder.newFile(), true, "ifinstruction: if_else_multiple_instructions");
    }

    @Test
    public void testifinstruction_if_else_rightexpr_conditions_skip_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ok/if_else_rightexpr_conditions_skip.b314", testFolder.newFile(), true, "ifinstruction: if_else_rightexpr_conditions_skip");
    }

    @Test
    public void testifinstruction_if_else_with_sub_ifs_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ok/if_else_with_sub_ifs.b314", testFolder.newFile(), true, "ifinstruction: if_else_with_sub_ifs");
    }

    @Test
    public void testifinstruction_if_multiple_instructions_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ok/if_multiple_instructions.b314", testFolder.newFile(), true, "ifinstruction: if_multiple_instructions");
    }

    @Test
    public void testifinstruction_if_rightexpr_conditions_skip_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ok/if_rightexpr_conditions_skip.b314", testFolder.newFile(), true, "ifinstruction: if_rightexpr_conditions_skip");
    }

    @Test
    public void testifinstruction_previous_series_and_if_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ok/previous_series_and_if.b314", testFolder.newFile(), true, "ifinstruction: previous_series_and_if");
    }

    @Test
    public void testifinstruction_previous_series_and_if_else_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ok/previous_series_and_if_else.b314", testFolder.newFile(), true, "ifinstruction: previous_series_and_if_else");
    }

    @Test
    public void testifinstruction_previous_series_and_if_else_with_sub_if_else_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ok/previous_series_and_if_else_with_sub_if_else.b314", testFolder.newFile(), true, "ifinstruction: previous_series_and_if_else_with_sub_if_else");
    }

    @Test
    public void testifinstruction_previous_series_and_if_with_sub_if_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ok/previous_series_and_if_with_sub_if.b314", testFolder.newFile(), true, "ifinstruction: previous_series_and_if_with_sub_if");
    }

    //
    // Serie ifinstruction KO
    //
    @Test
    public void testifinstruction_if_else_with_sub_affect_wrong_type_condition_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ko/if_else_with_sub_affect_wrong_type_condition.b314", testFolder.newFile(), false, "ifinstruction: if_else_with_sub_affect_wrong_type_condition");
    }

    @Test
    public void testifinstruction_if_else_with_sub_compute_wrong_type_condition_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ko/if_else_with_sub_compute_wrong_type_condition.b314", testFolder.newFile(), false, "ifinstruction: if_else_with_sub_compute_wrong_type_condition");
    }

    @Test
    public void testifinstruction_if_else_with_sub_if_and_wrong_type_condition_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ko/if_else_with_sub_if_and_wrong_type_condition.b314", testFolder.newFile(), false, "ifinstruction: if_else_with_sub_if_and_wrong_type_condition");
    }

    @Test
    public void testifinstruction_if_else_wrong_type_condition_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ko/if_else_wrong_type_condition.b314", testFolder.newFile(), false, "ifinstruction: if_else_wrong_type_condition");
    }

    @Test
    public void testifinstruction_if_else_wrong_type_condition_1_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ko/if_else_wrong_type_condition_1.b314", testFolder.newFile(), false, "ifinstruction: if_else_wrong_type_condition_1");
    }

    @Test
    public void testifinstruction_if_else_wrong_type_condition_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ko/if_else_wrong_type_condition_2.b314", testFolder.newFile(), false, "ifinstruction: if_else_wrong_type_condition_2");
    }

    @Test
    public void testifinstruction_if_with_sub_affect_wrong_type_condition_1_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ko/if_with_sub_affect_wrong_type_condition_1.b314", testFolder.newFile(), false, "ifinstruction: if_with_sub_affect_wrong_type_condition_1");
    }

    @Test
    public void testifinstruction_if_with_sub_compute_wrong_type_condition_1_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ko/if_with_sub_compute_wrong_type_condition_1.b314", testFolder.newFile(), false, "ifinstruction: if_with_sub_compute_wrong_type_condition_1");
    }

    @Test
    public void testifinstruction_if_with_sub_if_wrong_type_condition_1_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ko/if_with_sub_if_wrong_type_condition_1.b314", testFolder.newFile(), false, "ifinstruction: if_with_sub_if_wrong_type_condition_1");
    }

    @Test
    public void testifinstruction_if_wrong_type_condition_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ko/if_wrong_type_condition.b314", testFolder.newFile(), false, "ifinstruction: if_wrong_type_condition");
    }

    @Test
    public void testifinstruction_if_wrong_type_condition_1_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ko/if_wrong_type_condition_1.b314", testFolder.newFile(), false, "ifinstruction: if_wrong_type_condition_1");
    }

    @Test
    public void testifinstruction_if_wrong_type_condition_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/ifinstruction/ko/if_wrong_type_condition_2.b314", testFolder.newFile(), false, "ifinstruction: if_wrong_type_condition_2");
    }

}