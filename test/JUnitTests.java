import org.junit.Test;
import org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnitTests {
    @Test
    public void testquick(){
        ArrayList<Integer> input = new ArrayList<>(){
            {
                add(78);
                add(1);
                add(82);
                add(27);
                add(14);
                add(182);
                add(167);
                add(157);
                add(53);
                add(129);
                add(171);
                add(14);
                add(113);
                add(54);
                add(90);
                add(184);
                add(142);
                add(87);
                add(145);
                add(130);
                add(112);
                add(172);
                add(149);
                add(74);
                add(96);
                add(60);
                add(13);
                add(147);
                add(19);
                add(79);
                add(49);
                add(191);
                add(111);
                add(135);
                add(37);
                add(181);
                add(10);
                add(84);
                add(38);
                add(27);
                add(97);
                add(190);
                add(47);
                add(167);
                add(153);
                add(13);
                add(128);
                add(127);
                add(134);
                add(69);
                add(124);
                add(105);
                add(134);
                add(70);
                add(62);
                add(4);
                add(177);
                add(199);
                add(184);
                add(181);
                add(72);
                add(106);
                add(151);
                add(190);
                add(174);
                add(59);
                add(126);
                add(136);
                add(199);
                add(90);
                add(69);
                add(42);
                add(52);
                add(40);
                add(181);
                add(162);
                add(112);
                add(126);
                add(195);
                add(135);
                add(11);
                add(112);
                add(111);
                add(17);
                add(8);
                add(169);
                add(161);
                add(184);
                add(44);
                add(176);
                add(175);
                add(154);
                add(182);
                add(72);
                add(148);
                add(25);
                add(173);
                add(118);
                add(128);
                add(160);
                add(99);
            }
        };
        QuicksortTask task = new QuicksortTask(input, 0, input.size()-1);
        task.invoke();
        String result = "";
        for (int i = 0; i < input.size(); i++){
            result = result + input.get(i) + ":";
        }
        assertEquals("1:4:8:10:11:13:13:14:14:17:19:25:27:27:37:38:40:42:44:47:49:52:53:54:59:60:62:69:69:70:72:72:74:78:79:82:84:87:90:90:96:97:99:105:106:111:111:112:112:112:113:118:124:126:126:127:128:128:129:130:134:134:135:135:136:142:145:147:148:149:151:153:154:157:160:161:162:167:167:169:171:172:173:174:175:176:177:181:181:181:182:182:184:184:184:190:190:191:195:199:199:", result);

    }

    @Test
    public void testmerge(){
        ForkJoinPool pool = new ForkJoinPool();
        ArrayList<Integer> input = new ArrayList<>(){
            {
                add(78);
                add(1);
                add(82);
                add(27);
                add(14);
                add(182);
                add(167);
                add(157);
                add(53);
                add(129);
                add(171);
                add(14);
                add(113);
                add(54);
                add(90);
                add(184);
                add(142);
                add(87);
                add(145);
                add(130);
                add(112);
                add(172);
                add(149);
                add(74);
                add(96);
                add(60);
                add(13);
                add(147);
                add(19);
                add(79);
                add(49);
                add(191);
                add(111);
                add(135);
                add(37);
                add(181);
                add(10);
                add(84);
                add(38);
                add(27);
                add(97);
                add(190);
                add(47);
                add(167);
                add(153);
                add(13);
                add(128);
                add(127);
                add(134);
                add(69);
                add(124);
                add(105);
                add(134);
                add(70);
                add(62);
                add(4);
                add(177);
                add(199);
                add(184);
                add(181);
                add(72);
                add(106);
                add(151);
                add(190);
                add(174);
                add(59);
                add(126);
                add(136);
                add(199);
                add(90);
                add(69);
                add(42);
                add(52);
                add(40);
                add(181);
                add(162);
                add(112);
                add(126);
                add(195);
                add(135);
                add(11);
                add(112);
                add(111);
                add(17);
                add(8);
                add(169);
                add(161);
                add(184);
                add(44);
                add(176);
                add(175);
                add(154);
                add(182);
                add(72);
                add(148);
                add(25);
                add(173);
                add(118);
                add(128);
                add(160);
                add(99);
            }
        };
        MergesortTask task = new MergesortTask(input.subList(0,input.size()));
        pool.invoke(task);
        String result = "";
        for (int i = 0; i < input.size(); i++){
            result = result + input.get(i) + ":";
        }
        assertEquals("1:4:8:10:11:13:13:14:14:17:19:25:27:27:37:38:40:42:44:47:49:52:53:54:59:60:62:69:69:70:72:72:74:78:79:82:84:87:90:90:96:97:99:105:106:111:111:112:112:112:113:118:124:126:126:127:128:128:129:130:134:134:135:135:136:142:145:147:148:149:151:153:154:157:160:161:162:167:167:169:171:172:173:174:175:176:177:181:181:181:182:182:184:184:184:190:190:191:195:199:199:", result);
    }

    @Test
    public void testPartition(){
        ArrayList<Integer> input = new ArrayList<>(){
            {
                add(78);
                add(1);
                add(82);
                add(27);
                add(14);
                add(182);
                add(167);
                add(157);
                add(53);
                add(129);
                add(171);
                add(14);
                add(113);
                add(54);
                add(90);
                add(184);
                add(142);
                add(87);
                add(145);
                add(130);
                add(112);
                add(172);
                add(149);
                add(74);
                add(96);
                add(60);
                add(13);
                add(147);
                add(19);
                add(79);
                add(49);
                add(191);
                add(111);
                add(135);
                add(37);
                add(181);
                add(10);
                add(84);
                add(38);
                add(27);
                add(97);
                add(190);
                add(47);
                add(167);
                add(153);
                add(13);
                add(128);
                add(127);
                add(134);
                add(69);
                add(124);
                add(105);
                add(134);
                add(70);
                add(62);
                add(4);
                add(177);
                add(199);
                add(184);
                add(181);
                add(72);
                add(106);
                add(151);
                add(190);
                add(174);
                add(59);
                add(126);
                add(136);
                add(199);
                add(90);
                add(69);
                add(42);
                add(52);
                add(40);
                add(181);
                add(162);
                add(112);
                add(126);
                add(195);
                add(135);
                add(11);
                add(112);
                add(111);
                add(17);
                add(8);
                add(169);
                add(161);
                add(184);
                add(44);
                add(176);
                add(175);
                add(154);
                add(182);
                add(72);
                add(148);
                add(25);
                add(173);
                add(118);
                add(128);
                add(160);
                add(99);
            }
        };
        int p = QuicksortTask.partition(input, 0, input.size()-1);
        assertEquals(33, p);
    }

}
