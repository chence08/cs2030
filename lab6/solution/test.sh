echo "\n=====LEVEL 1=====\n"
jshell ImmutableMap.java Keyable.java KeyableMap.java Assessment.java Module.java Student.java < l1.jsh
echo "\n\n=====LEVEL 2=====\n"
jshell ImmutableMap.java Keyable.java KeyableMap.java Assessment.java Module.java Student.java < l2.jsh
echo "\n\n=====LEVEL 3=====\n"
jshell ImmutableMap.java Keyable.java KeyableMap.java Assessment.java Module.java Student.java < l3.jsh
echo "\n\n=====LEVEL 3 ROSTER=====\n"
jshell ImmutableMap.java Keyable.java KeyableMap.java Assessment.java Module.java Student.java Roster.java < l3-roster.jsh
echo "\n\n=====LEVEL 4=====\n"
jshell ImmutableMap.java Keyable.java KeyableMap.java Assessment.java Module.java Student.java Roster.java < l4.jsh
echo "\n\n=====LEVEL 5=====\n"
java Main < l5.in
