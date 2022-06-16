const model = require('../utils/db');
//import jwt from 'jsonwebtoken';
const jwt = require("jsonwebtoken");
const controllerLogin = {};
controllerLogin.login = (req, res) => {

    if (req.body.user == "admin" && req.body.pwd == "123") {
        const user = {
            name: req.body.user,
            password: req.body.pwd,
            id: 1,
            nombre: "Henry",
            email: "henry@email.com"
        };
        jwt.sign({ user }, 'mysecretkey', { expiresIn: '3002s' }, (err, token) => {
            res.json({
                token,
                message: 'you  successfully logged in'
            });
        });
    } else {
        res.status(400).send('no user by that name');
    }
};



module.exports = controllerLogin;