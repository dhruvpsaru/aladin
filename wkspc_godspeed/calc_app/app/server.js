'use strict';

const express = require('express');
const winston = require('winston');
const request = require('request');

const PORT = 8600;
const app = express();
const service_url = 'http://172.16.1.61:8080';

const logger = new (winston.Logger) ({
 transports: [
     new (winston.transports.Console)(),
     new (winston.transports.File)({ filename: 'sum.log'})
 ]
});

app.get('/calc', function(req, res) {
    try {
        logger.info("Recieved params: " + req.query.p2 + " and " + req.query.p3 +  ". Operator is " + req.query.p1);

	var operator = req.query.p1;
        var params = [req.query.p2, req.query.p3];

        res.setHeader('Content-Type', 'application/json');
        res.setHeader("Access-Control-Allow-Origin", "*");

	if (operator == 'add') {
            searchAndCall('ADD', params, res);
	} else if (operator = 'min') {
            searchAndCall('MIN', params, res);
        } else if (operator = 'mul') {
            searchAndCall('MUL', params, res);
        } else {
            res.send("No matching operand found.");
        }	
    } catch (e) {
        res.send(e);
    }
 
});

app.get('/', function(req, res) {
    res.send("This service adds two integer.");
});

app.get('/info', function(req, res) {
     res.setHeader('Content-Type', 'application/json');
     res.setHeader("Access-Control-Allow-Origin", "*");
     res.send(JSON.stringify({service_name: 'Addition', methods : { name: '/add', params: [{name: 'p1', type: 'int'}, {name: 'p2', type: 'int'}]}, tags: ['ADD', 'Addition']}));
});

app.listen(PORT);
logger.info('Running on http://localhost:' + PORT);

var searchAndCall = function(key, args, r) {
    request(service_url + "/tags/" + key, function(error, data){
        if (!error) {
            var queryString = '';
            for (var i = 1; i <= args.length; i++) {
                queryString += "p" + i + "=" + args[i-1];
		if (i != args.length) {
			queryString += "&"
		}
            }
    
            console.log(data.body + "?" + queryString);

            request(data.body + "?" + queryString, function(error, data){
                if (!error) {
                    r.send(data.body);
                } else {
                    r.send(error);
                }
            }) 
        }
    }) ;
}

