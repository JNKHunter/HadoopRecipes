To run the examples, you will need Hadoop running in pseudo distributed mode on your local machine, with fs.defaultFS
running on port 9000
```
<property>
        <name>fs.defaultFS</name>
        <value>hdfs://localhost:9000</value>
</property>
```

You will also need a file called "quangle.txt" copied into the root of the hadoop file system.
```
echo "quangle" >> ~/quangle2.txt
hadoop fs -put ~/quangle2.txt /quangle2.txt
```