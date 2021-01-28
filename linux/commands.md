# Linux Commands

## present working directory
```
pwd
```

## list of files
```
ls
```

## list all files including hidden
```
ls -a
```

## open explorer from present working directory
```
start .
open .
```

## Rename file
### Solution 1
* Create a copy of the existing file with the new desired name and then delete the old file
* This creates a copy of the same file with a new name in the same location
```
$ cp oldfile newfile
```
* This will delete the old file keeping the newfile intact
```
rm oldfile
```
### Solution 2
* This just moves the old file, to a new name
```
$mv old-file-name  new-file-name
```

## Rename file to different location
* If you want to change not only the name of the file, but also it’s location, use the following command
```
$ mv RR2 newdir/.
```

## Rename Multiple files
* In order to rename multiple files at once we can make use of wildcard characters (for ex: ‘*’). In the below example we will rename all the files with .txt to .dat
```
$ mv *.txt *.dat
```
* For example: we have a file with a name RR#.txt, whereas the filename should contain only alphanumeric values instead of any special characters. This can be fixed with the following command
```
$ mv RR?.txt RR1.txt
```

## Rename directory
* Renaming a directory in Linux and Unix is similar to renaming a file. All we need to do is replace the file name with the directory name that is to be renamed
* For example, if we wanted to rename the directory “RR1” to “ST1”, then we can use the following command
```
$ mv RR1 ST1
```

## Verbose
* If you’re renaming a number of files, or doing other mass operations, you may want to track what is happening. Linux has an easy way of doing that with the -v or -verbose option
```
$ mv -v source.txt new_source.txt
```

## Mass move and rename
* Linux has another command, mmv, which stands for mass, move and rename. This is extremely helpful for renaming multiple files at a go. Its utility is not just limited to renaming of files. It can be used for moving, linking and appending multiple files as well. The reason why I like it most because it is the safest way to do these tasks. mmv does it all without any sudden destruction of files due to collisions of target names with existing file names. Moreover, before doing anything, mmv tries to identify any errors that would result from the entire set of actions specified and equips the user with the option of either terminating before beginning, or proceeding by avoiding the offending parts
```
$ mmv [options]
```
* This should give you a good insight into how to rename files in Linux. If you want to try out more options, just use the ‘man’ command, and Linux will list out all the options, along with how to use them
```
$ man move
```

## Download and install jdk 8
* download jdk rpm
```
wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u141-b15/336fa29ff2bb4ef291e347e091f7f4a7/jdk-8u141-linux-x64.rpm
```
* Install JDK 8
```
sudo yum install -y jdk-8u141-linux-x64.rpm
```

## Run java jar file as demon in linux
```
java -jar [jar-file-name].jar
```
```
nohup java -jar zuul-eureka/accounts-service.jar &
```