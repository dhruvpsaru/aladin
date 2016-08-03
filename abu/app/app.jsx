var React = require('react');
var ReactDOM = require('react-dom');
var {Route, Router, IndexRoute, hashHistory} = require('react-router');

var LoginForm = require('LoginForm');

ReactDOM.render(
    <Router history={hashHistory}>
        <Route path='/' component={LoginForm}>
        </Route>
    </Router>, 
    document.getElementById("app")
);