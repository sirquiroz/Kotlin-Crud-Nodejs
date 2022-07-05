//import express, { json } from "express";
/*const express = require('express'),
    path = require('path'),
    myConnection = require('express-myconnection'),

    mysql = require('mysql'),
    morgan = require('morgan');
    */

//import express from 'express';
const express = require("express");
const app = express();

// Settings
app.set('port', process.env.PORT || 3000);
//app.set('views', path.join(__dirname, 'views'));
//app.set('view engine', 'ejs');

// Middlewares
app.use(express.json());
/*app.use(morgan('dev'));
app.use(myConnection(mysql, {
    host: 'localhost',
    user: 'root',
    password: '',
    port: 3306,
    database: 'dbcompany'
}, 'single'));*/
app.use(express.urlencoded({ extended: false }));

//importing routes
const employeesRoutes = app.use(require('./routes/employees'));

// Starting the server
//app.listen(3000, () => {
app.listen(app.get('port'), () => {
    console.log(`Server on port http://localhost:${app.get('port')}`);
});

// static files
//app.use(express.static(path.join(__dirname, 'public')));