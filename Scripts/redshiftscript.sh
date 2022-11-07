#!/bin/bash

temp=$2

if [ -z "$2" ] 
then
	temp="4500"
fi

on="redshift -m randr -O $temp"
off="redshift -m randr -x"

if [ $1 == "on" ] 
then 
	if [ -z "$2" ] 
	then
		echo "Temperature not set. Defaulting to 4500."
		temp="4500"
	fi
	echo "Temperature set to $temp."
	$on
elif [ $1 == "off" ]
then
	echo "Temperature set to default (6500)."
	$off
else 
	echo "Unrecognized argument."
fi





