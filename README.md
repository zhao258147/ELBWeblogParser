# ELBWeblogParser

MapReduce jobs to parse ELB logs.

MapReduce job1: 

Read from log files, map each line based on visitor IP. Sort mapped lines with timestamp
Reducer groups visitor actions read from the logs into sessions. 
If visitor has no action for more than 15 minutes then comes back, a new session is created.
Content requests(GET requests for web resource or POST requests) renew the previous session. 
Meaning that if a user was away for more than 15 minutes, but subsequently sends in a css GET request, the previous session is extended to include the css request.
