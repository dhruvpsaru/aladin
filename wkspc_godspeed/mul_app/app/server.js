'use strict';

const express = require('express');
const winston = require('winston');

const PORT = 8600;
const app = express();

const logger = new (winston.Logger) ({
 transports: [
     new (winston.transports.Console)(),
     new (winston.transports.File)({ filename: 'sum.log'})
 ]
});

app.get('/mul', function(req, res) {
    try {
        logger.info("Recieved params: " + req.query.p1 + " and " + req.query.p2);
        var result = parseInt(req.query.p1, 10) * parseInt(req.query.p2, 10);

	res.setHeader("Access-Control-Allow-Origin", "*");
    	res.setHeader('Content-Type', 'application/json');
        res.send(JSON.stringify({mul: result}));
    } catch (e) {
        res.send(e);
    }
 
});

app.get('/info', function(req, res) {
    res.setHeader("Access-Control-Allow-Origin", "*");
    res.setHeader('Content-Type', 'application/json');
    res.send(JSON.stringify({service_name: 'Multiply', methods : { name: '/mul', params: [{name: 'p1', type: 'int'}, {name: 'p2', type: 'int'}]}, tags: ['MUL', 'Multiply']}))
});

app.get('/', function(req, res) {
    res.setHeader("Access-Control-Allow-Origin", "*");
    res.setHeader('Content-Type', 'application/json');
    res.send("This is a microservice for multiplying two integers.")
})


app.listen(PORT);
logger.info('Running on http://localhost:' + PORT);
