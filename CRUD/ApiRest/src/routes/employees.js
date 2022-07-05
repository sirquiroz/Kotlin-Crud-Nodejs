const express = require('express');


const employeesController = require('../controllers/employeesController');
const loginController = require('../controllers/loginController');

const verify = require('../utils/verify');
const router = express.Router();


//Rutas de empleados
//router.get('/', employeesController.list);
router.get('/', verify, employeesController.list);
router.get('/get_employee/:id', verify, employeesController.get);
router.post('/add_employee', verify, employeesController.save);
router.put('/update_employee/:id', verify, employeesController.update);
router.delete('/delete_employee/:emp_id', verify, employeesController.delete);


//Rutas de Login
router.post('/login', loginController.login);


module.exports = router;


