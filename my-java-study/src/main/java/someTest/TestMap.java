package someTest;

import org.junit.Test;

import java.util.*;

/**
 * Created by firstsword on 2019/2/13.
 */
public class TestMap {
    @Test
    public void testIdentityHashMap() {

        Dog dog = new Dog();
        dog.name = "dog";

        Map<Dog, String> hashMap = new HashMap<>();
        Map<Dog, String> identityHashMap = new IdentityHashMap<>();

        hashMap.put(dog, "hashMap");
        identityHashMap.put(dog, "identityHashMap");

        System.out.println(hashMap);
        System.out.println(identityHashMap);

        System.out.println("-------------------");

        dog.name = "dog2";

        hashMap.put(dog, "hashMap");
        identityHashMap.put(dog, "identityHashMap");

        System.out.println(hashMap);
        System.out.println(identityHashMap);
    }

    private class Dog {
        public String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Dog dog = (Dog) o;
            return Objects.equals(name, dog.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    @Test
    public void testMapAsKey() {
        Map<Map<String, ?>, Map<String, ?>> map = new HashMap<>();

        Map<String, String> keyA = new HashMap<>();
        keyA.put("key-name", "key");

        Map<String, String> valueA = new HashMap<>();
        valueA.put("A1", "A1");
        valueA.put("A2", "A2");

        Map<String, String> keyB = new HashMap<>();
        keyB.put("key-name", "key");

        Map<String, String> valueB = new HashMap<>();
        valueB.put("B1", "B1");
        valueB.put("B2", "B2");

        map.put(keyA, valueA);
        map.put(keyB, valueB);

        System.out.println(map.size());
        System.out.println(map);
    }

    @Test
    public void testSingleMap() {
        Map<String, String> map = Collections.singletonMap("key", "value1");
        map.put("key", "value2");
        System.out.println(map.size());
        System.out.println(map);
    }

    @Test
    public void testMapTemp() {
        Map<String, String> m = new HashMap<>();
        m.put("a", "a");
        m.put("b", "b");

        System.out.println(m);

        testMapChange(m);

        System.out.println(m);
    }

    public void testMapChange(Map<String, String> map) {
        map.put("a", "aa");
    }
}


