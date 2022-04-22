javac cs2030/simulator/*.java
javac cs2030/util/*.java
javac Main3.java
echo "=====Test 3_1====="
java Main3 < tests/L2/2_1.in | diff tests/L3/3_1.out -
echo "\n=====Test 3_2====="
java Main3 < tests/L3/3_2.in | diff tests/L3/3_2.out -
echo "\n=====Test 3_3====="
java Main3 < tests/L3/3_3.in | diff tests/L3/3_3.out -