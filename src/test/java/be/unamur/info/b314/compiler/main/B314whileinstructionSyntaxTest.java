package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314whileinstructionSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314whileinstructionSyntaxTest.class);

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
    // Serie whileinstruction OK
    //
    @Test
    public void testwhileinstruction_array_tour_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/whileinstruction/ok/array_tour.b314", testFolder.newFile(), true, "whileinstruction: array_tour");
    }

    @Test
    public void testwhileinstruction_for_as_while_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/whileinstruction/ok/for_as_while.b314", testFolder.newFile(), true, "whileinstruction: for_as_while");
    }

    @Test
    public void testwhileinstruction_while_constant_conditions_skip_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/whileinstruction/ok/while_constant_conditions_skip.b314", testFolder.newFile(), true, "whileinstruction: while_constant_conditions_skip");
    }

    @Test
    public void testwhileinstruction_while_multiple_instructions_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/whileinstruction/ok/while_multiple_instructions.b314", testFolder.newFile(), true, "whileinstruction: while_multiple_instructions");
    }

    @Test
    public void testwhileinstruction_while_rightexpr_conditions_skip_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/whileinstruction/ok/while_rightexpr_conditions_skip.b314", testFolder.newFile(), true, "whileinstruction: while_rightexpr_conditions_skip");
    }

    @Test
    public void testwhileinstruction_while_with_sub_whiles_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/whileinstruction/ok/while_with_sub_whiles.b314", testFolder.newFile(), true, "whileinstruction: while_with_sub_whiles");
    }

    //
    // Serie whileinstruction KO
    //
    @Test
    public void testwhileinstruction_if_with_sub_compute_wrong_type_condition_1_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/whileinstruction/ko/if_with_sub_compute_wrong_type_condition_1.b314", testFolder.newFile(), false, "whileinstruction: if_with_sub_compute_wrong_type_condition_1");
    }

    @Test
    public void testwhileinstruction_if_with_sub_if_wrong_type_condition_1_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/whileinstruction/ko/if_with_sub_if_wrong_type_condition_1.b314", testFolder.newFile(), false, "whileinstruction: if_with_sub_if_wrong_type_condition_1");
    }

    @Test
    public void testwhileinstruction_if_wrong_type_condition_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/whileinstruction/ko/if_wrong_type_condition.b314", testFolder.newFile(), false, "whileinstruction: if_wrong_type_condition");
    }

    @Test
    public void testwhileinstruction_if_wrong_type_condition_1_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/whileinstruction/ko/if_wrong_type_condition_1.b314", testFolder.newFile(), false, "whileinstruction: if_wrong_type_condition_1");
    }

    @Test
    public void testwhileinstruction_if_wrong_type_condition_2_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/whileinstruction/ko/if_wrong_type_condition_2.b314", testFolder.newFile(), false, "whileinstruction: if_wrong_type_condition_2");
    }

    @Test
    public void testwhileinstruction_while_with_sub_affect_wrong_type_condition_1_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/whileinstruction/ko/while_with_sub_affect_wrong_type_condition_1.b314", testFolder.newFile(), false, "whileinstruction: while_with_sub_affect_wrong_type_condition_1");
    }

}