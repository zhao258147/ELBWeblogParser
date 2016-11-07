# ELBWeblogParser

MapReduce jobs to parse ELB logs.


MapReduce job1: 

Read from log files, map each line based on visitor IP. Sort mapped lines with timestamp
Reducer groups visitor actions read from the logs into sessions. 
If visitor has no action for more than 30 minutes and comes back after, a new session is started.
Session summary is written in a temp folder.


MapReduce job2:

Read from session summary folder, map all lines to the same key.
The lone reducer receives all summary items. It goes through all these items, calculate average numbers and find the top 5 visitors who have the longest session lengths. 


To run the program:

use parameters: [input folder location] [output folder location] [temp folder location]

Output can be found in the output folder.

