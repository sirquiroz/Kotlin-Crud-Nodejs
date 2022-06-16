const express = require('express');


const employeesController = require('../controllers/employeesController');
const loginController = require('../controllers/loginController');

//import verify from '../utils/verify';
const verify = require('../utils/verify');
const router = express.Router();


//Rutas de empleados
router.get('/', verify, employeesController.list);
router.get('/get/:id', employeesController.get);
router.post('/add', employeesController.save);
router.put('/update/:id', employeesController.update);
router.delete('/delete/:id', employeesController.delete);


//Rutas de Login
//router.post('/user', loginController.postUser);
//router.get('/users', verify, loginController.getUser);
router.post('/login', loginController.login);


//router.get('/login', employeesController.verificalogin);
//router.post('/update/:id', employeesController.update);
//router.get('/delete/:id', employeesController.delete);

module.exports = router;


