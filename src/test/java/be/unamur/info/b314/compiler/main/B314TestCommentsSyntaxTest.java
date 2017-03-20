package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314TestCommentsSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314TestCommentsSyntaxTest.class);

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
    // Serie TestComments OK
    //
    @Test
    public void testTestComments_comments_everywhere_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestComments/ok/comments_everywhere.b314", testFolder.newFile(), true, "TestComments: comments_everywhere");
    }

    @Test
    public void testTestComments_empty_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestComments/ok/empty.b314", testFolder.newFile(), true, "TestComments: empty");
    }

    @Test
    public void testTestComments_empty_multiple_lines_and_tab_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestComments/ok/empty_multiple_lines_and_tab.b314", testFolder.newFile(), true, "TestComments: empty_multiple_lines_and_tab");
    }

    //
    // Serie TestComments KO
    //
    @Test
    public void testTestComments_at_least_one_instruction_in_default_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestComments/ko/at_least_one_instruction_in_default.b314", testFolder.newFile(), false, "TestComments: at_least_one_instruction_in_default");
    }

    @Test
    public void testTestComments_missing_declare_bloc_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestComments/ko/missing_declare_bloc.b314", testFolder.newFile(), false, "TestComments: missing_declare_bloc");
    }

    @Test
    public void testTestComments_missing_default_bloc_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestComments/ko/missing_default_bloc.b314", testFolder.newFile(), false, "TestComments: missing_default_bloc");
    }

}