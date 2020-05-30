# AVRO Notes

## Evolution of data formats
* CSV
* Relational Tables
* XML
* JSON
* AVRO

### CSV - Comma Separated Values
* Advantages
	* Easy to parse
	* Easy to read
* Dis Advantages
	* Data types not present
	* Parsing is tricky if data contains commas
	* Column names may or may not be there

### Relational Tables
* Example
```
create table emp(
	id number,
	name varchar(50)
);
```
* Advantages
	* Data fits in table
* Dis Advantages
	* Data has to be flat (row and columns). No nesting
	* Data is stored in database. So definition will be different for each database
	
### XML

### JSON - Javascript Object Notatio
* Examples
```
{
	"id" : 100,
	"name": "avia"
}
```
* Advantages
	* Data can take any form like arrays, nested elements
	* Widely accepted. Every language has parsers
	* Easily shared across network
* Dis Advantages
	* No schema enforcing
	* Can be really big in size because of repeated keys

### AVRO
* Avro is defined by schema. Shema is written in JSON
* We can view Avro as JSON with schema attached to it
```
{
	"type": "record",
	"name": "employee",
	"namespace": "my.employees",
	"fields": [
		{
			"name": "firstName",
			"type": "string",
			"default": "None"
		},
		{
			"name": "age",
			"type": "int",
			"default": -1
		},
		{
			"name": "address",
			"default": {},
			"type": {
				"type": "record",
				"name": "mail-address",
				"fields": [
					{
						"name": "street",
						"type": "string",
						"default": "None"
					},
					{
						"name": "city",
						"type": "string",
						"default": "None"
					}
				]
			}
		}
	]
}
```
* Advantages
	* Data is fully typed
	* Data is compressed automatically. So less CPU space usage
	* Schema comes along with data
	* Documentation is embedded in schema
	* Data can be read across any language
* Dis Advantages
	* Avro support for some languages may be lacking
	* Can't print the data without using Avro tools because it's compressed and serialized
	
### Avro vs Protobuf vs Thrift vs Parquet vs ORC etc
* Overall, all these data formats achieve pretty much same goal
* At kafka level we prefer Avro because we care about message being self explanatory and fully described as we are dealing with streaming
* Avro has good support from Hadoop based technologies like Hive
* Avro has choosen as the only supported data format from Confluent Schema Registry