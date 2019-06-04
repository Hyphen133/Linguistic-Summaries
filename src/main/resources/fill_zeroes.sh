#!/bin/bash

for i in $( seq 1 2); do
  sed -e "s/^,/$2,/" -e "s/,,/,$2,/g" -e "s/,$/,$2/" -i all_matches.csv
done

sed -i 's/"Taiwan, Province of China"/Taiwan/g' all_matches.csv
sed -i 's/"gerald,-zhu-shen-yao"/gerald-zhu-shen-yao/g' all_matches.csv
sed -i 's/"daniel,-ford-jebsen"/daniel-ford-jebsen/g' all_matches.csv

