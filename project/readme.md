**Workspace structure.**

```
. <--- do all your testing here!
├── Main2.java
├── Main3.java
├── Main4.java
├── Main5.java
├── Main6.java
├── Main7.java
├── Main8.java
├── cs2030
│   ├── simulator
│   │   └── Customer.java
│   └── util
│       ├── ImList.java
│       ├── Lazy.java
│       └── Pair.java
├── readme.md
└── tests
    ├── L0
    │   ├── l0.jsh
    │   └── test0.sh
    ├── L1
    │   ├── l1.jsh
    │   └── test1.sh
    ├── L2
    │   ├── Main2.in
    │   ├── l2.jsh
    │   └── test2.sh
    ├── L3
    │   ├── 3_1.out
    │   ├── 3_2.in
    │   ├── 3_2.out
    │   ├── 3_3.in
    │   ├── 3_3.out
    │   └── test3.sh
    ├── L4
    │   ├── 4_1.in
    │   ├── 4_2.in
    │   ├── 4_3.in
    │   └── test4.sh
    ├── L5
    │   ├── 5_1.in
    │   ├── 5_1.out
    │   ├── 5_2.in
    │   ├── 5_2.out
    │   ├── 5_3.in
    │   ├── 5_3.out
    │   ├── 5_4.in
    │   ├── 5_4.out
    │   ├── example.jsh
    │   └── test5.sh
    ├── L6
    │   ├── 6_1.in
    │   ├── 6_1.out
    │   ├── 6_2.in
    │   ├── 6_2.out
    │   ├── 6_3.in
    │   ├── 6_3.out
    │   └── test6.sh
    ├── L7
    │   ├── 7_1.in
    │   ├── 7_1.out
    │   ├── 7_2.in
    │   ├── 7_2.out
    │   └── test7.sh
    └── L8
        ├── 8_1.in
        ├── 8_1.out
        ├── 8_2.in
        ├── 8_2.out
        ├── 8_3.in
        ├── 8_3.out
        ├── 8_4.in
        ├── 8_4.out
        └── test8.sh

13 directories, 60 files
```

# How to test?

If you want to use this test suite, please replace the cs2030 package (folder) with your own.

**The test script is not written for Level 5-8, please write your own tests with the .in and .out files provided!**

1. Make sure you are at the right directory.

	```
	$ pwd
	/cs2030/project
	```

2. test Level 0

	```
	$ tests/L0/test0.sh
	```

	If you see permission denied, simply run

	```
	$ chmod +x tests/L0/test0.sh
	```

## Things you can tweak

For level 3, if your code is correct, you should see:

```
$ tests/L3/test3.sh
=====Test 3_1=====

=====Test 3_2=====

=====Test 3_3=====
```

this is because I am comparing differences between the printed output with an .out file, this is the command for one such testcase:

```bash
java Main3 < tests/L2/2_1.in | diff tests/L3/3_1.out -
```

------

Whereas, for level 4, you would see:

```
$ tests/L4/test4.sh
=====Test 4_1=====
0.500 1 arrives
0.500 1 serves by 1
0.600 2 arrives
0.600 2 waits at 1
0.700 3 arrives
0.700 3 leaves
1.500 1 done serving by 1
1.500 2 serves by 1
2.500 2 done serving by 1
[0.450 2 1]

=====Test 4_2=====
0.500 1 arrives
0.500 1 serves by 1
0.600 2 arrives
0.600 2 waits at 1
0.700 3 arrives
0.700 3 leaves
1.500 1 done serving by 1
1.500 2 serves by 1
1.500 4 arrives
1.500 4 waits at 1
1.600 5 arrives
1.600 5 leaves
1.700 6 arrives
1.700 6 leaves
2.500 2 done serving by 1
2.500 4 serves by 1
3.500 4 done serving by 1
[0.633 3 3]

=====Test 4_3=====
0.500 1 arrives
0.500 1 serves by 1
0.600 2 arrives
0.600 2 serves by 2
0.700 3 arrives
0.700 3 waits at 1
1.500 1 done serving by 1
1.500 3 serves by 1
1.500 4 arrives
1.500 4 waits at 1
1.600 2 done serving by 2
1.600 5 arrives
1.600 5 serves by 2
1.700 6 arrives
1.700 6 waits at 2
2.500 3 done serving by 1
2.500 4 serves by 1
2.600 5 done serving by 2
2.600 6 serves by 2
3.500 4 done serving by 1
3.600 6 done serving by 2
[0.450 6 0]
```

because my command does not pipe to `diff`

```
java Main4 < tests/L4/4_1.in
```

