package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314globalvariabledeclSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314globalvariabledeclSyntaxTest.class);

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
    // Serie globalvariabledecl OK
    //
    @Test
    public void testglobalvariabledecl_arrays_in_all_shapes_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/globalvariabledecl/ok/arrays_in_all_shapes.b314", testFolder.newFile(), true, "globalvariabledecl: arrays_in_all_shapes");
    }

    @Test
    public void testglobalvariabledecl_mixing_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/globalvariabledecl/ok/mixing.b314", testFolder.newFile(), true, "globalvariabledecl: mixing");
    }

    @Test
    public void testglobalvariabledecl_nul_size_array_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/globalvariabledecl/ok/nul_size_array.b314", testFolder.newFile(), true, "globalvariabledecl: nul_size_array");
    }

    @Test
    public void testglobalvariabledecl_one_boolean_variable_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/globalvariabledecl/ok/one_boolean_variable.b314", testFolder.newFile(), true, "globalvariabledecl: one_boolean_variable");
    }

    @Test
    public void testglobalvariabledecl_one_integer_variable_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/globalvariabledecl/ok/one_integer_variable.b314", testFolder.newFile(), true, "globalvariabledecl: one_integer_variable");
    }

    @Test
    public void testglobalvariabledecl_one_square_variable_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/globalvariabledecl/ok/one_square_variable.b314", testFolder.newFile(), true, "globalvariabledecl: one_square_variable");
    }

    //
    // Serie globalvariabledecl KO
    //
    @Test
    public void testglobalvariabledecl_array_with_negative_size_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/globalvariabledecl/ko/array_with_negative_size.b314", testFolder.newFile(), false, "globalvariabledecl: array_with_negative_size");
    }

    @Test
    public void testglobalvariabledecl_id_starts_with_number_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/globalvariabledecl/ko/id_starts_with_number.b314", testFolder.newFile(), false, "globalvariabledecl: id_starts_with_number");
    }

    @Test
    public void testglobalvariabledecl_keyword_as_id_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/globalvariabledecl/ko/keyword_as_id.b314", testFolder.newFile(), false, "globalvariabledecl: keyword_as_id");
    }

    @Test
    public void testglobalvariabledecl_same_id_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/globalvariabledecl/ko/same_id.b314", testFolder.newFile(), false, "globalvariabledecl: same_id");
    }

    @Test
    public void testglobalvariabledecl_underscore_in_id_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/globalvariabledecl/ko/underscore_in_id.b314", testFolder.newFile(), false, "globalvariabledecl: underscore_in_id");
    }

}