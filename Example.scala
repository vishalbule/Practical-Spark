###  1) Word count in file.
--------------------------------------------------------------
	val file = sc.textFile("hdfs:///word_count.txt")
	val words = file.flatMap(w=>w.split("""[\s,.;:!?]+"""))
	val words = file.flatMap(w=>w.split(" "))
	
	val words_count =words.map(word=>(word,1)).reduceByKey(_+_)
--------------------------------------------------------------
2)  Most frequently use word in the file. 
	word_count.sortBy(_._2, false).collect.foreach(println)
	word_count.sortBy(_._2, false).first
	
3) Max length of word in the file.
--------------------------------------------------------------
	val words_length =words.map(word=>(word,word.length))
	words_length.sortBy(_._2, false).first
	
	
4)  Find Max Value In Spark Rdd Using Scala
--------------------------------------------------------------
	http://bigdataprogrammers.com/find-max-value-in-spark-rdd-using-scala/
	val emp_data = sc.textFile("hdfs:///emp_data.txt")
	
	val emp_header=emp_data.first()
	println(emp_header)

	val emp_data_without_header = emp_data.filter(line => line!=emp_header)
	println ("No. of Partition=" +emp_data_without_header.partitions.size)
	
	// Max Salary 	
	val emp_sal_list=emp_data_without_header.map(x =>x.split(",")).map(x=>(x(5).toDouble))
	println("Highest Salary:"+emp_sal_list.max())
	
	Here, at first, I have taken only distinct value from the salary. After that sorted
	the records in descending order and taken the 1st record which is the max value.
	As we were having 2 partitions, if we don’t provide partition 1, then it will sort
	in each partition.
	
	val max_sal = emp_sal_list.distinct.sortBy(x=>x.toDouble,true,1)
			or 
	val max_sal = emp_sal_list.distinct.sortBy(x=>x,false)
	println("Highest Salary:"+max_sal.frist)
	
	// Second Max Salary
	
	val second_sal = max_sal.zipWithIndex().filter(index => index._2 == 1)
	println("Second Highest Salary=" +second_sal._1.first)
	
	
	// Max salary with employee name 
	val sal_with_empName = emp_dta_without_header.map(x =>x.split(",")).map(x=>(x(5).toDouble,x(1)))
	val max_Sal_Emp = sal_with_empName.groupByKey.takeOrdered(1)(ordering[Double].reverse.on(x=>x._1))
	

5) Write the missing code in the given program to display the expected output to 
   identify animals that have names with four letters. Output: Array((4,lion))
--------------------------------------------------------------------------------------------

	val a = sc.parallelize(List("dog","tiger","lion","cat","spider","eagle"),2)
	val b=a.map(x=>(x,x.length))
	val b = a.keyBy(_.length)
	val c = b.filter(_._2==4)
	c.first
