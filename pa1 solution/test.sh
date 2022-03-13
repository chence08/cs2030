echo "\n=====LEVEL 1=====\n"
jshell Person.java < l1.jsh
echo "\n\n=====LEVEL 2=====\n"
jshell Test.java ART.java PCR.java < l2.jsh
echo "\n\n=====LEVEL 3=====\n"
jshell Person.java Test.java ART.java PCR.java ImList.java Protocol.java P1.java P2.java Discharged.java Case.java < l3.jsh
echo "\n\n=====LEVEL 4=====\n"
jshell Person.java Test.java ART.java PCR.java ImList.java Protocol.java P1.java P2.java Discharged.java Case.java < l4.jsh
echo "\n\n=====LEVEL 5=====\n"
jshell Person.java Test.java ART.java PCR.java ImList.java Protocol.java P1.java P2.java P3.java Discharged.java Case.java ContactCase.java < l5.jsh
