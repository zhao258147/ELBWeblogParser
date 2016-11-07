# ELBWeblogParser

MapReduce jobs to parse ELB logs. Note that unique visitors are identified by their IP addresses meaning that if 2 visitors share the same IP address, they are considered the same visitor. If cookie/login/web token info can be sent over, visitor identification can be much more acurate.

MapReduce job1: 

Read from log files, map each line based on visitor IP. Sort mapped lines by timestamp using custom partitioner, grouping comparator and sort comparator inside hadoop.
Reducers then group mapped visitor actions read from the logs into sessions based on timestamp. 
If visitor has no action for more than 30 minutes and comes back after, a new session is started.
Reducers then write the session summaries in a temp folder for further processing.


MapReduce job2:

Read from session summary folder, map all lines to the same key.
The lone reducer receives all summary items. It goes through all these items, calculate average session length, average unique url visits and find the top 5 visitors who have the longest session lengths. 


To run the program:
use parameters: [input folder location] [output folder location] [temp folder location]

Output can be found in the output folder.

Example results with 30 minutes session interval:
Top Visitors    180.179.213.71 120.29.232.107 54.251.151.39 121.58.175.128 220.226.206.7 
Average Session Time    605
Average Unique URL Visits   6.139823645852078

