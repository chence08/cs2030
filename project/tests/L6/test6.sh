javac cs2030/simulator/*.java
javac cs2030/util/*.java
javac Main6.java
echo "\n=====Test 6_1====="
java Main6 < tests/L6/6_1.in | diff tests/L6/6_1.out -
echo "\n=====Test 6_2====="
java Main6 < tests/L6/6_2.in | diff tests/L6/6_2.out -
echo "\n=====Test 6_3====="
java Main6 < tests/L6/6_3.in | diff tests/L6/6_3.out -