//import cs5004.animator.model.Window;
//import cs5004.animator.util.AnimationBuilder;
//import cs5004.animator.util.AnimationReader;
//import cs5004.animator.util.Builder;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//
//
//public class TestView {
//    private AnimationBuilder<Window> builder;
//    private Readable readable;
//
//    @Before
//    public void setUp() {
//        this.builder = new Builder();
//    }
//
//    @Test
//    public void testSvg() {
//        try {
//            readable = new FileReader("../toh-3-head.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        Window win = AnimationReader.parseFile(readable, builder);
//        assertEquals(win.toSvgString(20), );
//    }
//}
