const mysqlConnection = require("../util/database");

const controller = {};

controller.list = (req, res) => {

    mysqlConnection.query("SELECT * FROM tblusers", (err, rows, fields) => {
        if (!err) {
            res.json({
                "code": 200,
                "error": false,
                "message": "Listado de Usuarios",
                "users": rows
            });

        } else {
            res.json({
                "code": 500,
                "error": true,
                "message": err
            });
        }

    });
};
controller.add = (req, res) => {
    const { name, lastname, email } = req.body;
    //!console.log(name, lastname, email);
    const srtquery = `INSERT INTO tblusers(name, lastname, email) VALUES(?,?,?)`;
    mysqlConnection.query(srtquery, [name, lastname, email], (err, rows, fields) => {
        if (err) {
            res.json({
                "code": 500,
                "error": true,
                "message": err
            });
        } else {
            res.json({
                "code": 200,
                "error": false,
                "message": "Usuario Agregado"
            })
        }
    });

};
module.exports = controller;