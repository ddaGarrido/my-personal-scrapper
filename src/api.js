const serverless = require('serverless-http');
const express = require('express');
const api = express();

//Routes
const index = require('./routes/index');
const web = require('./routes/web');

api.use(express.json());
api.use(express.urlencoded({ extended: true }));

api.use('/', index);
api.use('/web', web);

const port = process.env.PORT || 3000;

api.listen(port, () => {
    console.log(`Listening on :${port}!`);
});

module.exports.handler = serverless(api);