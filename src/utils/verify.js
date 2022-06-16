const verify = (req, res, next) => {
    const bearerHeader = req.headers['authorization'];
    //check if bearer is undifined 
    if (typeof bearerHeader !== 'undefined') {
        //spit at the space
        //what the split does it turn a string into an array
        //const bearer = bearerHeader.split(' ');
        //get token from array
        //const bearerToken = bearer[1];

        const bearerToken = bearerHeader.split(" ")[1];
        req.token = bearerToken;
        //set token
        req.token = bearerToken;
        next();
    } else {
        //forbidden
        res.json({
            message: 'forbiaaadden'
        });
    }

    //res.sendStatus(403);
}
module.exports = verify;
//export default verify;

/*

// Authorization: Bearer <token>
function verifyToken(req, res, next){
     const bearerHeader =  req.headers['authorization'];

     if(typeof bearerHeader !== 'undefined'){
          
          next();
     }else{
         res.sendStatus(403);
     }
}

*/