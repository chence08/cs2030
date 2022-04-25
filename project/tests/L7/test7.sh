javac cs2030/simulator/*.java
javac cs2030/util/*.java
javac Main7.java
echo "\n=====Test 7_1====="
java Main7 < tests/L7/7_1.in | diff tests/L7/7_1.out -
echo "\n=====Test 7_2====="
java Main7 < tests/L7/7_2.in | diff tests/L7/7_2.out -