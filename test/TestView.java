import cs5004.animator.model.Window;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Builder;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;


public class TestView {
    private AnimationBuilder<Window> builder;
    private Readable readable;

    @Before
    public void setUp() {
        this.builder = new Builder();
    }

    @Test
    public void testTextView() {
        try {
            readable = new FileReader("./toh-3.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Window win = AnimationReader.parseFile(readable, builder);
        assertEquals(win.toString(),"Shapes:\n" +
        "Type: Rectangle\n" +
        "Min Corner: (190,180), Width: 20, Height: 30, Color: (0, 49, 90) " +
                        "Appears at t=1 Disappears at t=302\n" +
        "Type: Rectangle\n" +
        "Min Corner: (167,210), Width: 65, Height: 30, Color: (6, 247, 41) " +
                        "Appears at t=1 Disappears at t=302\n" +
        "Type: Rectangle\n" +
        "Min Corner: (145,240), Width: 110, Height: 30, Color: (11, 45, 175) " +
                        "Appears at t=1 Disappears at t=302\n" +
        "Shape disk1 moves from (190,180) to (190,50) from t=25 to t=35\n" +
        "Shape disk1 moves from (190,50) to (490,50) from t=36 to t=46\n" +
        "Shape disk1 moves from (490,50) to (490,240) from t=47 to t=57\n" +
        "Shape disk2 moves from (167,210) to (167,50) from t=57 to t=67\n" +
        "Shape disk2 moves from (167,50) to (317,50) from t=68 to t=78\n" +
        "Shape disk2 moves from (317,50) to (317,240) from t=79 to t=89\n" +
        "Shape disk1 moves from (490,240) to (490,50) from t=89 to t=99\n" +
        "Shape disk1 moves from (490,50) to (340,50) from t=100 to t=110\n" +
        "Shape disk1 moves from (340,50) to (340,210) from t=111 to t=121\n" +
        "Shape disk3 moves from (145,240) to (145,50) from t=121 to t=131\n" +
        "Shape disk3 moves from (145,50) to (445,50) from t=132 to t=142\n" +
        "Shape disk3 moves from (445,50) to (445,240) from t=143 to t=153\n" +
        "Shape disk3 changes color from (11, 45, 175) to (0, 255, 0) from t=153 to t=161\n" +
        "Shape disk1 moves from (340,210) to (340,50) from t=153 to t=163\n" +
        "Shape disk1 moves from (340,50) to (190,50) from t=164 to t=174\n" +
        "Shape disk1 moves from (190,50) to (190,240) from t=175 to t=185\n" +
        "Shape disk2 moves from (317,240) to (317,50) from t=185 to t=195\n" +
        "Shape disk2 moves from (317,50) to (467,50) from t=196 to t=206\n" +
        "Shape disk2 moves from (467,50) to (467,210) from t=207 to t=217\n" +
        "Shape disk1 moves from (190,240) to (190,50) from t=217 to t=227\n" +
        "Shape disk2 changes color from (6, 247, 41) to (0, 255, 0) from t=217 to t=225\n" +
        "Shape disk1 moves from (190,50) to (490,50) from t=228 to t=238\n" +
        "Shape disk1 moves from (490,50) to (490,180) from t=239 to t=249\n" +
        "Shape disk1 changes color from (0, 49, 90) to (0, 255, 0) from t=249 to t=257\n");
    }

    @Test
    public void testSvgView() {
        try {
            readable = new FileReader("./toh-3.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Window win = AnimationReader.parseFile(readable, builder);
        assertEquals(win.toSvgString(20),
                "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"410px\" height=\"220px\" viewBox=\"145 50 555 270\">\n" +
"<rect x=\"190\" y=\"180\" width=\"20\" height=\"30\" fill=\"rgb(0,49,90)\" visibility=\"hidden\">\n" +
"<animate attributeName=\"visibility\" to=\"visible\" begin=\"0s\"/>\n" +
"<animate attributeName=\"visibility\" to=\"hidden\" begin=\"15s\"/>\n" +
"<animate attributeName=\"x\" from=\"190\" to=\"190\" begin=\"1.3s\" end=\"1.8s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"180\" to=\"50\" begin=\"1.3s\" end=\"1.8s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"x\" from=\"190\" to=\"490\" begin=\"1.8s\" end=\"2.3s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"50\" to=\"50\" begin=\"1.8s\" end=\"2.3s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"x\" from=\"490\" to=\"490\" begin=\"2.4s\" end=\"2.9s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"50\" to=\"240\" begin=\"2.4s\" end=\"2.9s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"x\" from=\"490\" to=\"490\" begin=\"4.5s\" end=\"5.0s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"240\" to=\"50\" begin=\"4.5s\" end=\"5.0s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"x\" from=\"490\" to=\"340\" begin=\"5.0s\" end=\"5.5s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"50\" to=\"50\" begin=\"5.0s\" end=\"5.5s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"x\" from=\"340\" to=\"340\" begin=\"5.6s\" end=\"6.1s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"50\" to=\"210\" begin=\"5.6s\" end=\"6.1s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"x\" from=\"340\" to=\"340\" begin=\"7.7s\" end=\"8.2s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"210\" to=\"50\" begin=\"7.7s\" end=\"8.2s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"x\" from=\"340\" to=\"190\" begin=\"8.2s\" end=\"8.7s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"50\" to=\"50\" begin=\"8.2s\" end=\"8.7s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"x\" from=\"190\" to=\"190\" begin=\"8.8s\" end=\"9.3s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"50\" to=\"240\" begin=\"8.8s\" end=\"9.3s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"x\" from=\"190\" to=\"190\" begin=\"10.9s\" end=\"11.4s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"240\" to=\"50\" begin=\"10.9s\" end=\"11.4s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"x\" from=\"190\" to=\"490\" begin=\"11.4s\" end=\"11.9s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"50\" to=\"50\" begin=\"11.4s\" end=\"11.9s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"x\" from=\"490\" to=\"490\" begin=\"12.0s\" end=\"12.5s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"50\" to=\"180\" begin=\"12.0s\" end=\"12.5s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"fill\" from=\"rgb(0,49,90)\" to=\"rgb(0,255,0)\" begin=\"12.5s\" end=\"12.9s\" dur=\"0.4\" fill=\"freeze\"/>\n" +
"</rect>\n" +
"<rect x=\"167\" y=\"210\" width=\"65\" height=\"30\" fill=\"rgb(6,247,41)\" visibility=\"hidden\">\n" +
"<animate attributeName=\"visibility\" to=\"visible\" begin=\"0s\"/>\n" +
"<animate attributeName=\"visibility\" to=\"hidden\" begin=\"15s\"/>\n" +
"<animate attributeName=\"x\" from=\"167\" to=\"167\" begin=\"2.9s\" end=\"3.4s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"210\" to=\"50\" begin=\"2.9s\" end=\"3.4s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"x\" from=\"167\" to=\"317\" begin=\"3.4s\" end=\"3.9s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"50\" to=\"50\" begin=\"3.4s\" end=\"3.9s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"x\" from=\"317\" to=\"317\" begin=\"4.0s\" end=\"4.5s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"50\" to=\"240\" begin=\"4.0s\" end=\"4.5s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"x\" from=\"317\" to=\"317\" begin=\"9.3s\" end=\"9.8s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"240\" to=\"50\" begin=\"9.3s\" end=\"9.8s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"x\" from=\"317\" to=\"467\" begin=\"9.8s\" end=\"10.3s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"50\" to=\"50\" begin=\"9.8s\" end=\"10.3s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"x\" from=\"467\" to=\"467\" begin=\"10.4s\" end=\"10.9s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"50\" to=\"210\" begin=\"10.4s\" end=\"10.9s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"fill\" from=\"rgb(6,247,41)\" to=\"rgb(0,255,0)\" begin=\"10.9s\" end=\"11.3s\" dur=\"0.4\" fill=\"freeze\"/>\n" +
"</rect>\n" +
"<rect x=\"145\" y=\"240\" width=\"110\" height=\"30\" fill=\"rgb(11,45,175)\" visibility=\"hidden\">\n" +
"<animate attributeName=\"visibility\" to=\"visible\" begin=\"0s\"/>\n" +
"<animate attributeName=\"visibility\" to=\"hidden\" begin=\"15s\"/>\n" +
"<animate attributeName=\"x\" from=\"145\" to=\"145\" begin=\"6.1s\" end=\"6.6s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"240\" to=\"50\" begin=\"6.1s\" end=\"6.6s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"x\" from=\"145\" to=\"445\" begin=\"6.6s\" end=\"7.1s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"50\" to=\"50\" begin=\"6.6s\" end=\"7.1s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"x\" from=\"445\" to=\"445\" begin=\"7.2s\" end=\"7.7s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"y\" from=\"50\" to=\"240\" begin=\"7.2s\" end=\"7.7s\" dur=\"0.5s\" fill=\"freeze\"/>\n" +
"<animate attributeName=\"fill\" from=\"rgb(11,45,175)\" to=\"rgb(0,255,0)\" begin=\"7.7s\" end=\"8.1s\" dur=\"0.4\" fill=\"freeze\"/>\n" +
"</rect>\n" +
"</svg>");


    }
}
