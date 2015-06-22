#!/bin/bash

if ! [ -z $1 ]; then

        searchString=$1
        #echo $1
else
        searchString="MAH"
fi

linenumber=1
notanumber=1

append="\sand\sevent\skey"
searchString="$searchString$append"

#echo $searchString

while ( [ $notanumber -eq 1 ] &&  [ $linenumber -lt 10 ] )
do
        lastmatch=`sed -n "/${searchString}/p" /apps/assg/logs/assg.log | tac | sed -n "${linenumber} p"`;
        #echo $lastmatch

        #echo $notanumber

        eventid=`echo $lastmatch | awk '{print $NF}'`;

        #echo $eventid

        regex='^[0-9]+$';

        if ! [[ $eventid =~ $regex ]]; then
                notanumber=1
        else
                notanumber=0
        fi

        linenumber=`expr $linenumber + 1`

done

echo $eventid
