const proxy = require('http-proxy-middleware');
module.exports = function(app) {
  app.use(proxy('/v2', {
    target: 'http://localhost:8085/',
    changeOrigin: true,
    pathRewrite: {
      '^/api/old-path': '/api/new-path', // rewrite path
      '^/v2': '/', // remove base path
    },
  }));
};
