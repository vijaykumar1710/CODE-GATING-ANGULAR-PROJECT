# Working of services

* Each service follows a common set of steps to produce a quality parameter
1) Every service has a *run()* method which expects *sourceCodePath* of project as input parameter 
2) The *generateCommand()* of every service is invoked to get the command line command to run the tool
3) The command is then run inside a process which is managed by **ProcessUtility**.
4) The reports generated by tools are then parsed to extract the quality parameter
5) The threshold for the particular parameter is the fetched by **Threshold Configuration Service** and is compared with the result to 
make a decision on "Go" or "No go".

* Separate Endpoints are available to invoke each service.
* All services can also be invoked at once by passing the source code project path to *gating/allservices* endpoint. Which runs all services
and stores the results in a csv.The threshold used here can be either set by user, in which case it would be fetched from *thresholdconfig.properties*
file or the **results of previous run** can also be used as thresholds.

## ThresholdConfigService
* Service to allow users to configure thresholds for the final gating result

## Process Utility
* Service forks a process using process builder, runs a command inside that process, and return the exitstatus of that process 

### Note
* Simian has 2 thresholds - 1 of the thresholds present in **thresholdconfig.properties** which is used to compare how many duplicate lines
of code can be toleratedd. The 2nd threshold present in **simianconfig.properties** which is used by simian tool to determine how 
many consecutive lines of code should be considered as threshold. This can be set using *simianConfigService*.
