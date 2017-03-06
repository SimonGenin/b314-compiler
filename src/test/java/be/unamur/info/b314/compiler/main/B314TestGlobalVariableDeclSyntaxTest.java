package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314TestGlobalVariableDeclSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314TestGlobalVariableDeclSyntaxTest.class);

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
    // Serie TestGlobalVariableDecl OK
    //
    @Test
    public void testTestGlobalVariableDecl_arrays_in_all_shapes_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestGlobalVariableDecl/ok/arrays_in_all_shapes.b314", testFolder.newFile(), true, "TestGlobalVariableDecl: arrays_in_all_shapes");
    }

    @Test
    public void testTestGlobalVariableDecl_mixing_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestGlobalVariableDecl/ok/mixing.b314", testFolder.newFile(), true, "TestGlobalVariableDecl: mixing");
    }

    @Test
    public void testTestGlobalVariableDecl_nul_size_array_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestGlobalVariableDecl/ok/nul_size_array.b314", testFolder.newFile(), true, "TestGlobalVariableDecl: nul_size_array");
    }

    @Test
    public void testTestGlobalVariableDecl_one_boolean_variable_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestGlobalVariableDecl/ok/one_boolean_variable.b314", testFolder.newFile(), true, "TestGlobalVariableDecl: one_boolean_variable");
    }

    @Test
    public void testTestGlobalVariableDecl_one_integer_variable_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestGlobalVariableDecl/ok/one_integer_variable.b314", testFolder.newFile(), true, "TestGlobalVariableDecl: one_integer_variable");
    }

    @Test
    public void testTestGlobalVariableDecl_one_square_variable_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/TestGlobalVariableDecl/ok/one_square_variable.b314", testFolder.newFile(), true, "TestGlobalVariableDecl: one_square_variable");
    }

    //
    // Serie TestGlobalVariableDecl KO
    //
    @Test
    public void testTestGlobalVariableDecl_array_with_negative_size_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestGlobalVariableDecl/ko/array_with_negative_size.b314", testFolder.newFile(), false, "TestGlobalVariableDecl: array_with_negative_size");
    }

    @Test
    public void testTestGlobalVariableDecl_id_starts_with_number_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestGlobalVariableDecl/ko/id_starts_with_number.b314", testFolder.newFile(), false, "TestGlobalVariableDecl: id_starts_with_number");
    }

    @Test
    public void testTestGlobalVariableDecl_keyword_as_id_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestGlobalVariableDecl/ko/keyword_as_id.b314", testFolder.newFile(), false, "TestGlobalVariableDecl: keyword_as_id");
    }

    @Test
    public void testTestGlobalVariableDecl_same_id_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestGlobalVariableDecl/ko/same_id.b314", testFolder.newFile(), false, "TestGlobalVariableDecl: same_id");
    }

    @Test
    public void testTestGlobalVariableDecl_underscore_in_id_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/TestGlobalVariableDecl/ko/underscore_in_id.b314", testFolder.newFile(), false, "TestGlobalVariableDecl: underscore_in_id");
    }

}