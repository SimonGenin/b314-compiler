package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314whenclauseSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314whenclauseSyntaxTest.class);

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
    // Serie whenclause OK
    //
    @Test
    public void testwhenclause_multiple_when_with_constant_condition_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/whenclause/ok/multiple_when_with_constant_condition.b314", testFolder.newFile(), true, "whenclause: multiple_when_with_constant_condition");
    }

    @Test
    public void testwhenclause_multiple_when_with_constant_conditions_skip_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/whenclause/ok/multiple_when_with_constant_conditions_skip.b314", testFolder.newFile(), true, "whenclause: multiple_when_with_constant_conditions_skip");
    }

    @Test
    public void testwhenclause_multiple_when_with_fct_call_condition_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/whenclause/ok/multiple_when_with_fct_call_condition.b314", testFolder.newFile(), true, "whenclause: multiple_when_with_fct_call_condition");
    }

    @Test
    public void testwhenclause_multiple_when_with_fct_call_conditions_skip_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/whenclause/ok/multiple_when_with_fct_call_conditions_skip.b314", testFolder.newFile(), true, "whenclause: multiple_when_with_fct_call_conditions_skip");
    }

    @Test
    public void testwhenclause_multiple_when_with_gloabl_var_value_condition_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/whenclause/ok/multiple_when_with_gloabl_var_value_condition.b314", testFolder.newFile(), true, "whenclause: multiple_when_with_gloabl_var_value_condition");
    }

    @Test
    public void testwhenclause_multiple_when_with_rightexpr_conditions_and_instructions_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/whenclause/ok/multiple_when_with_rightexpr_conditions_and_instructions.b314", testFolder.newFile(), true, "whenclause: multiple_when_with_rightexpr_conditions_and_instructions");
    }

    @Test
    public void testwhenclause_multiple_when_with_rightexpr_conditions_skip_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/whenclause/ok/multiple_when_with_rightexpr_conditions_skip.b314", testFolder.newFile(), true, "whenclause: multiple_when_with_rightexpr_conditions_skip");
    }

    @Test
    public void testwhenclause_one_when_with_constant_condition_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/whenclause/ok/one_when_with_constant_condition.b314", testFolder.newFile(), true, "whenclause: one_when_with_constant_condition");
    }

    @Test
    public void testwhenclause_one_when_with_constant_condition_skip_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/whenclause/ok/one_when_with_constant_condition_skip.b314", testFolder.newFile(), true, "whenclause: one_when_with_constant_condition_skip");
    }

    //
    // Serie whenclause KO
    //
    @Test
    public void testwhenclause_multiple_default_clauses_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/whenclause/ko/multiple_default_clauses.b314", testFolder.newFile(), false, "whenclause: multiple_default_clauses");
    }

    @Test
    public void testwhenclause_multiple_when_with_fct_call_condition_wrong_type_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/whenclause/ko/multiple_when_with_fct_call_condition_wrong_type.b314", testFolder.newFile(), false, "whenclause: multiple_when_with_fct_call_condition_wrong_type");
    }

    @Test
    public void testwhenclause_multiple_when_with_rightexpr_condition_wrong_type_1_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/whenclause/ko/multiple_when_with_rightexpr_condition_wrong_type_1.b314", testFolder.newFile(), false, "whenclause: multiple_when_with_rightexpr_condition_wrong_type_1");
    }

    @Test
    public void testwhenclause_multiple_when_with_rightexpr_condition_wrong_type_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/whenclause/ko/multiple_when_with_rightexpr_condition_wrong_type_2.b314", testFolder.newFile(), false, "whenclause: multiple_when_with_rightexpr_condition_wrong_type_2");
    }

    @Test
    public void testwhenclause_multiple_when_with_rightexpr_condition_wrong_type_3_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/whenclause/ko/multiple_when_with_rightexpr_condition_wrong_type_3.b314", testFolder.newFile(), false, "whenclause: multiple_when_with_rightexpr_condition_wrong_type_3");
    }

    @Test
    public void testwhenclause_multiple_when_with_rightexpr_conditions_and_instructions_wrongly_typed_1_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/whenclause/ko/multiple_when_with_rightexpr_conditions_and_instructions_wrongly_typed_1.b314", testFolder.newFile(), false, "whenclause: multiple_when_with_rightexpr_conditions_and_instructions_wrongly_typed_1");
    }

    @Test
    public void testwhenclause_multiple_when_with_rightexpr_conditions_and_instructions_wrongly_typed_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/whenclause/ko/multiple_when_with_rightexpr_conditions_and_instructions_wrongly_typed_2.b314", testFolder.newFile(), false, "whenclause: multiple_when_with_rightexpr_conditions_and_instructions_wrongly_typed_2");
    }

    @Test
    public void testwhenclause_when_clauses_after_default_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/whenclause/ko/when_clauses_after_default.b314", testFolder.newFile(), false, "whenclause: when_clauses_after_default");
    }

}