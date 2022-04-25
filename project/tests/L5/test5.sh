javac cs2030/simulator/*.java
javac cs2030/util/*.java
javac Main5.java
echo "\n=====Test 5_1====="
java Main5 < tests/L5/5_1.in | diff tests/L5/5_1.out -
echo "\n=====Test 5_2====="
java Main5 < tests/L5/5_2.in | diff tests/L5/5_2.out -
echo "\n=====Test 5_3====="
java Main5 < tests/L5/5_3.in | diff tests/L5/5_3.out -
echo "\n=====Test 5_4====="
java Main5 < tests/L5/5_4.in | diff tests/L5/5_4.out -