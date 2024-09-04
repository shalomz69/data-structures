package com.datastructures.service.data;

public class TestPerson implements Comparable<TestPerson> {
        public String name;
        public int age;

    public TestPerson(String name, int age) {
            this.name = name; this.age = age;
        }

        @Override
        public int compareTo(TestPerson o) {
            return Integer.compare(this.age, o.age);
        }

        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }

