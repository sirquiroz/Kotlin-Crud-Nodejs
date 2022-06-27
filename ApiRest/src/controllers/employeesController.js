
const mysqlConnection = require('../utils/database');
const config = require('../config')
const controller = {};

//import jwt from 'jsonwebtoken';
const jwt = require("jsonwebtoken");


// GET all Employees
controller.list = (req, res) => {

    /*
        mysqlConnection.query('SELECT * FROM employee', (err, rows, fields) => {
            if (!err) {
                res.json({
                    rows,
    
                });
            } else {
                console.log(err);
            }
        });
        */
    // console.log("AQUI");
    jwt.verify(req.token, config.secret, (err, authData) => {
        if (err) {
            //console.log("aqui" + req.token);
            //es.sendStatus(403);
            res.json({
                message: 'Acceso no permitido'
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
                    //console.log(rows);
                } else {
                    console.log(err);
                }
            });
        }
    })
    // res.status(200).send(model.userDetails);





    /*req.getConnection((err, conn) => {
        conn.query('SELECT * FROM customer', (err, customers) => {
            if (err) {
                res.json(err);
            }
            res.render('customers', {
                data: customers
            });
        });
    });*/
};
// GET An Employee
controller.get = (req, res) => {
    const { id } = req.params;
    mysqlConnection.query('SELECT * FROM employee WHERE id = ?', [id], (err, rows, fields) => {
        if (!err) {
            res.json(rows[0]);
        } else {
            console.log(err);
        }
    });
};

controller.edit = (req, res) => {
    const { id } = req.params;
    req.getConnection((err, conn) => {
        conn.query("SELECT * FROM customer WHERE id = ?", [id], (err, rows) => {
            res.render('customers_edit', {
                data: rows[0]
            })
        });
    });
};

// INSERT An Employee
controller.save = (req, res) => {
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
            res.json({ status: 'Employeed Saved' });
        } else {
            console.log(err);
        }
    });
    /*const data = req.body;
    console.log(req.body)
    req.getConnection((err, connection) => {
        const query = connection.query('INSERT INTO customer set ?', data, (err, customer) => {
            console.log(customer)
            res.redirect('/');
        })
    })*/
};

// UPDATE Employee
controller.update = (req, res) => {
    const { name, edad, email } = req.body;
    const { id } = req.params;
    const query = `SET @id = ?; SET @name = ?; SET @email = ?; SET @edad = ?; CALL employeeAddOrEdit(@id, @name, @email,@edad);`;
    mysqlConnection.query(query, [id, name, email, edad], (err, rows, fields) => {
        if (!err) {
            res.json({ status: 'Employee Updated' });
        } else {
            console.log(err);
        }
    });

    /*const { id } = req.params;
    const newCustomer = req.body;
    req.getConnection((err, conn) => {

        conn.query('UPDATE customer set ? where id = ?', [newCustomer, id], (err, rows) => {
            res.redirect('/');
        });
    });*/
};

// DELETE An Employee
controller.delete = (req, res) => {
    const { id } = req.params;
    mysqlConnection.query('DELETE FROM employee WHERE id = ?', [id], (err, rows, fields) => {
        if (!err) {
            res.json({ status: 'Employee Deleted' });
        } else {
            console.log(err);
        }
    });
    /*const { id } = req.params;
    req.getConnection((err, connection) => {
        connection.query('DELETE FROM customer WHERE id = ?', [id], (err, rows) => {
            res.redirect('/');
        });
    });*/
}









/*
controller.verificalogin = (req, res) => {
    const { user, pwd } = req.body;
    //user = req.body.user;
    //pwd = req.body.pwd;

    console.log(user + " " + pwd);
    if (user == "admin" && pwd == "123") {
        // res.body("true");
        //res.status(200).send();
    } else {
        console.log("Error de usuario");
        //res.json({ login: false });
        //res.status(403).send();
    }
}
*/


module.exports = controller;