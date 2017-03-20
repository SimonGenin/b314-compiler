package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314programSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314programSyntaxTest.class);

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
    // Serie program OK
    //
    @Test
    public void testprogram_no_var_func_declared_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/program/ok/no_var_func_declared.b314", testFolder.newFile(), true, "program: no_var_func_declared");
    }

    @Test
    public void testprogram_no_when_bloc_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/program/ok/no_when_bloc.b314", testFolder.newFile(), true, "program: no_when_bloc");
    }

    @Test
    public void testprogram_several_decl_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/program/ok/several_decl.b314", testFolder.newFile(), true, "program: several_decl");
    }

    @Test
    public void testprogram_several_when_bloc_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/program/ok/several_when_bloc.b314", testFolder.newFile(), true, "program: several_when_bloc");
    }

    //
    // Serie program KO
    //
    @Test
    public void testprogram_missing_your_turn_statement_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/program/ko/missing_your_turn_statement.b314", testFolder.newFile(), false, "program: missing_your_turn_statement");
    }

}