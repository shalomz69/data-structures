package com.datastructures.service;

import com.datastructures.service.data.TestPerson;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QuickPushDataStructureTest {

    @Test
    public void testPushThenPop() {
        final QuickPushDataStructure<Integer> quickPush = new QuickPushDataStructure<>();
        int[] input = {10, 3, 6, 7, 2, 4};
        for (int val : input) quickPush.push(val);

        int[] expected = {10, 7, 6, 4, 3, 2};
        for (int i = 0; i < expected.length; i++) {
            int popped = quickPush.pop();
            assert popped == expected[i] : "QuickPush failed at index " + i;
        }
        assert quickPush.pop() == null : "QuickPush should return null when empty";
    }

    @Test
    public void testPushPopCombined(){
        final QuickPushDataStructure<Integer> quickPush = new QuickPushDataStructure<>();
        quickPush.push(11);
        quickPush.push(10);

        assert quickPush.pop() == 11;
        quickPush.push(11);
        quickPush.push(7);

        assert quickPush.pop() == 11;
        assert quickPush.pop() ==10;
        assert quickPush.pop() == 7;
        assert quickPush.pop() ==null;

    }

    @Test
    public void testPushThenPopCustomObjects() {
        final QuickPushDataStructure<TestPerson> quickPush = new QuickPushDataStructure<>();
        quickPush.push(new TestPerson("Diana", 28));
        quickPush.push(null);
        quickPush.push(new TestPerson("Alice", 30));
        quickPush.push(new TestPerson("Bob", 25));
        quickPush.push(new TestPerson("Charlie", 35));


        TestPerson p = quickPush.pop();
        assert p.name.equals("Charlie") : "Expected Charlie, got " + p.name;
        p = quickPush.pop();
        assert p.name.equals("Alice") : "Expected Alice, got " + p.name;
    }

    @Test
    public void testMultithreading() throws InterruptedException {
        final QuickPushDataStructure<Integer> threadSafe = new QuickPushDataStructure<>();
        final Runnable pushTask = () -> {
            for (int i = 0; i < 100; i++) {
                threadSafe.push(i);
            }
        };

        final Thread t1 = new Thread(pushTask);
        final Thread t2 = new Thread(pushTask);
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




