package day19homework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Teststream {
    public static void main(String[] args) throws IOException {

        List<heroes> list = new ArrayList<>();
        Stream<String> lines = Files.lines(Paths.get("heroes.txt"));
        Stream<heroes> heroesStream1 = lines.map(x -> x.split("\t")).map(x ->
                new heroes(
                        Integer.parseInt(x[0]),
                        x[1], x[2], x[3],
                        Integer.parseInt(x[4]),
                        Integer.parseInt(x[5]),
                        Integer.parseInt(x[6])));
        heroesStream1.forEach(x ->list.add(x));

        //按出生地分组；
        Stream<heroes> heroesStream = list.stream();
        Map<String, List<heroes>> list2 = heroesStream.collect(Collectors.groupingBy(h -> h.getCity()));
        System.out.println(list2);
        //女性寿命最高
        Stream<heroes> heroesStream2 = list.stream();
        List<heroes> list3 = heroesStream2.filter(h -> h.getSex().equals("女")).sorted((x, x1) ->
                 (x1.getDeadline() - x1.getBrith())-(x.getDeadline() - x.getBrith())
        ).limit(1).collect(Collectors.toList());
        System.out.println(list3);
        //寿命前三
        Stream<heroes> heroesStream3 = list.stream();
        List<heroes> list4 = heroesStream3.sorted((h, h1) ->
                -((h.getDeadline() - h.getBrith()) - (h1.getDeadline() - h1.getBrith()))
        ).limit(3).collect(Collectors.toList());
        System.out.println(list4);
        //武力值前三
        Stream<heroes> heroesStream4 = list.stream();
        List<heroes> list5 = heroesStream4.sorted((he, he1) ->
                -(he.getNengli() - he1.getNengli())
        ).limit(3).collect(Collectors.toList());
        System.out.println(list5);
        //按各个年龄段分组： 0~20, 21~40, 41~60, 60以上
        Stream<heroes> heroesStream51 = list.stream();
        List<heroes> list61 = heroesStream51.filter(heroes ->
                (heroes.getDeadline() - heroes.getBrith()) < 20).collect(Collectors.toList());
        System.out.println("年龄段为0~20："+list61);
        Stream<heroes> heroesStream52 = list.stream();
        List<heroes> list62 = heroesStream52.filter(heroes ->
                20 < (heroes.getDeadline() - heroes.getBrith()) && (heroes.getDeadline() - heroes.getBrith()) <= 40)
                .collect(Collectors.toList());
        System.out.println("年龄段为21~40："+list62);
        Stream<heroes> heroesStream53 = list.stream();
        List<heroes> list63 = heroesStream53.filter(heroes ->
                (heroes.getDeadline() - heroes.getBrith()) > 40 && (heroes.getDeadline() - heroes.getBrith()) <= 60)
                .collect(Collectors.toList());
        System.out.println("年龄段为41~60："+list63);
        Stream<heroes> heroesStream54 = list.stream();
        List<heroes> list64 = heroesStream54.filter(heroes ->
                (heroes.getDeadline() - heroes.getBrith()) > 60).collect(Collectors.toList());
        System.out.println("年龄段为60以上："+list64);
        //按武力段分组： >=90， 80~89, 70~79, <70
        Supplier<Stream<heroes>> streamSupplier = () -> list.stream();
        System.out.println("武力值>=90:"+
                streamSupplier.get().filter(h -> h.getNengli() >= 90).collect(Collectors.toList()));
        System.out.println("武力值80~89:"+
                streamSupplier.get().filter(h -> h.getNengli() >= 80 && h.getNengli() < 90).collect(Collectors.toList()));
        System.out.println("武力值70~79:"+
                streamSupplier.get().filter(h -> h.getNengli() >= 70 && h.getNengli() < 80).collect(Collectors.toList()));
        System.out.println("武力值<70:"+
                streamSupplier.get().filter(h -> h.getNengli() < 70).collect(Collectors.toList()));

        //按出生地分组后，统计各组人数
        Map<String, List<heroes>> collect1 = streamSupplier.get().collect(Collectors.groupingBy(h -> h.getCity()));
        Map<String, Integer> map = new HashMap<>();
        for (Map.Entry<String, List<heroes>> listEntry : collect1.entrySet()) {
            map.put(listEntry.getKey(),listEntry.getValue().size());
        }
        System.out.println(map);
    }

}

