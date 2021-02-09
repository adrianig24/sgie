#!/bin/bash

	URL_TOKEN=$1"/api/token/login"
	URL_ADM=$1"/api/admin/device/echo"
	URL_DGC=$1"/api/diagnostic/echo"
	URL_APP=$1"/api/app/echo"


	echo "Admin User, Token:"

	if [ $2 = "adm" ];
	then
		restoken=$(curl -s --location --request POST ${URL_TOKEN} \
			--header 'Content-Type: application/json' \
			--data-raw '{
				"username": "adm@server.com",
				"password": "password"
			}')
	fi

        if [ $2 = "dgc" ];
	then
		restoken=$(curl -s --location --request POST ${URL_TOKEN} \
        	        --header 'Content-Type: application/json' \
                	--data-raw '{
                        	"username": "trcimsczet@server.com",
	                        "password": "password"
        	        }')

	fi

        if [ $2 = "app" ];
        then
		restoken=$(curl -s --location --request POST ${URL_TOKEN} \
               		--header 'Content-Type: application/json' \
               		 --data-raw '{
                        	"username": "FCR-WGJ-XVR",
                        	"password": "password"
                }')
	fi


	TOKEN=$(echo $restoken | jq -c '.token' | tr --delete \")
	echo "     >>> " $TOKEN

	echo ""
	echo "Admin Controller:"
	resadm=$(curl -s --location --request GET ${URL_ADM} --header "Authorization: Bearer "${TOKEN})
	echo "     >>> " $resadm

	echo ""
	echo "Diagnostic Controller:"
	resdgc=$(curl -s --location --request GET ${URL_DGC} --header "Authorization: Bearer "${TOKEN})
	echo "     >>> " $resdgc

	echo ""
	echo "App Controller:"
	resapp=$(curl -s --location --request GET ${URL_APP} --header "Authorization: Bearer "${TOKEN})
	echo "     >>> " $resapp

	echo ""
exit
