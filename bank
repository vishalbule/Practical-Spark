val bankRDD = sc.textFile("/home/vishal/Documents/Data/bank.csv")

val bankCM = bankRDD.map(x=>x.split(";"))

Filter records that does not have value for all fields
--------------------------------------------------------
val goodCM = bankCM.filter(x =>x.length == 17 )


Total number of customers who got credit and who could not get credit.
-----------------------------------------------------------------------
val creditCHK = goodCM.map(x => (x(16),1 )).reduceByKey(_+_)
creditCHK.countByKey.foreach(println)

Note: CountByKey is an action operation and returns a map whereas
      reduceByKey() is a transformation operation and returns a new rdd

Find how many people are married, employed and educated who got the loan 
and who could not get the loan.
-----------------------------------------------------------------------

val loanCHK = goodCM.filter(x=>x(1) != "unemployed" 
