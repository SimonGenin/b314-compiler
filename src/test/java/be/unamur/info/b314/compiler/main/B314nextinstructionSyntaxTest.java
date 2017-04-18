package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314nextinstructionSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314nextinstructionSyntaxTest.class);

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
    // Serie nextinstruction OK
    //
    @Test
    public void testnextinstruction_next_do_nothing_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ok/next_do_nothing.b314", testFolder.newFile(), true, "nextinstruction: next_do_nothing");
    }

    @Test
    public void testnextinstruction_next_move_east_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ok/next_move_east.b314", testFolder.newFile(), true, "nextinstruction: next_move_east");
    }

    @Test
    public void testnextinstruction_next_move_north_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ok/next_move_north.b314", testFolder.newFile(), true, "nextinstruction: next_move_north");
    }

    @Test
    public void testnextinstruction_next_move_south_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ok/next_move_south.b314", testFolder.newFile(), true, "nextinstruction: next_move_south");
    }

    @Test
    public void testnextinstruction_next_move_west_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ok/next_move_west.b314", testFolder.newFile(), true, "nextinstruction: next_move_west");
    }

    @Test
    public void testnextinstruction_next_shoot_east_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ok/next_shoot_east.b314", testFolder.newFile(), true, "nextinstruction: next_shoot_east");
    }

    @Test
    public void testnextinstruction_next_shoot_north_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ok/next_shoot_north.b314", testFolder.newFile(), true, "nextinstruction: next_shoot_north");
    }

    @Test
    public void testnextinstruction_next_shoot_south_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ok/next_shoot_south.b314", testFolder.newFile(), true, "nextinstruction: next_shoot_south");
    }

    @Test
    public void testnextinstruction_next_shoot_west_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ok/next_shoot_west.b314", testFolder.newFile(), true, "nextinstruction: next_shoot_west");
    }

    @Test
    public void testnextinstruction_next_use_fruits_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ok/next_use_fruits.b314", testFolder.newFile(), true, "nextinstruction: next_use_fruits");
    }

    @Test
    public void testnextinstruction_next_use_map_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ok/next_use_map.b314", testFolder.newFile(), true, "nextinstruction: next_use_map");
    }

    @Test
    public void testnextinstruction_next_use_radio_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ok/next_use_radio.b314", testFolder.newFile(), true, "nextinstruction: next_use_radio");
    }

    @Test
    public void testnextinstruction_next_use_soda_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ok/next_use_soda.b314", testFolder.newFile(), true, "nextinstruction: next_use_soda");
    }

    //
    // Serie nextinstruction KO
    //
    @Test
    public void testnextinstruction_next_move_map_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ko/next_move_map.b314", testFolder.newFile(), false, "nextinstruction: next_move_map");
    }

    @Test
    public void testnextinstruction_next_move_soda_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ko/next_move_soda.b314", testFolder.newFile(), false, "nextinstruction: next_move_soda");
    }

    @Test
    public void testnextinstruction_next_shoot_map_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ko/next_shoot_map.b314", testFolder.newFile(), false, "nextinstruction: next_shoot_map");
    }

    @Test
    public void testnextinstruction_next_shoot_soda_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ko/next_shoot_soda.b314", testFolder.newFile(), false, "nextinstruction: next_shoot_soda");
    }

    @Test
    public void testnextinstruction_next_use_ammo_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ko/next_use_ammo.b314", testFolder.newFile(), false, "nextinstruction: next_use_ammo");
    }

    @Test
    public void testnextinstruction_next_use_dirt_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ko/next_use_dirt.b314", testFolder.newFile(), false, "nextinstruction: next_use_dirt");
    }

    @Test
    public void testnextinstruction_next_use_north_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ko/next_use_north.b314", testFolder.newFile(), false, "nextinstruction: next_use_north");
    }

    @Test
    public void testnextinstruction_next_use_west_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/nextinstruction/ko/next_use_west.b314", testFolder.newFile(), false, "nextinstruction: next_use_west");
    }

}