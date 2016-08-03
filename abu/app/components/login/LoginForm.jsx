var React = require('react');
var LoginForm = React.createClass({
    getDefaultProps: function(){
        return {
            divStyle: {
                background: "#fff",
                padding: "15px",
                borderRadius: "2pt",
                marginTop: "25vh"
            }
        };
    },
    onloginButtonClick: function(evt){
        evt.preventDefault();
        var usernameTxt = this.refs.username;
        var passwordTxt = this.refs.password;
        var usernameValue = usernameTxt.value.trim();
        var passwordValue = passwordTxt.value.trim();
        if(usernameValue == '' || passwordValue == ''){
            alert("Please enter a valid username/password!!");
            return false;
        }
            
        alert("send to server username="+usernameValue+"     password="+passwordValue);
    },
    render: function(){
        return (
            <div className="row">
                <div className="col-md-4 col-md-offset-4" style={this.props.divStyle}>
                    <form onSubmit={this.onloginButtonClick}>
                    <input ref="username" type="text" className="form form-control space-out" placeholder="Username" />
                    <input ref="password" type="text" className="form form-control space-out" placeholder="Password" />
                    <center><button className="btn btn-primary space-out">Login</button></center>
                    </form>
                    
                </div>
            </div>
        );
    }
});

module.exports = LoginForm;