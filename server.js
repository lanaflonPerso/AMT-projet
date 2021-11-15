const express = require('express');
const app = express();
const port = 3000;
//var  bodyParser = require('body-parser');
var bodyParser = require('body-parser')
class User{
    constructor(username, password){
        this.username = username;
        this.password = password;
    }
}
var users = [];
/*
https://stackoverflow.com/questions/10005939/how-do-i-consume-the-json-post-data-in-an-express-application
 */

//app.use(bodyParser.json());
/*
https://expressjs.com/fr/starter/hello-world.html
 */
//app.use(express.json());
var jsonParser = bodyParser.json()
app.get('/', (req, res) => {
    res.send('Hello World!')
})

// With middleware
/*app.use('/accounts/register', function(req, res, next){
    users.push(req.body)
    console.log(users)
    res.json({
        "id": 0,
        "username": "test",
        "role": "admin"
    })
    next();
})*/
app.post('/accounts/register', jsonParser, function (
    req, res) {
    console.log(req.body)
    users.push(req.body)
    console.log(users[0])
    res.json({
        "id": 0,
        "username": "test",
        "role": "admin"
    })

    //console.log(req.body)
    //res.send("hello")
    //res.json({title: "GeeksforGeeks"})
})

app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`)
})
