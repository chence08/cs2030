javac cs2030/simulator/*.java
javac cs2030/util/*.java
javac Main4.java
echo "=====Test 4_1====="
java Main4 < tests/L4/4_1.in | diff tests/L4/4_1.out -
echo "\n=====Test 4_2====="
java Main4 < tests/L4/4_2.in | diff tests/L4/4_2.out -
echo "\n=====Test 4_3====="
java Main4 < tests/L4/4_3.in | diff tests/L4/4_3.out -