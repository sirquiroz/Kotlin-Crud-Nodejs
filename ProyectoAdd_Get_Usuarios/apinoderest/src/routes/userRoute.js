const express = require("express");

const userController = require("../controllers/userController")
const router = express.Router();


//* se definen los endpoint del APIRest
router.get("/", userController.list);
router.post("/create", userController.add);


module.exports = router;