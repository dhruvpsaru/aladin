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

app.get('/add', function(req, res) {
    try {
        logger.info("Recieved params: " + req.query.p1 + " and " + req.query.p2);
        var sum = parseInt(req.query.p1, 10) + parseInt(req.query.p2, 10);
        
        res.setHeader('Content-Type', 'application/json');
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.send(JSON.stringify({sum: sum}));
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
