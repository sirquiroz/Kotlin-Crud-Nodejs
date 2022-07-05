
const mysqlConnection = require('../utils/database');
const config = require('../config')
const controller = {};

//import jwt from 'jsonwebtoken';
const jwt = require("jsonwebtoken");

function veriticatoken(req) {
    jwt.verify(req.token, config.secret, (err, authData) => {
        if (err) {
            //res.sendStatus(403);
            return false;
        } else {
            return true;
        }
    })
}

// GET all Employees
controller.list = (req, res) => {

    if (veriticatoken(req)) {
        res.json({
            "code": 403,
            "error": true,
            "message": 'Acceso no permitido'
        });
    } else {
        mysqlConnection.query('SELECT * FROM employee', (err, rows, fields) => {
            if (!err) {
                res.json({
                    "status_code": 202,
                    "message": "Listado",
                    "employees": rows,
                    //authData
                });
                console.log(rows);
            } else {
                res.json({
                    "code": 500,
                    "error": true,
                    "message": err
                });
            }
        });
    }
};
// GET An Employee
controller.get = (req, res) => {
    const { id } = req.params;
    wt.verify(req.token, config.secret, (err, authData) => {
        if (err) {
            //res.sendStatus(403);
            res.json({
                "code": 403,
                "error": true,
                "message": 'Acceso no permitido'
            });
        } else {
            mysqlConnection.query('SELECT * FROM employee WHERE id = ?', [id], (err, rows, fields) => {
                if (!err) {
                    res.json(rows[0]);
                } else {
                    console.log(err);
                    res.json({
                        "code": 500,
                        "error": true,
                        "message": err
                    });
                }
            });
        }
    })
};
// INSERT An Employee
controller.save = (req, res) => {
    wt.verify(req.token, config.secret, (err, authData) => {
        if (err) {
            //res.sendStatus(403);
            res.json({
                "code": 403,
                "error": true,
                "message": 'Acceso no permitido'
            });
        } else {
            const { id, name, edad, email } = req.body;
            console.log(id, name, edad, email);
            const query = `
    SET @id = ?;
    SET @name = ?;
    SET @email = ?;
    SET @edad = ?;
    CALL employeeAddOrEdit(@id, @name, @email,@edad);
  `;
            mysqlConnection.query(query, [id, name, email, edad], (err, rows, fields) => {
                if (!err) {
                    res.json({
                        error: false,
                        message: 'Employeed Saved'
                    });
                } else {
                    res.json({
                        error: true,
                        message: err
                    });
                    console.log(err);
                }
            });
        }
    })
};
// UPDATE Employee
controller.update = (req, res) => {
    const { name, edad, email } = req.body;
    const { id } = req.params;
    const query = `SET @id = ?; SET @name = ?; SET @email = ?; SET @edad = ?; CALL employeeAddOrEdit(@id, @name, @email,@edad);`;
    //const query = `UPDATE employee SET name=?, email=?,edad=? WHERE id=?;`;
    mysqlConnection.query(query, [id, name, email, edad], (err, rows, fields) => {
        if (!err) {
            res.json({
                "code": 200,
                "error": false,
                "message": 'Employee Updated'
            });
        } else {
            console.log(err);
            res.json({
                "code": 500,
                "error": true,
                "message": err
            });
        }
    });
};
// DELETE An Employee
controller.delete = (req, res) => {
    const { emp_id } = req.params;
    mysqlConnection.query('DELETE FROM employee WHERE id = ?', [emp_id], (err, rows, fields) => {
        if (!err) {
            res.json({
                "code": 200,
                "error": false,
                "message": 'Employee Deleted'
            });
        } else {
            console.log(err);
            res.json({
                "code": 500,
                "error": true,
                "message": err
            });
        }
    });
}
module.exports = controller;