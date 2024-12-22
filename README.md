Class Scheduling

Compile the files with 'compile.bat'

See 'run.bat' to run the program, run 'run-t1.bat' or 'run-t2.bat' to batch run every single test for t1 or t2 respectively

Running in a terminal use the following parameters: java -cp build ClassScheduling.Main <popSize> <maxGenerations> <crossoverRate> <mutationRate> <crossoverType>

After running all the files, each one will be created separately, to make it easier to perform tests on this data run the 'run-combine.bat' to combine all the csv into one file for each trial run.

--- Expected Values ---<br>
popSize: 	int [0, inf]<br>
maxGenerations: int [0, inf]<br>
crossoverRate:	double [0, 1.0]<br>
mutationRate:	double [0, 1.0]<br>
CrossoverType: 	UNIFORM, TWO_POINT<br>
Seed:           <0 or VALUE<br>
Directory:	relative directory<br>

Notes:
mutation Rate should stay lower than 20% or it will probably never converge.
A Seed of less than zero is a flag to be system current time.
Directory example: t1	t2
