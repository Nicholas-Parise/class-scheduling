echo starting
java -cp build ClassScheduling.Main 1000 500 1.0 0.0 UNIFORM -1 t2
java -cp build ClassScheduling.Main 1000 500 1.0 0.1 UNIFORM -1 t2
java -cp build ClassScheduling.Main 1000 500 0.9 0.0 UNIFORM -1 t2
java -cp build ClassScheduling.Main 1000 500 0.9 0.1 UNIFORM -1 t2
java -cp build ClassScheduling.Main 1000 500 1.0 0.2 UNIFORM -1 t2
echo 2Point
java -cp build ClassScheduling.Main 1000 500 1.0 0.0 TWO_POINT -1 t2
java -cp build ClassScheduling.Main 1000 500 1.0 0.1 TWO_POINT -1 t2
java -cp build ClassScheduling.Main 1000 500 0.9 0.0 TWO_POINT -1 t2
java -cp build ClassScheduling.Main 1000 500 0.9 0.1 TWO_POINT -1 t2
java -cp build ClassScheduling.Main 1000 500 1.0 0.2 TWO_POINT -1 t2
echo done