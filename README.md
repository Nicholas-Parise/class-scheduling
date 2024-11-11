Class Scheduling

Compile the files with 'compile.bat'

Run the program with 'run.bat' for default settings 

Running in a terminal use the following parameters: java -cp build ClassScheduling.Main <popSize> <maxGenerations> <crossoverRate> <mutationRate> <crossoverType>

--- Expected Values ---
popSize: 	int [0, inf]
maxGenerations: int [0, inf]
crossoverRate:	double [0, 1.0]
mutationRate:	double [0, 1.0]
CrossoverType: 	UNIFORM, TWO_POINT

Notes:
mutation Rate should stay lower than 20% or it will probably never converge.
