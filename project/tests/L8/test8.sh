javac cs2030/simulator/*.java
javac cs2030/util/*.java
javac Main8.java
echo "\n=====Test 8_1====="
java Main8 < tests/L8/8_1.in | diff tests/L8/8_1.out -
echo "\n=====Test 8_2====="
java Main8 < tests/L8/8_2.in | diff tests/L8/8_2.out -
echo "\n=====Test 8_3====="
java Main8 < tests/L8/8_3.in | diff tests/L8/8_3.out -
echo "\n=====Test 8_4====="
java Main8 < tests/L8/8_4.in | diff tests/L8/8_4.out -
