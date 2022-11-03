#!/bin/bash



if [ -z "$1" ] 
then
	echo "Error. Pass the file name  as argument."
	exit 1 
else
	if [ $1 == "-h" ] 
	then 
		echo "Usage: execute the script passing the file name as argument.
The script will compile the file namefile.asm into a namefile.o file and then link filename.o producing the executable file filename.
Commands used:
	nasm -f elf64 -o filename.o filename.asm 
	ld -o filename filename.o"
		exit 2
	else 
		name=$1
		if [ $name==*".asm"* ]
		then 
			name=${name//.asm/""}
		fi
		if [ $name==*".o"* ] 
		then 
			name=${name//.o/""} 
		fi
		
		cmp="nasm -f elf64 -o $name.o $name.asm"
		ld="ld -o $name $name.o"
		
		$cmp 
		$ld
	fi
fi

exit 0

