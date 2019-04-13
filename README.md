# Challenge

Write a class to check if an int value is contained in a list of type CustomNumberEntity by the fastest means possible.

Constraints:
- Your class MUST implement the provided NumberFinder interface.

- The list of CustomNumberEntity values should be read from a Json file, a short example is given below.

- The contains method of your implementation MUST use the provided FasterComparator.compare method to compare the int value with each CustomNumberEntity. How you do this in the fastest possible time is the key. FastestComparator.compare cannot be modified and no other comparison method should be used (hashing, indexes etc)

- Do not cast or convert provided parameter types, compare method from FastestComparator will handle this. e.g. do not cast from int to String, or CustomNumberEntity.number to int (even if is not used for comparison purpose)

- You MUST include Junit tests for running your code.
- Write your code using java 7 or 8

# Solution

As the input can contain values that can be mapped to a `String` but would fail to be  casted to a `Integer` in `FastestComparator:compare`, like "s" and "null", I had to create a utility method called `NumberUtil::isInt`, that would only compare the `CustomNumberEntity` containing values able to be parsed and ignore those invalid.

Then I proceeded to reading the file () content. I used Gson to parse the json lines to a list of `CustomNumberEntity` objects.  

My next step was to implement a sequential processing of the `CustomNumberEntity` list  and assess the time it took and how to improve it. This is called `SequentialNumberFinderImpl`

After verifying the time needed to process the example input file, I proceeded to make another implementation of the `NumberFinder` interface, called `StreamNumberFinderImpl`. In this one, I made use of the Java Stream API to compare each element. As expected, taking into account the small overhead added by the Stream, the result was not faster.

Lastly I created `ParallelStreamNumberFinderImpl`, to again, make use of Stream API, but this time with the help of multithreading. It will perform the processing in a custom `ForkJoinPool` thread pool. The parallelism is configured to two threads per core.

  
#####Result

To better compare the time each implementation would take, I temporarily set `FastestComparator:compare` variable `mSeconds` to 10000. After some benchmarking runs using NumberFinderTest, this was the result:

| SequentialNumberFinderImpl | StreamNumberFinderImpl | ParallelStreamNumberFinderImpl |
| :------------------------: | :--------------------: | :----------------------------: |
| ~ 60s | ~ 60s | ~ 10s |


Thanks to its parallelism, the fastest method to achieve the objective is `ParallelStreamNumberFinderImpl`.

# How to test

This project is built with Maven. To execute the test include in the requirements document (find number 100), executing the following command will run the test case:

`mvn test`  

The three algorithms can be tested with `NumberFinderTest`.

 