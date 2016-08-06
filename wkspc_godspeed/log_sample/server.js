'use strict';

var sys = require('util'),
    spawn = require('child_process').spawn,

    // args from command line
    filename, servers;


if (process.argv.length < 4) {
 // return sys.puts("Usage: node remote-tail.js filename server1 [serverN]");
}

filename = process.argv[2];
//servers  = process.argv.slice(3);

function writeData(data) {
  console.log(data);
}

function readData(data) {
  var lines = data.toString().split("\n")
  for (var i = 0, len = lines.length; i < len; i++) {
    if (lines[i].length > 0) {
      writeData(lines[i])
    }
  }
}


//for (var x = 0, len = servers.length; x < len; x++) {
  //var server = servers[x];
 // console.log(server);
  console.log(filename);

  // Look at http://nodejs.org/api.html#_child_processes for detail.
  var tail = spawn("tail", ["-f", filename]);

  tail.stdout.on("data", function(data) {
    readData( data);
  });
//}  
