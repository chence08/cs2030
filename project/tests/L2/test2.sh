javac cs2030/simulator/*.java
javac cs2030/util/*.java
jshell < tests/L2/l2.jsh
echo "\n\n=====Test 2_1====="
javac Main2.java
java Main2 < tests/L2/2_1.in