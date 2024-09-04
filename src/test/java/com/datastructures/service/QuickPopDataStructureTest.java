package com.datastructures.service;

import com.datastructures.service.data.TestPerson;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QuickPopDataStructureTest {

    @Test
    public void testPushThenPop() {
        QuickPopDataStructure<Integer> quickPop = new QuickPopDataStructure<>();
        int[] input = {3, 6, 7, 2, 4};
        for (int val : input) quickPop.push(val);

        int[] expected = {7, 6, 4, 3, 2};
        for (int i = 0; i < expected.length; i++) {
            int popped = quickPop.pop();
            assert popped == expected[i] : "QuickPop failed at index " + i;
        }
        assert quickPop.pop() == null : "QuickPop should return null when empty";
    }

    @Test
    public void testPushPopCombined(){
        final QuickPopDataStructure<Integer> quickPush = new QuickPopDataStructure<>();
        quickPush.push(11);
        quickPush.push(10);

        assert quickPush.pop() == 11;
        quickPush.push(11);
        quickPush.push(7);

        assert quickPush.pop() == 11;
        assert quickPush.pop() == 10;
        assert quickPush.pop() == 7;
        assert quickPush.pop()==null;

    }

    @Test
    public void testPushThenPopWithCustomObjects() {
        QuickPopDataStructure<TestPerson> quickPop = new QuickPopDataStructure<>();
        quickPop.push(new TestPerson("Alice", 30));
        quickPop.push(null);
        quickPop.push(new TestPerson("Bob", 25));
        quickPop.push(new TestPerson("Charlie", 35));
        quickPop.push(new TestPerson("Diana", 28));

        TestPerson p = quickPop.pop();
        assert p.name.equals("Charlie") : "Expected Charlie, got " + p.name;
        p = quickPop.pop();
        assert p.name.equals("Alice") : "Expected Alice, got " + p.name;
    }

    @Test
    public void testMultithreading() throws InterruptedException {
        QuickPopDataStructure<Integer> threadSafe = new QuickPopDataStructure<>();

        Runnable pushTask = () -> {
            for (int i = 0; i < 100; i++) {
                threadSafe.push(i);
            }
        };

        Thread t1 = new Thread(pushTask);
        Thread t2 = new Thread(pushTask);
        t1.start(); t2.start();
        t1.join(); t2.join();

        // Pop all elements and ensure no crash
        for (int i = 0; i < 200; i++) {
            Integer val = threadSafe.pop();
            assert val != null : "Expected non-null value at index " + i;
        }

        assert threadSafe.pop() == null : "Should be empty now";
    }

}
