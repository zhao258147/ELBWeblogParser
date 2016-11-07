# ELBWeblogParser

MapReduce jobs to parse ELB logs.


MapReduce job1: 

Read from log files, map each line based on visitor IP. Sort mapped lines with timestamp using custom partitioner, grouping comparator and sort comparator inside hadoop.
Reducers then group mapped visitor actions read from the logs into sessions based on timestamp. 
If visitor has no action for more than 30 minutes and comes back after, a new session is started.
Reducers then write the session summarries in a temp folder for further processing.


MapReduce job2:

Read from session summary folder, map all lines to the same key.
The lone reducer receives all summary items. It goes through all these items, calculate average session length, average unique url visits and find the top 5 visitors who have the longest session lengths. 


To run the program:
use parameters: [input folder location] [output folder location] [temp folder location]

Output can be found in the output folder.

