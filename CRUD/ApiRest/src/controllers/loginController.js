const model = require('../utils/db');
const config = require('../config');
//import jwt from 'jsonwebtoken';
const jwt = require("jsonwebtoken");
const controllerLogin = {};
controllerLogin.login = (req, res) => {
    if (req.body.email == "admin" && req.body.pwd == "123") {
        console.log("Usuario " + req.body.email + " Logoneado ");
        const user = {
            email: req.body.email,
            pwd: req.body.pwd,
            id: 1,
            name: "Miguel Quiroz",
        };
        //const token = jwt.sign(user, config.secret, { expiresIn: config.expiresIn })
        //const refreshToken = jwt.sign(user, config.refreshTokenSecret, { expiresIn: config.refreshTokenLife })
        /*res.json({
            "islogged": true,
            "token": token,
            user
        });
        const response = {
            "status": "Logged in",
            "token": token,
            "refreshToken": refreshToken,
        }*/
        //createdAt updatedAt
        jwt.sign({ user }, config.secret, { expiresIn: '3000s' }, (err, token, authData) => {
            res.json({
                token,
                //"user":user,
                user,
                "islogged": true,
                message: 'you  successfully logged in'
            });
        });
    } else {
        res.json({
            "islogged": false,
            "error": true,
            message: 'Usuario/Contraseña no valido'
        });
        //res.status(400).send('no user by that name');
    }
};
//? pregunta 
//! alerta
//* comentario destacado
// comentario simples de better comentario
//TODO: add authData
module.exports = controllerLogin;