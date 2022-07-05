const express = require("express");
const config = require("./config.json");
const app = express();

app.set('port', process.env.PORT || config.PORT)

app.use(express.json());

app.use(express.urlencoded({ extended: false }));

const userRoutes = app.use(require("./routes/userroute"))

app.listen(app.get('port'), () => {
    console.log(`Servidor en puerto http://localhost:${app.get('port')}`);
});
//* kill process de node
//lsof -i tcp:3001
//sudo pkill node
//sudo kill -9 PID
//OcultarArchivos MAC
//chflags hidden ~/Desktop/* 
//chflags nohidden ~/Desktop/ *